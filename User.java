package com.voting.votingportal;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "voter_id", nullable = false, unique = true)
    private String voterId;

    @Column(name = "aadhar_id", nullable = false, unique = true)
    private String aadharId;

    @Column(name = "dob", nullable = false)
    private String dob;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "has_voted", nullable = false)
    private boolean hasVoted = false;

    // ── Getters ──────────────────────────────────────────────
    public Long getUserId()       { return userId; }
    public String getName()       { return name; }
    public String getVoterId()    { return voterId; }
    public String getAadharId()   { return aadharId; }
    public String getDob()        { return dob; }
    public String getGender()     { return gender; }
    public String getPhone()      { return phone; }
    public String getPassword()   { return password; }
    public boolean isHasVoted()   { return hasVoted; }

    // ── Setters ──────────────────────────────────────────────
    public void setUserId(Long userId)         { this.userId = userId; }
    public void setName(String name)           { this.name = name; }
    public void setVoterId(String voterId)     { this.voterId = voterId; }
    public void setAadharId(String aadharId)   { this.aadharId = aadharId; }
    public void setDob(String dob)             { this.dob = dob; }
    public void setGender(String gender)       { this.gender = gender; }
    public void setPhone(String phone)         { this.phone = phone; }
    public void setPassword(String password)   { this.password = password; }
    public void setHasVoted(boolean hasVoted)  { this.hasVoted = hasVoted; }
}
