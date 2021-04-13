package com.example.rusprof;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentSetPunktTask extends Fragment {
    public EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setpunkttask_fragment, container, false);
        editText=view.findViewById(R.id.texttaskpunkt);
        return view;
    }
}
