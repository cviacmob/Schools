package com.cviac.s4iApp.datamodel;

import java.util.Date;

/**
 * Created by john on 12/14/2016.
 */

public class RegInfo {

    private String FirstName;
    private String EmailID1;
    private String Mobile1;
    private String Gender;
    private String DOB;
    private String Country;
    private String otp;
    private Date StartDate;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmailID1() {
        return EmailID1;
    }

    public void setEmailID1(String emailID1) {
        EmailID1 = emailID1;
    }

    public String getMobile1() {
        return Mobile1;
    }

    public void setMobile1(String mobile1) {
        Mobile1 = mobile1;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }


}