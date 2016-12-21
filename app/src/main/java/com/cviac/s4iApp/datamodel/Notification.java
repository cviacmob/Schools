package com.cviac.s4iApp.datamodel;

public class Notification {
  String names;
    String desc;
  //  String desc2;
    int imageid;


    public Notification(String names, int imageid,String desc) {
        this.names = names;
        this.desc = desc;
       // this.desc2 = desc2;
        this.imageid = imageid;
    }


    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public String getDesc2() {
//        return desc2;
//    }

//    public void setDesc2(String desc2) {
//        this.desc2 = desc2;
//    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }


}
