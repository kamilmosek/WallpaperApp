package com.example.wallpaperapp.App;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {
    @Override
    public void onCreate() {//when we run app this class will be implemented 1st add to androidManifest
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("wallpaper.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
