/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kafka.streams.interactive.query;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.HostInfo;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.mddarr.inventory.Product;
import org.mddarr.inventory.PurchaseCount;
import org.mddarr.inventory.PurchaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;

@SpringBootApplication
public class InventoryServiceInteractiveQueries {
	static final String PLAY_EVENTS = "play-events";
	static final String SONG_FEED = "song-feed";
	static final String TOP_FIVE_KEY = "all";
	static final String TOP_FIVE_SONGS_STORE = "top-five-songs";
	static final String ALL_SONGS = "all-songs";

	@Autowired
	private InteractiveQueryService interactiveQueryService;

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceInteractiveQueries.class, args);
	}

	public static class KStreamMusicSampleApplication {

		private static final Long MIN_CHARTABLE_DURATION = 30 * 1000L;
		private static final String SONG_PLAY_COUNT_STORE = "song-play-count";

		static final String TOP_FIVE_SONGS_BY_GENRE_STORE = "top-five-songs-by-genre";
		static final String TOP_FIVE_KEY = "all";
//
		@Bean
		public BiConsumer<KStream<String, PurchaseEvent>, KTable<Long, Product>> process() {

			return (s, t) -> {
				// create and configure the SpecificAvroSerdes required in this example
				final Map<String, String> serdeConfig = Collections.singletonMap(
						AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

				final SpecificAvroSerde<PurchaseEvent> playEventSerde = new SpecificAvroSerde<>();
				playEventSerde.configure(serdeConfig, false);

				final SpecificAvroSerde<Product> keySongSerde = new SpecificAvroSerde<>();
				keySongSerde.configure(serdeConfig, true);

				final SpecificAvroSerde<Product> valueSongSerde = new SpecificAvroSerde<>();
				valueSongSerde.configure(serdeConfig, false);

				final SpecificAvroSerde<PurchaseCount> songPlayCountSerde = new SpecificAvroSerde<>();
				songPlayCountSerde.configure(serdeConfig, false);

				// Accept play events that have a duration >= the minimum
				final KStream<Long, PurchaseEvent> playsBySongId =
						s.map((key, value) -> KeyValue.pair(value.getProductId(), value));
//								filter((region, event) -> event.getDuration() >= MIN_CHARTABLE_DURATION)
//								// repartition based on song id
//								.map((key, value) -> KeyValue.pair(value.getSongId(), value));

				// join the plays with song as we will use it later for charting
				final KStream<Long, Product> songPlays = playsBySongId.leftJoin(t,
						(value1, song) -> song,
						Joined.with(Serdes.Long(), playEventSerde, valueSongSerde));

				// create a state store to track song play counts
				final KTable<Product, Long> productPurchaseCounts = songPlays.groupBy((songId, song) -> song,
						Serialized.with(keySongSerde, valueSongSerde))
						.count(Materialized.<Product, Long, KeyValueStore<Bytes, byte[]>>as(SONG_PLAY_COUNT_STORE)
								.withKeySerde(valueSongSerde)
								.withValueSerde(Serdes.Long()));

				final TopFiveProducts topFiveSerde = new TopFiveProducts();

				// Compute the top five charts for each genre. The results of this computation will continuously update the state
				// store "top-five-songs-by-genre", and this state store can then be queried interactively via a REST API (cf.
				// MusicPlaysRestService) for the latest charts per genre.
//				productPurchaseCounts.groupBy((product, purchase_count) ->
//								KeyValue.pair(product.getBrand().toLowerCase(),
//										new PurchaseCount(product.getProductId(), purchase_count)),
//						Serialized.with(Serdes.String(), songPlayCountSerde))
//						// aggregate into a TopFiveSongs instance that will keep track
//						// of the current top five for each genre. The data will be available in the
//						// top-five-songs-genre store
//						.aggregate(TopFiveProducts::new,
//								(aggKey, value, aggregate) -> {
//									aggregate.add(value);
//									return aggregate;
//								},
//								(aggKey, value, aggregate) -> {
//									aggregate.remove(value);
//									return aggregate;
//								},
//								Materialized.<String, TopFiveSongs, KeyValueStore<Bytes, byte[]>>as(TOP_FIVE_SONGS_BY_GENRE_STORE)
//										.withKeySerde(Serdes.String())
//										.withValueSerde(topFiveSerde)
//						);
//
//				// Compute the top five chart. The results of this computation will continuously update the state
//				// store "top-five-songs", and this state store can then be queried interactively via a REST API (cf.
//				// MusicPlaysRestService) for the latest charts per genre.
//				productPurchaseCounts.groupBy((product, purchase_count) ->
//								KeyValue.pair(TOP_FIVE_KEY,
//										new PurchaseCount(product.getProductId(), purchase_count)),
//						Serialized.with(Serdes.String(), songPlayCountSerde))
//						.aggregate(TopFiveProducts::new,
//								(aggKey, value, aggregate) -> {
//									aggregate.add(value);
//									return aggregate;
//								},
//								(aggKey, value, aggregate) -> {
//									aggregate.remove(value);
//									return aggregate;
//								},
//								Materialized.<String, TopFiveSongs, KeyValueStore<Bytes, byte[]>>as(TOP_FIVE_SONGS_STORE)
//										.withKeySerde(Serdes.String())
//										.withValueSerde(topFiveSerde)
//						);
			};

		}
	}
//
//	@RestController
//	public class FooController {
//
//		private final Log logger = LogFactory.getLog(getClass());
//
//		@RequestMapping("/product/idx")
//		public ProductBean product(@RequestParam(value="id") Long id) {
//			final ReadOnlyKeyValueStore<Long, Product> productStore =
//					interactiveQueryService.getQueryableStore(InventoryServiceInteractiveQueries.ALL_SONGS, QueryableStoreTypes.<Long, Product>keyValueStore());
//
//			final Product product = productStore.get(id);
//			if (product == null) {
//				throw new IllegalArgumentException("hi");
//			}
//			return new ProductBean(product.getBrand(), product.getName()) ;
//		}
//
//		@RequestMapping("/charts/top-five")
//		@SuppressWarnings("unchecked")
//		public List<ProductPurchaseCountBean> topFive(@RequestParam(value="genre") String genre) {
//
//			HostInfo hostInfo = interactiveQueryService.getHostInfo(InventoryServiceInteractiveQueries.TOP_FIVE_SONGS_STORE,
//					InventoryServiceInteractiveQueries.TOP_FIVE_KEY, new StringSerializer());
//
//			if (interactiveQueryService.getCurrentHostInfo().equals(hostInfo)) {
//				logger.info("Top Five songs request served from same host: " + hostInfo);
//				return topFiveSongs(InventoryServiceInteractiveQueries.TOP_FIVE_KEY, InventoryServiceInteractiveQueries.TOP_FIVE_SONGS_STORE);
//			}
//			else {
//				//find the store from the proper instance.
//				logger.info("Top Five songs request served from different host: " + hostInfo);
//				RestTemplate restTemplate = new RestTemplate();
//				return restTemplate.postForObject(
//						String.format("http://%s:%d/%s", hostInfo.host(),
//								hostInfo.port(), "charts/top-five?genre=Punk"), "punk", List.class);
//			}
//		}
//
//		private List<ProductPurchaseCountBean> topFiveSongs(final String key, final String storeName) {
//			final ReadOnlyKeyValueStore<String, TopFiveProducts> topFiveStore =
//					interactiveQueryService.getQueryableStore(storeName, QueryableStoreTypes.<String, TopFiveProducts>keyValueStore());
//
//			// Get the value from the store
//			final TopFiveProducts value = topFiveStore.get(key);
//			if (value == null) {
//				throw new IllegalArgumentException(String.format("Unable to find value in %s for key %s", storeName, key));
//			}
//			final List<ProductPurchaseCountBean> results = new ArrayList<>();
//			value.forEach(productPurchaseCount -> {
//
//				HostInfo hostInfo = interactiveQueryService.getHostInfo(InventoryServiceInteractiveQueries.ALL_SONGS,
//						productPurchaseCount.getProductId(), new LongSerializer());
//
//				if (interactiveQueryService.getCurrentHostInfo().equals(hostInfo)) {
//					logger.info("Song info request served from same host: " + hostInfo);
//
//					final ReadOnlyKeyValueStore<Long, Product> productStore =
//							interactiveQueryService.getQueryableStore(InventoryServiceInteractiveQueries.ALL_SONGS, QueryableStoreTypes.<Long, Product>keyValueStore());
//
//					final Product product = productStore.get(productPurchaseCount.getProductId());
//					results.add(new ProductPurchaseCountBean(product.getBrand(),product.getName(), productPurchaseCount.getCount()));
//				}
//				else {
//					logger.info("Song info request served from different host: " + hostInfo);
//					RestTemplate restTemplate = new RestTemplate();
//					ProductBean product = restTemplate.postForObject(
//							String.format("http://%s:%d/%s", hostInfo.host(),
//									hostInfo.port(), "song/idx?id=" + productPurchaseCount.getProductId()),  "id", ProductBean.class);
//					results.add(new ProductPurchaseCountBean(product.getBrand(),product.getName(),productPurchaseCount.getCount()));
//				}
//
//
//			});
//			return results;
//		}
//	}
//
	/**
	 * Serde for TopFiveSongs
	 */
	private static class TopFiveSerde implements Serde<TopFiveProducts> {

		@Override
		public Serializer<TopFiveProducts> serializer() {

			return new Serializer<TopFiveProducts>() {
				@Override
				public void configure(final Map<String, ?> map, final boolean b) {
				}
				@Override
				public byte[] serialize(final String s, final TopFiveProducts topFiveProducts) {

					final ByteArrayOutputStream out = new ByteArrayOutputStream();
					final DataOutputStream
							dataOutputStream =
							new DataOutputStream(out);
					try {
						for (PurchaseCount purchaseCount : topFiveProducts) {
							dataOutputStream.writeLong(purchaseCount.getProductId());
							dataOutputStream.writeLong(purchaseCount.getCount());
						}
						dataOutputStream.flush();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					return out.toByteArray();
				}
			};
		}

		@Override
		public Deserializer<TopFiveProducts> deserializer() {

			return (s, bytes) -> {
				if (bytes == null || bytes.length == 0) {
					return null;
				}
				final TopFiveProducts result = new TopFiveProducts();

				final DataInputStream
						dataInputStream =
						new DataInputStream(new ByteArrayInputStream(bytes));

				try {
					while(dataInputStream.available() > 0) {
						result.add(new PurchaseCount(dataInputStream.readLong(),
								dataInputStream.readLong()));
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				return result;
			};
		}
	}
//
//	/**
//	 * Used in aggregations to keep track of the Top five songs
//	 */
	public static class TopFiveProducts implements Iterable<PurchaseCount> {
		private final Map<Long, PurchaseCount> currentSongs = new HashMap<>();
		private final TreeSet<PurchaseCount> topFive = new TreeSet<>((o1, o2) -> {
			final int result = o2.getCount().compareTo(o1.getCount());
			if (result != 0) {
				return result;
			}
			return o1.getProductId().compareTo(o2.getProductId());
		});

		public void add(final PurchaseCount songPlayCount) {
			if(currentSongs.containsKey(songPlayCount.getProductId())) {
				topFive.remove(currentSongs.remove(songPlayCount.getProductId()));
			}
			topFive.add(songPlayCount);
			currentSongs.put(songPlayCount.getProductId(), songPlayCount);
			if (topFive.size() > 5) {
				final PurchaseCount last = topFive.last();
				currentSongs.remove(last.getProductId());
				topFive.remove(last);
			}
		}

		void remove(final PurchaseCount value) {
			topFive.remove(value);
			currentSongs.remove(value.getProductId());
		}

		@Override
		public Iterator<PurchaseCount> iterator() {
			return topFive.iterator();
		}
//	}
	}
}
