package com.sinelead.syld_phone.view.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.widget.TextView;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.mvp.constract.ResgisterConstract;
import com.sinelead.syld_phone.view.mvp.presenter.ResgisterPresenter;
import com.sinelead.syld_phone.view.utils.FontCustom;

import butterknife.BindView;
import butterknife.OnClick;

public class ResgisterActivity extends BaseActivity implements ResgisterConstract.View {

    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.user)
    protected AppCompatEditText user;
    @BindView(R.id.pwd)
    protected AppCompatEditText pwd;
    @BindView(R.id.pwd_again)
    protected AppCompatEditText pwdConfirm;

    protected ResgisterPresenter presenter;

    @Override
    protected void initPresenter() {
        presenter = new ResgisterPresenter(this);
    }

    @Override
    protected void initView() {
        title.setTypeface(FontCustom.setFont(this, "ITCEDSCR.TTF"));
    }

    @OnClick(R.id.resgister)
    protected void onResgister() {
        presenter.resgister();
    }

    @OnClick(R.id.back)
    protected void onBack() {
        finish();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_resgister;
    }

    @Override
    public void startActivity(Class clazz) {
        Intent intentmain = new Intent(this, clazz).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentmain);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        user.setFilters(filters);
    }

    @Override
    public String getUser() {
        return user.getText().toString();
    }

    @Override
    public String getPassword() {
        return pwd.getText().toString();
    }

    @Override
    public String getPasswordConfirm() {
        return pwdConfirm.getText().toString();
    }
}
