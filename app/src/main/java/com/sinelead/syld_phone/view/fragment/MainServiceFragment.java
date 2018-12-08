package com.sinelead.syld_phone.view.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

public class MainServiceFragment extends BaseFragment {
    private static Fragment fragment;

    public static Fragment newInstance() {
        fragment = new Fragment();
        return fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getLayout() {
        return 0;
    }
}
