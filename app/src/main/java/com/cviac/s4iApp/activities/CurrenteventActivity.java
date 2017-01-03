package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.Currenteventadapter;
import com.cviac.s4iApp.datamodel.Currentevent;

import java.util.List;

/**
 * Created by john on 11/25/2016.
 */

public class CurrenteventActivity extends Fragment {
    private ListView lv;
    List<Currentevent> emps;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View collegues = inflater.inflate(R.layout.currentevent_frgs, container, false);

        lv=(ListView)collegues.findViewById(R.id.collegueslist);
        lv.setDivider(null);
        emps=getCollegues();
        lv.setAdapter(new Currenteventadapter(emps,getActivity().getApplicationContext()));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos1,
                                    long pos2) {

                Currentevent currentevent = emps.get(pos1);
                // Conversation cov=new Conversation();
                //    cov.setEmpid(emp.getEmpID());
                //   cov.setName(emp.getName());
//             Intent i = new Intent(getActivity().getApplicationContext(), ChatActivity.class);
//                i.putExtra("conversewith", cov);
                //    startActivity(i);

                Toast.makeText(lv.getContext(), "clicked:"+ currentevent.getEvent_name(), Toast.LENGTH_SHORT).show();

            }});


        return collegues;
    }

    private List<Currentevent> getCollegues() {
        return Currentevent.getcurrentevents();
    }

  /*  private List<Currentevent> getCollegues()
    {
        List<Currentevent> emps = new ArrayList<Currentevent>();

        Currentevent currentevent = new Currentevent();
        currentevent.setEvent_name("Cycle Race");
        currentevent.setEvent_date("30/01/2011");
        currentevent.setEvent_description("Schools for India conducted the 3rd International Cycle Race on East Coast Road, Chennai India.");
        currentevent.setLocation("Chennai");

       //
        // currentevent.setImage_url(R.drawable.schoolseventscurrent);
        emps.add(currentevent);

//        emp = new Currentevent();
//        emp.setName("Cricket");
//        emp.setSports("02/10/2016");
//        emp.setSports2("Conducting Cricket");
//        emp.setPlace("Banaglure");
//        emp.setImageURL(R.drawable.cricket);
//        emps.add(emp);
//
//        emp = new Currentevent();
//        emp.setName("VolleyBall");
//        emp.setSports("15/10/2016");
//        emp.setSports2("Conducting VolleyBall");
//        emp.setPlace("Mumabai");
//        emp.setImageURL(R.drawable.volleyball);
//        emps.add(emp);
//
//        emp = new Currentevent();
//        emp.setName("FoodBall");
//        emp.setSports("23/01/2016");
//        emp.setSports2("Conducting FoodBall");
//        emp.setPlace("Delhi");
//        emp.setImageURL(R.drawable.footbal);
//        emps.add(emp);

        return emps;

    }
*/
}
