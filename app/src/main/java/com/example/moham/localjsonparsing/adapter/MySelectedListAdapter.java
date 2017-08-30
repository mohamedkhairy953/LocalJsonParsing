package com.example.moham.localjsonparsing.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.moham.localjsonparsing.BR;
import com.example.moham.localjsonparsing.R;
import com.example.moham.localjsonparsing.pojo.ServiceModel;

import java.util.ArrayList;

/**
 * Created by moham on 8/30/2017.
 */

public class MySelectedListAdapter extends RecyclerView.Adapter<MySelectedListAdapter.MyVH> {

    private final ArrayList<ServiceModel> data;
    Context ctx;

    public MySelectedListAdapter(ArrayList<ServiceModel> myDataset, Context c) {
        data = myDataset;
        ctx = c;
    }
    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.row_selected_layout, parent, false);
        return new MySelectedListAdapter.MyVH(binding);
    }

    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        final ServiceModel model = data.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MyVH(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object obj) {
            binding.setVariable(BR.serviceModel, obj);
            binding.executePendingBindings();
        }
    }
}
