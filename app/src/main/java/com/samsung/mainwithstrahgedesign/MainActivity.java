package com.samsung.mainwithstrahgedesign;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.samsung.mainwithstrahgedesign.adapterForBt.BtConsts;
import com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap;
import com.samsung.mainwithstrahgedesign.bluetooth.BtConnection;
import com.samsung.mainwithstrahgedesign.bluetooth.ConnectThread;
import com.samsung.mainwithstrahgedesign.bluetooth.ReceiveThread;
import com.samsung.mainwithstrahgedesign.db.MyDbManager;
import com.samsung.mainwithstrahgedesign.fragments.AccountFragment;
import com.samsung.mainwithstrahgedesign.fragments.LeftFragment;
import com.samsung.mainwithstrahgedesign.fragments.LibraryFragment;
import com.samsung.mainwithstrahgedesign.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {
    private MenuItem menuItem;
    private BluetoothAdapter btAdapter;
    private final int Enable_Request = 17;
    private SharedPreferences pref;
    private BtConnection btConnection;
    private MyDbManager myDbManager;
    private TextView left_tv;
    private int si=0;
    private MenuItem menuItem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.action_results);
        init();

        myDbManager= new MyDbManager(this);


        }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menuItem = menu.findItem(R.id.id_bt_btn);
        menuItem2 = menu.findItem(R.id.play);
        menu.findItem(R.id.id_okey).setVisible(false);
        setBtIcon();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.id_bt_btn) {
            if (btAdapter.isEnabled()) {
                btAdapter.disable();
                menuItem.setIcon(R.drawable.bt_enable);
            } else {
                enableBt();
                menuItem.setIcon(R.drawable.ic_bt_disabled);
            }
        } else if (item.getItemId() == R.id.id_menu) {
            if (btAdapter.isEnabled()) {
                Intent i = new Intent(MainActivity.this, BtListActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
            }

        }else if (item.getItemId() == R.id.id_connect) {
            btConnection.connect();

//        }else if (item.getItemId() == R.id.action_settings) {
//
//            setTitle(R.string.action_settings);
//            SettingsFragment settingsFragment = new SettingsFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.counteiner, settingsFragment);
//            ft.commit();
//
//        }else if (item.getItemId() == R.id.action_account) {
//
//            setTitle(R.string.action_account);
//            AccountFragment accountFragment = new AccountFragment();
//            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//            ft2.replace(R.id.counteiner, accountFragment);
//            ft2.commit();
        }else if (item.getItemId() ==R.id.action_library) {

            setTitle(R.string.action_library);
            LibraryFragment libraryFragment = new LibraryFragment();
            FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
            ft3.replace(R.id.counteiner, libraryFragment);
            ft3.commit();

        }else if(item.getItemId()==R.id.action_results) {
            setTitle(R.string.action_results);
            LeftFragment leftFragment = new LeftFragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.counteiner, leftFragment);
            ft1.commit();

        }
        else if(item.getItemId()==R.id.play){
            if(btAdapter.isEnabled()&& SaveToPrefMap.connect){
                if(si==0){
                    menuItem2.setIcon(R.drawable.ic_baseline_stop_24);
                    btConnection.sendMessage("2");
                    si++;
                }else if(si==1){
                    menuItem2.setIcon(R.drawable.ic_baseline_play_arrow_24);
                    BtConnection bt= new BtConnection(this);
                    btConnection.sendMessage("1");
                    si=0;
                }
            }else{
                if(!btAdapter.isEnabled()){
                    Toast.makeText(this, "Turn on bluetooth", Toast.LENGTH_SHORT).show();
                }else if(!SaveToPrefMap.connect){
                    Toast.makeText(this, "Connect to device", Toast.LENGTH_SHORT).show();
                }

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Enable_Request) {
            if (requestCode == RESULT_OK) {
                //здесь пишется код,если бт включен
                setBtIcon();
            }
        }
    }

    private void setBtIcon() {
        if (btAdapter.isEnabled()) {
            menuItem.setIcon(R.drawable.ic_bt_disabled);
        } else {
            menuItem.setIcon(R.drawable.bt_enable);
        }
    }

    private void init() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        pref = getSharedPreferences(BtConsts.MY_PREF, Context.MODE_PRIVATE);
        btConnection=new BtConnection(this);
    }

    private void enableBt() {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(i, Enable_Request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();
    }
}

