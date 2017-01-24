package com.cviac.s4iApp.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.SocialInfoAdapter;
import com.cviac.s4iApp.datamodel.SocialInfo;

import java.util.ArrayList;
import java.util.List;


public class SocialActivity extends AppCompatActivity {

    private Button buttonClick,okbutton;
    Spinner imag;
    EditText text;
    Context context;
    List<SocialInfo> socialList;
    SocialInfoAdapter adapt;


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        context=this;
        socialList = new ArrayList<SocialInfo>();
        adapt = new SocialInfoAdapter(this, socialList);
        setTitle ("Social");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView vw = (ListView) findViewById(R.id.listView1);
        vw.setAdapter(adapt);

        // adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);

        buttonClick = (Button) findViewById(R.id.addbutton);

        // add listener to button
        buttonClick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(SocialActivity.this);
                dialog.setContentView(R.layout.activity_dailoge);
                dialog.setTitle("Social Site");


                // set the custom dialog components - text, image and button
                text = (EditText) dialog.findViewById(R.id.editText1);
                text.setText("Custom dialog Android example.");
                imag = (Spinner) dialog.findViewById(R.id.spinner1);

                itemselect();

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.button2);
                // if button is clicked, close the custom dialog
                declineButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                okbutton = (Button) dialog.findViewById(R.id.button1);
                // if button is clicked, close the custom dialog

                OnClickListener listener=new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        String web = text.getText().toString();
                        if (!web.equals("")) {
                            String schannel = imag.getSelectedItem().toString();
                            SocialInfo info = new SocialInfo();
                            info.setChannel(schannel);
                            info.setUrl(text.getText().toString());
                            socialList.add(info);
                            //adapt.notifyDataSetInvalidated();
                            adapt.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else if(web.equals("")){
                            Toast.makeText(getApplicationContext(),"Please Select Social site", Toast.LENGTH_LONG).show();
                        }
                    }
                };
                okbutton.setOnClickListener(listener);
            }
        });
    }

    protected void itemselect() {

        imag.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {

                if(arg2==0){
                    TextView spinnerText = (TextView) imag.getChildAt(0);

                 //   spinnerText.setTextColor(Color.RED);
                    text.setText("");
                }

                if (imag.getSelectedItem().toString().equals("Facebook")) {
                    text.setText("www.facebook.com");
                } else if (imag.getSelectedItem().toString().equals("Twitter")) {
                    text.setText("www.Twitter.com");
                } else if (imag.getSelectedItem().toString().equals("Gmail")) {
                    text.setText("www.Gmail.com");
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
