package com.voting.votingportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // POST /api/register
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    // POST /api/login
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(
                loginRequest.getVoterId(),
                loginRequest.getAadharId(),
                loginRequest.getPassword()
        );
    }

    // POST /api/vote
    @PostMapping("/vote")
    public String vote(@RequestBody VoteRequest voteRequest) {
        return userService.vote(
                voteRequest.getVoterId(),
                voteRequest.getCandidate()
        );
    }
}
