package com.example.signuprealtimelogin;

public class HelperClass {

    String name, username, password, email;
    public HelperClass() {

    }
    public HelperClass(String name, String username, String password, String email) {
        this.name = name;
        this.email=email;
        this.username=username;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}
