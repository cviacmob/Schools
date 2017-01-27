package com.cviac.s4iApp.datamodel;

/**
 * Created by john on 1/5/2017.
 */

public class ProfilePicUpload {
    private int code;
    private String desc;
    private String Image_url;
    private String MemID;

    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }


    public ProfilePicUpload() {
    }

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
