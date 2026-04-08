package com.voting.votingportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // ── REGISTER ──────────────────────────────────────────────
    public String register(User user) {

        // Null / empty checks
        if (user.getVoterId() == null || user.getVoterId().trim().isEmpty()) {
            return "Voter ID is required.";
        }
        if (user.getAadharId() == null || user.getAadharId().trim().isEmpty()) {
            return "Aadhaar ID is required.";
        }
        if (user.getDob() == null || user.getDob().trim().isEmpty()) {
            return "Date of birth is required.";
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password is required.";
        }

        // Duplicate Voter ID check
        if (userRepository.existsByVoterId(user.getVoterId().trim())) {
            return "Voter ID is already registered!";
        }

        // Duplicate Aadhaar check
        if (userRepository.existsByAadharId(user.getAadharId().trim())) {
            return "Aadhaar ID is already registered!";
        }

        // Age verification — must be 18+
        try {
            LocalDate dob = LocalDate.parse(user.getDob().trim());
            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 18) {
                return "Access denied: You must be 18 or older to register.";
            }
        } catch (Exception e) {
            return "Invalid date of birth. Use format YYYY-MM-DD.";
        }

        // Trim all fields before saving
        user.setVoterId(user.getVoterId().trim());
        user.setAadharId(user.getAadharId().trim());
        user.setName(user.getName().trim());
        user.setPhone(user.getPhone().trim());

        userRepository.save(user);
        return "Registration successful!";
    }

    // ── LOGIN ─────────────────────────────────────────────────
    public String login(String voterId, String aadharId, String password) {
        if (voterId == null || aadharId == null || password == null) {
            return "All fields are required.";
        }

        Optional<User> user = userRepository
                .findByVoterIdAndAadharIdAndPassword(
                        voterId.trim(), aadharId.trim(), password);

        if (user.isPresent()) {
            return "Login successful!";
        }
        return "Invalid credentials. Please check your Voter ID, Aadhaar, and password.";
    }

    // ── VOTE ──────────────────────────────────────────────────
    public String vote(String voterId, String candidate) {
        if (voterId == null || voterId.trim().isEmpty()) {
            return "Voter ID is required.";
        }
        if (candidate == null || candidate.trim().isEmpty()) {
            return "Please select a candidate.";
        }

        Optional<User> optionalUser = userRepository.findByVoterId(voterId.trim());

        if (optionalUser.isEmpty()) {
            return "Voter not found. Please register first.";
        }

        User user = optionalUser.get();

        // One-vote enforcement
        if (user.isHasVoted()) {
            return "You have already voted! Duplicate voting is not permitted.";
        }

        // Record the vote
        user.setHasVoted(true);
        userRepository.save(user);

        return "Vote cast successfully for " + candidate + "!";
    }
}
