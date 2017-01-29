package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import  com.github.nkzawa.socketio.client.IO;
import  com.github.nkzawa.socketio.client.Socket;
import  com.github.nkzawa.emitter.Emitter;

import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private Socket mSocket;
    private String pingMessage = "HEY YOU GUYYYYYYYYYYYYSSS";
    public MediaPlayer mp;

    {
        try {
            //192.168.244.91:9000  i forgot the http dummy
            mSocket = IO.socket("http://192.168.244.91:9000");
        } catch (URISyntaxException e) {
            System.out.println("URI exception handled");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Attach listener to button
        //connect to the socket
        mSocket.on("pong", onPong);
        mSocket.on("showtime", onShowtime);
        mSocket.connect();
        Button button = (Button) findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


                //new GetNTPTask().execute("time.nist.gov");

                System.out.println(mSocket);
                mSocket.emit("ping", pingMessage);
                //Intent intent = new Intent(context, Connected.class);

               // startActivity(intent);
               //CANT STOP NO BRAKES
        }
        });
    }
    private Emitter.Listener onShowtime = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            long timeOffset = 0;
            JSONArray data = (JSONArray) args[0];
            new GetNTPTask(data, getApplicationContext()).execute("time.nist.gov");

        }
    };

    private Emitter.Listener onPong = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            //make the Mediaplayer object and call it as soon as its ready (quick)
//            MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.bloop );
//            mp.start();

        }
    };



}
