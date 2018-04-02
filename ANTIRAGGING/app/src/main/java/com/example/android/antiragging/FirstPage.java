package com.example.android.antiragging;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstPage extends AppCompatActivity {
 public Button btn;


    public void welcome(){
        btn = (Button) findViewById(R.id.welcome);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent logger = new Intent(getApplicationContext(),Secondpage.class);
               startActivity(logger);
            }
        });
    }
    MediaButton_Receiver mediaReceiver = new MediaButton_Receiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        registerMediaButtonEventReceiver(mediaReceiver );



        welcome();
    }

    private void registerMediaButtonEventReceiver(MediaButton_Receiver mediaReceiver) {
        IntentFilter filter =new IntentFilter(Intent.ACTION_CAMERA_BUTTON);
        registerReceiver(mediaReceiver, filter);
    }
    @Override
    public void onDestroy() {
        unregisterReceiver(mediaReceiver);
        super.onDestroy();

    }



}
