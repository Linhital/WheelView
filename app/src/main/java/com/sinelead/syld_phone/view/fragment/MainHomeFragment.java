package com.sinelead.syld_phone.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sinelead.syld_phone.R;
import com.sinelead.syld_phone.bean.Menu;
import com.sinelead.syld_phone.view.adapter.HomeMenuAdapter;
import com.view.wheelview.WheelData;
import com.view.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainHomeFragment extends BaseFragment {
    private static MainHomeFragment fragment;

    @BindView(R.id.cycle)
    protected WheelView wheelView;
    @BindView(R.id.modules)
    protected RecyclerView moudles;
    protected Toolbar toolbar;
    protected TextView title;
    private HomeMenuAdapter adapter;
    protected List<WheelData> mList = new ArrayList<>();
    private List<Menu> menus = new ArrayList<>();

    public static Fragment newInstance() {
        return newInstance(null);
    }

    public static Fragment newInstance(Bundle bundle) {
        if (fragment == null) {
            fragment = new MainHomeFragment();
        }
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        mList.add(new WheelData("https://ww3.sinaimg.cn/large/610dc034jw1fasakfvqe1j20u00mhgn2.jpg", "标题1"));
        mList.add(new WheelData("s", "标题2"));
        mList.add(new WheelData("https://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg", "标题3"));
        mList.add(new WheelData("https://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg", "标题4"));
        toolbar = view.findViewById(R.id.toolbar);
        title = view.findViewById(R.id.title);
        title.setText(getResources().getText(R.string.navigation_home));
        wheelView.setDEBUG(true);
        wheelView.setData(mList);
        Menu menu = new Menu();
        menu.setDrawableID(R.drawable.news);
        menu.setTitle("新闻动态");
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        menus.add(menu);
        wheelView.setOnPageClickListener(new WheelView.OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {

            }
        });
        adapter = new HomeMenuAdapter(menus);
        moudles.setAdapter(adapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main_home;
    }


}
