//package com.cviac.s4iApp.adapters;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import com.cviac.s4iApp.R;
//
//public class CustomList extends ArrayAdapter<String>{
//
//private final Activity context;
//private final String[] web;
//public CustomList(Activity context,
//                  String[] names, String[] web, Integer[] imageid) {
//super(context, R.layout.notifications, web);
//this.context = context;
//this.web = web;
//
//
//}
//@Override
//public View getView(int position, View view, ViewGroup parent) {
//LayoutInflater inflater = context.getLayoutInflater();
//View rowView= inflater.inflate(R.layout.notifications, null, true);
//TextView txtTitle = (TextView) rowView.findViewById(R.id.textView1);
//txtTitle.setText(web[position]);
//return rowView;
//}
//}
