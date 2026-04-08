package com.voting.votingportal;

public class VoteRequest {

    private String voterId;
    private String candidate;

    public VoteRequest() {}

    public String getVoterId()             { return voterId; }
    public void setVoterId(String v)       { this.voterId = v; }

    public String getCandidate()           { return candidate; }
    public void setCandidate(String c)     { this.candidate = c; }
}
