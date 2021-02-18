package com.lupinesoft.myfirebasecrud;

public class PhotoModel {
    String photoURL;
    String name;
    String details;

    public PhotoModel(){}

    public PhotoModel(String photoURL, String name, String details) {
        this.photoURL = photoURL;
        this.name = name;
        this.details = details;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
