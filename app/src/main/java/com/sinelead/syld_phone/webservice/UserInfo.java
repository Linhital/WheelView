package com.sinelead.syld_phone.webservice;

import com.sinelead.syld_phone.bean.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInfo {
    @POST("login")
    Call<User> getUser(@Path("school") String school, @Path("name") String name, @Path("pwd") String pwd);

    @POST("login")
    Call<User> getUser(@Path("phone") String phone, @Path("verification") String verification);

    @POST("resgister")
    Call<User> resgister(@Path("user") String user, @Path("password") String pwd);
}
