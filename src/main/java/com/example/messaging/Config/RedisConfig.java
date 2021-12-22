package com.example.messaging.Config;

import com.example.messaging.Dto.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisConfig {

    @Autowired
    private StreamListener<String, ObjectRecord<String, NotificationMessage>> streamListener;

    @Value("${stream.key}")
    private String streamKey;

    @Bean
    public Subscription subscription(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions<String, ObjectRecord<String, NotificationMessage>> options
                = StreamMessageListenerContainer.StreamMessageListenerContainerOptions.builder()
                .pollTimeout(Duration.ofMillis(100))
                .targetType(NotificationMessage.class)
                .build();

        StreamMessageListenerContainer<String, ObjectRecord<String, NotificationMessage>> listenerContainer =
                StreamMessageListenerContainer.create(redisConnectionFactory, options);

        Subscription subscription = listenerContainer.receive(
                StreamOffset.create(streamKey, ReadOffset.lastConsumed()), streamListener
        );

        listenerContainer.start();
        return subscription;
    }
}
