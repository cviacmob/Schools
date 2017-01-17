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
import com.cviac.s4iApp.sfiapi.RegisterResponse;
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
    String Mobile;
    String name1;
    String mail1;
    String contry;
    String radio;
    String DOB;
    String verifycode;
    Button buttonverify,resendbtn;
    EditText pin;
    ProgressDialog progressDialog;
   // SFIApi api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Intent i = getIntent();
        Mobile = i.getStringExtra("mobile");
         mail1 = i.getStringExtra("Email");
        name1 = i.getStringExtra("Name");
        DOB = i.getStringExtra("DOB");
        contry = i.getStringExtra("Country");
        radio = i.getStringExtra("Gender");


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
                            .baseUrl("http://192.168.1.13")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    SFIApi api = retrofit.create(SFIApi.class);
                    RegInfo regInfo = new RegInfo();
                    regInfo.setMobile1(Mobile);
                    regInfo.setOtp(verifycode);
                    setProgressDialog();
                    final Call<VerifyResponse> call = api.verifyPin(regInfo);
                    call.enqueue(new Callback<VerifyResponse>() {
                        @Override
                        public void onResponse(Response<VerifyResponse> response, Retrofit retrofit) {
                            VerifyResponse otp = response.body();
                            int code = otp.getCode();
                            if (code == 0) {
                              //  progressDialog.set Message("Retrieving Contacts from Server...");

                                Prefs.putString("isregistered", "true");
                                Intent mainIntent = new Intent(Otpverification.this,NavigationActivity
                                        .class);
                                startActivity(mainIntent);
                                finish();
                            } else if (code == 1002){
                                progressDialog.dismiss();
                                Toast.makeText(Otpverification.this, "Invalid Mobile number Or Pin", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(Otpverification.this, "API Invoke Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

                }
            }
        });
        resendbtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.1.13")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                SFIApi api = retrofit.create(SFIApi.class);
                RegInfo regInfo = new RegInfo();
                regInfo.setFirstName(name1);
                regInfo.setEmailID1(mail1);
                regInfo.setCountry(contry);
                regInfo.setGender(radio);
                regInfo.setDOB(DOB);
                regInfo.setMobile1(Mobile);
                final Call<RegisterResponse> call =api.registerMobile(regInfo);
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
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
        });
        /*Toast.makeText(getApplicationContext(), "Resend OTP Sucessfully " , Toast.LENGTH_SHORT ).show();*/

    }
    private void setProgressDialog() {
        progressDialog = new ProgressDialog(Otpverification.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
