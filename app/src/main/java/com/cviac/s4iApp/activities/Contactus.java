package com.cviac.s4iApp.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.SchoolsforIndia;
import com.cviac.s4iApp.datamodel.ContactInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class Contactus extends AppCompatActivity {
    private EditText email,phon,name,msg;
    private Activity context;
    TextView tv1;
    TextView tv2;
    TextView tv4;

    Button b1;
    Spinner feed;
    Button call;
    private static final int MY_PERMISSION_CALL_PHONE = 10;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        setTitle ("Contact us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        email = (EditText)findViewById(R.id.emailedit);
        name = (EditText)findViewById(R.id.nameedit);
        feed =(Spinner)findViewById(R.id.feedbackspin);
        msg =(EditText)findViewById(R.id.msgedit);

/*
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv4 = (TextView) findViewById(R.id.textView4);*/
        call= (Button) findViewById(R.id.callbtn);
        call.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:9791402344"));
                if (ActivityCompat.checkSelfPermission(Contactus.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Contactus.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_CALL_PHONE);
                    return;
                }
                startActivity(callIntent);
            }
        });

        phon = (EditText) findViewById(R.id.subedit);
         b1=(Button)findViewById(R.id.scbmitbtn);
        b1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {

                String nam = name.getText().toString();
                String emai = email.getText().toString();
                String sub = phon.getText().toString();
                String message =msg.getText().toString();
                String spin = feed.getSelectedItem().toString();
               // final String email1 = email.getText().toString();
                // TODO Auto-generated method stub

                boolean error=false;
                if (!isValidEmail(email)) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                    error = true;
                }
                    String phn=phon.getText().toString();
		/* Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(phn);*/
                    if (phon.length()  <1) {
                        phon.setError("invalid message");
                        phon.requestFocus();
                        error = true;
                    }

                    String nam1e=name.getText().toString();
		/* Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(phn);*/

                    if (nam1e.length() <1) {
                        name.setError("please Enter username");
                        name.requestFocus();
                        error = true;
                    }

                String msgBody = getMessagebody(nam, emai, sub, message, spin);

                SchoolsforIndia school =(SchoolsforIndia)getApplicationContext();

                if (school.isNetworkStatus()) {
                    school.sendEmail("kathiravankrishnans@gmail.com", "Contact Us", msgBody);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }

              //  finish();

                String memId = Prefs.getString("MemId","");
                ContactInfo contact =new ContactInfo();
                contact.setName(nam);
                contact.setMail(emai);
                contact.setSubject(sub);
                contact.setForm(spin);
                contact.setMessages(message);
                contreg(contact);

            }
        });

        //phon=(EditText)findViewById(R.id.editText3);

        Spinner sp=(Spinner)findViewById(R.id.feedbackspin);
        List<String> list = new ArrayList<String>();
        list.add("select category");
        list.add("FAQ");
        list.add("Feedback");
        list.add("Enquiry");
        list.add("Volunteers");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter);


    }
    protected boolean isValidEmail(EditText email2) {
        // TODO Auto-generated method stub
        String emi=email2.getText().toString();

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emi);
        return matcher.matches();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:+919791402344"));
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(callIntent);
                }
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
    private void contreg(ContactInfo contact){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/192.168.1.13")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<ContactInfo> call = api.contatctreg(contact);
        call.enqueue(new Callback<ContactInfo>() {
            @Override
            public void onResponse(Response<ContactInfo> response, Retrofit retrofit) {
                //Toast.makeText(Contactus.this, "Submited Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Contactus.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
        Toast.makeText(getApplicationContext(), "Submited Successfully" , Toast.LENGTH_SHORT ).show();


    }
    private String getMessagebody(String Name, String Emailid, String Subject, String msg, String feedback) {

        StringBuilder msgBody = new StringBuilder();
        msgBody.append("Name: " + Name + "\n");
        msgBody.append("Email: " + Emailid + "\n");
        msgBody.append("Subject: " + Subject + "\n");
        msgBody.append("Feedback: " + feedback + "\n");
        msgBody.append("Message: " + msg + "\n");

        return msgBody.toString();
    }

}













