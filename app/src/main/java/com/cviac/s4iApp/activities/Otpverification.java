package com.cviac.s4iApp.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.sfiapi.RegInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.cviac.s4iApp.sfiapi.VerifyResponse;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Otpverification extends AppCompatActivity {
    String mobile;
    String verifycode;
    Button buttonverify,resendbtn;
    EditText pin;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent i = getIntent();
        mobile = i.getStringExtra("mobile");

        buttonverify = (Button) findViewById(R.id.verifybutton);
        resendbtn = (Button) findViewById(R.id.resendbtn);
        pin = (EditText) findViewById(R.id.editverify);
        pin.setRawInputType(Configuration.KEYBOARD_12KEY);
        setTitle("OTP Verification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonverify.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                {

                    verifycode = pin.getText().toString();
                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                    okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.1.18")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    SFIApi api = retrofit.create(SFIApi.class);
                    RegInfo regInfo = new RegInfo();
                    regInfo.setMobile1(mobile);
                    regInfo.setOtp(verifycode);
                    setProgressDialog();
                    final Call<VerifyResponse> call = api.verifyPin(regInfo);
                    call.enqueue(new Callback<VerifyResponse>() {
                        @Override
                        public void onResponse(Response<VerifyResponse> response, Retrofit retrofit) {
                            VerifyResponse otp = response.body();
                            int code = otp.getCode();
                            if (code == 0) {
                                progressDialog.setMessage("Retrieving Contacts from Server...");

                                Prefs.putString("isregistered", "true");
                                Intent mainIntent = new Intent(Otpverification.this,NavigationActivity
                                        .class);
                                startActivity(mainIntent);

                                finish();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(Otpverification.this, "Invalid PIN", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(Otpverification.this, "API Invoke Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                    // TODO Auto-generated method stub

                }
            }
        });
       /* resendbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://apps.cviac.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                SFIApi api = retrofit.create(SFIApi.class);
                RegInfo regInfo = new RegInfo();
                regInfo.setMobile1(mobile);
                final Call<VerifyResponse> call=api.registerMobile(regInfo);
                call.enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Response<VerifyResponse> response, Retrofit retrofit) {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(Otpverification.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        t.printStackTrace();
                    }
                });
            }
        });*/
    }
    private void setProgressDialog() {
        progressDialog = new ProgressDialog(Otpverification.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Verifying...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
