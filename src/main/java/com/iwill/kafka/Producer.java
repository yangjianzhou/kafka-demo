package com.iwill.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producer {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.3.104:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);

        String msg = "kafka msg ";

        producer.send(new ProducerRecord<>("test", 3, msg), ((recordMetadata, e) -> {
            System.out.println("offsite : " +  recordMetadata.offset() + ", partition : " + recordMetadata.partition());
        }));
        TimeUnit.SECONDS.sleep(2);
    }
}
