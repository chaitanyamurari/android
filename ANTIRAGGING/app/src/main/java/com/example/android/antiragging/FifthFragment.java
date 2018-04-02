package com.example.android.antiragging;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class FifthFragment extends Fragment {
    private static final String chait = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewContacts, btnAddPhonebook;
    private EditText edtxtname, edtxtnumber;
    final int RQS_PICKCONTACT = 1;

    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fifth, container, false);


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

        edtxtname = (EditText) v.findViewById(R.id.emergencyname);
        edtxtnumber = (EditText) v.findViewById(R.id.emergencynumber);
        btnAdd = (Button) v.findViewById(R.id.addcontacts);
        btnAddPhonebook = (Button) v.findViewById(R.id.addphonebook);
        btnViewContacts = (Button) v.findViewById(R.id.viewcontacts);
        mDatabaseHelper = new DatabaseHelper(getActivity());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtxtname.getText().toString();
                String number = edtxtnumber.getText().toString();
                if (edtxtname.length() != 0 && edtxtnumber.length() == 10) {
                    AddDATA(name, number);
                    edtxtnumber.setText("");
                    edtxtname.setText("");
                } else {
                    Toast.makeText(getActivity(), "either you have not filled both the fields or your given no is not 10 digit numbers", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnAddPhonebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uriContact = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                Intent intentPickContact = new Intent(Intent.ACTION_PICK, uriContact);
                startActivityForResult(intentPickContact, RQS_PICKCONTACT);
            }
        });
        btnViewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListDataActivity.class);
                startActivity(intent);
            }
        });

        return v;

    }


    public void AddDATA(String name, String number) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setNumber(number);
        boolean insertData = mDatabaseHelper.addData(contact);

        if (insertData) {
            Toast.makeText(getActivity(), "data entered succesfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case RQS_PICKCONTACT:
                if (resultCode == RESULT_OK) {
                    Uri contactUri = intent.getData();
                    Cursor nameCursor = getActivity().getContentResolver().query(contactUri, null, null, null, null);
                    if (nameCursor!=null && nameCursor.moveToFirst()) {
                        String namephonebook = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        String numberphonebook = nameCursor.getString(nameCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        AddDATA(namephonebook, numberphonebook);

                        nameCursor.close();
                    }
                }
                break;
        }
    }


}

