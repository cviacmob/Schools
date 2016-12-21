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
import com.cviac.s4iApp.adapters.EventsAdapter;
import com.cviac.s4iApp.datamodel.Event;

import java.util.ArrayList;
import java.util.List;


public class Events extends Fragment {

    private ListView lv1;
    List<Event> emps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View events = inflater.inflate(R.layout.events_frgs, container, false);
        //((TextView)event.findViewById(R.id.events)).setText("Events");
        lv1=(ListView)events.findViewById(R.id.eventslist);
        lv1.setDivider(null);
        emps=getEvents();
        lv1.setAdapter(new EventsAdapter(emps,getActivity().getApplicationContext()));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos1,
                                    long pos2) {

                Event emp = emps.get(pos1);

                Toast.makeText(lv1.getContext(), "clicked:"+ emp.getName(), Toast.LENGTH_SHORT).show();

            }});


        return events;
    }
    private List<Event> getEvents()
    {
        List<Event> emps = new ArrayList<Event>();
        Event emp = new Event();
        emp.setName("Cycle Race");
        emp.setType("18/01/2007");
        emp.setDiscription("Schools for India is conducting the Annual International Cycle race, called the ICR on ECR");
        emp.setPlace1("Chennai");
        emp.setImageURL(R.drawable.schoolseventpast);
        emps.add(emp);

//        emp = new Event();
//        emp.setName("Cricket");
//        emp.setType("02/10/2015");
//        emp.setDiscription("Conducting Cricket");
//        emp.setPlace1("Chennai");
//        emp.setImageURL(R.drawable.cricket);
//        emps.add(emp);
//
//
//        emp = new Event();
//        emp.setName("FoodBall");
//        emp.setType("15/10/2015");
//        emp.setDiscription("Conducting FoodBall");
//        emp.setPlace1("Mumbai");
//        emp.setImageURL(R.drawable.footbal);
//        emps.add(emp);
//
//        emp = new Event();
//        emp.setName("VolleyBall");
//        emp.setType("23/01/2015");
//        emp.setDiscription("VolleyBall");
//        emp.setPlace1("Conducting Bangalore");
//        emp.setImageURL(R.drawable.volleyball);
//        emps.add(emp);
        return emps;

    }
}
