package org.mddarr.inventory;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@SpringBootApplication
public class InventoryApplication {

	public static final int WINDOW_SIZE_MS = 30000;
	public static class InventoryConsumer {
		public static final String INPUT_TOPIC = "input";
		public static final String OUTPUT_TOPIC = "output";
		public static final int WINDOW_SIZE_MS = 30000;




	}

		public static void main(String[] args) {

			final InputStream pageViewRegionSchema = ClicksProcessor.class.getClassLoader()
					.getResourceAsStream("avro/views.avsc");
			int b =1;
		SpringApplication.run(InventoryApplication.class, args);
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
