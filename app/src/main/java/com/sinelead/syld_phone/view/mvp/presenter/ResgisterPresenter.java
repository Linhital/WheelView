package com.sinelead.syld_phone.view.mvp.presenter;


import android.text.InputFilter;
import android.text.Spanned;

import com.sinelead.syld_phone.view.activity.MainActivity;
import com.sinelead.syld_phone.view.mvp.constract.ResgisterConstract;

public class ResgisterPresenter implements ResgisterConstract.Presenter {
    private ResgisterConstract.View view;

    public ResgisterPresenter(ResgisterConstract.View view) {
        this.view = view;
        initData();
    }

    @Override
    public void initData() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.toString().equals(" ")) {
                    return "";
                }
                return null;
            }
        };

        view.setFilters(new InputFilter[]{filter});
    }

    public void resgister() {
        String user = view.getUser();
        String pwd = view.getPassword();
        String pwdConfirm = view.getPasswordConfirm();

        if (user.length() == 0) {
            view.showSnackBar("请输入账号");
            return;
        }

        if (pwd.length() == 0) {
            view.showSnackBar("请输入密码");
            return;
        }

        if (pwdConfirm.length() == 0) {
            view.showSnackBar("请输入确认密码");
            return;
        }

        if (!pwd.equals(pwdConfirm)) {
            view.showSnackBar("确认密码输入错误");
            return;
        }

//        RequestRetrofit retrofit = new RequestRetrofit();
//        retrofit.setOnResultListener(new RequestRetrofit.OnResultListener() {
//            @Override
//            public void onSuccess() {
//                view.startActivity(MainActivity.class);
//            }
//
//            @Override
//            public void onFailed(int code) {
//
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        }).onResgister(view.getUser(), view.getPassword());
        view.startActivity(MainActivity.class);
    }
}
