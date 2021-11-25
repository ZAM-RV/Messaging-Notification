package com.example.messaging.Dto.Services;

import com.example.messaging.Dto.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListenerService implements StreamListener<String, ObjectRecord<String,NotificationMessage>> {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void onMessage(ObjectRecord<String, NotificationMessage> message) {

    }
}
