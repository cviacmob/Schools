package com.cviac.s4iApp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.activities.MembershipActivity;
import com.cviac.s4iApp.activities.TermActivity;
import com.cviac.s4iApp.datamodel.MembershipInfo;
import com.cviac.s4iApp.datamodel.RegisterResponse;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ListAdapter extends BaseExpandableListAdapter {

    /* private static Context mContext;
     ProgressDialog progressDialog;
     SFIApi api;
     RegInfo regInfo;
 */
    private Activity context;
    private Map<String, List<String>> ParentListItems;
    private List<String> Items;
    protected CompoundButton member;

    public ListAdapter(Activity context, List<String> Items,
                       Map<String, List<String>> ParentListItems) {
        this.context = context;
        this.ParentListItems = ParentListItems;
        this.Items = Items;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return ParentListItems.get(Items.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View ListView, ViewGroup parent) {
        final String CoursesName = (String) getChild(groupPosition,
                childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (ListView == null) {
            ListView = inflater.inflate(R.layout.apply_child_list_item, null);
        }

        TextView item = (TextView) ListView.findViewById(R.id.textView1);

        //item.setText(CoursesName);
        setTextViewHTML(item, CoursesName);
        final CheckBox chkbox = (CheckBox) ListView.findViewById(R.id.checkBox1);
        Button agreebtn = (Button) ListView.findViewById(R.id.button1);

        agreebtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String regType = "";

                if (groupPosition == 0 && childPosition == 0) {
                    StringBuilder result = new StringBuilder();
                    result.append("Membership\n");
                    if (chkbox.isChecked()) {
                        result.append("accepted");

                    /*    Toast.makeText(context, result.toString(),
                                Toast.LENGTH_SHORT).show();*/

                        Intent mainIntent = new Intent(context,
                                MembershipActivity.class);
                        context.startActivity(mainIntent);
                    } else
                        Toast.makeText(context, "please accept and check the terms and conditions", Toast.LENGTH_LONG)
                                .show();
                    return;
                }
                if (groupPosition == 1 && childPosition == 0) {
                    regType = "VOLUNTEER";
                    StringBuilder result = new StringBuilder();
                    result.append("Volunteer is\n");
                    if (chkbox.isChecked()) {
                        result.append("accepted");

                     /*   Toast.makeText(context, result.toString(),
                                Toast.LENGTH_SHORT).show();*/
                    } else
                        Toast.makeText(context, "please accept and check the terms and conditions", Toast.LENGTH_LONG)
                                .show();
                } else if (groupPosition == 2 && childPosition == 0) {
                    regType = "SPONSER";
                    StringBuilder result = new StringBuilder();
                    result.append("Sponsor is\n");
                    if (chkbox.isChecked()) {
                        result.append("accepted");

                       /* Toast.makeText(context, result.toString(),
                                Toast.LENGTH_SHORT).show();*/
                    } else
                        Toast.makeText(context, "please accept and check the terms and conditions", Toast.LENGTH_LONG)
                                .show();
                } else if (groupPosition == 3 && childPosition == 0) {
                    regType = "PARTNER";
                    StringBuilder result = new StringBuilder();
                    result.append("Partner is\n");
                    if (chkbox.isChecked()) {
                        result.append("accepted");

                      /*  Toast.makeText(context, result.toString(),
                                Toast.LENGTH_SHORT).show();*/
                    } else
                        Toast.makeText(context, "please accept and check the terms and conditions", Toast.LENGTH_LONG)
                                .show();
                } else if (groupPosition == 4 && childPosition == 0) {
                    regType = "MENTOR";
                    StringBuilder result = new StringBuilder();
                    result.append("Mentor is\n");
                    if (chkbox.isChecked()) {
                        result.append("accepted");

                       /* Toast.makeText(context, result.toString(),
                                Toast.LENGTH_SHORT).show();*/
                    } else
                        Toast.makeText(context, "please accept and check the terms and conditions", Toast.LENGTH_LONG)
                                .show();
                }

                String memId = Prefs.getString("MemId", "");
                MembershipInfo info = new MembershipInfo();
                info.setReg_type(regType);
                info.setMemID(memId);
                memRegister(info);
            }
        });

        Button cancelbtn = (Button) ListView.findViewById(R.id.button2);
        cancelbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(1);
            }
        });
        return ListView;

    }

    protected void finish() {
        // TODO Auto-generated method stub

    }

    public int getChildrenCount(int groupPosition) {
        return ParentListItems.get(Items.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return Items.get(groupPosition);
    }

    public int getGroupCount() {
        return Items.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    protected void makeLinkClickable(SpannableStringBuilder strBuilder,
                                     final URLSpan span) {
        int start = strBuilder.getSpanStart(span);
        int end = strBuilder.getSpanEnd(span);
        int flags = strBuilder.getSpanFlags(span);
        final String url = span.getURL();
        ClickableSpan clickable = new ClickableSpan() {
            public void onClick(View view) {

                String memtype = url;

                Intent mainIntent = new Intent(context,
                        TermActivity.class);
                mainIntent.putExtra("memtype", memtype);
                context.startActivity(mainIntent);

            }
        };
        strBuilder.setSpan(clickable, start, end, flags);
        strBuilder.removeSpan(span);
    }

    protected void setTextViewHTML(TextView text, String html) {
        CharSequence sequence = Html.fromHtml(html);
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
        URLSpan[] urls = strBuilder.getSpans(0, sequence.length(),
                URLSpan.class);
        for (URLSpan span : urls) {
            makeLinkClickable(strBuilder, span);
        }
        text.setText(strBuilder);
        text.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View ListView, ViewGroup parent) {
        String CoursesFull = (String) getGroup(groupPosition);
        if (ListView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ListView = infalInflater.inflate(R.layout.apply_parent_list_item,
                    null);
        }
        TextView item = (TextView) ListView.findViewById(R.id.textView1);
        item.setText(CoursesFull);
        return ListView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private void memRegister(MembershipInfo info) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<RegisterResponse> call = api.memberreg(info);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Response<RegisterResponse> response, Retrofit retrofit) {
                Toast.makeText(context, "Apply Success", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "Apply Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }
}