package com.ben.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ben.androiddemo.R;
import com.ben.androiddemo.activity.base.BaseActivity;

/**
 * Created on 17/4/12.
 *
 * @author Ben
 */


public class CollapsingToolbarLayoutActivity extends BaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_collapsing_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionbar.setTitle("CollapsingToolbarLayout Demo");
    }
}
