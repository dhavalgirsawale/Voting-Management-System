package com.example.votingapp.repository;

import com.example.votingapp.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    // Changed from existsByUserIdAndSessionId to existsByUserUserIdAndSessionId
    boolean existsByUserUserIdAndSessionId(String userId, Long sessionId);
}