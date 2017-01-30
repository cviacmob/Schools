package com.cviac.s4iApp.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.SchoolsforIndia;
import com.cviac.s4iApp.sfiapi.RegInfo;
import com.cviac.s4iApp.sfiapi.RegisterResponse;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.okhttp.OkHttpClient;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegistrationActivity extends AppCompatActivity implements OnClickListener {

    private String[] arraySpinnervalues;
    private int s_pos;
    // Widget GUI
    ProgressDialog progressDialog;
    ImageView btnCalendar;
    EditText txtDate;
    SFIApi api;
    RegInfo regInfo;
    Calendar c = Calendar.getInstance();
    int startYear = c.get(Calendar.YEAR);
    int startMonth = c.get(Calendar.MONTH);

    int startDay = c.get(Calendar.DAY_OF_MONTH);
    String value;
    RadioButton maleRadioButton, femaleRadioButton, otherbutton;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay;

    /**
     * Called when the activity is first created.
     */
    protected static final Context context = null;

    private EditText email, phon, name, dob;
    private Spinner country;
    private RadioGroup radiogroup;
    private RadioButton radioButton;
    private Button submit;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    // String btndob;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnCalendar = (ImageView) findViewById(R.id.imageView2);

        Spinner sp = (Spinner) findViewById(R.id.country);
        this.arraySpinnervalues = new String[]{"INDIA", "US", "UK"};
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinnervalues);
        sp.setAdapter(adap);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s_pos = i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //  txtDate = (EditText) findViewById(R.id.editText4);

        btnCalendar.setOnClickListener(this);

        radiogroup = (RadioGroup) findViewById(R.id.radio);
        submit = (Button) findViewById(R.id.bu1);

        maleRadioButton = (RadioButton) findViewById(R.id.radioButton1);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton2);
        otherbutton = (RadioButton) findViewById(R.id.radioButton3);

        email = (EditText) findViewById(R.id.editText2);
        name = (EditText) findViewById(R.id.editText1);
        phon = (EditText) findViewById(R.id.editText3);
        dob = (EditText) findViewById(R.id.editText4);
        country = (Spinner) findViewById(R.id.country);

        value = email.getText().toString();
        //String radiovalue = ((RadioButton) this.findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();
        submit.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                SchoolsforIndia app = (SchoolsforIndia) RegistrationActivity.this.getApplication();
                final String name1 = name.getText().toString();
                final String mail1 = email.getText().toString();
                final String Mobile = phon.getText().toString();
                final String contry = arraySpinnervalues[s_pos];

                int id = radiogroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(id);
                final String radio = radioButton.getText().toString();
                final String DOB = dob.getText().toString();
            /*    if (app.isNetworkStatus()) {*/


                boolean error = false;
                if (name1.length() == 0) {
                    name.setError("username not entered");
                    name.requestFocus();
                    error = true;
                }

                if (isValidEmail(email.getText().toString()) == false) {
                    email.setError("Enter valid email");
                    email.requestFocus();
                    error = true;
                }

                if (phon.length() < 10) {
                    phon.setError("invalid phone number");
                    phon.requestFocus();
                    error = true;
                }
                radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    }

                    String getGender() {
                        if (maleRadioButton.isSelected()) {
                            return "Male";
                        } else if (femaleRadioButton.isSelected()) {
                            return "Female";
                        } else if (otherbutton.isSelected()) {
                            return "others";
                        } else {

                            return null;
                        }
                    }

                });

                if (dob.getText().toString().length() == 0) {
                    dob.setError("Enter Your Date of Birth");
                    dob.requestFocus();
                    error = true;
                }

                if (error == false) {
                    progressDialog = new ProgressDialog(RegistrationActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    // progressDialog.setIndeterminateDrawable(R.drawable.custom_progress_dialog);
                    //android:indeterminateDrawable="@drawable/custom_progress_dialog"
                    progressDialog.setMessage("Registering...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    OkHttpClient okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
                    okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://schoolsforindia.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(okHttpClient)
                            .build();
                    api = retrofit.create(SFIApi.class);
                    regInfo = new RegInfo();
                    regInfo.setMobile1(Mobile);
                    regInfo.setFirstName(name1);
                    regInfo.setEmailID1(mail1);
                    regInfo.setCountry(contry);
                    regInfo.setGender(radio);
                    regInfo.setDOB(DOB);
                /*} else {
                    Toast.makeText(getApplicationContext(),
                            "Please Check Your Internet Connection and try again", Toast.LENGTH_LONG).show();
                }*/
                    final Call<RegisterResponse> call = api.registerMobile(regInfo);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                            if (progressDialog != null) {
                                progressDialog.dismiss();

                            }
                            RegisterResponse rsp = response.body();
                            int code = rsp.getCode();
                            String Mem = rsp.getMemID();
                            String id = rsp.getID();
                            if (code == 0) {

                                Intent i = new Intent(RegistrationActivity.this, Otpverification.class);

                                i.putExtra("mobile", Mobile);
                                i.putExtra("Email", mail1);
                                i.putExtra("Name", name1);
                                i.putExtra("DOB", DOB);
                                i.putExtra("Country", contry);
                                i.putExtra("Gender", radio);

                                startActivity(i);
                                finish();


                                Prefs.edit();
                                Prefs.putString("Name", name1);
                                Prefs.putString("Email", mail1);
                                Prefs.putString("Mobile", Mobile);
                                Prefs.putString("MemId", Mem);
                                Prefs.putString("Pid", id);

                            } else if (code == 1004) {

                                Prefs.edit();
                                Prefs.putString("Name", name1);
                                Prefs.putString("Email", mail1);
                                Prefs.putString("Mobile", Mobile);
                                Prefs.putString("MemId", Mem);
                                Prefs.putString("Pid", id);
                                Prefs.putString("isregistered", "true");
                                Toast.makeText(RegistrationActivity.this, "Already Registered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistrationActivity.this, NavigationActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                                // progressDialog = null;
                            }
                            Toast.makeText(RegistrationActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            t.printStackTrace();

                        }
                    });


                }
            }

        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected boolean isValidEmail(String email) {
        // TODO Auto-generated method stub
        // String emi=email.getText().toString();

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == btnCalendar) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            dob.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);


                        }
                    }, mYear, mMonth, mDay);

            dpd.getDatePicker().setMaxDate(System.currentTimeMillis());

            dpd.show();
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Registration Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
