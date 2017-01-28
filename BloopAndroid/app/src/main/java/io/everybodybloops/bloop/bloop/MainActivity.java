package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Attach listener to button
        Button button = (Button) findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                // do something on the click
//                Context context = getApplicationContext();
//                CharSequence text = "Hello";
//                Toast toast = Toast.makeText(context, text,Toast.LENGTH_SHORT);
//                toast.show();
                // instert function call for establishing connection


            }
        });
    }
}
