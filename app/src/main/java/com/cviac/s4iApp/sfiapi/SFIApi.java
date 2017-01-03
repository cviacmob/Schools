package com.cviac.s4iApp.sfiapi;

import com.cviac.s4iApp.datamodel.Currentevent;
import com.cviac.s4iApp.datamodel.Event;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
/**
 * Created by john on 12/14/2016.
 */

public interface SFIApi {

    @POST("/S4I/S4Idbop.php/register")
    Call<RegisterResponse> registerMobile(@Body RegInfo regInfo);

    @POST("/S4I/S4Idbop.php/verifyotp")
    Call<VerifyResponse> verifyPin(@Body RegInfo regInfo);

    @POST("/S4I/S4Idbop.php/membership")
    Call<RegisterResponse> memberreg(@Body MembershipApi membershipApi);

    @POST("/S4I/S4Idbop.php/profile_update")
   Call<RegisterResponse> myprofilereg(@Body MyProfileApi myProfileApi);

    @GET("/S4I/S4Idbop.php/members/?")
    Call<RegisterResponse> myProfilereg(@Body MyProfileApi myprofileApi);

    @POST("/S4I/S4Idbop.php/contactus")
    Call<ContactApi> contatctreg(@Body ContactApi contactApi);

    @GET("/S4I/S4Idbop.php/Events/PAST")
    Call<List<Event>> getEvents();

    @GET("/S4I/S4Idbop.php/Events/Current")
    Call<List<Currentevent>> getCurrent();
}

