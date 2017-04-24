package com.ben.androiddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ben.androiddemo.R;
import com.ben.androiddemo.fragment.base.BaseFragment;

/**
 * Created on 17/4/11.
 *
 * @author Ben
 */


public class ItemFragment extends BaseFragment {
    public static final String ITEM_NUMBER = "ITEM_NUMBER";
    private int mItem_number;


    public static ItemFragment newInstance(int item) {
        Bundle bundle = new Bundle();
        bundle.putInt(ITEM_NUMBER, item);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItem_number = getArguments().getInt(ITEM_NUMBER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText("Item #" + mItem_number + "");
        return view;
    }
}
