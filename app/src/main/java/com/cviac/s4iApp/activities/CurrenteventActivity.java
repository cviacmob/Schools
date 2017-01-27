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
import com.cviac.s4iApp.sfiapi.SFIApi;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by john on 11/25/2016.
 */

public class CurrenteventActivity extends Fragment {
    private ListView lv;
    //List<Currentevent> emps;
    List<Currentevent> currentlist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View collegues = inflater.inflate(R.layout.currentevent_frgs, container, false);

        lv = (ListView) collegues.findViewById(R.id.collegueslist);
        lv.setDivider(null);
        currentlist = getCollegues();
        lv.setAdapter(new Currenteventadapter(currentlist, getActivity().getApplicationContext()));
        getCurrent();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos1,
                                    long pos2) {

                Currentevent currentevent = currentlist.get(pos1);
                // Conversation cov=new Conversation();
                //    cov.setEmpid(emp.getEmpID());
                //   cov.setName(emp.getName());
//             Intent i = new Intent(getActivity().getApplicationContext(), ChatActivity.class);
//                i.putExtra("conversewith", cov);
                //    startActivity(i);

                Toast.makeText(lv.getContext(), "clicked:" + currentevent.getEvent_name(), Toast.LENGTH_SHORT).show();

            }
        });


        return collegues;
    }

    private List<Currentevent> getCollegues() {
        return Currentevent.getcurrentevents();
    }


    private void getCurrent() {
        Retrofit ret = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SFIApi api = ret.create(SFIApi.class);
        final Call<List<Currentevent>> call = api.getCurrent();
        call.enqueue(new Callback<List<Currentevent>>() {
            @Override
            public void onResponse(Response<List<Currentevent>> response, Retrofit retrofit) {
                currentlist = response.body();
                Currentevent.deleteAll();
                currentsavedinfo(currentlist);
            }

            @Override
            public void onFailure(Throwable t) {
               /* Toast.makeText(CurrenteventActivity.this, "API Invoke Error :" + t.getMessage(), Toast.LENGTH_SHORT).show();
*/
            }
        });


    }

    private void currentsavedinfo(List<Currentevent> currentinfo) {
        for (Currentevent cuinfo : currentlist) {
            Currentevent ctevent = new Currentevent();
            ctevent.setEvent_name(cuinfo.getEvent_name());
            ctevent.setEvent_description(cuinfo.getEvent_description());
            ctevent.setLocation(cuinfo.getLocation());
            ctevent.setEvent_date(cuinfo.getEvent_date());
            ctevent.setImage_url(cuinfo.getImage_url());
            ctevent.save();

        }
    }

}
