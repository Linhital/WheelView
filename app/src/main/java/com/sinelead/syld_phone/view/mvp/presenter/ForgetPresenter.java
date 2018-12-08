package com.sinelead.syld_phone.view.mvp.presenter;

import android.Manifest;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;

import com.sinelead.syld_phone.view.activity.MainActivity;
import com.sinelead.syld_phone.view.mvp.constract.ForgetConstract;
import com.sinelead.syld_phone.view.utils.DecimalUtil;

public class ForgetPresenter implements ForgetConstract.Presenter {
    private ForgetConstract.View view;


    public ForgetPresenter(ForgetConstract.View view) {
        this.view = view;
        initData();
    }

    @Override
    public void initData() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (view.getPhoneNumber().length() >= 11)
                    return "";
                try {
                    Integer.parseInt(source.toString());
                } catch (Exception e) {
                    return "";
                }
                return null;
            }
        };
        view.setFilters(new InputFilter[]{filter});

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String verification = view.getVerification();
                if (verification.length() > 0) {
                    view.setLoginEnable(true);
                } else {
                    view.setLoginEnable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        view.addTextChangeListener(watcher);

    }

    @Override
    public void getVerification() {
        String phone = view.getPhoneNumber();
        if (DecimalUtil.isChinaPhoneLegal(phone)) {
            view.requestPermissions(Manifest.permission.RECEIVE_SMS);
            /*RequestRetrofit retrofit = new RequestRetrofit();
            retrofit.setOnResultListener(new RequestRetrofit.OnResultListener() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onFailed(int code) {

                }

                @Override
                public void onError() {

                }
            }).onVerification(phone);*/

            view.startTime();
        } else {
            view.showSnackBar("手机号码错误，请重新输入");
        }
    }

    public void login() {
        String phone = view.getPhoneNumber();
        if (phone.trim().length() == 0) {
            view.showSnackBar("请输入手机号码");
            return;
        }
        if (DecimalUtil.isChinaPhoneLegal(phone)) {
            view.startActivity(MainActivity.class);
        } else {
            view.showSnackBar("请输入正确的手机号码");
        }
    }
}
