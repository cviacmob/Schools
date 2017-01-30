package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.NotificationInfo;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class Notificationadapter extends ArrayAdapter<NotificationInfo> {

    private List<NotificationInfo> eve;

    private int lastPostion = -1;
    Context mContext;

    /* public Notificationadapter(NotificationActivity objects, List<NotificationInfo> context) {
         super(objects,R.layout.notificaton_item , context);
         eve = (List<NotificationInfo>) objects;
         mContext = (Context) context;
     }*/
    public Notificationadapter(List<NotificationInfo> objects, Context context) {
        super(context, R.layout.notificaton_item, objects);
        eve = objects;
        mContext = context;
    }


    public static class ViewHolder {
        public TextView names;
        public TextView desc, txtdate;
        public ImageView imageid;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vw = convertView;
        ViewHolder holder;
        NotificationInfo noteset = getItem(position);

        if (convertView == null) {

            LayoutInflater inf = LayoutInflater.from(getContext());
            vw = inf.inflate(R.layout.notificaton_item, parent, false);
            /*String name = Prefs.getString("Name", "");*/
            holder = new ViewHolder();
            holder.desc = (TextView) vw.findViewById(R.id.text_notify);
            holder.names = (TextView) vw.findViewById(R.id.text_desc);
            holder.txtdate = (TextView) vw.findViewById(R.id.textViewdate);
            holder.imageid = (ImageView) vw.findViewById(R.id.imagenotify);
            /*holder.names.setText(name);*/
            vw.setTag(holder);
        } else {
            holder = (ViewHolder) vw.getTag();
        }
        String url1 = noteset.getImageid();
        if (url1 != null && url1.length() > 0) {
            Picasso.with(mContext).load(noteset.getImageid()).resize(80, 80).transform(new CircleTransform())
                    .into(holder.imageid);
        } else {
            Picasso.with(mContext).load(R.drawable.school).resize(80, 80).transform(new CircleTransform())
                    .into(holder.imageid);
        }
        holder.names.setText(noteset.getTitle());
        holder.desc.setText(noteset.getDescription());
        String timeStamp = new SimpleDateFormat("yyyy-MMM-dd").format(new Date(noteset.getDate().toString()));
        holder.txtdate.setText(timeStamp);
      /*  Date date = new Date();
        String stringDate = DateFormat.getDateInstance().format(date);
        holder.txtdate.setText(stringDate);*/

        return vw;


    }
}

