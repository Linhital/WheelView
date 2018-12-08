package com.sinelead.syld_phone.view.activity;


import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.adapter.SpinnerAdapter;
import com.sinelead.syld_phone.view.mvp.constract.LoginConstract;
import com.sinelead.syld_phone.view.mvp.presenter.LoginPresenter;
import com.sinelead.syld_phone.view.utils.FontCustom;
import com.sinelead.syld_phone.view.wigdet.ClearEditText;
import com.sinelead.syld_phone.view.wigdet.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener, ClearEditText.BackProcessListener,
        LoginConstract.View {

    @BindView(R.id.app_name)
    protected TextView name;
    @BindView(R.id.wechat)
    protected ImageView weChat;
    @BindView(R.id.user_name)
    protected ClearEditText user;
    @BindView(R.id.password)
    protected ClearEditText password;
    @BindView(R.id.school)
    protected Spinner school;

    private LoginPresenter presenter;

    @Override
    protected void beforeLayout() {
        super.beforeLayout();
        setFullScreenNoNavigation();
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        getContentView().setBackground(ContextCompat.getDrawable(this, R.drawable.bg_login));
        name.setTypeface(FontCustom.setFont(this, "FZSTK.TTF"));
        user.setOnEditorActionListener(this);
        password.setOnEditorActionListener(this);
        user.setProcess(this);
        password.setProcess(this);
        View view = (View) name.getParent();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                school.setFocus(true);
            }
        });
    }

    /**
     * 登录跳转，如果成功则跳转到主页面
     */
    @OnClick(R.id.login)
    protected void skipMainActivity() {
        presenter.startActivity();
        startActivity(MainActivity.class);
    }

    /**
     * 忘记密码跳转，跳转到手机号码登录界面
     */
    @OnClick(R.id.forget)
    protected void skipForgetActivity() {
        startActivity(new Intent(this, ForgetActivity.class));
    }

    /**
     * 注册跳转，跳转到注册界面
     */
    @OnClick(R.id.resgister)
    protected void onResgister() {
        startActivity(new Intent(this, ResgisterActivity.class));
    }

    /**
     * 获取界面的布局
     *
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * 软键盘点击完成时，Spinner获取焦点，目的是使EditText输入框失去焦点
     *
     * @param v
     * @param actionId
     * @param event
     * @return
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId) {
            case EditorInfo.IME_ACTION_DONE:
                school.setFocus(true);
                break;
        }
        return false;
    }

    /**
     * 返回键隐藏键盘时，Spinner获取焦点，目的是使EditText输入框失去焦点
     */
    @Override
    public void hideDown() {
        school.setFocus(true);
    }

    @Override
    public void setSchoolAdapter(List<String> schools) {
        SpinnerAdapter<String> adapter = new SpinnerAdapter<>(schools);
        school.setAdapter(adapter);
    }

    @Override
    public int getSchoolPosition() {
        return school.getCurrentPosition();
    }

    @Override
    public void setSchoolPosition(int position) {
        school.setCurrentPosition(position);
    }

    @Override
    public String getUser() {
        return user.getText().toString();
    }

    @Override
    public void setUser(String users) {
        user.setText(users);
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void setPassword(String pwd) {
        password.setText(pwd);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        user.setFilters(filters);
    }

    @Override
    public void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
}
