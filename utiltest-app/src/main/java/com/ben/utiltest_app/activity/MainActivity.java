package com.ben.utiltest_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ben.utiltest_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.data_store_demo)
    Button mDataStoreButton;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mActionbar.setDisplayHomeAsUpEnabled(false);
        mDataStoreButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_store_demo:
                startActivity(new Intent(this, DataStoreActivity.class));
                break;
        }
    }
}
