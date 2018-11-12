package com.example.burak.projectz;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private final String DEVICE_ADDRESS = "98:D3:31:F5:20:90"; //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    String command;

    Button forward_btn, back_btn, right_btn, left_btn, stop_btn, conn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        forward_btn = (Button) findViewById(R.id.forward);
        back_btn = (Button) findViewById(R.id.back);
        right_btn = (Button) findViewById(R.id.right);
        left_btn = (Button) findViewById(R.id.left);
        stop_btn = (Button) findViewById(R.id.stop);
        conn_btn = (Button) findViewById(R.id.conn);

        //OnTouchListener code for the stop button (button long press)
            stop_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "0";
                    try{
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        //OnTouchListener code for the forward button (button long press)
            forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "1";
                    try{
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        //OnTouchListener code for the back button (button long press)
            back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "2";
                    try{
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        //OnTouchListener code for the back button (button long press)
            left_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "3";
                    try{
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        //OnTouchListener code for the back button (button long press)
        right_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "4";
                    try{
                        outputStream.write(command.getBytes()); //transmits the value of command to the bluetooth module
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else if(event.getAction() == MotionEvent.ACTION_UP){
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });

        conn_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BTinit()){
                    BTconnect();
                }
            }
        });

    }

    //Initializes bluetooth module
    public boolean BTinit(){
        boolean found = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled()){
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter,0);
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        Set<BluetoothDevice> bondedDevices = bluetoothAdapter.getBondedDevices();
        if(bondedDevices.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
        }
        else{
            for(BluetoothDevice iterator : bondedDevices){
                if(iterator.getAddress().equals(DEVICE_ADDRESS)) {
                    device = iterator;
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean BTconnect(){
        boolean connected = true;
        try {
            socket = device.createRfcommSocketToServiceRecord(PORT_UUID);
            socket.connect();
            Toast.makeText(getApplicationContext(),
                    "Connection to bluetooth device successful", Toast.LENGTH_LONG).show();
        }
        catch(IOException e){
            e.printStackTrace();
            connected = false;
        }
        if(connected) {
            try {
                outputStream = socket.getOutputStream(); //gets the output stream of the socket
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return connected;
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

}
