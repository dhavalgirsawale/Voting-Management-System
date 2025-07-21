package com.example.votingapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
    @Column(name = "user_id")  // Explicitly map to user_id column
    private String userId;
    
    private String password;
    private boolean isAdmin;
    private boolean canVote=true;
    //private boolean hasVoted;
}