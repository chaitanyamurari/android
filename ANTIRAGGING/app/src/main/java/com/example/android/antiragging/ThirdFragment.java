package com.example.android.antiragging;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    private TextView txtugc, txtdeveloper;


    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        v.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getActivity().finish();
                        Intent intent = new Intent(getActivity(), Secondpage.class);
                        startActivity(intent);

                        return true;
                    }
                }
                return false;
            }
        });




        txtugc = (TextView)v.findViewById(R.id.text1);
        txtdeveloper = (TextView)v.findViewById(R.id.text2);
        txtugc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"helpline@antiraggging.in"});
                intent.setType("message/rfc822");
                startActivity(intent);
            }});

        txtdeveloper.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"antiraggingdeveloper@gmail.com"});
                intent.setType("message/rfc822");
                startActivity(intent);
            }});
        return v;
    }

}
