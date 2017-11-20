package com.example.eladgofer.project.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eladgofer.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    TextView aboutbody;
    String aboutText = "Pass Time is a free app that helps you stay\n" +
            " up to date with current news and movies,\n" +
            " it also helps you keep your thoughts and tasks organized.\n" +
            "Thanks alot to icons8 for the AWSOME icons\n" +
            "you can visit them at:\n" +
            "https://icons8.com/";

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);
        aboutbody = (TextView) v.findViewById(R.id.aboutBody);
        aboutbody.setText(aboutText);
        return v;
    }

}
