package com.cviac.s4iApp.activities;

import android.Manifest;
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

import com.cviac.s4iApp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contactus extends AppCompatActivity {
    private EditText email,phon,name;

    TextView tv1;
    TextView tv2;
    TextView tv4;

    Button b1;
    Spinner s1;
    Button call;
    private static final int MY_PERMISSION_CALL_PHONE = 10;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        setTitle ("Contact us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        email = (EditText)findViewById(R.id.emailedit);
        name = (EditText)findViewById(R.id.nameedit);

/*
        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv4 = (TextView) findViewById(R.id.textView4);*/
        call= (Button) findViewById(R.id.button3);
        call.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:8489674524"));
                if (ActivityCompat.checkSelfPermission(Contactus.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Contactus.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_CALL_PHONE);
                    return;
                }
                startActivity(callIntent);
            }
        });

        phon = (EditText) findViewById(R.id.msgedit);
         b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {

                final String email1 = email.getText().toString();
                // TODO Auto-generated method stub

                boolean error = false;
                if (!isValidEmail(email)) {
                    email.setError("Invalid Email");
                    email.requestFocus();
                    error = true;
                }
                    String phn=phon.getText().toString();
		/* Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(phn);*/

                    if (phon.length()  <10) {
                        phon.setError("invalid phone number");
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
                }


        });


        //phon=(EditText)findViewById(R.id.editText3);



        Spinner sp=(Spinner)findViewById(R.id.spinner1);
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
                    callIntent.setData(Uri.parse("tel:8489674524"));
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


}













