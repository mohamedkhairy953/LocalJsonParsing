package com.example.moham.localjsonparsing.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.moham.localjsonparsing.factory.OurFactory;
import com.example.moham.localjsonparsing.R;
import com.example.moham.localjsonparsing.adapter.MyAdapter;
import com.example.moham.localjsonparsing.pojo.ServiceModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PullService_Frag extends Fragment {


    private RecyclerView myRecyc;
    private Button next_btn;
    private LinearLayoutManager mLayoutManager;

    public PullService_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pull_service, container, false);
        getActivity().setTitle("Service List");
        init_views(v);
        ArrayList<ServiceModel> serviceModels = parseJson();
        MyAdapter myAdapter = new MyAdapter(serviceModels, getActivity());
        myRecyc.setAdapter(myAdapter);
        return v;
    }

    private void init_views(View v) {
        myRecyc = (RecyclerView) v.findViewById(R.id.recycler);
        next_btn = (Button) v.findViewById(R.id.next_btn);
        myRecyc.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        myRecyc.setLayoutManager(mLayoutManager);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(OurFactory.selectedModels.isEmpty()){
                    Toast.makeText(getActivity(), "no selected services", Toast.LENGTH_SHORT).show();
                    return;
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new ShowSelected_Frag())
                        .commit();
            }
        });
    }

    private ArrayList<ServiceModel> parseJson() {
        ArrayList<ServiceModel> myServicesList = new ArrayList<>();
        try {
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject obj_inside = m_jArry.getJSONObject(i);
                ServiceModel serviceModel = OurFactory.setObject(obj_inside);
                myServicesList.add(serviceModel);
            }
        } catch (JSONException e) {
            Log.d("gggg", e.getLocalizedMessage());
        }
        return myServicesList;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("services.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
