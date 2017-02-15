package com.cviac.s4iApp.datamodel;

public class SocialInfo {

    private String channel;
    private String url;
    private String social;
    private String MemId;

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getMemId() {
        return MemId;
    }

    public void setMemId(String memId) {
        MemId = memId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
