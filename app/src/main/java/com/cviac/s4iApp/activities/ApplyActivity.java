package com.cviac.s4iApp.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.adapters.ListAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApplyActivity extends AppCompatActivity {

    List<String> ChildList;
    Map<String, List<String>> ParentListItems;
    ExpandableListView expandablelistView;

    List<String> ParentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        setTitle("Apply");
        loadApplyList();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String memterm = getString(R.string.member_term);
        String voluterm = getString(R.string.volunter_term);
        String sponterm = getString(R.string.sponsor_term);
        String partnerterm = getString(R.string.partner_term);
        String mentorterm = getString(R.string.mentor_term);
       /* String commterm= getString(R.string.community_term);
        String socialterm= getString(R.string.social_term);*/


        ParentListItems = new LinkedHashMap<String, List<String>>();

        for (String HoldItem : ParentList) {
            if (HoldItem.equals("MEMBER")) {
                loadChild(new String[]{memterm});
            } else if (HoldItem.equals("VOLUNTEER"))
                loadChild(new String[]{voluterm});
            else if (HoldItem.equals("SPONSOR"))
                loadChild(new String[]{sponterm});
            else if (HoldItem.equals("PARTNER"))
                loadChild(new String[]{partnerterm});
            else if (HoldItem.equals("MENTOR"))
                loadChild(new String[]{mentorterm});
          /*  else if (HoldItem.equals("COMMUNITY CHAMPIONS"))
                loadChild(new String[] { commterm});
            else if (HoldItem.equals("SOCIAL MEDIA LINKS"))
                loadChild(new String[] { socialterm});*/
            ParentListItems.put(HoldItem, ChildList);
        }

        expandablelistView = (ExpandableListView) findViewById(R.id.expand1);
        final ExpandableListAdapter expListAdapter = new ListAdapter(this,
                ParentList, ParentListItems);
        expandablelistView.setAdapter(expListAdapter);

        expandablelistView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();


                return true;
            }
        });
    }

    private void loadChild(String[] ParentElementsName) {
        ChildList = new ArrayList<String>();
        for (String model : ParentElementsName)
            ChildList.add(model);
    }

    private void loadApplyList() {
        Resources res = getResources();
        String[] applyas = res.getStringArray(R.array.applyas_list);
        for (int i = 0; i < applyas.length; i++) {
            ParentList.add(applyas[i]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }


}