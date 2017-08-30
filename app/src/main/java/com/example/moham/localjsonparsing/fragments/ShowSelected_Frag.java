package com.example.moham.localjsonparsing.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.moham.localjsonparsing.R;
import com.example.moham.localjsonparsing.adapter.MySelectedListAdapter;
import com.example.moham.localjsonparsing.factory.OurFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowSelected_Frag extends Fragment {

    RecyclerView listview;

    private Button finish_btn;
    private LinearLayoutManager mLayoutManager;

    public ShowSelected_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_selected, container, false);
        getActivity().setTitle("Selected List");

        init_view(v);

        return v;
    }

    private void init_view(View v) {
        listview = (RecyclerView) v.findViewById(R.id.selected_recycler);
        finish_btn = (Button) v.findViewById(R.id.finish_btn);
        listview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        listview.setLayoutManager(mLayoutManager);
        MySelectedListAdapter mySelectedListAdapter = new MySelectedListAdapter(OurFactory.selectedModels, getActivity());
        listview.setAdapter(mySelectedListAdapter);


        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OurFactory.selectedModels.clear();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new PullService_Frag())
                        .commit();
            }
        });
    }

}
