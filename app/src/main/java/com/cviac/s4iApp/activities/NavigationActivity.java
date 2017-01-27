package com.cviac.s4iApp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_navigation);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final int applied = Prefs.getInt("applied", 0);
        //Prefs.putInt("applied", 1);
        if (applied == 1) {
            btn.setText("View Profile");
        }

        btn = (Button) findViewById(R.id.applaybtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (applied == 0) {
                    Intent in1 = new Intent(NavigationActivity.this, ApplyActivity.class);
                    startActivity(in1);
                } else {
                    Intent in1 = new Intent(NavigationActivity.this, ProfileActivity.class);
                    startActivity(in1);
                }
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setUserdetails(navigationView);
    }

    private void setUserdetails(NavigationView navigationView) {
        View hview = navigationView.getHeaderView(0);
        TextView un = (TextView) hview.findViewById(R.id.uname);
        TextView um = (TextView) hview.findViewById(R.id.umail);

        String name = Prefs.getString("Name", "");
        String phne = Prefs.getString("Email", "");

        un.setText(name);
        um.setText(phne);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        // Handle the camera action
        if (id == R.id.nav_events) {
            Intent i = new Intent(NavigationActivity.this, HomeActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_profile) {
            Intent i = new Intent(NavigationActivity.this, ProfileActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_social) {
            Intent i = new Intent(NavigationActivity.this, SocialActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_notifi) {
            Intent i = new Intent(NavigationActivity.this, NotificationActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_Contact) {
            Intent i = new Intent(NavigationActivity.this, Contactus.class);
            startActivity(i);


        } else if (id == R.id.nav_about) {
            Intent i = new Intent(NavigationActivity.this, AboutActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_faq) {
            Intent i = new Intent(NavigationActivity.this, FAQActivity.class);
            startActivity(i);


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
