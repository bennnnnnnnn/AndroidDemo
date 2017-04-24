package com.ben.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.ben.androiddemo.R;
import com.ben.androiddemo.activity.base.BaseActivity;
import com.ben.androiddemo.adapet.ItemsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 17/4/11.
 *
 * @author Ben
 */


public class TabLayoutActivity extends BaseActivity {

    @BindView(R.id.main_viewpager)
    ViewPager mViewpager;
    @BindView(R.id.main_tablayout)
    TabLayout mTabLayout;

    private ItemsPagerAdapter mAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_tablayout_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initViewpager();
    }

    private void initViewpager() {
        mAdapter = new ItemsPagerAdapter(getSupportFragmentManager(), this);
        mViewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED); // item 多时 MODE_SCROLLABLE, 少时 MODE_FIXED

//        // 自定义 tab添加view  getPageTitle return null  新写getTabView
//        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = mTabLayout.getTabAt(i);
//            if (null != tab) {
//                tab.setCustomView(mAdapter.getTabView(i));
//            }
//        }
    }
}
