package com.cviac.s4iApp.datamodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

/**
 * Created by john on 11/25/2016.
 */
@Table(name = "events")
public class Event extends Model {
    @Column(name = "event_name")
    private String event_name;
    @Column(name = "event_date")
    private Date event_date;
    @Column(name = "event_description")
    private String event_description;
    @Column(name = "location")
    private String location;
    @Column(name = "image_url")
    private String image_url;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static List<Event> getevents() {
        return new Select()
                .from(Event.class)
                .orderBy("event_date DESC")
                .execute();

    }


    public static void deleteAll() {
        new Delete().from(Event.class).execute();
    }
}
