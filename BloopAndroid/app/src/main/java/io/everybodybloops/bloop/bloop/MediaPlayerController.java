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
    MediaPlayerController(Context c) {// will also need time.
        MediaPlayer mediaPlayer = MediaPlayer.create(c, R.raw.bloop);


     }

    public void playSound(){ // Needs to take a time
        this.mediaPlayer.start();

    }

    // method to close the MediaPlayer instance
    public void close(){
        mediaPlayer.release();
        mediaPlayer = null;
    }


}
