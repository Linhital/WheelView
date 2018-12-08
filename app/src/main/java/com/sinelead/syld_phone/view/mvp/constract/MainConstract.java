package com.sinelead.syld_phone.view.mvp.constract;


import com.sinelead.syld_phone.view.mvp.BasePresenter;
import com.sinelead.syld_phone.view.mvp.BaseView;

public interface MainConstract {
    interface View<Presenter> extends BaseView {
    }

    interface Presenter extends BasePresenter {
    }
}
