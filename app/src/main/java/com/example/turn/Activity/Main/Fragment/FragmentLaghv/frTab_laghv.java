package com.example.turn.Activity.Main.Fragment.FragmentLaghv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.turn.R;

public class frTab_laghv extends Fragment {

    public static frTab_laghv newInstance() {

        Bundle args = new Bundle();
        frTab_laghv fragment = new frTab_laghv();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_tab_laghv, container, false);
// cods here

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


}