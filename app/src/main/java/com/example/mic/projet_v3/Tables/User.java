package com.example.mic.projet_v3.Tables;

public class User {

    int user_id;
    String user_name;
    String user_age;
    String user_sexe;
    String user_username;
    String user_password;

    public User() {
    }

    public User(int user_id, String user_name, String user_age, String user_sexe, String user_username, String user_password) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_sexe = user_sexe;
        this.user_username = user_username;
        this.user_password = user_password;
    }

    public User(String user_name, String user_age, String user_sexe, String user_username, String user_password) {
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_sexe = user_sexe;
        this.user_username = user_username;
        this.user_password = user_password;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_age(String user_age) {
        this.user_age = user_age;
    }

    public void setUser_sexe(String user_sexe) {
        this.user_sexe = user_sexe;
    }

    public void setUser_username(String user_username) {
        this.user_username = user_username;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }


    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_age() {
        return user_age;
    }

    public String getUser_sexe() {
        return user_sexe;
    }

    public String getUser_username() {
        return user_username;
    }

    public String getUser_password() {
        return user_password;
    }

}
