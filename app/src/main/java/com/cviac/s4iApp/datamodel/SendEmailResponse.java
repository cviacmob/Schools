package com.cviac.s4iApp.datamodel;

/**
 * Created by john on 1/10/2017.
 */

public class SendEmailResponse {
    private int code;
    private String desc;

    public SendEmailResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
