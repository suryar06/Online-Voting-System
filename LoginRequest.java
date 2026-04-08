package com.voting.votingportal;

public class LoginRequest {

    private String voterId;
    private String aadharId;
    private String password;

    public LoginRequest() {}

    public String getVoterId()             { return voterId; }
    public void setVoterId(String v)       { this.voterId = v; }

    public String getAadharId()            { return aadharId; }
    public void setAadharId(String a)      { this.aadharId = a; }

    public String getPassword()            { return password; }
    public void setPassword(String p)      { this.password = p; }
}
