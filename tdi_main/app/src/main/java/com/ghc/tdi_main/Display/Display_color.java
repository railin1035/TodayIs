package com.ghc.tdi_main.Display;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ghc.tdi_main.R;

public class Display_color extends Fragment {


    public Display_color() {


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //display_color_layout을 inflate
        return inflater.inflate(R.layout.display_color_layout, container, false);
    }
}