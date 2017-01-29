package io.everybodybloops.bloop.bloop;

/**
 * Created by Christian on 2017-01-28.
 */

import  com.github.nkzawa.socketio.client.IO;
import  com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class SocketConnect {

    private Socket mSocket;
    {
        try{
            mSocket = IO.socket("");
        }catch (URISyntaxException e){}
    }

}
