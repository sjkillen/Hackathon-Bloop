package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import  com.github.nkzawa.socketio.client.IO;
import  com.github.nkzawa.socketio.client.Socket;

import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Socket mSocket;

    {
        try {
            //192.168.244.91:9000
            mSocket = IO.socket("http://192.168.244.91:9000");
        } catch (URISyntaxException e) {
            System.out.println("URI exception handled");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();
        //Attach listener to button
        Button button = (Button) findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                new GetNTPTask().execute("time.nist.gov");
                mSocket.connect();
                System.out.println(mSocket);
                Intent intent = new Intent(context, Connected.class);

               // startActivity(intent);
        }
        });
    }
}
