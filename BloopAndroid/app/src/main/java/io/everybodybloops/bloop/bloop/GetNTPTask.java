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
    GetNTPTask(JSONArray a, Context c){
        super();
        this.a = a;
        this.mp = MediaPlayer.create(c ,R.raw.bloop );
    }
    private Date time;
    private JSONArray a;
    private MediaPlayer mp;
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
                //System.out.println(timeOffset);
                EventWaitThread(returnTime, timeoffset, soundTime );

            } catch (JSONException e){
                System.out.println("JSON Exception");
            }
            //  System.out.println(timeOffset);
        }
        return null;
    };
    private void EventWaitThread(final long ntpTime, final long oldTime, final long soundTime){//no see
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long newNtpTime = System.currentTimeMillis() - oldTime + ntpTime;
                    System.out.println("before the sleep");
                    System.out.println("ntptime: " + ntpTime);
                    System.out.println("oldTime: " + oldTime);
                    System.out.println("soundtime: " + soundTime);
                    System.out.println(soundTime - newNtpTime);
                    Thread.sleep(soundTime - newNtpTime);
                    mp.start();
                    System.out.println("it it here");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}

