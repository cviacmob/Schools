package com.cviac.s4iApp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.MemberFeeInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MembershipFeeActivity extends AppCompatActivity {
    MemberFeeInfo memberfee;
        ProgressDialog progressDialog;
    String feeid = Prefs.getString("Pid", "");
    public TextView tenscltxt;
    public TextView onescltxt;
    public TextView clstxt;
    public TextView bricktxt;
    public TextView annualtxt;
    public TextView frdannaultxt;
    public TextView frdmonthtxt;
    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipfee);
        setTitle("Membership fee");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tenscltxt = (TextView) findViewById(R.id.amnt1);
        onescltxt = (TextView) findViewById(R.id.amnt2);
        clstxt = (TextView) findViewById(R.id.amt3);
        bricktxt = (TextView) findViewById(R.id.amt4);
        annualtxt = (TextView) findViewById(R.id.amt5);
        frdannaultxt = (TextView) findViewById(R.id.amt6);
        frdmonthtxt = (TextView) findViewById(R.id.amt7);


        memberupdate(memberfee);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void memberupdate(MemberFeeInfo memberfeeinfo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        setProgressDialog();
        final Call<List<MemberFeeInfo>> call = api.getfeeinfo();
        call.enqueue(new Callback<List<MemberFeeInfo>>() {
            @Override
            public void onResponse(Response<List<MemberFeeInfo>> response, Retrofit retrofit) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
                List<MemberFeeInfo> minfolist = response.body();
                if (minfolist.size() > 0) {
                    MemberFeeInfo info = minfolist.get(0);
                    tenscltxt.setText(info.getTen_Schools());
                    onescltxt.setText(info.getOne_School());
                    clstxt.setText(info.getOne_Class());
                    bricktxt.setText(info.getOne_Brick());
                    annualtxt.setText(info.getAnnual_AdoptChild());
                    frdannaultxt.setText(info.getAnnual());
                    frdmonthtxt.setText(info.getMonthly());
                }

            }

            @Override
            public void onFailure(Throwable t) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    // progressDialog = null;
                }
                t.printStackTrace();

            }
        });

    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(MembershipFeeActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Checking...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

}
