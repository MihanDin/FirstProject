package com.samsung.mainwithstrahgedesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.db.MyConstants;

public class LeftFragment extends Fragment {
private ImageFilterButton left_main,right,right_main;
private SharedPreferences pref;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, null, false);
        left_main= view.findViewById(R.id.runnerbtn);
        right_main= view.findViewById(R.id.energybtn);
        right= view.findViewById(R.id.weightbtn);
        TextView left_tv=view.findViewById(R.id.left_tv);
        pref = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        left_tv.setText("Высота прыжка: "+"\n "+String.valueOf(pref.getInt(MyConstants.JUMP_NUMBER,71))+" см");
        left_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftMainFragment()).commit();
            }
        });
        right_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new RightMainFragment()).commit();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new RightFragment()).commit();
            }
        });
        return view;


    }
}