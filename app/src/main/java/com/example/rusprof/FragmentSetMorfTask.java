package com.example.rusprof;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentSetMorfTask extends Fragment{
    TextView task,part;
    EditText answer;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.setmorftask_fragment, container, false);
            answer=view.findViewById(R.id.edmorfe);
            task=view.findViewById(R.id.texttaskmorf);
            part=view.findViewById(R.id.textanswermorf);
            return view;
        }
}
