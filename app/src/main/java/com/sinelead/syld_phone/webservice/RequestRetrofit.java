package com.sinelead.syld_phone.webservice;


import com.sinelead.syld_phone.bean.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestRetrofit {
    public static final String baseurl = "http://www.baidu.com/";
    private OnResultListener listener;

    public static Retrofit onConnect() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public RequestRetrofit setOnResultListener(OnResultListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 登录网络请求
     *
     * @param params
     */
    public void onLogin(String... params) {
        Retrofit retrofit = onConnect();
        UserInfo userInfo = retrofit.create(UserInfo.class);
        Call<User> call = null;
        switch (params.length) {
            case 1:
                break;
            case 2:
                call = userInfo.getUser(params[0], params[1]);
                break;
            case 3:
                call = userInfo.getUser(params[0], params[1], params[2]);
                break;
        }

        if (call != null) {
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() == null)
                        listener.onFailed(0);
                    else
                        listener.onSuccess();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    listener.onError();
                }
            });
        }
    }

    /**
     * 获取验证码
     *
     * @param phone
     */
    public void onVerification(String phone) {
        Retrofit retrofit = onConnect();
        MessageInfo messageInfo = retrofit.create(MessageInfo.class);
        Call<String> call = messageInfo.getMessage(phone);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() == null)
                    listener.onFailed(0);
                else
                    listener.onSuccess();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onError();
            }
        });
    }

    /**
     * 用户注册
     *
     * @param user
     * @param pwd
     */
    public void onResgister(String user, String pwd) {
        Retrofit retrofit = onConnect();
        UserInfo userInfo = retrofit.create(UserInfo.class);
        Call<User> call = userInfo.resgister(user, pwd);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null)
                    listener.onFailed(0);
                else
                    listener.onSuccess();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                listener.onError();
            }
        });
    }


    public interface OnResultListener {
        void onSuccess();

        void onFailed(int code);

        void onError();
    }
}
