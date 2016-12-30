package com.cviac.s4iApp.sfiapi;

import retrofit.Call;
import retrofit.http.Body;
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

    @POST("/S4I/S4Idbop.php/update_profile")
   Call<MyProfileApi> myprofilereg(@Body MyProfileApi myProfileApi);

    @POST("/S4I/S4Idbop.php/contactus")
    Call<ContactApi> contatctreg(@Body ContactApi contactApi);



}

