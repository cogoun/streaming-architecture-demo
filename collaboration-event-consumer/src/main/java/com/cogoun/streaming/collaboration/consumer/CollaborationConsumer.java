package com.cogoun.streaming.collaboration.consumer;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.event.CollaborationEvent;
import com.cogoun.streaming.collaboration.repository.CollaborationRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.stream.StreamSupport;

@Component
public class CollaborationConsumer {

    private static Logger LOGGER = LoggerFactory.getLogger(CollaborationConsumer.class);

    private final CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private CollaborationRepository collaborationRepository;

    @KafkaListener(
            topics = "aTestTopic",
            groupId = "collaboration.consumers.group",
            containerFactory = "collaborationEventConcurrentKafkaListenerContainerFactory")
    public void consume(CollaborationEvent collaborationEvent) {
        this.latch.countDown();
        Collaboration collaboration = CollaborationEvent.Builder.from(collaborationEvent);
        Collaboration latest =  StreamSupport.stream(collaborationRepository.findAll().spliterator(), false)
            .max(Comparator.comparing(Collaboration::getId))
            .orElse(emptyCollaboration());
        collaboration.setId(latest.getId()+1);
        Collaboration savedCollaboration = collaborationRepository.save(collaboration);
        LOGGER.info("Collaboration: [" + collaboration.toString() + "] was saved in the database");
    }

    private Collaboration emptyCollaboration() {
        Collaboration collaboration = new Collaboration();
        collaboration.setId(0);
        return collaboration;
    }
}
