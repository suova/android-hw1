package com.akuznecova.numbers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Elem extends Fragment {
    private static final String COLOR = "color";
    private static final String NUMBER = "number";
    private int color;
    private String number;

    static Elem newInstance(String number, int color) {
        Elem fragment = new Elem();
        Bundle args = new Bundle();
        args.putInt(COLOR, color);
        args.putString(NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            color = getArguments().getInt(COLOR);
            number = getArguments().getString(NUMBER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elem, container, false);
        TextView number = view.findViewById(R.id.big_elem);
        number.setText(this.number);
        number.setTextColor(this.color);
        return view;
    }
}
