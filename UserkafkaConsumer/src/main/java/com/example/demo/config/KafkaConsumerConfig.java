package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConsumerConfig {
	@Bean
	public ConsumerFactory<String, Object> consumerFactory(){
		Map<String, Object> config=new HashMap<String, Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringSerializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		config.put(ConsumerConfig.GROUP_ID_CONFIG,"user-1");
		return new DefaultKafkaConsumerFactory<>(config);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> concurrentKafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Object> concurrentKafkaListenerContainerFactory=new ConcurrentKafkaListenerContainerFactory<>();
	     concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}
}
