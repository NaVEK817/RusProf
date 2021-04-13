package com.example.rusprof;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class FragmenMainActivityMainChoose extends Fragment {
    public ImageButton imageButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainchoose_fragment, container, false);
        imageButton=view.findViewById(R.id.mainchoose_btn);
        return view;
    }
}
