package com.sinelead.syld_phone.view.activity;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.mvp.BasePresenter;
import com.sinelead.syld_phone.view.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    private Unbinder unbinder;
    protected ViewStub stub;
    private View container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeLayout();
        setContentView(R.layout.activity_base);
        stub = findViewById(R.id.stub);
        container = findViewById(R.id.container);
        stub.setLayoutResource(getLayout());
        stub.inflate();
        unbinder = ButterKnife.bind(this);

        initView();
        initPresenter();
    }

    protected void beforeLayout() {
    }

    ;

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void requestPermissions(String permissions) {
        if (Build.VERSION.SDK_INT >= 23)
            if (ContextCompat.checkSelfPermission(this, permissions) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{permissions}, 2);
            }
    }


    public View getContentView() {
        return container;
    }

    @Override
    public void showSnackBar(String snack) {
        Snackbar.make(getContentView(), snack, Snackbar.LENGTH_SHORT).show();
    }

    protected void setFullScreenNoNavigation() {
        setFullScreenNoNavigation(Color.TRANSPARENT);
    }

    protected void setFullScreenNoNavigation(Integer color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }
}
