package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.CurrentEvent;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Currenteventadapter extends ArrayAdapter<CurrentEvent> {

    private List<CurrentEvent> emps;
    private int lastPostion = -1;

    Context mContext;

    public Currenteventadapter(List<CurrentEvent> objects, Context context) {
        super(context, R.layout.currentevent_item, objects);
        emps = objects;
        mContext = context;
    }

    public static class ViewHolder {
        public TextView nameView;
        public TextView mobile;
        public TextView place;
        public TextView sports;
        public ImageView empimage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vw = convertView;
        ViewHolder holder;
        CurrentEvent current = emps.get(position);
        if (convertView == null) {

            LayoutInflater inf = LayoutInflater.from(getContext());
            vw = inf.inflate(R.layout.currentevent_item, parent, false);
            holder = new ViewHolder();

            holder.nameView = (TextView) vw.findViewById(R.id.colleguesname);
            holder.mobile = (TextView) vw.findViewById(R.id.textemail);
            holder.place = (TextView) vw.findViewById(R.id.place);
            holder.sports = (TextView) vw.findViewById(R.id.sports);
            holder.empimage = (ImageView) vw.findViewById(R.id.empimage);
            vw.setTag(holder);

            // String
            // imageurl="http://www.gantrypark.com/Portals/12/Users/066/14/53314/adam-parker-large.jpg";
            //Picasso.with(mContext).load(imageurl).resize(130, 130).transform(new CircleTransform())
            // .into(holder.empimage);


        } else {
            holder = (ViewHolder) vw.getTag();
        }
//CurrentEvent currentevent = new CurrentEvent();
        Date date = null;
        holder.nameView.setText(current.getEvent_name());
        holder.mobile.setText(current.getEvent_description());
        holder.place.setText(current.getLocation());
        String timeStamp = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(current.getEvent_date().toString()));
        holder.sports.setText(timeStamp);

        String url1 = current.getImage_url();

        if (url1 != null && url1.length() > 0) {
            //url1 = url1.replace("localhost","192.168.42.50");
            Picasso.with(mContext).load(current.getImage_url()).resize(80, 80).transform(new CircleTransform())
                    .into(holder.empimage);
        } else {
            Picasso.with(mContext).load(R.drawable.schoolseventscurrent).resize(80, 80).transform(new CircleTransform())
                    .into(holder.empimage);
        }
        return vw;

    }

}