package com.example.wallpaperapp.Models;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("id") //z gsona bedzie pobierac z jego i na obiekt javy zmieniac
    String id;
    @SerializedName("description")
    String description;
    @SerializedName("urls")
    PhotoUrl url = new PhotoUrl();
    @SerializedName("user")
    User user = new User();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public void setUrl(PhotoUrl url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
