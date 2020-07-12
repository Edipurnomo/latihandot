package com.example.splashlogin.API;

import com.example.splashlogin.model.LoginBody;
import com.example.splashlogin.model.LoginResult;
import com.example.splashlogin.model.SignUpBody;
import com.example.splashlogin.model.SignUpResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApiService {
    @POST("login")
    Call<LoginResult> getResultInfo (@Body LoginBody loginBody);


    @POST("api/user")
    Call<SignUpResult> SigupUser (@Body SignUpBody Body);

}



