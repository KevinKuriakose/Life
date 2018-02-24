package com.example.kuria.life;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuickEmergency extends Fragment implements View.OnClickListener{

    View view;
    Button begin;
    public QuickEmergency() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_quick_emergency,container,false);
        begin=(Button)view.findViewById(R.id.begin);
        begin.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        Intent userHome = new Intent(getActivity(), QuickMaps.class);
        startActivity(userHome);
    }

}
