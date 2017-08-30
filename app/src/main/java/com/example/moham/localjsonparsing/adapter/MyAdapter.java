package com.example.moham.localjsonparsing.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.moham.localjsonparsing.BR;
import com.example.moham.localjsonparsing.factory.OurFactory;
import com.example.moham.localjsonparsing.R;
import com.example.moham.localjsonparsing.pojo.ServiceModel;

import java.util.ArrayList;

/**
 * Created by moham on 8/30/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final ArrayList<ServiceModel> data;
    Context ctx;

    public MyAdapter(ArrayList<ServiceModel> myDataset, Context c) {
        data = myDataset;
        ctx = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_layout, parent, false);
        return new MyViewHolder(binding, binding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ServiceModel model = data.get(position);
        holder.bind(model);
        holder.box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    OurFactory.selectedModels.add(model);
                } else {
                    OurFactory.selectedModels.remove(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        CheckBox box;

        public MyViewHolder(ViewDataBinding binding, View view) {
            super(binding.getRoot());
            this.binding = binding;
            box = (CheckBox) view.findViewById(R.id.chkbox);
        }

        public void bind(Object obj) {
            binding.setVariable(BR.serviceModel, obj);
            binding.executePendingBindings();
        }
    }
}
