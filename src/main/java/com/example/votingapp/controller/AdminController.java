package com.example.votingapp.controller;

import com.example.votingapp.model.User;
import com.example.votingapp.service.AuthService;
import com.example.votingapp.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private VotingService votingService;
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/create-session")
    public ResponseEntity<?> createSession(@RequestParam String title) {
        Long sessionId = votingService.createSession(title);
        return ResponseEntity.ok(sessionId);
    }
    
    @PostMapping("/end-session")
    public ResponseEntity<?> endSession(@RequestParam Long sessionId) {
        votingService.endSession(sessionId);
        return ResponseEntity.ok("Session ended");
    }
    
    @PostMapping("/add-option")
    public ResponseEntity<?> addOption(
            @RequestParam Long sessionId,
            @RequestParam String option) {
        votingService.addOption(sessionId, option);
        return ResponseEntity.ok("Option added");
    }
    
    @GetMapping("/results")
    public ResponseEntity<?> getResults(@RequestParam Long sessionId) {
        return ResponseEntity.ok(votingService.getResults(sessionId));
    }
    
    /*@PostMapping("/reset")
    public ResponseEntity<?> resetVotes() {
        votingService.resetVotes();
        return ResponseEntity.ok("Voting system reset");
    }*/
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam String userId,
            @RequestParam String password,
            @RequestParam(defaultValue = "false") boolean isAdmin) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setPassword(password); // Plain text for now
            user.setAdmin(isAdmin);
            user.setCanVote(!isAdmin); // Admins can't vote by default
            //user.setHasVoted(false);
            
            authService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/grant-access")
    public ResponseEntity<?> grantVotingAccess(
            @RequestParam String userId,
            @RequestParam boolean canVote) {
        // Implementation would update user's canVote status
        return ResponseEntity.ok("Voting access updated");
    }
 // In AdminController.java

    @PostMapping("/reset-votes")
    public ResponseEntity<?> resetVotes() {
        votingService.resetAllSessions();
        return ResponseEntity.ok("All votes and results have been reset");
    }

    @PostMapping("/hard-reset")
    public ResponseEntity<?> hardReset() {
        votingService.hardResetAllVotingData();
        return ResponseEntity.ok("All voting data has been completely reset");
    }
}