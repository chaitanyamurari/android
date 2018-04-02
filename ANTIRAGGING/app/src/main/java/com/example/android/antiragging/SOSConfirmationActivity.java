package com.example.android.antiragging;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SOSConfirmationActivity extends AppCompatActivity {
    LocationManager locationManager;
    Context mContext;
    DatabaseHelper dbHelper ;
    private Button sosconfirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosconfirmation);
        confirmationsos();
    }

    public void confirmationsos(){
        sosconfirmation = (Button) findViewById(R.id.sosconfirmation);

        sosconfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
                dbHelper = new DatabaseHelper(getApplicationContext());

                String messageemergency = "i am currently being ragged";
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                messageemergency += "my location is";
                messageemergency += "\n https://maps.google.com/maps/?q=" + locationNet.getLatitude() + "," + locationNet.getLongitude();



                List<Contact> contacts = dbHelper.getData();
                if (contacts == null)
                {

                }else {
                    for (Contact c : contacts) {

                        SmsManager.getDefault().sendTextMessage(c.getNumber(), null, messageemergency, null, null);

                    }
                }

                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);

            }
        });
    }
}
