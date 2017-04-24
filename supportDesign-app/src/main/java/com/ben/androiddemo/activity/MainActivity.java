package com.ben.androiddemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ben.androiddemo.R;
import com.ben.androiddemo.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.drawer_navigation_demo)
    Button mDrawerNavigationButton;
    @BindView(R.id.tabLayout_demo)
    Button mTabLayoutButton;
    @BindView(R.id.collapsingToolbarLayout_demo)
    Button mCollapsingToolbarLayoutButton;
    @BindView(R.id.textLayout_demo)
    Button mTextLayoutButton;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mActionbar.setDisplayHomeAsUpEnabled(false);
        mDrawerNavigationButton.setOnClickListener(this);
        mTabLayoutButton.setOnClickListener(this);
        mCollapsingToolbarLayoutButton.setOnClickListener(this);
        mTextLayoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drawer_navigation_demo:
                startActivity(new Intent(this, DrawerNavigationActivity.class));
                break;
            case R.id.tabLayout_demo:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.collapsingToolbarLayout_demo:
                startActivity(new Intent(this, CollapsingToolbarLayoutActivity.class));
                break;
            case R.id.textLayout_demo:
                startActivity(new Intent(this, TextLayoutActivity.class));
                break;
        }
    }
}
