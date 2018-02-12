package com.example.kuria.life;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadDocs extends Fragment implements View.OnClickListener {
View view;
    Button upload;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
view= inflater.inflate(R.layout.fragment_upload_docs,container,false);
upload=(Button)view.findViewById(R.id.upload);
upload.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent userHome = new Intent(getActivity(), UploadActivity.class);
        startActivity(userHome);
    }
}
