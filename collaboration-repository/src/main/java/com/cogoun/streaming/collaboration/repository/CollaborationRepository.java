package com.cogoun.streaming.collaboration.repository;

import com.cogoun.streaming.collaboration.domain.Collaboration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaborationRepository extends CrudRepository<Collaboration, String> {
}
