package com.example.movieapp.FRAGMENT;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieapp.Adapter.ItemsearchAdapter;
import com.example.movieapp.Data.DataCenter;
import com.example.movieapp.R;

public class WhishList extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_whish_list, container, false);

        ItemsearchAdapter adapter=new ItemsearchAdapter(context);

        RecyclerView recyclerView=view.findViewById(R.id.favouriteitems);

        adapter.setLists(DataCenter.getInstance(requireActivity()).getFunction().getAll());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


        return view;
    }

    public WhishList(Context context)
    {
        this.context=context;

    }
}