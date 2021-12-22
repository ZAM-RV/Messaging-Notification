package com.example.messaging.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NotificationMessage {
    private String from;
    private String to;
    private String type;
}
