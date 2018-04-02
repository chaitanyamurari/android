package com.example.android.antiragging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form2Activity extends AppCompatActivity {
    String ragginigcontent;


    public void normalcomplaint(){
        final EditText edText = (EditText)findViewById(R.id.complainername);
        final EditText edText1 = (EditText)findViewById(R.id.complainercourse);
        final EditText edText2 = (EditText)findViewById(R.id.complainerbranch);
        final EditText edText3 = (EditText)findViewById(R.id.complaineryear);
        final EditText edText4 = (EditText)findViewById(R.id.complainermobile);
        final EditText edText5 = (EditText)findViewById(R.id.complainercollegename);
        final EditText edText6 = (EditText)findViewById(R.id.complainercollegeemail);
        final EditText edText7 = (EditText)findViewById(R.id.complainertopic);
        final EditText edText8 = (EditText)findViewById(R.id.complainercomplain);
        Button b = (Button)findViewById(R.id.normalcomplaint);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String email = edText6.getText().toString();

                if( edText.getText().toString().length() == 0 || edText1.getText().toString().length() == 0|| edText2.getText().toString().length() == 0||edText3.getText().toString().length() == 0 ||edText4.getText().toString().length() == 0 ||edText5.getText().toString().length() == 0 ||edText6.getText().toString().length() == 0 || edText7.getText().toString().length() == 0 ||edText8.getText().toString().length() == 0 )
                {
                    Toast.makeText(getApplicationContext(), "plzzz fill all the details", Toast.LENGTH_LONG).show();
                }else {if (edText4.getText().toString().length() != 10)
                {   Toast.makeText(getApplicationContext(), "enter 10 digit number ", Toast.LENGTH_LONG).show();
                }else {
                    if (email.matches(emailPattern)) {
                        ragginigcontent = "NAME  -";
                        ragginigcontent += edText.getText().toString();
                        ragginigcontent += "\nCOURSE  -";
                        ragginigcontent += edText1.getText().toString();
                        ragginigcontent += "\nBRANCH  -";
                        ragginigcontent += edText2.getText().toString();
                        ragginigcontent += "\nYEAR  -";
                        ragginigcontent += edText3.getText().toString();
                        ragginigcontent += "\nMOBILE NO  -";
                        ragginigcontent += edText4.getText().toString();
                        ragginigcontent += "\nCOLLEGE NAME  -";
                        ragginigcontent += edText5.getText().toString();
                        ragginigcontent += "\nCOLLEGE EMAIL  -";
                        ragginigcontent += edText6.getText().toString();
                        ragginigcontent += "\nCOMPLAIN TOPIC  -";
                        ragginigcontent += edText7.getText().toString();
                        ragginigcontent += "\nCOMPLAINT  -";
                        ragginigcontent += edText8.getText().toString();

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto"));
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{edText6.getText().toString()});
                        intent.putExtra(Intent.EXTRA_SUBJECT, new String[]{edText7.getText().toString()});
                        intent.putExtra(Intent.EXTRA_TEXT, ragginigcontent);
                        intent.setType("message/rfc822");
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                    }
                }}
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        normalcomplaint();
    }
}
