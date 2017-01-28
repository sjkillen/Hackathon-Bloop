package io.everybodybloops.bloop.bloop;

import android.content.Context;
import android.content.Intent;
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
        final Context context = getApplicationContext();
        //Attach listener to button
        Button button = (Button) findViewById(R.id.connectButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //start connected activity

                Intent intent = new Intent(context, Connected.class);
                startActivity(intent);
        }
        });
    }
}
