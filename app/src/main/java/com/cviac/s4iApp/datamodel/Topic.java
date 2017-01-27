package com.cviac.s4iApp.datamodel;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private List<Question> questions;

    public Topic() {
        questions = new ArrayList<Question>();// TODO Auto-generated constructor stub
    }

    public String getName() {
        return name;
    }

    public void setName(String topic) {
        this.name = topic;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }

}
