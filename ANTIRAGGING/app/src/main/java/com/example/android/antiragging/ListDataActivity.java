package com.example.android.antiragging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

      private static  final  String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;

    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        populatedListView();
    }

    private void  populatedListView() {
        Log.d(TAG, "populateListView:  Displaying data in the ListView");

        List<Contact> data = mDatabaseHelper.getData();
        List<String> names = new ArrayList<>();
        if (data == null) {
            Toast.makeText(getApplicationContext(), "plz set emergency contacts  then view them " , Toast.LENGTH_LONG).show();

        } else{

            for (Contact c : data) {

                if(c.getName() == null || c.getName() == "")
                {

                }
                else {
                    names.add(c.getName());
                }
            }
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
            mListView.setAdapter(adapter);


            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = adapterView.getItemAtPosition(i).toString();
                    String number = adapterView.getItemAtPosition(i).toString();
                    Log.d(TAG, "onItemClick: you clicked on" + name + "-" + number);
                    Contact data = mDatabaseHelper.getItemId(name);

                    if (data != null) {
                        Log.d(TAG, "onItemClick: the number is-" + number);
                        Intent editScreen = new Intent(ListDataActivity.this, EditDataActivity.class);
                        editScreen.putExtra("number", data.getNumber());
                        editScreen.putExtra("name", data.getName());
                        startActivity(editScreen);
                    } else {
                        Toast.makeText(getApplicationContext(), "no number associated with the name", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        }

}
