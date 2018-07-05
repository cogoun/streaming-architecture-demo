package com.cogoun.streaming.collaboration.controller;

import com.cogoun.streaming.collaboration.controller.exception.CollaborationNotFoundException;
import com.cogoun.streaming.collaboration.domain.Collaboration;
import com.cogoun.streaming.collaboration.repository.CollaborationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/collaboration")
public class CollaborationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollaborationController.class);

    @Autowired
    private CollaborationRepository collaborationRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long create(@RequestBody Collaboration collaboration) {
        Collaboration latest =  StreamSupport.stream(collaborationRepository.findAll().spliterator(), false)
                .max(Comparator.comparing(Collaboration::getId))
                .orElse(emptyCollaboration());
        Collaboration newCollaboration = emptyCollaboration();
        newCollaboration.setId(latest.getId()+1);
        newCollaboration.setSubject(collaboration.getSubject());
        Collaboration savedCollaboration = collaborationRepository.save(newCollaboration);
        return savedCollaboration.getId();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Collaboration> collaborations() {
        return StreamSupport.stream(collaborationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{collaborationId}", method = RequestMethod.GET)
    @ResponseBody
    public Collaboration get(@PathVariable long collaborationId) {
        return collaborationRepository
                .findById(String.valueOf(collaborationId))
                .orElseThrow(() -> new CollaborationNotFoundException(collaborationId));
    }

    private Collaboration emptyCollaboration() {
        Collaboration collaboration = new Collaboration();
        collaboration.setId(0);
        return collaboration;
    }


}
