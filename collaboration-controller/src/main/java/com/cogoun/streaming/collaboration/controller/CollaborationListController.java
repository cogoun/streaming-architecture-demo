package com.cogoun.streaming.collaboration.controller;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.repository.CollaborationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class CollaborationListController {

    @Autowired
    CollaborationRepository collaborationRepository;

    @RequestMapping("/collaborations")
    public List<Collaboration> collaborations() {
        return StreamSupport.stream(collaborationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
