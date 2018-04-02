package com.example.android.antiragging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by chait on 7/18/2017.
 */

public class MediaButton_Receiver extends BroadcastReceiver {
    static int x = 0;
    LocationManager locationManager;
    Context mContext;
    DatabaseHelper dbHelper ;
    static long firstclick;
    static long secondclick;
    public MediaButton_Receiver() {
        // Required empty public constructor

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        dbHelper = new DatabaseHelper(context);

        x++;



        if (x == 2) {

            firstclick = System.currentTimeMillis();
        } else if (x == 4) {
            secondclick = System.currentTimeMillis();

            if ((secondclick - firstclick) < 1000) {

                Intent intentone = new Intent(context.getApplicationContext(), SOSConfirmationActivity.class);
                intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentone);




                x = 0;
                firstclick = 0;
                secondclick = 0;
            } else {
                x = 0;
                firstclick = 0;
                secondclick = 0;
            }

            }

        }

    }

