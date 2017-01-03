package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.cviac.s4iApp.R;

public class MembershipFeeActivity extends AppCompatActivity
{

    Button button2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipfee);
        setTitle("Membership fee");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

   /* private void contreg(ContactApi contact){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:/169.254.164.75")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<ContactApi> call = api.contatctreg(contact);
        call.enqueue(new Callback<ContactApi>() {
            @Override
            public void onResponse(Response<ContactApi> response, Retrofit retrofit) {
                Toast.makeText(Contactus.this, "Submited Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(Contactus.this, "Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });

    }*/
}
