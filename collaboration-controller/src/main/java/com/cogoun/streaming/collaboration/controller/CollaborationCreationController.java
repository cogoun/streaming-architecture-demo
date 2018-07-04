package com.cogoun.streaming.collaboration.controller;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.repository.CollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController
public class CollaborationCreationController {

    @Autowired
    private CollaborationRepository collaborationRepository;

    @RequestMapping("/collaborations/new")
    public Collaboration create(String text) {
        Collaboration latest =  StreamSupport.stream(collaborationRepository.findAll().spliterator(), false).max(Comparator.comparing(Collaboration::getId)).get();
        Collaboration collaboration = new Collaboration();
        collaboration.setId(latest.getId() + 1);
        collaboration.setSubject(text);
        return collaboration;
    }


}
