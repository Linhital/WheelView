package com.sinelead.syld_phone.view.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.BroadcastReceiver.SMSBroadcastReceiver;
import com.sinelead.syld_phone.view.mvp.constract.ForgetConstract;
import com.sinelead.syld_phone.view.mvp.presenter.ForgetPresenter;
import com.sinelead.syld_phone.view.utils.FontCustom;
import com.sinelead.syld_phone.view.utils.TimerTextView;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity implements ForgetConstract.View {

    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.verification_get)
    protected TextView verificationGet;
    @BindView(R.id.verification)
    protected EditText verification;
    @BindView(R.id.phone)
    protected EditText phone;
    @BindView(R.id.login)
    protected Button login;

    private TimerTextView timer;
    private ForgetPresenter presenter;
    private SMSBroadcastReceiver receiver;

    @Override
    protected void initPresenter() {
        /*注册短信获取的广播*/
        receiver = new SMSBroadcastReceiver(verification);
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(receiver, filter);

        timer = new TimerTextView(verificationGet, getResources().getString(R.string.verification_get), 60);
    }

    @Override
    protected void initView() {
        presenter = new ForgetPresenter(this);
        title.setTypeface(FontCustom.setFont(this, "ITCEDSCR.TTF"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @OnClick(R.id.back)
    protected void onBack() {
        finish();
    }

    /**
     * 点击获取验证码，并进入一分钟的倒计时
     */
    @OnClick(R.id.verification_get)
    protected void onTime() {
        presenter.getVerification();
    }

    /**
     * 点击登录按钮，如果登录成功，将以前建立的所有的activity销毁
     */
    @OnClick(R.id.login)
    protected void onLogin() {
        presenter.login();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget;
    }

    /**
     * 开始倒计时
     */
    @Override
    public void startTime() {
        timer.start();
    }

    @Override
    public String getPhoneNumber() {
        return phone.getText().toString();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        phone.setText(phoneNumber);
    }

    @Override
    public String getVerification() {
        return verification.getText().toString();
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        phone.setFilters(filters);
    }

    @Override
    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void setLoginEnable(boolean enable) {
        login.setEnabled(enable);
    }

    @Override
    public void addTextChangeListener(TextWatcher watcher) {
        verification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (verification.getText().toString().length() > 0)
                    login.setEnabled(true);
                else
                    login.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
