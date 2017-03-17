package com.cviac.s4iApp.datamodel;

public class Profile {

    public String Name;
    public String Email;
    public String Mobile;
    public String Gender;

    public String Address1;
    public String Address2;
    public String CityState;
    public String ZipCode;

    public String OAddress1;
    public String OAddress2;
    public String CityState2;
    public String OZipcode;

    public String MembershipType;
    public String MembershipPeriod;
    public String Doj;

    private String Facebook;
    private String Blog;
    private String Linkedin;
    private String Flickr;
    private String Twitter;

    public Profile() {
    }

    public Profile(String name, String email, String mobile) {
        Email = email;
        Name = name;
        Mobile = mobile;
    }


    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getBlog() {
        return Blog;
    }

    public void setBlog(String blog) {
        Blog = blog;
    }

    public String getLinkedin() {
        return Linkedin;
    }

    public void setLinkedin(String linkedin) {
        Linkedin = linkedin;
    }

    public String getFlickr() {
        return Flickr;
    }

    public void setFlickr(String flickr) {
        Flickr = flickr;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }



    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getCityState() {
        return CityState;
    }

    public void setCityState(String cityState) {
        CityState = cityState;
    }

    public String getOZipcode() {
        return OZipcode;
    }

    public void setOZipcode(String OZipcode) {
        this.OZipcode = OZipcode;
    }


    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getOAddress1() {
        return OAddress1;
    }

    public void setOAddress1(String OAddress1) {
        this.OAddress1 = OAddress1;
    }

    public String getOAddress2() {
        return OAddress2;
    }

    public void setOAddress2(String OAddress2) {
        this.OAddress2 = OAddress2;
    }

    public String getCityState2() {
        return CityState2;
    }

    public void setCityState2(String cityState2) {
        CityState2 = cityState2;
    }

//	public String getZipcode() {
//		return OZipcode;
//	}
//
//	public void setZipcode(String zipcode) {
//		OZipcode = zipcode;
//	}
//

    public String getMembershipType() {
        return MembershipType;
    }

    public void setMembershipType(String membershipType) {
        MembershipType = membershipType;
    }

    public String getMembershipPeriod() {
        return MembershipPeriod;
    }

    public void setMembershipPeriod(String membershipPeriod) {
        MembershipPeriod = membershipPeriod;
    }

    public String getDoj() {
        return Doj;
    }

    public void setDoj(String doj) {
        Doj = doj;
    }


}
