package com.cogoun.streaming.collaboration.producer;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.event.CollaborationEvent;
import com.cogoun.streaming.collaboration.event.CollaborationEventType;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class CollaborationEventProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void produceStartCollaborationRoundEvent(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String messageJson;
        messageJson = objectMapper.writeValueAsString(CollaborationEvent.Builder.from(collaboration, CollaborationEventType.STARTED));
        kafkaTemplate.send(topic, messageJson);
    }


}
