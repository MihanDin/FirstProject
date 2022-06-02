package com.samsung.mainwithstrahgedesign.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import com.samsung.mainwithstrahgedesign.adapterForLibrary.SaveToPrefMap;

import java.io.IOException;


public class ConnectThread extends Thread {
    private BluetoothAdapter btAdapter;
    private BluetoothDevice device;
    private BluetoothSocket mSocket;
    private ReceiveThread rthread;
    private Context context;
    public static final String UUID = "00001101-0000-1000-8000-00805F9B34FB";


    public ConnectThread(Context context, BluetoothAdapter btAdapter, BluetoothDevice device) {
        this.context=context;
        this.btAdapter = btAdapter;
        this.device = device;
        try {
            mSocket = device.createRfcommSocketToServiceRecord(java.util.UUID.fromString(UUID));
        } catch (IOException e) {

        }
    }

    @Override
    public void run() {
        btAdapter.cancelDiscovery();
        try {
            mSocket.connect();
            Log.d("MyLog", "Connected");
            SaveToPrefMap.connect=true;
            rthread=new ReceiveThread(context,mSocket);
            rthread.start();
        } catch (IOException e) {
            Log.d("MyLog", "Not Connected");
            closeConnection();
        }
    }

    public void closeConnection() {
        try {
            mSocket.close();
        } catch (IOException y) {

        }
    }

    public ReceiveThread getRthread() {
        return rthread;
    }
}
