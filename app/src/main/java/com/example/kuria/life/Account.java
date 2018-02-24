package com.example.kuria.life;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {


    public Account() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account, container, false);
       TextView name = (TextView)v.findViewById(R.id.name);
       name.setText("Name : "+LoginActivity.first+" "+LoginActivity.last);
        TextView email = (TextView)v.findViewById(R.id.email);
        email.setText("Email : "+LoginActivity.email);
        TextView mob = (TextView)v.findViewById(R.id.mob);
        mob.setText("Contact : "+LoginActivity.phno);
        TextView aadhar = (TextView)v.findViewById(R.id.aadhar);
        aadhar.setText("Aadhaar ID : "+LoginActivity.aadhar);
        TextView bgrp = (TextView)v.findViewById(R.id.bgrp);
        bgrp.setText("Blood Group : "+LoginActivity.blood);


        return v;
    }

}
