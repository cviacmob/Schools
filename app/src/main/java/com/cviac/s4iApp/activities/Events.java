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
import com.cviac.s4iApp.sfiapi.SFIApi;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class Events extends Fragment {
    List<Event> evenlist;
    private ListView lv1;
    List<Event> emps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getevents();

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

                Event event = emps.get(pos1);

                Toast.makeText(lv1.getContext(), "clicked:"+ event.getEvent_name(), Toast.LENGTH_SHORT).show();

            }});


        return events;
    }
    private List<Event> getEvents() {
        return Event.getevents();
    }
   /* private List<Event> getEvents()
    {
        List<Event> emps = new ArrayList<Event>();
        Event event = new Event();
        event.setEvent_name("Cycle Race");
        event.setEvent_date("18/01/2007");
        event.setEvent_description("Schools for India is conducting the Annual International Cycle race, called the ICR on ECR");
        event.setLocation("Chennai");
       // event.setImage_url(R.drawable.schoolseventpast);
        emps.add(event);

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

    }*/
   private void getevents() {
       Retrofit ret = new Retrofit.Builder()
               .baseUrl("http://192.168.42.75")
               .addConverterFactory(GsonConverterFactory.create())
               .build();


       SFIApi api = ret.create(SFIApi.class);
       final Call<List<Event>> call = api.getEvents();
       call.enqueue(new Callback<List<Event>>() {
           @Override
           public void onResponse(Response<List<Event>> response, Retrofit retrofit) {
               evenlist = response.body();
               Event.deleteAll();
               saveeventInfo(evenlist);
           }


           @Override
           public void onFailure(Throwable throwable) {

             /*  Toast.makeText(Events.this, "API Invoke Error :" + throwable.getMessage(), Toast.LENGTH_SHORT).show();*/
               //emps = null;
           }
       });
   }

    private void saveeventInfo(List<Event> empInfos) {
        for (Event empinfo : evenlist) {
            Event emp = new Event();
            emp.setEvent_name(empinfo.getEvent_name());
            emp.setEvent_description(empinfo.getEvent_description());
            emp.setLocation(empinfo.getLocation());
            emp.setEvent_date(empinfo.getEvent_date());
            emp.setImage_url(empinfo.getImage_url());
            emp.save();

        }
    }
}
