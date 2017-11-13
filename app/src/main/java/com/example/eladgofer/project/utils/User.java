package com.example.eladgofer.project.utils;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

/**
 * A User Object:
 */

//Firebase Objects: POJO
//1) must have an empty constructor
//2) Getters and Setters for all the properties

public class User {
    //Properties:
    private String displayName;
    private String email;
    private String photoUrl;
    private String uid; //toString, Getters And Setters...

    public User(FirebaseUser user) {
        this.displayName = user.getDisplayName();
        this.email = user.getEmail();
        this.uid = user.getUid();
        Uri firePhoto = user.getPhotoUrl();
        if (firePhoto == null) {
            photoUrl = "https://www.microsoftsmarthq.com.au/assets/images/user-default.png";
        } else {
            photoUrl = firePhoto.toString();
        }
    }

    //Required Empty constructor:
    public User() {
    }

    //Getters And Setters:
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "User:" +
                "displayName: " + displayName + '\'' +
                ", email: " + email + '\'' +
                ", photoUrl: " + photoUrl + '\'' +
                ", uid: " + uid + '\'' +
                '}';
    }
}
