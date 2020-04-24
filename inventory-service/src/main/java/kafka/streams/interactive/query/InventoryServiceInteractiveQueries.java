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

	}
//
}
	/**
	 * Serde for TopFiveSongs
	 */

//
//	/**
//	 * Used in aggregations to keep track of the Top five songs
//	 */
//	public static class TopFiveProducts implements Iterable<PurchaseCount> {
//		private final Map<Long, PurchaseCount> currentSongs = new HashMap<>();
//		private final TreeSet<PurchaseCount> topFive = new TreeSet<>((o1, o2) -> {
//			final int result = o2.getCount().compareTo(o1.getCount());
//			if (result != 0) {
//				return result;
//			}
//			return o1.getProductId().compareTo(o2.getProductId());
//		});
//
//		public void add(final PurchaseCount songPlayCount) {
//			if(currentSongs.containsKey(songPlayCount.getProductId())) {
//				topFive.remove(currentSongs.remove(songPlayCount.getProductId()));
//			}
//			topFive.add(songPlayCount);
//			currentSongs.put(songPlayCount.getProductId(), songPlayCount);
//			if (topFive.size() > 5) {
//				final PurchaseCount last = topFive.last();
//				currentSongs.remove(last.getProductId());
//				topFive.remove(last);
//			}
//		}
//
//		void remove(final PurchaseCount value) {
//			topFive.remove(value);
//			currentSongs.remove(value.getProductId());
//		}
//
//		@Override
//		public Iterator<PurchaseCount> iterator() {
//			return topFive.iterator();
//		}
////	}
//	}

