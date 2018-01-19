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
import com.cviac.s4iApp.sfiapi.MembershipInfo;
import com.cviac.s4iApp.sfiapi.RegisterResponse;
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

public class MembershipActivity extends AppCompatActivity implements OnItemSelectedListener
{
    Object value;
    ProgressDialog progressDialog;
    SFIApi api;
    String memidd = Prefs.getString("MemId","");
    private Spinner spinner1, spinner2,spinner3,spinner4,spinnerCountry, spinnerCity, spinnerPlan, spinnertype;
    private Button button2;
    private  Button btton1;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    protected void onCreate(Bundle savedInstanceState)
    {
        setTitle("Membership");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        spinnerCountry = (Spinner) findViewById(R.id.spinner1);
        spinnerCity = (Spinner) findViewById(R.id.spinner2);
        spinnerPlan = (Spinner) findViewById(R.id.spinner4);
        spinnertype = (Spinner) findViewById(R.id.spinner3);
        spinnerCountry.setOnItemSelectedListener(this);
        btton1=(Button)findViewById(R.id.submitbutton);
        Button button2=(Button)findViewById(R.id.button2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        button2.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MembershipActivity.this,MembershipFeeActivity.class);
                startActivity(i);



            }
        });
        btton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                SchoolsforIndia app = (SchoolsforIndia) MembershipActivity.this.getApplication();
                String state = spinnerCountry.getSelectedItem().toString();
                String dis = spinnerCity.getSelectedItem().toString();
                String plan = spinnerPlan.getSelectedItem().toString();
                String type = spinnertype.getSelectedItem().toString();
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
                    MembershipInfo membershipApi= new MembershipInfo();
                    membershipApi.setMemState(state);
                    membershipApi.setMemDis(dis);
                    membershipApi.setMemPlan(plan);
                    membershipApi.setMemType(type);
                    membershipApi.setMemID(memidd);
                    membershipApi.setReg_type("Member");
                    final Call<RegisterResponse> call = api.memberreg(membershipApi);
                    call.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                            if (progressDialog != null) {
                                setAlarm();
                                Toast.makeText(getApplicationContext(), "Submited Successfully" , Toast.LENGTH_SHORT ).show();
                                progressDialog.dismiss();

                            }

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                                // progressDialog = null;
                            }
                            Toast.makeText(MembershipActivity.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });
                }


                //Toast.makeText(MembershipActivity.this,"success",Toast.LENGTH_LONG).show();
//
//                Toast.makeText(MembershipActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) +
//                                "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+
//                                "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem()) +
//                                "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem()),
//                        Toast.LENGTH_SHORT).show();
            }

        });



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
                               long arg3)
    {
        parent.getItemAtPosition(pos);

        String dist_pos = "dist_" + pos;
        int resID = getId(dist_pos, R.array.class);
        final String[] dists = getResources().getStringArray(resID);

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dists);
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
        // btton1 = (Button) findViewById(R.id.submitbutton);



    }
    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
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

    /*    Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 2, alarmIntent);*/

        spinnerPlan.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                value = parent.getItemAtPosition(position);

                switch (position) {
                    case 0:
                        Calendar calendar = Calendar.getInstance();
                        calendar = Calendar.getInstance();
                        calendar.setTime(today);
                        calendar.add(Calendar.YEAR, 1);
                        Date Year = calendar.getTime();
                        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                AlarmManager.INTERVAL_DAY, alarmIntent);
                        break;
                    case 1:
                        calendar = Calendar.getInstance();
                        calendar.setTime(today);
                        calendar.add(Calendar.MONTH, 6);
                        Date half = calendar.getTime();
                        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        break;
                    case 2:
                        calendar = Calendar.getInstance();
                        calendar.setTime(today);
                        calendar.add(Calendar.MONTH, 3);
                        Date qurt = calendar.getTime();
                        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        break;
                    case 3:
                        calendar = Calendar.getInstance();
                        calendar.setTime(today);
                        calendar.add(Calendar.DAY_OF_MONTH, 30);
                        Date month = calendar.getTime();
                        alarmMgr.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
                        break;

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}