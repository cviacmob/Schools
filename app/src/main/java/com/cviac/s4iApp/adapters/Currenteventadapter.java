package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.Currentevent;

import java.util.List;


public class Currenteventadapter extends ArrayAdapter<Currentevent> {

    private List<Currentevent> emps;
    private int lastPostion = -1;

    Context mContext;

    public Currenteventadapter(List<Currentevent> objects, Context context) {
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
        Currentevent emp= emps.get(position);
        if(convertView==null){

            LayoutInflater inf = LayoutInflater.from(getContext());
            vw = inf.inflate(R.layout.currentevent_item, parent, false);
            holder = new ViewHolder();

            holder.nameView = (TextView) vw.findViewById(R.id.colleguesname);
            holder.mobile = (TextView) vw.findViewById(R.id.textemail);
            holder.place =(TextView) vw.findViewById(R.id.place);
            holder.sports =(TextView)vw.findViewById(R.id.sports);
            holder.empimage = (ImageView) vw.findViewById(R.id.empimage);
            vw.setTag(holder);

            // String
            // imageurl="http://www.gantrypark.com/Portals/12/Users/066/14/53314/adam-parker-large.jpg";
            //Picasso.with(mContext).load(imageurl).resize(130, 130).transform(new CircleTransform())
            // .into(holder.empimage);


        }else
        {
            holder=(ViewHolder)vw.getTag();
        }

        holder.nameView.setText(emp.getName());
        holder.mobile.setText(emp.getSports2());
        holder.place.setText(emp.getPlace());
        holder.sports.setText(emp.getSports());
        holder.empimage.setImageResource(emp.getImageURL());
        return vw;

    }

}