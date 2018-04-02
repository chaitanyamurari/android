package com.example.android.antiragging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditDataActivity extends AppCompatActivity {

    private static final  String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private TextView editTextname, editTextnumber;

    DatabaseHelper mDatabaseHelper;

    private String selecetedName;
    private String selectednumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnSave = (Button)findViewById(R.id.save);
        btnDelete = (Button)findViewById(R.id.delete);
        editTextname = (TextView) findViewById(R.id.nameedit);
        editTextnumber = (TextView) findViewById(R.id.numberedit);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent recieveintent = getIntent();

        selectednumber = recieveintent.getStringExtra("number");
        selecetedName = recieveintent.getStringExtra("name");

        editTextnumber.setText(selectednumber);
        editTextname.setText(selecetedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EditDataActivity.this, Secondpage.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Contact saved ", Toast.LENGTH_SHORT).show();

            }});

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 mDatabaseHelper.deletenamenumber(selectednumber,selecetedName);
                  editTextname.setText("");
                  editTextnumber.setText("");
                  Intent intent = new Intent(EditDataActivity.this, Secondpage.class);
                  startActivity(intent);
                  Toast.makeText(getApplicationContext(), "Contact deleted ", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
