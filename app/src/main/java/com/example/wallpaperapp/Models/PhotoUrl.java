package com.example.wallpaperapp.Models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class PhotoUrl extends RealmObject {
    @SerializedName("full")
    String full;
    @SerializedName("regular")
    String regular;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }
}
