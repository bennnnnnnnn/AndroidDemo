package com.ben.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ben.androiddemo.R;
import com.ben.androiddemo.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 17/4/12.
 *
 * @author Ben
 */


public class TextLayoutActivity extends BaseActivity {
    @BindView(R.id.pets_edittext)
    EditText mPetsText;
    @BindView(R.id.hobby_edittext)
    EditText mHobbyText;
    @BindView(R.id.phone_edittext)
    EditText mPhoneText;
    @BindView(R.id.account_edittext)
    EditText mAcountText;
    @BindView(R.id.phone_layout)
    TextInputLayout mPhoneLayout;
    @BindView(R.id.account_layout)
    TextInputLayout mAccountLayout;
    @BindView(R.id.multiple_textview)
    AutoCompleteTextView mMultipleText;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_text_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initMultipleText();

        // 取消错误提示
        mPhoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mPhoneLayout.getError() != null) {
                    mPhoneLayout.setError(null);
                }
            }
        });

        mAcountText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    Toast.makeText(TextLayoutActivity.this, "下一步就绪", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

    }

    public void clickOk(View view) {
        if (mPhoneText.getText().toString().length() != mPhoneLayout.getCounterMaxLength()) {
            mPhoneLayout.setError("error");
        }
    }

    private void initMultipleText() {
        String[] strings = {"aaa111", "aaa222", "aaa333", "aaa444"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        mMultipleText.setAdapter(arrayAdapter); //两个字符及以上会提示
    }

}
