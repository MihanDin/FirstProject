package com.samsung.mainwithstrahgedesign.fragments;

import static com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap.jumpi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.Jump;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.JumpAdapter;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap;
import com.samsung.mainwithstrahgedesign.db.MyConstants;
import com.samsung.mainwithstrahgedesign.db.MyDbManager;
import com.samsung.mainwithstrahgedesign.db.Obrabotchik;

import java.util.ArrayList;
import java.util.List;

public class FragmentTrainings extends Fragment {
    private RecyclerView myRecycle;
    private JumpAdapter jumpAdapter;
    private  SharedPreferences prfs;
    private MyDbManager myDbManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trainings, null, false);
        List<Jump> lstJump= new ArrayList<>();
        prfs = getContext().getSharedPreferences(BtConsts.JUMP_KEY, Context.MODE_PRIVATE);
        for (int i = 1; i < Obrabotchik.razdelJump(myDbManager.getFromDb((SaveToPrefMap.ID)+1)).size()+1; i++) {
            lstJump.add(new Jump(i));
        }
        myRecycle =view.findViewById(R.id.recycleViewJumps);
        jumpAdapter= new JumpAdapter(getContext(),lstJump);
        myRecycle.setAdapter(jumpAdapter);
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        myDbManager= new MyDbManager(getContext());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.findItem(R.id.id_okey).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.id_okey){
            if(SaveToPrefMap.countJumpi()==0){
                Toast.makeText(getContext(), "Choose one Jump", Toast.LENGTH_SHORT).show();
            }else if(SaveToPrefMap.countJumpi()==1) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.counteiner, new LeftFragment()).commit();
                int find = 0;
                for (int i = 0; i < SaveToPrefMap.jumpi.length; i++) {
                    if (jumpi[i] == true) {
                        find = i;
                        break;
                    }
                }
                SharedPreferences.Editor edit= prfs.edit();
                edit.putInt(MyConstants.JUMP_NUMBER,Obrabotchik.razdelJump(myDbManager.getFromDb((SaveToPrefMap.ID)+1)).get(find));
                edit.apply();
                for (int i = 0; i < SaveToPrefMap.jumpi.length;i++) {
                    jumpi[i]=false;
                }

            }else{
                Toast.makeText(getContext(), "Choose ONLY one Jump ", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}