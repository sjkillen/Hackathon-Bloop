package io.everybodybloops.bloop.bloop;

import android.os.AsyncTask;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.net.InetAddress;
import java.util.Date;

public class GetNTPTask extends AsyncTask <String,Void,Date> {
    private Date time;
    @Override
    protected Date doInBackground(String... urls) {
        String TIME_SERVER = urls[0];
        NTPUDPClient timeClient = new NTPUDPClient();
        try {
            InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
            TimeInfo timeInfo = timeClient.getTime(inetAddress);
            long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
            time = new Date(returnTime);
            System.out.println(time);
            return time;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    };
}

