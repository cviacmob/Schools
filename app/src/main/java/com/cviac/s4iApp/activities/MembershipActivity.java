package com.cviac.s4iApp.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.SchoolsforIndia;
import com.cviac.s4iApp.notificationreceiver.AlarmReceiver;
import com.cviac.s4iApp.datamodel.MembershipInfo;
import com.cviac.s4iApp.datamodel.RegisterResponse;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MembershipActivity extends AppCompatActivity implements OnItemSelectedListener {
    Object value;
    ProgressDialog progressDialog;
    SFIApi api;
    String memidd = Prefs.getString("MemId", "");
    private Spinner spinner1, spinner2, spinner3, spinner4, spinnerCountry, spinnerCity, spinnerPlan, spinnertype;
    private Button button2;
    Button btn;
    Button btton1;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    String plan;

    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Membership");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        // setContentView(R.layout.content_navigation);
        btn = (Button) findViewById(R.id.applaybtn);
        spinnerCountry = (Spinner) findViewById(R.id.spinner1);
        spinnerCity = (Spinner) findViewById(R.id.spinner2);
        spinnerPlan = (Spinner) findViewById(R.id.spinner4);
        spinnertype = (Spinner) findViewById(R.id.spinner3);
        spinnerCountry.setOnItemSelectedListener(this);
        btton1 = (Button) findViewById(R.id.submitbutton);
        Button button2 = (Button) findViewById(R.id.button2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MembershipActivity.this, MembershipFeeActivity.class);
                startActivity(i);
            }
        });
        btton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SchoolsforIndia app = (SchoolsforIndia) MembershipActivity.this.getApplication();
                boolean error = false;
                if (error == false) {
                    progressDialog = new ProgressDialog(MembershipActivity.this, R.style.AppTheme_Dark_Dialog);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Submit");
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
                    MembershipInfo membershipApi = new MembershipInfo();
                    String state = spinnerCountry.getSelectedItem().toString();
                    membershipApi.setMemState(state);
                    String dis = spinnerCity.getSelectedItem().toString();
                    membershipApi.setMemDis(dis);
                    plan = spinnerPlan.getSelectedItem().toString();
                    membershipApi.setMemPlan(plan);
                    String type = spinnertype.getSelectedItem().toString();
                    membershipApi.setMemType(type);
                    membershipApi.setMemID(memidd);
                    membershipApi.setReg_type("Member");
                    final Call<RegisterResponse> call = api.memberreg(membershipApi);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                            RegisterResponse rsp = response.body();
                            int code = rsp.getCode();
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                            }
                            if (code == 0) {
                                setAlarm();
                                Toast.makeText(getApplicationContext(), "Submited Successfully", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MembershipActivity.this, NavigationActivity.class);
                                startActivity(intent);
                                finish();

                                SchoolsforIndia app = (SchoolsforIndia) getApplication();
                                app.notifyapply();
                            }
                            if (code == 1013) {
                                Toast.makeText(MembershipActivity.this, "Already Registered Please change your plan", Toast.LENGTH_LONG).show();

                                SchoolsforIndia app = (SchoolsforIndia) getApplication();
                                app.notifyapply();

                                Intent intent = new Intent(MembershipActivity.this, NavigationActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(MembershipActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });
                }
            }

        });

        NavigationActivity.btn.setVisibility(View.GONE);
    }


    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
                               long arg3) {
        parent.getItemAtPosition(pos);

        String dist_pos = "dist_" + pos;
        int resID = getId(dist_pos, R.array.class);
        final String[] dists = getResources().getStringArray(resID);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dists);
        spinnerCity.setAdapter(adapter);


    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new Membership_Activity());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void setAlarm() {
        final Date today = new Date();
        alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        switch (plan) {
            case "Annual":
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
             /*   calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 32);

                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);

                        calendar.setTime(today);*/
                calendar.add(Calendar.YEAR, 1);
                Date Year = calendar.getTime();
                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
                break;
            case "Halfyearly":
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
              /*  calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 35);

                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
                        calendar.setTime(today);*/
                calendar.add(Calendar.MONTH, 6);
                Date half = calendar.getTime();
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                break;
            case "Quartyearly":
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
               /* calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 38);

                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
                        calendar.setTime(today);*/
                calendar.add(Calendar.MONTH, 3);
                Date qurt = calendar.getTime();
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                break;
            case "Monthly":
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());
               /* calendar.set(Calendar.HOUR_OF_DAY, 12);
                calendar.set(Calendar.MINUTE, 42);

                alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY, alarmIntent);
                        calendar.setTime(today);*/
                calendar.add(Calendar.DAY_OF_MONTH, 30);
                Date month = calendar.getTime();
                alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                break;
        }
    }
}