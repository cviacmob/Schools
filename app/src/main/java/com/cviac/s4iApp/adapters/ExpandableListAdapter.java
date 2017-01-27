package com.cviac.s4iApp.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cviac.s4iApp.Prefs;
import com.cviac.s4iApp.R;
import com.cviac.s4iApp.sfiapi.MyProfileInfo;
import com.cviac.s4iApp.sfiapi.SFIApi;
import com.squareup.okhttp.OkHttpClient;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    //  private Profile pr;
    // private MembershipInfo pro;
    private Activity context;
    private Map<String, List<String>> laptopCollections;
    private List<String> laptops;
    private EditText result;
    private MyProfileInfo Mpi;
    MyProfileInfo myProfile;
    String memId = Prefs.getString("MemId", "");

    public ExpandableListAdapter(Activity context, List<String> laptops,
                                 Map<String, List<String>> laptopCollections, MyProfileInfo Mpi) {
        this.context = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
        this.Mpi = Mpi;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).get(
                childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    private void getValue(int groupPosition, final int childPosition, TextView vw) {

    }

    private void setValue(int groupPosition, final int childPosition, TextView vw, String defvalue) {
        switch (groupPosition) {
            case 1:
                switch (childPosition) {
                    case 0:
                        vw.setText(Mpi.getFirstName() == null ? defvalue : Mpi.getFirstName());
                        break;
                    case 1:
                        vw.setText(Mpi.getEmailID2() == null ? defvalue : Mpi.getEmailID2());
                        break;
                    case 2:
                        vw.setText(Mpi.getMobile2() == null ? defvalue : Mpi.getMobile2());
                        break;
                    case 3:
                        vw.setText(Mpi.getGender() == null ? defvalue : Mpi.getGender());
                        break;
                }
                break;
            case 2:
                switch (childPosition) {
                    case 0:
                        vw.setText(Mpi.getHouseNo() == null ? defvalue : Mpi.getHouseNo());
                        break;
                    case 1:
                        vw.setText(Mpi.getTown() == null ? defvalue : Mpi.getTown());
                        break;
                    case 2:
                        vw.setText(Mpi.getState() == null ? defvalue : Mpi.getState());
                        break;
                    case 3:
                        vw.setText(Mpi.getPIN() == null ? defvalue : Mpi.getPIN());
                        break;
                }
                break;
            case 3:
                switch (childPosition) {
                    case 0:
                        vw.setText(Mpi.getCompNumber() == null ? defvalue : Mpi.getCompNumber());
                        break;
                    case 1:
                        vw.setText(Mpi.getCompTown() == null ? defvalue : Mpi.getCompTown());
                        break;
                    case 2:
                        vw.setText(Mpi.getCompState() == null ? defvalue : Mpi.getCompState());
                        break;
                    case 3:
                        vw.setText(Mpi.getCompPIN() == null ? defvalue : Mpi.getCompPIN());
                        break;
                }
                break;
            case 0:
                switch (childPosition) {
                    case 0:
                        vw.setText(Mpi.getMemType() == null ? defvalue : Mpi.getMemType());
                        break;
                    case 1:
                        vw.setText(Mpi.getMemPlan() == null ? defvalue : Mpi.getMemPlan());
                        break;
                   /* case 2:
                        vw.setText(pr.getDoj() == null ? defvalue : pr.getDoj());
                        break;*/
                }
                break;
        }
    }

    private void setProfileValue(int groupPosition, final int childPosition, TextView vw) {
        switch (groupPosition) {
            case 1:
                switch (childPosition) {
                    case 0:
                        Mpi.setFirstName(vw.getText().toString());
                        break;
                    case 1:
                        Mpi.setEmailID2(vw.getText().toString());
                        break;
                    case 2:
                        Mpi.setMobile2(vw.getText().toString());
                        break;
                    case 3:
                        Mpi.setGender(vw.getText().toString());
                        break;
                }
                break;
            case 2:
                switch (childPosition) {
                    case 0:
                        Mpi.setHouseNo(vw.getText().toString());
                        break;
                    case 1:
                        Mpi.setTown(vw.getText().toString());
                        break;
                    case 2:
                        Mpi.setState(vw.getText().toString());
                        break;
                    case 3:
                        Mpi.setPIN(vw.getText().toString());
                        break;
                }
                break;
            case 3:
                switch (childPosition) {
                    case 0:
                        Mpi.setCompNumber(vw.getText().toString());
                        break;
                    case 1:
                        Mpi.setCompTown(vw.getText().toString());
                        break;
                    case 2:
                        Mpi.setCompState(vw.getText().toString());
                        break;
                    case 3:
                        Mpi.setCompPIN(vw.getText().toString());
                        break;
                }
                break;
            case 0:
                switch (childPosition) {
                    case 0:
                        Mpi.setMemType(vw.getText().toString());
                        break;
                    case 1:
                        Mpi.setMemPlan(vw.getText().toString());
                        break;
                   /* case 2:
                        pr.setDoj(vw.getText().toString());
                        break;*/
                }
                break;
        }
    }

    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String defvalue = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.profile_child_item, null);
        }
        //final View myLayout = inflater.inflate(R.layout.editor, null);

        final TextView item = (TextView) convertView.findViewById(R.id.laptop);


        setValue(groupPosition, childPosition, item, defvalue);

        ImageView img = (ImageView) convertView.findViewById(R.id.write);
        img.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                if (
                        groupPosition == 1 && childPosition == 0 ||
                                groupPosition == 1 && childPosition == 1 ||
                                groupPosition == 1 && childPosition == 2 ||
                                groupPosition == 2 && childPosition == 0 ||
                                groupPosition == 2 && childPosition == 1 ||
                                groupPosition == 2 && childPosition == 3 ||
                                groupPosition == 3 && childPosition == 0 ||
                                groupPosition == 3 && childPosition == 1 ||
                                groupPosition == 3 && childPosition == 3) {
                    getEditText(item, groupPosition, childPosition, defvalue);
                } else if
                        (groupPosition == 2 && childPosition == 2) {
                    getStateCity(item, groupPosition, childPosition, defvalue);

                } else if
                        (groupPosition == 1 && childPosition == 3) {
                    getRadioOption(item, groupPosition, childPosition, defvalue);
                } else if
                        (groupPosition == 3 && childPosition == 2) {
                    getstatecity2(item, groupPosition, childPosition, defvalue);

                } else if
                        (groupPosition == 0 && childPosition == 0) {
                    getMembershiptype(item, groupPosition, childPosition, defvalue);
                } else if
                        (groupPosition == 0 && childPosition == 1) {
                    getmembershipperiod(item, groupPosition, childPosition, defvalue);
                }
