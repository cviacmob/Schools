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

import java.util.List;

public class NotificationActivity extends AppCompatActivity{

    private ListView lv1;

    private List<NotificationInfo> noty;

    private Notificationadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ListView lv1 = (ListView)findViewById(R.id.listnotify);
        setTitle("Notification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        noty = getEvents();
        adapter = new Notificationadapter(noty, this);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos1,
                                    long pos2) {

                NotificationInfo emp = noty.get(pos1);

                // Toast.makeText(lv1.getContext(), "clicked:" + emp.getEvent_title(), Toast.LENGTH_SHORT).show();




            }
        });
        NotificationInfo.deleteAll();
        saveeventInfo(noty);

    }

    private List<NotificationInfo> getEvents() {
        return NotificationInfo.getevents();
    }
    private void saveeventInfo(List<NotificationInfo> empInfos) {
        for (NotificationInfo empinfo : noty) {
            NotificationInfo empp = new NotificationInfo();
            empp.setTitle(empinfo.getTitle());
            empp.setDescription(empinfo.getDescription());
            empp.setDate(empinfo.getDate());
            empp.setImageid(empinfo.getImageid());
            empp.save();

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}







