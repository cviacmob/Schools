
package com.cviac.s4iApp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cviac.s4iApp.R;
import com.cviac.s4iApp.activities.FAQActivity;
import com.cviac.s4iApp.datamodel.FAQ;
import com.cviac.s4iApp.datamodel.Question;
import com.cviac.s4iApp.datamodel.Topic;

public class Faqadapter extends BaseExpandableListAdapter {
    FAQActivity ctx;
    FAQ faqobj;

    public Faqadapter(FAQActivity ctx, FAQ faq) {
        this.ctx = ctx;
        this.faqobj = faq;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return faqobj.getTopics().get(groupPosition).getQuestions().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View ListView,
                             ViewGroup parent) {


        final Question qs = (Question) getChild(groupPosition, childPosition);


        LayoutInflater inflater = ctx.getLayoutInflater();

        if (ListView == null) {
            ListView = inflater.inflate(R.layout.child_list_item, null);
        }

        TextView item = (TextView) ListView.findViewById(R.id.questView);
        item.setText(childPosition + 1 + ". " + qs.getQuestion());

        TextView ansitem = (TextView) ListView.findViewById(R.id.ansView);
        ansitem.setText(qs.getAnswer());
        return ListView;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // TODO Auto-generated method stub
        return faqobj.getTopics().get(groupPosition).getQuestions().size();
    }

    @Override
    public Object getGroup(int pos) {
        // TODO Auto-generated method stub
        return faqobj.getTopics().get(pos);
    }

    @Override
    public int getGroupCount() {
        // TODO Auto-generated method stub
        int size = faqobj.getTopics().size();
        return size;
    }

    @Override
    public long getGroupId(int grppos) {
        // TODO Auto-generated method stub
        return grppos;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View ListView, ViewGroup parent) {
        Topic tp = (Topic) getGroup(groupPosition);
        if (ListView == null) {
            LayoutInflater infalInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ListView = infalInflater.inflate(R.layout.parent_list_item, null);
        }
        TextView item = (TextView) ListView.findViewById(R.id.textView1);
        item.setText(tp.getName());
        return ListView;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return true;
    }

}

