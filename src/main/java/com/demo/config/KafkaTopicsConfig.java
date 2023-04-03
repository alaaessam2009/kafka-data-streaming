package com.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

	@Value("${topic.small.amount}")
	private String smallTopic ;

	@Value("${topic.big.amount}")
	private String bigTopic ;

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name(smallTopic).build();
	}

	@Bean
	public NewTopic topic2() {
		return TopicBuilder.name(bigTopic).build();
	}


}