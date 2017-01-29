package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.Date;

public class GetNTPTask extends AsyncTask <String,Void,Void> {
    GetNTPTask(JSONArray a, Context c, int x, int y){
        super();
        this.a = a;
        this.xpos = x;
        this.ypos = y;
        this.mp = MediaPlayer.create(c ,R.raw.realvillan );
        this.mp2 = MediaPlayer.create(c,R.raw.sax1);
        this.mp3 = MediaPlayer.create(c,R.raw.sax2);
        this.mp4 = MediaPlayer.create(c,R.raw.num1);
        this.mp5 = MediaPlayer.create(c,R.raw.bloop);

    }
    private Date time;
    private JSONArray a;
    private int xpos;
    private int ypos;
    private MediaPlayer mp;
    private MediaPlayer mp2;
    private MediaPlayer mp3;
    private MediaPlayer mp4;
    private MediaPlayer mp5;


    @Override
    protected Void doInBackground(String... urls) {
        String TIME_SERVER = urls[0];
        NTPUDPClient timeClient = new NTPUDPClient();
        Long returnTime;
        long timeoffset;
        try {
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
//            time = new Date(returnTime)
            timeoffset = System.currentTimeMillis();
            System.out.println(returnTime + "-0");
            //return returnTime;
        } catch (Exception e) {
            System.out.println(e + "-1");
            return null;
        }
        for(int i = 0; i < a.length(); i++){
            try{
                JSONObject jObj =(JSONObject) a.get(i);
                long soundTime = jObj.getLong("time");
                String soundName = jObj.getString("sound");
                System.out.println(jObj.getLong("x"));
                System.out.println(jObj.getLong("y"));
                if (xpos == (int)jObj.getLong("x") && ypos == (int)jObj.getLong("y")){
                    EventWaitThread(returnTime, timeoffset, soundTime, soundName );
                }else {
                    System.out.println("not at your coordinates");
                }

                //System.out.println(timeOffset);


            } catch (JSONException e){
                System.out.println("JSON Exception" + e);
            }
            //  System.out.println(timeOffset);
        }
        return null;
    };
    private void EventWaitThread(final long ntpTime, final long oldTime, final long soundTime, final String soundName){//no see
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long newNtpTime = System.currentTimeMillis() - oldTime + ntpTime;
                    System.out.println("before the sleep");
                    System.out.println("ntptime: " + ntpTime);
//                    System.out.println("oldTime: " + oldTime);
                    System.out.println("soundname: " + soundName);
                    System.out.println(soundTime - newNtpTime);
                    Thread.sleep(soundTime - newNtpTime);
                    switch(soundName){
                        case "realvillan.mp3": mp.start();
                        case "sax1.mp3" : mp2.start();
                        case "sax2.mp3" : mp3.start();
                        case "num1.mp3" : mp4.start();
                        case "bloop.mp3" : mp5.start();
                        default:
                                break;
                    }
                    //asdasda

                    System.out.println("it it here");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    private void closeMediaPlayer(){
        mp.release();
        mp = null;
    }
}

