package com.example.parth.pman;

public class User {
String id;
        private String username;
        private String email;
        private String name;
        private String Email;
        private String gender;
        private String mobile;
        private String role;
        private String key;
        public User()
        {
        }

    public User(String name, String email) {
            this.name = name;
            this.Email= email;
    }

    public User(String id,String username, String email, String gender, String mobile, String role) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.gender = gender;
        this.mobile = mobile;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }
}



