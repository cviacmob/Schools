package com.cviac.s4iApp.datamodel;

import java.util.ArrayList;
import java.util.List;

public class FAQ {
    private List<Topic> topics;

    public FAQ() {
        // TODO Auto-generated constructor stub
        topics = new ArrayList<Topic>();// TODO Auto-g
    }


    public void addTopic(Topic t) {
        topics.add(t);
    }


    public List<Topic> getTopics() {
        return topics;
    }

}
