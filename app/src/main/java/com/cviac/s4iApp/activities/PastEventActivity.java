package com.cviac.s4iApp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.EventsAdapter;
import com.cviac.s4iApp.datamodel.PastEvent;
import com.cviac.s4iApp.datamodel.EventInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class PastEventActivity extends Fragment {
    List<PastEvent> evenlist;
    private ListView lv1;

    //  List<PastEvent> emps;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getevents();

        View events = inflater.inflate(R.layout.events_frgs, container, false);
        //((TextView)event.findViewById(R.id.events)).setText("PastEventActivity");
        lv1 = (ListView) events.findViewById(R.id.eventslist);
        lv1.setDivider(null);
        evenlist = getEvents();
        lv1.setAdapter(new EventsAdapter(evenlist, getActivity().getApplicationContext()));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos1,
                                    long pos2) {

                PastEvent event = evenlist.get(pos1);

              //  Toast.makeText(lv1.getContext(), "clicked:" + event.getEvent_name(), Toast.LENGTH_SHORT).show();

            }
        });


        return events;
    }

    private List<PastEvent> getEvents() {
        return PastEvent.getevents();
    }

    private void getevents() {
        Retrofit ret = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        SFIApi api = ret.create(SFIApi.class);
        final Call<List<EventInfo>> call = api.getEvents();
        call.enqueue(new Callback<List<EventInfo>>() {
            @Override
            public void onResponse(Response<List<EventInfo>> response, Retrofit retrofit) {
                List<EventInfo> list =  response.body();
                if (list == null)
                    return;
                List<PastEvent> pastevents = new ArrayList<PastEvent>();
                for (EventInfo info : list) {
                    PastEvent evt = new PastEvent();
                    evt.setEvent_name(info.getEvent_name());
                    evt.setEvent_description(info.getEvent_description());
                    evt.setEvent_date(info.getEvent_date());
                    evt.setLocation(info.getLocation());
                    evt.setImage_url(info.getImage_url());
                    pastevents.add(evt);
                }
                evenlist = pastevents;
                PastEvent.deleteAll();
                saveeventInfo(evenlist);
            }


            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();

             /*  Toast.makeText(PastEventActivity.this, "API Invoke Error :" + throwable.getMessage(), Toast.LENGTH_SHORT).show();*/
                //notifys = null;
            }
        });
    }

    private void saveeventInfo(List<PastEvent> empInfos) {
        for (PastEvent empinfo : evenlist) {
            PastEvent empp = new PastEvent();
            empp.setEvent_name(empinfo.getEvent_name());
            empp.setEvent_description(empinfo.getEvent_description());
            empp.setLocation(empinfo.getLocation());
            empp.setEvent_date(empinfo.getEvent_date());
            empp.setImage_url(empinfo.getImage_url());
            empp.save();

        }
    }
}