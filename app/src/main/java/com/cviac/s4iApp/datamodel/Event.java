package com.cviac.s4iApp.datamodel;

/**
 * Created by john on 11/25/2016.
 */

public class Event {
    String names;
    String types;
    String desc;
    String place1;
    int imageid;
    public String getName() {
        return names;
    }

    public String getType() {
        return types;
    }

    public int getImageURL() {  return imageid;    }

    public String getDiscription() {
        return desc;
    }

    public String getPlace1() { return place1;  }


    public void setName(String name) {
        this.names = name;
    }

    public void setType(String type) {
        this.types = type;
    }

    public void setDiscription(String discription) {
        this.desc = discription;
    }

    public void setPlace1(String place1) {this.place1=place1; }

    public void setImageURL(int ImageURL) {
        this.imageid = ImageURL;
    }

}
