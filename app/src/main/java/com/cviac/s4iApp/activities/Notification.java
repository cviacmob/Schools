/*

package com.cviac.s4iApp.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.Notificationadapter;
import com.cviac.s4iApp.datamodel.NotificationInfo;

import java.util.ArrayList;
import java.util.List;


public class NotificationActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    List<NotificationInfo> notifys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setTitle("Notification");
        loaddata();
        notifys = getnotify();
        // setContentView(R.layout.notificaton_item);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Notificationadapter adapter = new Notificationadapter(NotificationActivity.this, notifys);

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
    private List<NotificationInfo> getnotify() {
        return NotificationInfo.getnotfy();
    }

    private void loaddata() {
        List<NotificationInfo> getnotfy = new ArrayList<NotificationInfo>();
        for (NotificationInfo emp : notifys) {
            NotificationInfo noty = new NotificationInfo();
            emp.setTitle(noty.getTitle());
            emp.setDate(noty.getDate());
            emp.setDescription(noty.getDescription());
            emp.setImageid(noty.getImageid());
            emp.save();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}*/
