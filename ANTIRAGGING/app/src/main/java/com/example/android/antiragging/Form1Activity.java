package com.example.android.antiragging;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Form1Activity extends AppCompatActivity {

    String ragginigcontent;

    public void raggingsubmit() {
        final EditText edText = (EditText) findViewById(R.id.applicantname);
        final EditText edText1 = (EditText) findViewById(R.id.applicantcourse);
        final EditText edText2 = (EditText) findViewById(R.id.applicantbranch);
        final EditText edText3 = (EditText) findViewById(R.id.applicantyear);
        final EditText edText4 = (EditText) findViewById(R.id.applicantmobile);
        final EditText edText5 = (EditText) findViewById(R.id.applicantcollegename);
        final EditText edText6 = (EditText) findViewById(R.id.appliacntcollegeaddress);
        final EditText edText7 = (EditText) findViewById(R.id.applicantcollegeemail);
        final EditText edText8 = (EditText) findViewById(R.id.culpritname);
        final EditText edText9 = (EditText) findViewById(R.id.applicantcomplaint);
        Button b = (Button) findViewById(R.id.raggingButton);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String email = edText7.getText().toString();

                if (edText.getText().toString().length() == 0 || edText1.getText().toString().length() == 0 || edText2.getText().toString().length() == 0 || edText3.getText().toString().length() == 0 || edText4.getText().toString().length() == 0 || edText5.getText().toString().length() == 0 || edText6.getText().toString().length() == 0 || edText7.getText().toString().length() == 0 || edText8.getText().toString().length() == 0 || edText9.getText().toString().length() == 0)
                // userName.setError( "First name is required!" );
                {
                    Toast.makeText(getApplicationContext(), "plzzz fill all the details", Toast.LENGTH_LONG).show();
                } else {
                    if (edText4.getText().toString().length() != 10)
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
                            ragginigcontent += "\nCOLLEGE ADDRESS  -";
                            ragginigcontent += edText6.getText().toString();
                            ragginigcontent += "\nCOLLEGE EMAIL  -";
                            ragginigcontent += edText7.getText().toString();
                            ragginigcontent += "\nCULPRIT  -";
                            ragginigcontent += edText8.getText().toString();
                            ragginigcontent += "\nCOMPLAINT  -";
                            ragginigcontent += edText9.getText().toString();


                            String messageToSend = ragginigcontent;
                            String number = edText4.getText().toString();

                            //SmsManager sm = SmsManager.getDefault();
                            //sm.sendTextMessage("9458138190", null, ragginigcontent, null, null);
                            // SmsManager.getDefault().sendTextMessage(number, null, ragginigcontent, null, null);
                            Toast.makeText(getApplicationContext(), "message sent", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto"));
                            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{edText7.getText().toString()});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "RAGGING COMPLAIN");
                            intent.putExtra(Intent.EXTRA_TEXT, ragginigcontent);
                            intent.setType("message/rfc822");
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        raggingsubmit();
    }
}
