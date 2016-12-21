package com.cviac.s4iApp.activities;

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

import com.cviac.s4iApp.R;

import java.lang.reflect.Field;

public class MembershipActivity extends AppCompatActivity implements OnItemSelectedListener
{
    private Spinner spinner1, spinner2,spinner3,spinner4,spinnerCountry, spinnerCity;
    private Button button2;
    private  Button btton1;
    protected void onCreate(Bundle savedInstanceState)
    {
        setTitle("Membership");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership);
        spinnerCountry = (Spinner) findViewById(R.id.spinner1);
        spinnerCity = (Spinner) findViewById(R.id.spinner2);
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
                Toast.makeText(getApplicationContext(), "Submited Sucessfully" , Toast.LENGTH_SHORT ).show();

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
}