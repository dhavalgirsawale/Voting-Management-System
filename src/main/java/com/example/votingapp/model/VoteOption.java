package com.example.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vote_options")
@Data
public class VoteOption {
    @Id
    private String optionName;
    
    private int voteCount;
    
    @OneToMany(mappedBy = "voteOption", cascade = CascadeType.ALL)
    @JsonIgnore // Added this to break the cycle
    private List<Vote> votes;
    
    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonIgnore // Added this to break the cycle
    private VotingSession session;
}