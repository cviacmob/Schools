package com.cviac.s4iApp.activities;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.R;

import java.io.IOException;
import java.io.InputStream;

public class TermActivity extends AppCompatActivity {
    private long enqueue;
    private DownloadManager dm;
    TextView tv1;
    String termstxt = "";
    Button b1;
    Button btnShowProgress;
    ImageButton btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        tv1 = (TextView) findViewById(R.id.textTerm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String memtype = i.getStringExtra("memtype");

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    Toast.makeText(TermActivity.this, "File download complete",
                            Toast.LENGTH_LONG).show();
                }

            }
        };

        int idx = memtype.lastIndexOf("/");
        memtype = memtype.substring(idx + 1) + ".txt";

        termstxt = readTermsFromAsset(memtype);
        tv1.setText(termstxt);


        setTitle("Terms & Conditions");

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem actionViewItem = menu.findItem(R.id.ActionButton);
        // Retrieve the action-view from menu
        View v = MenuItemCompat.getActionView(actionViewItem);
        // Find the button within action-view
        ImageButton btn = (ImageButton) v.findViewById(R.id.imageButton);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
             /*   Resources res = getResources();
                String[] dow = res.getStringArray(R.array.applyas_list);
                String thisMonth = dow[2];

                switch (thisMonth){
                    case "MEMBER":

                        Toast.makeText(getApplicationContext(),dow[1],Toast.LENGTH_LONG).show();
                        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(
                                Uri.parse("http://www.vogella.de/img/lars/LarsVogelArticle7.png"));
                        enqueue = dm.enqueue(request);
                        break;
                    case "VOLUNTEER":

                        Toast.makeText(getApplicationContext(),dow[2],Toast.LENGTH_LONG).show();
                        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        request = new DownloadManager.Request(
                                Uri.parse("http://www.allindiaflorist.com/imgs/arrangemen4.jpg"));
                        enqueue = dm.enqueue(request);
                        break;
                }*/
                String[] dow = getResources().getStringArray(R.array.applyas_list);


                //int i=0;
                String q0 = dow[0];
                String q1 = dow[1];
                String q2 = dow[2];
                // i++;


                if (q0.equals("MEMBER")) {
                    Toast.makeText(getApplicationContext(), dow[0], Toast.LENGTH_LONG).show();
                    dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse("http://www.vogella.de/img/lars/LarsVogelArticle7.png"));
                    enqueue = dm.enqueue(request);
                } else if (q1.equals("VOLUNTEER")) {
                    Toast.makeText(getApplicationContext(), dow[1], Toast.LENGTH_LONG).show();
                    dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    DownloadManager.Request request = new DownloadManager.Request(
                            Uri.parse("http://www.allindiaflorist.com/imgs/arrangemen4.jpg"));
                    enqueue = dm.enqueue(request);
                }
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dow, menu);
        return true;
    }


    private String readTermsFromAsset(String name) {
        AssetManager assetManager = getAssets();
        InputStream input;
        String text = "";
        try {
            input = assetManager.open(name);
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            text = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
//    @Override
//   public boolean onOptionsItemSelected(MenuItem item) {
//       onBackPressed();
//        return true;
//   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
