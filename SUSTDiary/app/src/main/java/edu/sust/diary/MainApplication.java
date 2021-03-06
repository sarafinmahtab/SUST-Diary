package edu.sust.diary;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("diaryrealm.realm")
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
