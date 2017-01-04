package com.cviac.s4iApp.sfiapi;

import com.activeandroid.annotation.Column;

/**
 * Created by john on 12/28/2016.
 */

public class MyProfileApi {
    @Column(name = "MemPeriod")
    private String MemPeriod;
    @Column(name = "MemType")
    private String MemType;
    @Column(name = "name")
    private String name;
    @Column(name = "EmailID")
    private String EmailID;
    @Column(name = "Mobile")
    private String Mobile;
    @Column(name = "gender")
    private String gender;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "state")
    private String state;
    @Column(name = "pin")
    private String pin;
    @Column(name = "Com_address1")
    private String Comp_address1;
    @Column(name = "Com_address2")
    private String Comp_address2;
    @Column(name = "Com_state")
    private String Comp_state;
    @Column(name = "Com_pin")
    private String Comp_pin;
    @Column(name = "MemId",index = true)
    private String MemID;


    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }




    public String getMemPeriod() {
        return MemPeriod;
    }

    public void setMemPeriod(String memPeriod) {
        MemPeriod = memPeriod;
    }

    public String getMemType() {
        return MemType;
    }

    public void setMemType(String memType) {
        MemType = memType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String emailID) {
        EmailID = emailID;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getComp_address1() {
        return Comp_address1;
    }

    public void setComp_address1(String comp_address1) {
        Comp_address1 = comp_address1;
    }

    public String getComp_address2() {
        return Comp_address2;
    }

    public void setComp_address2(String comp_address2) {
        Comp_address2 = comp_address2;
    }

    public String getComp_state() {
        return Comp_state;
    }

    public void setComp_state(String comp_state) {
        Comp_state = comp_state;
    }

    public String getComp_pin() {
        return Comp_pin;
    }

    public void setComp_pin(String comp_pin) {
        Comp_pin = comp_pin;
    }



}
