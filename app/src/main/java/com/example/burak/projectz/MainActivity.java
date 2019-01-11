package com.example.burak.projectz;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private final String DEVICE_ADDRESS = "98:D3:34:90:4B:1A"; //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;

    String command;

    Button forward_btn, back_btn, right_btn, left_btn, stop_btn, conn_btn, open_btn, close_btn, scan_btn;
    ImageButton mic_btn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conn_btn = (Button) findViewById(R.id.conn);
        mic_btn = (ImageButton) findViewById(R.id.mic);

        forward_btn = (Button) findViewById(R.id.forward);
        back_btn = (Button) findViewById(R.id.back);
        right_btn = (Button) findViewById(R.id.right);
        left_btn = (Button) findViewById(R.id.left);
        stop_btn = (Button) findViewById(R.id.stop);

        open_btn = (Button) findViewById(R.id.open);
        close_btn = (Button) findViewById(R.id.close);
        scan_btn = (Button) findViewById(R.id.scanon);



        mic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                try{
                    startActivityForResult(intent,200);
                }
                catch (ActivityNotFoundException a){
                    Toast.makeText(getApplicationContext(),"Intent problem", Toast.LENGTH_SHORT).show();
                }


            }
        });


        //OnTouchListener code for the stop button (button long press)
            stop_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "0";
                    try{
                        outputStream.write(command.getBytes());
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


            forward_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "1";
                    try{
                        outputStream.write(command.getBytes());
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


            back_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "2";
                    try{
                        outputStream.write(command.getBytes());
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


            left_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "3";
                    try{
                        outputStream.write(command.getBytes());
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


        right_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "4";
                    try{
                        outputStream.write(command.getBytes());
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


        open_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "5";
                    try{
                        outputStream.write(command.getBytes());
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

        close_btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    command = "6";
                    try{
                        outputStream.write(command.getBytes());
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


//        scan_btn.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    command = "7";
//                    try{
//                        outputStream.write(command.getBytes());
//                    }
//                    catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//                else if(event.getAction() == MotionEvent.ACTION_UP){
//                    command = "0";
//                    try{
//                        outputStream.write(command.getBytes());
//                    }
//                    catch(IOException e){
//                        e.printStackTrace();
//                    }
//                }
//                return false;
//            }
//        });

        scan_btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                command = "8";
                try{
                    outputStream.write(command.getBytes());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
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
                outputStream = socket.getOutputStream();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return connected;
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);



                if (result.get(0).equalsIgnoreCase("dur")){
                    command = "0";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("ileri")){
                    command = "1";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("geri")){
                    command = "2";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("sol")){
                    command = "3";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("sağ")){
                    command = "4";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("aç")){
                    command = "5";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }

                else if (result.get(0).equalsIgnoreCase("kapat")){
                    command = "6";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                }



                else{
                    command = "10";
                    try{
                        outputStream.write(command.getBytes());
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}
