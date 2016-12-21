package com.cviac.s4iApp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.datamodel.SocialInfo;

import java.util.List;

public class SocialInfoAdapter  extends BaseAdapter {

    List<SocialInfo> socialList;
    Context context;

    public SocialInfoAdapter(Context ctx, List<SocialInfo> prgmNameList) {
        // TODO Auto-generated constructor stub
        socialList=prgmNameList;
        context=ctx;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return socialList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        SocialInfo obj = socialList.get(position);
        View rowView = convertView;
        Holder holder;
        if (convertView == null ) {
            holder=new Holder();
            LayoutInflater inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_socialinfoadapter, null);
            holder.tv=(TextView) rowView.findViewById(R.id.textView1);
            holder.img=(ImageView) rowView.findViewById(R.id.imageView1);

            rowView.setTag(holder);
        }else {
            holder = (Holder) rowView.getTag();
        }
        holder.tv.setText(obj.getUrl().toString());
        if (obj.getChannel().equalsIgnoreCase("facebook")) {
            holder.img.setImageResource(R.drawable.fb);
        }
        else if (obj.getChannel().equalsIgnoreCase("twitter")) {
            holder.img.setImageResource(R.drawable.twitter);
        }
        else if (obj.getChannel().equalsIgnoreCase("gmail")) {
            holder.img.setImageResource(R.drawable.gmail);
        }
        return rowView;
    }
}