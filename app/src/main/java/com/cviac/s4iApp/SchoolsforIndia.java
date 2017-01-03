package com.cviac.s4iApp;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.cviac.s4iApp.datamodel.Currentevent;
import com.cviac.s4iApp.datamodel.Event;

public class SchoolsforIndia extends MultiDexApplication {
    private boolean networkStatus =true;

    public boolean isNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(boolean networkStatus) {
        this.networkStatus = networkStatus;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();


            Configuration.Builder configurationBuilder = new Configuration.Builder(this);
            configurationBuilder.addModelClasses(Event.class);
          configurationBuilder.addModelClasses(Currentevent.class);
            ActiveAndroid.initialize(configurationBuilder.create());


    }
}
