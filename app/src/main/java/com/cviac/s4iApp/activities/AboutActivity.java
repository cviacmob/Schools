package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.cviac.s4iApp.R;

public class AboutActivity extends AppCompatActivity {

    TextView tv1;
    String abouttxt = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tv1 = (TextView) findViewById(R.id.textView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("About us");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}