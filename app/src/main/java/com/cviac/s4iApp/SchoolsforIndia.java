package com.cviac.s4iApp;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.cviac.s4iApp.datamodel.CurrentEvent;
import com.cviac.s4iApp.datamodel.EmailInfo;
import com.cviac.s4iApp.datamodel.NotificationInfo;
import com.cviac.s4iApp.datamodel.PastEvent;
import com.cviac.s4iApp.datamodel.SendEmailResponse;
import com.cviac.s4iApp.sfiapi.SFIApi;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SchoolsforIndia extends MultiDexApplication {
    private boolean networkStatus = true;

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
        configurationBuilder.addModelClasses(PastEvent.class);
        configurationBuilder.addModelClasses(CurrentEvent.class);
        configurationBuilder.addModelClasses(NotificationInfo.class);

        ActiveAndroid.initialize(configurationBuilder.create());
    }

    public void notifyapply() {
        Intent i = new Intent("notifyapply");
        sendBroadcast(i);
    }

    public void sendEmail(String emailid, String subject, String msgBody) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://apps.cviac.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        EmailInfo emailinfo = new EmailInfo(emailid, subject, msgBody);

        Call<SendEmailResponse> call = api.sendEmail(emailinfo);
        call.enqueue(new Callback<SendEmailResponse>() {
            @Override
            public void onResponse(Response<SendEmailResponse> response, Retrofit retrofit) {
                SendEmailResponse rsp = response.body();
                Toast.makeText(SchoolsforIndia.this,
                        "Send Email Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(SchoolsforIndia.this,
                        "Send Email Failed", Toast.LENGTH_LONG).show();

            }
        });


    }
}
