package com.rishistech.onlinecart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersFragment extends Fragment {

    private RecyclerView myordersrecylerview;

    public MyOrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_my_orders, container, false);
        myordersrecylerview=view.findViewById(R.id.my_orders_recyclerview);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myordersrecylerview.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList=new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.ic_launcher_foreground,2,"Pixel 3XL (Black)","Delivered On Monday 27th  June 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.ic_launcher_foreground,3,"Pixel 3XL (Black)","Delivered On Monday 27th  June 2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.ic_launcher_foreground,1,"Pixel 3XL (Black)","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.ic_launcher_foreground,4,"Pixel 3XL (Black)","Delivered On Monday 27th  June 2020"));

        MyOrderAdapter myOrderAdapter=new MyOrderAdapter(myOrderItemModelList);
        myordersrecylerview.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }
}