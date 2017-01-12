package com.cviac.s4iApp.datamodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Table(name = "Notification")
public class NotificationInfo extends Model{
    @Column(name = "description")
   private String description;
    @Column(name = "imageid")
     private   String imageid;
    @Column(name = "Date")
    private Date Date;
    @Column(name = "Title")
    private String Title;


    public NotificationInfo() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
       this.Date = date;
    }

    public String getImageid() {
        return imageid;
    }
    public void setImageid(String imageid) {
        this.imageid = imageid;
    }



    private static String getTodayEvent() {
        Calendar cal = Calendar.getInstance();
        int dat = cal.get(Calendar.DAY_OF_MONTH);
        String dformat = String.format("%02d", dat);
        int mnth = cal.get(Calendar.MONTH) + 1;
        String mformat = String.format("%02d", mnth);
        String wdate = "-" + mformat + "-" + dformat;
        return wdate;
    }


    public static List<NotificationInfo> applaydate() {
        String notedate = getTodayEvent();
        List<NotificationInfo> result = new ArrayList<>();
        List<NotificationInfo> notilist = new Select()
                .from(NotificationInfo.class)
                .execute();

        for (NotificationInfo note : notilist) {
            SimpleDateFormat format = new SimpleDateFormat("-MM-dd");
            String dt = format.format(note.getDate());
            if (dt.equals(notedate)) {
                result.add(note);
            }

        }
        return result;
    }

  /*  public static List<NotificationInfo> getnotfy() {
        return new Select()
                .from(NotificationInfo.class)
                .orderBy("event_date DESC")
                .execute();
    }*/
  public static List<NotificationInfo> getevents() {
      return new Select()
              .from(NotificationInfo.class)
              .orderBy("date DESC")
              .execute();
  }
    public static void deleteAll() {
        new Delete().from(NotificationInfo.class).execute();
    }

}
