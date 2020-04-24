package org.mddarr.inventory;

import org.apache.avro.Schema;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
public class ClicksProcessor {
//    @Bean
//    public BiFunction<KStream<String, Long>, KTable<String, String>,KStream<String,Long>> click_process(){
//
//
//    }
    public static final int WINDOW_SIZE_MS = 30000;

    @Bean
    @SuppressWarnings("unchecked")
    public Function<KStream<Bytes, String>, KStream<Bytes, WordCount>> inventoryprocess() {
        return input -> input.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .map((key, value) -> new KeyValue<>(value, value))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofMillis(WINDOW_SIZE_MS)))
                .count()
                .toStream()
                .map((key, value) -> new KeyValue(null, new InventoryApplication.WordCount(key.key(), value, new Date(key.window().start()), new Date(key.window().end()))));
    }
//
//    @Bean
//    public BiFunction<KStream<String, Long>, KTable<String, String>, KStream<String, Long>> clickprocess() {
//
//        final InputStream pageViewRegionSchema = ClicksProcessor.class.getClassLoader()
//                .getResourceAsStream("avro/views.avsc");
//
//
//    }
//        final Schema schema = new Schema.Parser().parse(pageViewRegionSchema);

//        return (clicksStream, regionsTable) -> clicksStream
//                .leftJoin(regionsTable, (view, region) -> {
//
//                })


    private static final class RegionWithClicks {
        private final String region;
        private final long clicks;
        public RegionWithClicks(String region, long clicks) {
            if (region == null || region.isEmpty()) {
                throw new IllegalArgumentException("region must be set");
            }
            if (clicks < 0) {
                throw new IllegalArgumentException("clicks must not be negative");
            }
            this.region = region;
            this.clicks = clicks;
        }
        public String getRegion() {
            return region;
        }
        public long getClicks() {
            return clicks;
        }
    }

    static class WordCount {
        private String word;
        private long count;
        private Date start;
        private Date end;
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("WordCount{");
            sb.append("word='").append(word).append('\'');
            sb.append(", count=").append(count);
            sb.append(", start=").append(start);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }
        WordCount() {}
        WordCount(String word, long count, Date start, Date end) {
            this.word = word;
            this.count = count;
            this.start = start;
            this.end = end;
        }
        public String getWord() {
            return word;
        }
        public void setWord(String word) {
            this.word = word;
        }
        public long getCount() {
            return count;
        }
        public void setCount(long count) {
            this.count = count;
        }
        public Date getStart() {
            return start;
        }
        public void setStart(Date start) {
            this.start = start;
        }
        public Date getEnd() {
            return end;
        }
        public void setEnd(Date end) {
            this.end = end;
        }
    }

}
