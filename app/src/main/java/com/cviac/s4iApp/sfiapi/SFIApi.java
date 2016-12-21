package com.cviac.s4iApp.sfiapi;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;
/**
 * Created by john on 12/14/2016.
 */

public interface SFIApi {

    @POST("/S4I/S4Idbop.php/registration")
    Call<RegisterResponse> registerMobile(@Body RegInfo regInfo);

    @POST("/S4I/S4Idbop.php/otpreg")
    Call<VerifyResponse> verifyPin(@Body RegInfo regInfo);

///*    @GET("/CVIACAPI/cviacdbop.php")
//    Call<List<EmployeeInfo>> getEmployees();*/
//    @Multipart
//    @POST("/CVIACAPI/upload.php")
//    Call<ProfileUpdateResponse> profileUpdate(@Query("emp_code") String empcode,  @Part("fileToUpload\"; filename=\"pp.png\" ") RequestBody file);
//    //  Call<List<Employee>> getemployees();

}

