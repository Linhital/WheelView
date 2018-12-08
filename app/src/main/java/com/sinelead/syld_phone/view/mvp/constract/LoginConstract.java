package com.sinelead.syld_phone.view.mvp.constract;

import android.text.InputFilter;

import com.sinelead.syld_phone.view.mvp.BasePresenter;
import com.sinelead.syld_phone.view.mvp.BaseView;

import java.util.List;

public interface LoginConstract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView {
        void setSchoolAdapter(List<String> schools);

        int getSchoolPosition();

        void setSchoolPosition(int position);

        String getUser();

        void setUser(String user);

        String getPassword();

        void setPassword(String password);

        void setFilters(InputFilter[] filters);

        void startActivity(Class clazz);

        void finish();
    }
}
