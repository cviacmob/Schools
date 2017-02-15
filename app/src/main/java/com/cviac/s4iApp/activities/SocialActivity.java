package com.cviac.s4iApp.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.SocialInfoAdapter;
import com.cviac.s4iApp.datamodel.SocialInfo;

import java.util.ArrayList;
import java.util.List;


public class SocialActivity extends AppCompatActivity {

    private Button buttonClick, okbutton;
    Spinner imag;
    EditText text;
    Context context;
    List<SocialInfo> socialList;
    SocialInfoAdapter adapt;
    WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        context = this;


        socialList = new ArrayList<SocialInfo>();


        adapt = new SocialInfoAdapter(this, socialList);
        setTitle("Social");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     final ListView vw = (ListView) findViewById(R.id.listsocial);

        vw.setAdapter(adapt);


        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setBuiltInZoomControls(true);

     vw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

         /*   Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
             // sending data to new activity
             i.putExtra("pos", position);
             startActivity(i);
             int pos = getIntent().getIntExtra("pos",0);*/
           /*  if (position == 0) {
                 //   spinnerText.setTextColor(Color.RED);
                 text.setText("");
             }*/

             switch (position) {
                 case 0:
                     /*String selectedFromList =(vw.getItemAtPosition(position).toString());
                     mWebView.loadUrl(selectedFromList);*/
                     mWebView.loadUrl("https://facebook.com/login");
                     break;
                 case 1:
                     mWebView.loadUrl("https://twitter.com/login");
                     break;
                 case 2:
                     mWebView.loadUrl("https://www.google.com/gmail/");
                     break;
             }

             // selected = adapterView.getItemAtPosition(vw.getFirstVisiblePosition() + position).toString();
            // String data=(String)adapterView.getItemAtPosition(position);
            // String itemString=vw.getSelectedItem().toString();
           /*  String selectedFromList =(vw.getItemAtPosition(position).toString());
             mWebView.loadUrl(selectedFromList);*/
         }

     });

        buttonClick = (Button) findViewById(R.id.addbutton);

        // add listener to button
        buttonClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(SocialActivity.this);
                dialog.setContentView(R.layout.activity_dailoge);
                dialog.setTitle("Social Site");


                // set the custom dialog components - text, image and button
                text = (EditText) dialog.findViewById(R.id.linkedit);
                text.setText("Custom dialog Android example.");
                imag = (Spinner) dialog.findViewById(R.id.linkspin);

                itemselect();

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.cancelbtn);
                // if button is clicked, close the custom dialog
                declineButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okbutton = (Button) dialog.findViewById(R.id.okbtn);
                // if button is clicked, close the custom dialog

                 OnClickListener listener = new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        // TODO Auto-generated method stub
                        String web = text.getText().toString();
                        if (!web.equals("")) {
                            String memId = Prefs.getString("MemId", "");
                            String schannel = imag.getSelectedItem().toString();
                            String nam = text.getText().toString();
                            String nam1 = text.getText().toString();
                            SocialInfo info = new SocialInfo();
                            info.setMemId(memId);
                            info.setSocial(nam);
                            info.setChannel(schannel);
                            info.setUrl(nam1);
                           // info.setUrl(text.getText().toString());
                            socialList.add(info);
                        //    soreg(info);
                            //adapt.notifyDataSetInvalidated();
                            adapt.notifyDataSetChanged();
                            dialog.dismiss();
                        } else if (web.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please Select Social site", Toast.LENGTH_LONG).show();
                        }


                    }
                };
                okbutton.setOnClickListener(listener);
            }
        });
    }

    protected void itemselect() {

        imag.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if (arg2 == 0) {
                    TextView spinnerText = (TextView) imag.getChildAt(0);

                    //   spinnerText.setTextColor(Color.RED);
                    text.setText("");
                }

                if (imag.getSelectedItem().toString().equals("Facebook")) {
                    text.setText("www.facebook.com");
                } else if (imag.getSelectedItem().toString().equals("Twitter")) {
                    text.setText("www.Twitter.com");
                } else if (imag.getSelectedItem().toString().equals("Gmail")) {
                    text.setText("www.Gmail.com");
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

  /*  private void soreg(SocialInfo social) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<SocialInfo> call = api.socialreg(social);
        call.enqueue(new Callback<SocialInfo>() {
            @Override
            public void onResponse(Response<SocialInfo> response, Retrofit retrofit) {
                Toast.makeText(getApplicationContext(), "Submited Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SocialActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
       // Toast.makeText(getApplicationContext(), "Submited Successfully", Toast.LENGTH_SHORT).show();


    }*/
}
