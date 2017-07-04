package com.ben.utiltest_app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ben.utiltest_app.R;
import com.ben.utiltest_app.utils.DataStores;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 17/7/4.
 *
 * @author Ben
 */


public class DataStoreActivity extends BaseActivity {

    @BindView(R.id.input_edt)
    EditText mInputEditText;
    @BindView(R.id.encrypt_tv)
    TextView mEncryptTextView;
    @BindView(R.id.decrypt_tv)
    TextView mDecryptTextView;
    @BindView(R.id.encrypt_btn)
    Button mEncryptButton;
    @BindView(R.id.decrypt_btn)
    Button mDecryptButton;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_data_store_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mEncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInputEditText.getText().toString();
                String encrypt = DataStores.encrypt(DataStoreActivity.this, input);
                mEncryptTextView.setText(encrypt);
            }
        });

        mDecryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encrypt = mEncryptTextView.getText().toString();
                String decrypt = DataStores.decrypt(DataStoreActivity.this, encrypt);
                mDecryptTextView.setText(decrypt);
            }
        });
    }
}
