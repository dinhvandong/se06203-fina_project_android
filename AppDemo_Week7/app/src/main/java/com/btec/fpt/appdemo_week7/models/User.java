package com.btec.fpt.appdemo_week7.models;


public class User {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String birthday;
    private String sex;

    public User() {
    }

    public User(int id, String email, String password, String fullName, String birthday, String sex) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.birthday = birthday;
        this.sex = sex;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
