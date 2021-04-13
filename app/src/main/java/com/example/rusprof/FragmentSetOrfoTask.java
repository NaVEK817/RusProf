package com.example.rusprof;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FragmentSetOrfoTask extends Fragment {
    public WordView wordView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setorfotask_fragment, container, false);
        wordView=view.findViewById(R.id.tV);
        return view;
    }
}
