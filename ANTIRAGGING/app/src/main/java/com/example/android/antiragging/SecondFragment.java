package com.example.android.antiragging;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    String content;
    String email = "antiraggingdeveloper@gmail.com";

   // String str = editText1.getText().toString();
    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

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


       final EditText edText = (EditText)v.findViewById(R.id.username);
       final EditText edText1 = (EditText)v.findViewById(R.id.useraddress);
       final EditText edText2 = (EditText)v.findViewById(R.id.usermoblie);
       final EditText edText3 = (EditText)v.findViewById(R.id.useremail);
       final EditText edText4 = (EditText)v.findViewById(R.id.userpincode);
       final EditText edText5 = (EditText)v.findViewById(R.id.userstate_country);
       final EditText edText6 = (EditText)v.findViewById(R.id.Feedback);


        Button b = (Button)v.findViewById(R.id.submitButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = "NAME  -";
                content += edText.getText().toString();
                content += "\n ADDRESS -";
                content += edText1.getText().toString();
                content += "\n MOBILE NO  -";
                content += edText2.getText().toString();
                content += "\n PIN CODE  -";
                content += edText4.getText().toString();
                content += "\n STATE/ COUNTRY  -";
                content += edText5.getText().toString();
                content += "\n FEEDBACK  -";
                content += edText6.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"antiraggingdeveloper@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, content);
                intent.setType("message/rfc822");
                startActivity(intent);



            }
        });
        return  v;
    }

}
