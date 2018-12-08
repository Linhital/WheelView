package com.sinelead.syld_phone.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.view.adapter.MainPagerAdapter;
import com.sinelead.syld_phone.view.fragment.MainHomeFragment;
import com.sinelead.syld_phone.view.fragment.MainServiceFragment;
import com.sinelead.syld_phone.view.mvp.presenter.MainPresenter;
import com.sinelead.syld_phone.view.wigdet.SlideViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.navigation)
    BottomNavigationView navigationView;
    @BindView(R.id.content)
    SlideViewPager content;

    private List<Fragment> fragments;
    private MainPagerAdapter adapter;

    @Override
    protected void beforeLayout() {
        setFullScreenNoNavigation(ContextCompat.getColor(this, R.color.colorPrimary));
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        if (fragments == null)
            fragments = new ArrayList<>();
        if (fragments.size() == 0) {
            Fragment homeFragment = MainHomeFragment.newInstance();
            Fragment serviceFragment = MainServiceFragment.newInstance();
            Fragment personalFragment;
            fragments.add(homeFragment);
            fragments.add(serviceFragment);
            adapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
            content.setAdapter(adapter);
        }

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                content.setCurrentItem(0);
                return true;
            case R.id.navigation_service:
                content.setCurrentItem(1);
                return true;
            case R.id.navigation_personal:
                return true;
        }
        return false;
    }
}
