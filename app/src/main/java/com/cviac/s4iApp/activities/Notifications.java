package com.cviac.s4iApp.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.Notificationadapter;
import com.cviac.s4iApp.datamodel.Notification;

import java.util.ArrayList;
import java.util.List;


public class Notifications extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    List<Notification> emps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notification");
        loaddata();
       // setContentView(R.layout.notificaton_item);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Notificationadapter adapter = new Notificationadapter(Notifications.this,emps);

        lv = (ListView) findViewById(R.id.listnotify);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
//
//        return Notifications;
    }

    private void loaddata() {

        emps = new ArrayList<>();

        Notification not1=new Notification("Schools for India will support on case to case basis any government or privately run schools. But Schools for India will concentrate on building and running its own schools across India. ",R.drawable.basketball,"Basketball");
        emps.add(not1);
        Notification not2=new Notification("Each district will have 10 to 12 schools based on the need, and will cover approximately 150 to 200 villages.",R.drawable.cricket,"Cricket");
        emps.add(not2);
        Notification not3=new Notification("Each school will be built over an area of 20 Acres, with built up area of 140,000 Sq Ft. ",R.drawable.footbal,"Foodbal");
        emps.add(not3);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}