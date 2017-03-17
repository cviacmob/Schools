/*
package com.cviac.s4iApp.datamodel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "socialinfo")
public class SocialInfo extends Model {

    @Column(name = "channel")
    private String channel;
    @Column(name = "url")
    private String url;
    @Column(name = "social")
    private String social;
    @Column(name = "MemID")
    private String MemID;

    public SocialInfo() {
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }


    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
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

    public static List<SocialInfo> getsocial() {
        return new Select()
                .from(SocialInfo.class)
                .execute();

    }
}
*/
