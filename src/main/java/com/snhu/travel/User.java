package com.snhu.travel;

public class User {
    private String username;
    private String password;

    public User(String uname, String pass) {
        username = uname;
        password = pass;
    }

    public User(String uname) {
        username = uname;
        password = "password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}
