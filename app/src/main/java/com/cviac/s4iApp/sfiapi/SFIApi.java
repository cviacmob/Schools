package com.cviac.s4iApp.sfiapi;

import com.cviac.s4iApp.datamodel.ContactInfo;
import com.cviac.s4iApp.datamodel.Currentevent;
import com.cviac.s4iApp.datamodel.EmailInfo;
import com.cviac.s4iApp.datamodel.Event;
import com.cviac.s4iApp.datamodel.MemberFeeInfo;
import com.cviac.s4iApp.datamodel.ProfilePicUpload;
import com.cviac.s4iApp.datamodel.SendEmailResponse;
import com.squareup.okhttp.RequestBody;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by john on 12/14/2016.
 */

public interface SFIApi {

    @POST("/S4IAPI/S4Idbop.php/register")
    Call<RegisterResponse> registerMobile(@Body RegInfo regInfo);

    @POST("/S4IAPI/S4Idbop.php/verifyotp")
    Call<VerifyResponse> verifyPin(@Body RegInfo regInfo);

    @POST("/S4IAPI/S4Idbop.php/membership")
    Call<RegisterResponse> memberreg(@Body MembershipInfo membershipApi);

    @POST("/S4IAPI/S4Idbop.php/profile_update")
    Call<MyProfileInfo> updateProfile(@Body MyProfileInfo myProfileApi);

    @GET("/S4IAPI/S4Idbop.php/members/{MemID}")
    Call<List<MyProfileInfo>> getmyprofile(@Path("MemID") String MemID);

    @POST("/S4IAPI/S4Idbop.php/contactus")
    Call<ContactInfo> contatctreg(@Body ContactInfo contactApi);

    @GET("/S4IAPI/S4Idbop.php/Events/PAST")
    Call<List<Event>> getEvents();

    @GET("/S4IAPI/S4Idbop.php/Events/Current")
    Call<List<Currentevent>> getCurrent();

    @GET("/S4IAPI/S4Idbop.php/membershipfeeinfo")
    Call<List<MemberFeeInfo>> getfeeinfo();

    @Multipart
    @POST("/S4IAPI/upload.php")
    Call<ProfilePicUpload> profileUpdate(@Query("MemID") String memId, @Part("fileToUpload\"; filename=\"pp.png\" ") RequestBody file);

    @POST("/CVIACAPI/cviacdbop.php/sendemail")
    Call<SendEmailResponse> sendEmail(@Body EmailInfo emailinfo);


}

