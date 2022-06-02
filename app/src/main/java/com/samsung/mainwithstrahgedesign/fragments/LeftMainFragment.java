package com.samsung.mainwithstrahgedesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.db.MyConstants;

public class LeftMainFragment extends Fragment {
    private ImageFilterButton left,right,right_main;
    private SharedPreferences pref;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left_main, null, false);
        left = view.findViewById(R.id.rulerbtn);
        right_main= view.findViewById(R.id.energybtn);
        right= view.findViewById(R.id.weightbtn);
        pref = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        TextView left_main_tv=view.findViewById(R.id.left_main_tv);
        String formattedDouble = String.format("%.1f", Math.sqrt(pref.getInt(MyConstants.JUMP_NUMBER,71)*0.01*2*10));
        left_main_tv.setText(" Начальная скорость: "+"\n "+formattedDouble+"м/с");
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftFragment()).commit();
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