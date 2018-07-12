package com.cogoun.streaming.collaboration.producer;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.event.CollaborationEvent;
import com.cogoun.streaming.collaboration.event.CollaborationEventType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.websocket.SendResult;
import java.util.concurrent.Future;

@Service
public class CollaborationEventProducer {

    private static Logger LOGGER = LoggerFactory.getLogger(CollaborationEventProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public void produceStartCollaborationRoundEvent(Collaboration collaboration) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info("Incoming collaboration class is: " + collaboration.toString());
        String messageJson;
        messageJson = objectMapper.writeValueAsString(CollaborationEvent.Builder.from(collaboration, CollaborationEventType.STARTED));
        LOGGER.info("message: " + messageJson + " is sent from topic: " + topic);
        Future future = kafkaTemplate.send(topic, messageJson);
        future.get();
    }


}
