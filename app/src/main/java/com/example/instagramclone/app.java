package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("5DxLF3XSGu2bcMJQUJVTXaNXMuAogZI0yADMa5c9")
                // if defined
                .clientKey("1Z3WiRGCwpAVCuh1mwubQqmhmCtF1iG2y1vZc2uN")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
