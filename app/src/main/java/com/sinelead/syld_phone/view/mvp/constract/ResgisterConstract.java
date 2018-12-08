package com.sinelead.syld_phone.view.mvp.constract;

import android.text.InputFilter;

import com.sinelead.syld_phone.view.mvp.BasePresenter;
import com.sinelead.syld_phone.view.mvp.BaseView;

public interface ResgisterConstract {
    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView {
        void startActivity(Class clazz);

        void setFilters(InputFilter[] filters);

        String getUser();

        String getPassword();

        String getPasswordConfirm();


    }
}
