package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.Event;

import java.util.List;


public class EventsAdapter extends ArrayAdapter<Event> {

    private List<Event> eve;

    private int lastPostion = -1;

    Context mContext;

    public EventsAdapter(List<Event> objects, Context context) {
        super(context, R.layout.events_item, objects);
        eve = objects;
        mContext = context;
    }

    public static class ViewHolder {
        public TextView nameView;
        public TextView typeview;
        public TextView disview;
        public ImageView imgview;
        public TextView place;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vw = convertView;
        ViewHolder holder;
        Event even = getItem(position);
        if (convertView == null) {

            LayoutInflater inf = LayoutInflater.from(getContext());
            vw = inf.inflate(R.layout.events_item, parent, false);
            holder = new ViewHolder();
            holder.nameView = (TextView) vw.findViewById(R.id.textViewNameevent);
            holder.typeview = (TextView) vw.findViewById(R.id.textViewdate);
            holder.disview = (TextView) vw.findViewById(R.id.textViewDescription);
            holder.place=(TextView) vw.findViewById(R.id.place1) ;
            holder.imgview = (ImageView) vw.findViewById(R.id.imagenotify);
            // Picasso.with(mContext).load(R.drawable.ic_launcher).resize(130, 130).transform(new CircleTransform()).into(holder.imgview);
            vw.setTag(holder);
        } else {
            holder = (ViewHolder) vw.getTag();
        }
        holder.nameView.setText(even.getName());
        holder.typeview.setText(even.getType());
        holder.imgview.setImageResource(even.getImageURL());
        holder.disview.setText(even.getDiscription());
        holder.place.setText(even.getPlace1());
        return vw;


    }
}
