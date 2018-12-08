package com.sinelead.syld_phone.view.mvp.presenter;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import com.sinelead.syld_phone.view.activity.MainActivity;
import com.sinelead.syld_phone.view.mvp.constract.LoginConstract;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter implements LoginConstract.Presenter, InputFilter {
    private List<String> schools;
    private LoginConstract.View view;

    public LoginPresenter(LoginConstract.View view) {
        this.view = view;
        initData();
    }

    /**
     * 初始化登录页的信息，加载学校、实现找好的过滤
     */
    @Override
    public void initData() {
        schools = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            schools.add("山东大学" + i);

        view.setSchoolAdapter(schools);
        view.setFilters(new InputFilter[]{this});
    }

    /**
     * 判断账号、密码是否为空，如果为空则提示错误，如果不为空则登录成功
     */
    public void startActivity() {
        if (TextUtils.isEmpty(view.getUser())) {
            view.showSnackBar("请输入账号");
            return;
        }

        if (TextUtils.isEmpty(view.getPassword())) {
            view.showSnackBar("请输入密码");
            return;
        }
        view.startActivity(MainActivity.class);
        view.finish();
//        RequestRetrofit retrofit = new RequestRetrofit();
//        retrofit.setOnResultListener(new RequestRetrofit.OnResultListener() {
//            @Override
//            public void onSuccess() {
//                view.startActivity(MainActivity.class);
//                view.finish();
//            }
//
//            @Override
//            public void onFailed(int code) {
//                view.showSnackBar("账号或密码错误");
//            }
//
//            @Override
//            public void onError() {
//                view.showSnackBar("服务连接失败");
//            }
//        }).onLogin(schools.get(view.getSchoolPosition()), view.getUser(), view.getPassword());

    }

    public void inspect() {

    }

    /**
     * 账号字符的过滤器，主要过滤掉空格
     *
     * @param source
     * @param start
     * @param end
     * @param dest
     * @param dstart
     * @param dend
     * @return
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (source.equals(" ")) {
            view.showSnackBar("账号中不能包含空格");
            return "";
        } else {
            return null;
        }
    }
}
