package com.example.movieapp.FRAGMENT;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.example.movieapp.Adapter.ItemsearchAdapter;
import com.example.movieapp.Data.DataClientServer;
import com.example.movieapp.R;
import com.example.movieapp.ViewModel.MoviView;


public class Search extends Fragment {

  private Context context;
    boolean check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_search, container, false);

        LinearLayout linearLayout=view.findViewById(R.id.parentLinerar);
        ImageView image=view.findViewById(R.id.noInternet);

        if(check)
        {
            RecyclerView recyclerView=view.findViewById(R.id.view_item);
            ItemsearchAdapter adapter=new ItemsearchAdapter(context);
            image.setVisibility(View.GONE);
            MoviView moviView= ViewModelProviders.of(requireActivity()).get(MoviView.class);

            SearchView searchView=view.findViewById(R.id.search_item);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    moviView.GetResult(query,1);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

            moviView.movieitems.observe(requireActivity(), new Observer<DataClientServer>() {
                @Override
                public void onChanged(DataClientServer dataServer) {
                    adapter.setLists(dataServer.getTvShows());
                }
            });

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        }
        else{
         image.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public Search(Context context,boolean check)
    {
        this.context=context;
        this.check=check;
    }
}