//        if (groupPosition == 0 && childPosition ==0){
//            AlertDialog alertDialog = alertDialogBuilder.create();
                // }

            }
        });

        // item.setText(laptop);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    private void getEditText(final TextView item, final int groupPosition, final int childPosition, final String defValue) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.editor, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edit1);

        if (item.getText().toString().equals(defValue)) {
            userInput.setText("");
        } else {
            userInput.setText(item.getText().toString());
        }


        // set dialog message
        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                item.setText(userInput.getText().toString());
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);

                                if (groupPosition == 1) {
                                    if (childPosition == 0) {
                                        String pro = userInput.getText().toString();
                                        profile.setFirstName(pro);
                                    } else if (childPosition == 1) {
                                        String pro1 = userInput.getText().toString();
                                        profile.setEmailID2(pro1);
                                    } else if (childPosition == 2) {
                                        String pro2 = userInput.getText().toString();
                                        profile.setMobile2(pro2);
                                    }
                                } else if (groupPosition == 2) {
                                    if (childPosition == 0) {
                                        String pro3 = userInput.getText().toString();
                                        profile.setHouseNo(pro3);

                                    } else if (childPosition == 1) {
                                        String pro4 = userInput.getText().toString();
                                        profile.setTown(pro4);

                                    } else if (childPosition == 3) {
                                        String pro5 = userInput.getText().toString();
                                        profile.setPIN(pro5);
                                    }
                                } else if (groupPosition == 3) {
                                    if (childPosition == 0) {
                                        String pro6 = userInput.getText().toString();
                                        profile.setCompNumber(pro6);

                                    } else if (childPosition == 1) {
                                        String pro7 = userInput.getText().toString();
                                        profile.setCompTown(pro7);

                                    } else if (childPosition == 3) {
                                        String pro8 = userInput.getText().toString();
                                        profile.setCompPIN(pro8);

                                    }
                                }

                                mypreg(profile);


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
        //alertDialog.getWindow().setLayout(900, 600);
    }

    private void getMembershiptype(final TextView item, final int groupPosition, final int childPosition, String defValue) {
        LayoutInflater li2 = LayoutInflater.from(context);
        View promptsView = li2.inflate(R.layout.memershiptype, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);
       /* final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edit1);*/

        final Spinner sp3 = (Spinner) promptsView.findViewById(R.id.spinner3);
        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // set dialog message
        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String state = sp3.getSelectedItem().toString();
                                item.setText(state);
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);
                                MyProfileInfo myProfile = new MyProfileInfo();

                                if (groupPosition == 0) {
                                    if (childPosition == 0) {
                                        String state1 = sp3.getSelectedItem().toString();
                                        profile.setMemType(state1);
                                    }
                                }
                                mypreg(profile);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    private void getmembershipperiod(final TextView item, final int groupPosition, final int childPosition, String defValue) {

        LayoutInflater li4 = LayoutInflater.from(context);
        View promptsView = li4.inflate(R.layout.membershipperiod_layout, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final Spinner sp4 = (Spinner) promptsView.findViewById(R.id.spinner4);
        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String period = sp4.getSelectedItem().toString();
                                item.setText(period);
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);
                                if (groupPosition == 0) {
                                    if (childPosition == 1) {
                                        String state2 = sp4.getSelectedItem().toString();
                                        profile.setMemPlan(state2);
                                    }
                                }
                                mypreg(profile);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void getStateCity(final TextView item, final int groupPosition, final int childPosition, String defValue) {

        LayoutInflater li3 = LayoutInflater.from(context);
        View promptsView = li3.inflate(R.layout.statecitydialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final Spinner sp1 = (Spinner) promptsView.findViewById(R.id.spinner1);
        final Spinner sp2 = (Spinner) promptsView.findViewById(R.id.spinner2);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);

                String dist_pos = "dist_" + i;
                int resID = getId(dist_pos, R.array.class);
                final String[] dists = view.getContext().getResources().getStringArray(resID);
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(),
                        android.R.layout.simple_spinner_item, dists);
                sp2.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // set dialog message
        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String state = sp1.getSelectedItem().toString();
                                String city = sp2.getSelectedItem().toString();
                                item.setText(state + "/" + city);
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);
                                if (groupPosition == 2) {
                                    if (childPosition == 2) {
                                        String state3 = sp1.getSelectedItem().toString();
                                        String city3 = sp2.getSelectedItem().toString();
                                        profile.setState(state3 + "/" + city3);
                                    }
                                }
                                mypreg(profile);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void getstatecity2(final TextView item, final int groupPosition, final int childPosition, String defValue) {

        LayoutInflater lir = LayoutInflater.from(context);
        View promptsView = lir.inflate(R.layout.statecitydialog2, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final Spinner sp = (Spinner) promptsView.findViewById(R.id.spin1);
        final Spinner spn = (Spinner) promptsView.findViewById(R.id.spin2);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);

                String dist_pos = "dist_" + i;
                int resID = getId(dist_pos, R.array.class);
                final String[] dists = view.getContext().getResources().getStringArray(resID);
                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(),
                        android.R.layout.simple_spinner_item, dists);
                spn.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // set dialog message
        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String state = sp.getSelectedItem().toString();
                                String city = spn.getSelectedItem().toString();
                                item.setText(state + "/" + city);
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);
                                if (groupPosition == 3) {
                                    if (childPosition == 2) {
                                        String state4 = sp.getSelectedItem().toString();
                                        String city4 = spn.getSelectedItem().toString();
                                        profile.setCompState(state4 + "/" + city4);
                                    }
                                }
                                mypreg(profile);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
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


    private void getRadioOption(final TextView item, final int groupPosition, final int childPosition, String defValue) {

        LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.radio_child, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // builder.setMessage("Enter");

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        RadioButton rd1 = (RadioButton) promptsView.findViewById(R.id.radio1);
        RadioButton rd2 = (RadioButton) promptsView.findViewById(R.id.radio2);
        final RadioGroup rs = (RadioGroup) promptsView.findViewById(R.id.radioSex);

        // set dialog message
        alertDialogBuilder
                .setTitle("EDIT " + defValue)
                .setCancelable(false)
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // int selectedId = rs.getCheckedRadioButtonId();
                                final String value = ((RadioButton) promptsView.findViewById(rs.getCheckedRadioButtonId())).getText().toString();
                                //item.setText(selectedId);
                                item.setText(value);
                                setProfileValue(groupPosition, childPosition, item);
                                String memId = Prefs.getString("MemId", "");
                                MyProfileInfo profile = new MyProfileInfo();
                                profile.setMemID(memId);
                                if (groupPosition == 1) {
                                    if (childPosition == 3) {
                                        final String value1 = ((RadioButton) promptsView.findViewById(rs.getCheckedRadioButtonId())).getText().toString();
                                        profile.setGender(value1);
                                    }
                                }
                                mypreg(profile);

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String laptopName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.profile_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(laptopName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void mypreg(MyProfileInfo profile) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(120000, TimeUnit.MILLISECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://schoolsforindia.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        SFIApi api = retrofit.create(SFIApi.class);
        final Call<MyProfileInfo> call = api.updateProfile(profile);
        call.enqueue(new Callback<MyProfileInfo>() {
            @Override
            public void onResponse(Response<MyProfileInfo> response, Retrofit retrofit) {
                Toast.makeText(context, "Apply Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, "Apply Error: " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

}
