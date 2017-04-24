package com.ben.androiddemo.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ben.androiddemo.R;
import com.ben.androiddemo.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 17/4/11.
 *
 * @author Ben
 */


public class DrawerNavigationActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_drawerlayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;
    Button button;

    private ActionBarDrawerToggle mDrawerToggle;
    private View headerView;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_drawer_navigation_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initNavigationView();
        initNaviHeaderView();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (null != mDrawerToggle) mDrawerToggle.syncState();
    }

    private void initNavigationView() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_view_open, R.string.navigation_view_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (mDrawerToggle != null) {
                    mDrawerToggle.onDrawerOpened(drawerView);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                if (mDrawerToggle != null) {
                    mDrawerToggle.onDrawerClosed(drawerView);
                }
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (mDrawerToggle != null) {
                    mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                if (mDrawerToggle != null) {
                    mDrawerToggle.onDrawerStateChanged(newState);
                }
            }
        });

        // 图标默认颜色
//        mNavigationView.setItemIconTintList(null);

        // Scrollbar 不显示
        disAbleNaviScrollBar();

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    private void disAbleNaviScrollBar() {
        if (mNavigationView != null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) mNavigationView.getChildAt(0);
            if (navigationMenuView != null) {
                navigationMenuView.setVerticalScrollBarEnabled(false);
            }
        }
    }

    private void initNaviHeaderView() {
        headerView = mNavigationView.getHeaderView(0);
        button = (Button) headerView.findViewById(R.id.header_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(DrawerNavigationActivity.this, "Clicked HeaderView!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_0:
                View view = MenuItemCompat.getActionView(item);
                TextView textView = (TextView) view.findViewById(R.id.define_tv);
                textView.setText("Hello World!");
                break;
            case R.id.item_1:
                Snackbar.make(mDrawerLayout, "click item_1", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.item_2:
                Snackbar.make(mDrawerLayout, "click item_2", Snackbar.LENGTH_LONG)
                        .setAction("this is item_2", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DrawerNavigationActivity.this, "clicked item_2 action", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
            case R.id.item_3:
                break;
            case R.id.item_4:
                break;
            case R.id.item_5:
                break;
            case R.id.item_6:
                break;
            case R.id.item_7:
                break;
            case R.id.item_8:
                break;
        }
        return true;
    }
}
