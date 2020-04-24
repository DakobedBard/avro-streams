package org.mddarr.inventory;

import io.confluent.kafka.schemaregistry.client.SchemaRegistryClient;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.annotation.InterfaceStability;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@InterfaceStability.Unstable
public class SpecificAvroSerializer<T extends SpecificRecord> implements Serializer<T> {
    private final KafkaAvroSerializer inner;

    public SpecificAvroSerializer() {
        this.inner = new KafkaAvroSerializer();
    }

    SpecificAvroSerializer(SchemaRegistryClient client) {
        this.inner = new KafkaAvroSerializer(client);
    }

    public void configure(Map<String, ?> serializerConfig, boolean isSerializerForRecordKeys) {
        this.inner.configure(ConfigurationUtils.withSpecificAvroEnabled(serializerConfig), isSerializerForRecordKeys);
    }

    public byte[] serialize(String topic, T record) {
        return this.inner.serialize(topic, record);
    }

    public void close() {
        this.inner.close();
    }
}