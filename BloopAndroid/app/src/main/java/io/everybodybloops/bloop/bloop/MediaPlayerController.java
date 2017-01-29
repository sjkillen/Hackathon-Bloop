package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Christian on 2017-01-28.
 * Controller for the media player
 */

public class MediaPlayerController extends Connected {


    MediaPlayer mediaPlayer;

    //constructor for the media player class
    MediaPlayerController(String url) {// will also need time.
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // Try and get the data source from the url passed

        try {
            mediaPlayer.setDataSource(url);

        System.out.println("datasource found" + url);
           mediaPlayer.prepare();
       } catch (java.io.IOException e){
           // the file could not be prepared
           CharSequence text = "File could not be prepared";
            System.out.println("prepare failed");
          // Toast toast = Toast.makeText(context, text,Toast.LENGTH_SHORT);
           //toast.show();

       }
        System.out.println("player prepared");
     }

    public void playSound(){ // Needs to take a time
        mediaPlayer.start();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    // method to close the MediaPlayer instance
    public void close(){
        mediaPlayer.release();
    }

}
