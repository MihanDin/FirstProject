package com.samsung.mainwithstrahgedesign.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import com.samsung.mainwithstrahgedesign.db.MyDbManager;
import com.samsung.mainwithstrahgedesign.db.Obrabotchik;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReceiveThread extends Thread {
    private BluetoothSocket socket;
    private InputStream inputS;
    private OutputStream outputS;
    private Context context;
    private byte[] rBuffer;
    private MyDbManager myDbManager;

    public ReceiveThread(Context context,BluetoothSocket socket) {
        this.socket = socket;
        try{
            inputS= socket.getInputStream();
        }catch (IOException e){

        }
        try{
            outputS= socket.getOutputStream();
        }catch (IOException e){

        }
        myDbManager= new MyDbManager(context);
    }

    @Override
    public void run() {
        rBuffer = new byte[100];
        while (true){
            try{
               int size= inputS.read(rBuffer);
                String message= new String(rBuffer,0,size);
                Log.d("MyLog","Message: "+message);
                myDbManager.insertToDb(Obrabotchik.perevodVString(Obrabotchik.perevodIzString(message)));
            }catch (IOException e){
                break;
            }
        }
    }
    public void sentMessage(byte[]byteArray){
        try{
            outputS.write(byteArray);
        }catch (IOException e){

        }
    }
}
