package com.cviac.s4iApp.sfiapi;

/**
 * Created by john on 12/28/2016.
 */
/*
@Table(name = "MyProfileInfo")
*/

public class MyProfileInfo{

    private String MemPlan;
    private String MemType;
    private String FirstName;
    private String EmailID2;
    private String Mobile2;
    private String Gender;
    private String HouseNo;
    private String Town;
    private String State;
    private String PIN;
    private String CompNumber;
    private String CompTown;
    private String CompState;
    private String CompPIN;
    private String MemID;
    private String Image_url;

    public MyProfileInfo() {
    }


    public String getImage_url() {
        return Image_url;
    }

    public void setImage_url(String image_url) {
        Image_url = image_url;
    }

    public String getMemPlan() {
        return MemPlan;
    }

    public void setMemPlan(String memPlan) {
        MemPlan = memPlan;
    }

    public String getMemType() {
        return MemType;
    }

    public void setMemType(String memType) {
        MemType = memType;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmailID2() {
        return EmailID2;
    }

    public void setEmailID2(String emailID2) {
        EmailID2 = emailID2;
    }

    public String getMobile2() {
        return Mobile2;
    }

    public void setMobile2(String mobile2) {
        Mobile2 = mobile2;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHouseNo() {
        return HouseNo;
    }

    public void setHouseNo(String houseNo) {
        HouseNo = houseNo;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getCompNumber() {
        return CompNumber;
    }

    public void setCompNumber(String compNumber) {
        CompNumber = compNumber;
    }

    public String getCompTown() {
        return CompTown;
    }

    public void setCompTown(String compTown) {
        CompTown = compTown;
    }

    public String getCompState() {
        return CompState;
    }

    public void setCompState(String compState) {
        CompState = compState;
    }

    public String getCompPIN() {
        return CompPIN;
    }

    public void setCompPIN(String compPIN) {
        CompPIN = compPIN;
    }

    public String getMemID() {
        return MemID;
    }

    public void setMemID(String memID) {
        MemID = memID;
    }




  /*  public static void updateProfileImageUrl(String memId, String url) {
        new Update(MyProfileInfo.class)
                .set("imageUrl = ?", url)
                .where("MemID = ?", memId)
                .execute();
                return;
    }*/
}
