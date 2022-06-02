package com.samsung.mainwithstrahgedesign.adapterForLibrary;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;

import java.util.List;

public class JumpAdapter  extends RecyclerView.Adapter<JumpAdapter.MyHolder> {
    private final Context context;
    private final LayoutInflater inflater;
    private final List<Jump> listJump;
    private SharedPreferences pref;

//    public Map<Integer,Boolean>  mapa = new HashMap<Integer,Boolean>();


    public JumpAdapter(Context context, List<Jump> listJump) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listJump = listJump;

    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private CheckBox chBtSelected;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            tv_id=itemView.findViewById(R.id.jumpsTrainName);
            chBtSelected=itemView.findViewById(R.id.checkTrainBox);

        }
    }
    private void savePref(int pos) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(BtConsts.ID_KEY, String.valueOf(listJump.get(pos).getNumbOfJump()));
        editor.apply();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= inflater.inflate(R.layout.jumps_adapter_item,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
            Jump jump= listJump.get(position);
        ((MyHolder)holder).tv_id.setText(jump.getNumbOfJump()+"-й Прыжок");
        ((MyHolder)holder).chBtSelected.setChecked(Boolean.getBoolean(String.valueOf(SaveToPrefMap.si[position])));
        holder.chBtSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SaveToPrefMap.jumpi[position]==false){
                    SaveToPrefMap.jumpi[position]=true;
                }else{
                    SaveToPrefMap.jumpi[position]=false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listJump.size();
    }
}