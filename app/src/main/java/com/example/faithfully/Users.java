package com.example.faithfully;

public class Users {
    private String userID;
    private String religion;

    public Users(){

    }

    public Users(String userID){
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getReligion() {
        return religion;
    }
}
