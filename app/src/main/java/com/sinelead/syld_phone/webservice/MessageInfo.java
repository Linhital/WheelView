package com.sinelead.syld_phone.webservice;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MessageInfo {
    @POST("message")
    Call<String> getMessage(@Path("phone") String phone);
}
