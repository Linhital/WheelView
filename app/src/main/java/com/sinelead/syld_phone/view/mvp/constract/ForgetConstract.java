package com.sinelead.syld_phone.view.mvp.constract;

import android.text.InputFilter;
import android.text.TextWatcher;

import com.sinelead.syld_phone.view.mvp.BasePresenter;
import com.sinelead.syld_phone.view.mvp.BaseView;

public interface ForgetConstract {
    interface Presenter extends BasePresenter {
        void getVerification();
    }

    interface View extends BaseView {
        void startTime();

        String getPhoneNumber();

        void setPhoneNumber(String phoneNumber);

        String getVerification();

        void setFilters(InputFilter[] filters);

        void startActivity(Class clazz);

        void setLoginEnable(boolean enable);

        void addTextChangeListener(TextWatcher watcher);

        void requestPermissions(String permissions);
    }
}
