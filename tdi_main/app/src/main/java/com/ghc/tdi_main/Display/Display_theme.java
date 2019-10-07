package com.ghc.tdi_main.Display;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ghc.tdi_main.R;
import java.util.ArrayList;


public class Display_theme extends Fragment {

    private RecyclerView mRecyclerView;
    private Display_Adapter display_adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Display_widgetdata> display_widgetdata;


    private int count = 1;
    public Display_theme() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.display_theme_layout, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_main_listtheme);
        mRecyclerView.setHasFixedSize(true);//아이템 추가, 삭제가 되더라도 뷰의 크기가 변동되지 않도록 설정.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        display_adapter = new Display_Adapter(display_widgetdata);

        mRecyclerView.setAdapter(display_adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset() {
        //for Test

        display_widgetdata = new ArrayList<>();

        /*형식
        ㅣ----------------------------------ㅣ
        ㅣ데이터         데이터         데이터ㅣ
        ㅣ----------------------------------ㅣ */
        display_widgetdata.add(new Display_widgetdata(count+"","apple" + count, "사과" + count));
    }

}

