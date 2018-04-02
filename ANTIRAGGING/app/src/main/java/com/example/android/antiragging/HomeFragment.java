package com.example.android.antiragging;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  {
    String messagetosend;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    LocationManager locationManager;
    private Context mContext;
    DatabaseHelper dbHelper;

    public HomeFragment(Context context) {
        // Required empty public constructor
        this.mContext = context;
        dbHelper = new DatabaseHelper(mContext);
    }

    public void emergency() {
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmation();  //  calling confirmation function for sending sos distress call


            }
        });
    }

    public void website() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createDialog(); //  calling dialog box


            }
        });

    }

    public void form1() {


        btn2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent logger = new Intent(getActivity(), Form1Activity.class);
                startActivity(logger);

            }
        });

    }

    public void form2() {


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logger = new Intent(getActivity(), Form2Activity.class);
                startActivity(logger);
            }
        });

    }

    private void createDialog() {
        final AlertDialog.Builder alertDig = new AlertDialog.Builder(getActivity());
        alertDig.setTitle("CONFIRMATION");  //    to set the title
        alertDig.setMessage("Please have a internet connection before you proceed to fill the online form. DO YOU AGREE?");   //    to set the msg to be displayed
        alertDig.setCancelable(false);


        alertDig.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            //      commands to be executed when yes button is pressed
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();    //   dismiss the popup when yes is clicked
                Uri uri = Uri.parse("http://www.antiragging.in/Site/Affidavits_Registration.aspx");   // adress of site for filling anti ragging form

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);   // passing the uri to the intent variable
                startActivity(intent);  //  starting the intent

            }
        });
        alertDig.setNegativeButton("No", new DialogInterface.OnClickListener() {

            //   commands to be executed when no button is pressed
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //   dismiss the popup when no is clicked
                Toast.makeText(getActivity(), "you didn,t agree to fill the online form", Toast.LENGTH_LONG).show();
            }

        });

        alertDig.create().show();
    }

    private void confirmation() {
        final AlertDialog.Builder alertDig = new AlertDialog.Builder(getActivity());
        alertDig.setTitle("CONFIRMATION");  //    to set the title
        alertDig.setMessage("An emergency SOS message would be send to your saved emergency numbers and you would be charged from your preferred sim. Do you agree to send an emergency SOS?");   //    to set the msg to be displayed
        alertDig.setCancelable(false);


        alertDig.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            //      commands to be executed when yes button is pressed
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();    //   dismiss the popup when yes is clicked

                messagetosend = "PLEASE HELP i AM CURRENTLY BEING RAGGED or mistreated ";

                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    return;
                    // for ActivityCompat#requestPermissions for more details.
                }
                messagetosend +=" my location is";

                    Location locationNet = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                  messagetosend += "\n https://maps.google.com/maps/?q=" + locationNet.getLatitude() + "," + locationNet.getLongitude();


                    Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                messagetosend += "\n https://www.google.com/maps/?q=" + locationGPS.getLatitude() + "," + locationGPS.getLongitude();


                messageSent();


            }
        });

        alertDig.setNegativeButton("No", new DialogInterface.OnClickListener() {

            //   commands to be executed when no button is pressed
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();     //   dismiss the popup when no is clicked
                Toast.makeText(getActivity(), " emergency ragging SOS not sent", Toast.LENGTH_LONG).show();
            }

        });

        alertDig.create().show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn3 = (Button) view.findViewById(R.id.imageButton3);
        btn2 = (Button) view.findViewById(R.id.imageButton2);
        btn1 = (Button) view.findViewById(R.id.imageButton1);
        btn4 = (Button) view.findViewById(R.id.topbutton);

        website();
        form2();
        form1();
        emergency();

        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
    }


    void messageSent() {
        List<Contact> contacts = dbHelper.getData();
        if (contacts == null)
        {
            Toast.makeText(getActivity(), "plz set emergency contacts to send SOS mesage to them " , Toast.LENGTH_LONG).show();
        }else {
            for (Contact c : contacts) {
                SmsManager.getDefault().sendTextMessage(c.getNumber(), null, messagetosend, null, null);
                Toast.makeText(getActivity(), "message sent to " + c.getName(), Toast.LENGTH_LONG).show();
                Log.d("Location", messagetosend);
            }
        }

    }



}



