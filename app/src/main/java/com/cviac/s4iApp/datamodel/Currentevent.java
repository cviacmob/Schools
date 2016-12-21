package com.cviac.s4iApp.datamodel;


public class Currentevent {
    private String sports;

    private String name;

    private String sports2;

    private String place;

    private  int ImageURL;

    public Currentevent() {
        // TODO Auto-generated constructor stub
    }

    public String getSports2() {
        return sports2;
    }

    public String getSports() {
        return sports;
    }

    public  String getPlace() {return place;}

    public int getImageURL() {  return ImageURL;    }

    public String getName() {
        return name;
    }



    public void setSports2(String sports2) {
        this.sports2 = sports2;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {this.place =place;}

    public void setImageURL(int ImageURL) {
        this.ImageURL = ImageURL;
    }

}
