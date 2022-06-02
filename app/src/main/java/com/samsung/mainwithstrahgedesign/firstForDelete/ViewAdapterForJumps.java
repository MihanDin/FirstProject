package com.samsung.mainwithstrahgedesign.firstForDelete;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.samsung.mainwithstrahgedesign.R;
import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.JumpAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapterForJumps extends ArrayAdapter<JumpAdapter> {
    private List<JumpAdapter> mainList;
    private List<ViewAdapterForLib.ViewHolder> viewHolderList;
    private SharedPreferences pref;

    public ViewAdapterForJumps(@NonNull Context context, int resource, List<JumpAdapter> btList) {

        super(context, resource, btList);
        mainList = btList;
        viewHolderList = new ArrayList<>();
        pref = context.getSharedPreferences(BtConsts.MY_PREF, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= defaultItem(convertView,position,parent);
        return convertView;
    }

//    private void savePref(int pos) {
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(BtConsts.JUMP_KEY, String.valueOf(mainList.get(pos).getJump()));
//        editor.apply();
//    }

    static class ViewHolder {
        TextView tvBtName;
        CheckBox chBtSelected;
    }
    private View defaultItem(View convertView, int position, ViewGroup parent){
        ViewAdapterForLib.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewAdapterForLib.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jumps_adapter_item, null, false);
            viewHolder.tvBtName = convertView.findViewById(R.id.jumpsTrainName);
            viewHolder.chBtSelected = convertView.findViewById(R.id.checkTrainBox);
            convertView.setTag(viewHolder);
            viewHolderList.add(viewHolder);

        } else {
            viewHolder = (ViewAdapterForLib.ViewHolder) convertView.getTag();
        }
//        viewHolder.tvBtName.setText(mainList.get(position).getJump()+" см");
        viewHolder.chBtSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ViewAdapterForLib.ViewHolder holder : viewHolderList) {
                    holder.chBtSelected.setChecked(false);
                }
                viewHolder.chBtSelected.setChecked(true);
//                savePref(position);
            }
        });
//        if (pref.getString(BtConsts.MAC_KEY, "no bt selected").equals(mainList.get(position).getBtDevice().getAddress()))
//            viewHolder.chBtSelected.setChecked(true);
        return convertView;
    }
}
