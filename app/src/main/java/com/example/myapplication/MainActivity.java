package com.example.myapplication;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    Button button2;
    Button button;
    EditText txt;
    Socket s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    s = new Socket("192.168.1.3", 6565);
                    Toast.makeText(getApplicationContext(), "connected to server", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    DataOutputStream dos = null;
                    dos = new DataOutputStream(s.getOutputStream());
                    DataInputStream din = null;
                    din = new DataInputStream(s.getInputStream());
                    txt = (EditText) findViewById(R.id.editTextTextPersonName);
                    txt.setVisibility(View.VISIBLE);
                    dos.writeUTF((txt.getText()).toString());
                    String msg = null;
                    msg = din.readUTF();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }  catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
/*        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("192.168.1.13",6565);
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dos.writeUTF(Edit_message.getText().toString());
                    dos.flush();
                    dos.close();
                    s.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();*/

}