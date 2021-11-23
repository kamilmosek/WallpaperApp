package com.example.wallpaperapp.App;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import io.realm.Realm;
import io.realm.RealmConfiguration;


import static android.content.ContentValues.TAG;

public class AppController extends Application {




    @Override
    public void onCreate() {//when we run app this class will be implemented 1st add to androidManifest
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("wallpaper.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {}
//        });


    }
}

