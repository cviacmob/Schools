package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.Notification;

import java.util.List;


public class Notificationadapter extends BaseAdapter {

    private List<Notification> eve;

    private int lastPostion = -1;

    Context mContext;

    public Notificationadapter(Context c, List<Notification> eve) {
        this.eve = eve;
        mContext = c;
    }
    public static class ViewHolder {
        public TextView names;
        public TextView desc;
       // public TextView desc2;
        public ImageView imageid;
        //public TextView place;
    }

    @Override
    public int getCount() {
        return eve.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vw = convertView;
        ViewHolder holder;
        Notification even = eve.get(position);
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vw = inflater.inflate(R.layout.notificaton_item, parent, false);
            holder = new ViewHolder();
            holder.names = (TextView) vw.findViewById(R.id.text_notify);
            holder.desc = (TextView) vw.findViewById(R.id.text_desc);
          //  holder.desc2 = (TextView) vw.findViewById(R.id.text_notiy2);
          //  holder.place=(TextView) vw.findViewById(R.id.place1) ;
            holder.imageid = (ImageView) vw.findViewById(R.id.imagenotify);
            // Picasso.with(mContext).load(R.drawable.ic_launcher).resize(130, 130).transform(new CircleTransform()).into(holder.imgview);
            vw.setTag(holder);
        } else {
            holder = (ViewHolder) vw.getTag();
        }
        holder.names.setText(even.getNames());
        holder.desc.setText(even.getDesc());
        holder.imageid.setImageResource(even.getImageid());
      //  holder.desc2.setText(even.getDesc2());
      //  holder.place.setText(even.getPlace1());
        return vw;


    }
}

