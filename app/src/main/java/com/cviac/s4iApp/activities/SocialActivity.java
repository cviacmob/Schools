/*
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
import com.cviac.s4iApp.datamodel.SocialResponse;
import com.cviac.s4iApp.datamodel.Socialupdate;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class SocialActivity extends AppCompatActivity {

    private Button buttonClick, okbutton;
    Spinner imag;
    EditText text;
    Context context;
    List<Socialupdate> socialList;
    List<SocialInfo> socialList1;
    SocialInfoAdapter adapt;
    WebView mWebView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        context = this;


        socialList = new ArrayList<Socialupdate>();


        adapt = new SocialInfoAdapter(this, socialList);
        setTitle("Social");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

     final ListView vw = (ListView) findViewById(R.id.listsocial);

        vw.setAdapter(adapt);
        socialList1=getsocial();

        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mWebView = (WebView)findViewById(R.id.webview);
        mWebView.setBackgroundColor(0);
        mWebView.getSettings().setBuiltInZoomControls(true);

     vw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

         */
/*   Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
             // sending data to new activity
             i.putExtra("pos", position);
             startActivity(i);
             int pos = getIntent().getIntExtra("pos",0);*//*

           */
/*  if (position == 0) {
                 //   spinnerText.setTextColor(Color.RED);
                 text.setText("");
             }*//*


             switch (position) {
                 case 0:
                     */
/*String selectedFromList =(vw.getItemAtPosition(position).toString());
                     mWebView.loadUrl(selectedFromList);*//*

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
           */
/*  String selectedFromList =(vw.getItemAtPosition(position).toString());
             mWebView.loadUrl(selectedFromList);*//*

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
                            Socialupdate info = new Socialupdate();
                            info.setMemID(memId);
                            info.setSocial(schannel);
                            info.setUrl(nam);
                            socialList.add(info);
                            adapt.notifyDataSetChanged();
                            soreg(info);
                          // socialsavedinfo(socialList1);
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
                    text.setText("www.twitter.com");
                } else if (imag.getSelectedItem().toString().equals("Blog")) {
                    text.setText("www.blog.com");
                }else if (imag.getSelectedItem().toString().equals("Flickr")) {
                    text.setText("www.flickr.com");
                }else if (imag.getSelectedItem().toString().equals("Linkedin")) {
                    text.setText("www.linkedin.com");
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
    private void soreg(Socialupdate social) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        SFIApi api = retrofit.create(SFIApi.class);
       */
/* Socialupdate info = new Socialupdate();
        String memId = Prefs.getString("MemID", "");
        info.setMemId(memId);*//*

        final Call<SocialResponse> call = api.socialreg(social);
        call.enqueue(new Callback<SocialResponse>() {
            @Override
            public void onResponse(Response<SocialResponse> response, Retrofit retrofit) {
                List<SocialInfo> socialInfos = new ArrayList<SocialInfo>();
                for (Socialupdate info : socialList) {
                    SocialInfo soupdate = new SocialInfo();
                    soupdate.setMemID(info.getMemID());
                    soupdate.setUrl(info.getUrl());
                    so.setSocial(info.getSocial());
                    socialInfos.add(evt);
                }
                socialList1=socialInfos;
                socialsavedinfo(socialList1);
                SocialResponse otp = response.body();
                int code = otp.getCode();
                if (code == 0) {
                    //  progressDialog.set Message("Retrieving Contacts from Server...");
                    Toast.makeText(getApplicationContext(), "Submited Successfully", Toast.LENGTH_SHORT).show();

                } else if (code == 1003) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SocialActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
       // Toast.makeText(getApplicationContext(), "Submited Successfully", Toast.LENGTH_SHORT).show();


    }
    private List<SocialInfo> getsocial() {
        return SocialInfo.getsocial();
    }
    private void socialsavedinfo(List<SocialInfo> socialInfo) {
        for (SocialInfo cuinfo : socialList1) {
            SocialInfo socialInfo1 = new SocialInfo();
            socialInfo1.setMemID(cuinfo.getMemID());
            socialInfo1.setUrl(cuinfo.getUrl());
            socialInfo1.setSocial(cuinfo.getSocial());
            socialInfo1.save();

        }
    }

}
*/
