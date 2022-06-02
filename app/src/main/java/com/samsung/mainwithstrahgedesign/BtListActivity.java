package com.samsung.mainwithstrahgedesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.samsung.mainwithstrahgedesign.adapterForBt.BtAdapter;
import com.samsung.mainwithstrahgedesign.adapterForBt.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BtListActivity extends AppCompatActivity {
    private final int BT_REQUEST_PERM = 111;
    private ListView listView;
    private BtAdapter adapter;
    private BluetoothAdapter btAdapter;
    private List<ListItem> list;
    private boolean isBtPermision = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_list);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        ActionBar actionBar = getSupportActionBar();
        list = new ArrayList<>();
        if (actionBar == null) return;
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listViewBt);
        adapter = new BtAdapter(this, R.layout.bt_list_item, list);
        listView.setAdapter(adapter);
        getPairedDevices();


    }

    private void getPairedDevices() {
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        list.clear();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                ListItem item = new ListItem();

                item.setBtDevice(device);
                list.add(item);
            }
            adapter.notifyDataSetChanged();
        }

    }








}