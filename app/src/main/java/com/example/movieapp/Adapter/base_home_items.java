package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.Activity.MovieShow;
import com.example.movieapp.Data.DataClientServer;
import com.example.movieapp.R;
import com.example.movieapp.ViewModel.MoviView;

import java.util.ArrayList;
import java.util.List;

public class base_home_items extends RecyclerView.Adapter<base_home_items.Holder>{
    private Context context ;
    private int  numchildlist=0;
    private List<Integer>pages=new ArrayList<>();


    public base_home_items(Context context) {
        this.context = context;

    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_parent_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        mainviewItems adapterone=new mainviewItems(context);
        mainviewItems adaptertwo=new mainviewItems(context);
        mainviewItems adapterthree=new mainviewItems(context);

        MoviView moviViewpageone=ViewModelProviders.of((FragmentActivity)context).get(MoviView.class);
        MoviView moviViewpagetwo=ViewModelProviders.of((FragmentActivity)context).get(MoviView.class);
        MoviView moviViewpagethree=ViewModelProviders.of((FragmentActivity)context).get(MoviView.class);

        if(position==0)
        {
            moviViewpageone.GetPostPageOne(pages.get(position));
            moviViewpageone.moviesone.observe((FragmentActivity) context, new Observer<DataClientServer>() {
                @Override
                public void onChanged(DataClientServer dataServer) {
                    adapterone.setMovies(dataServer.getTvShows());
                }
            });

            holder.recyclerView.setAdapter(adapterone);
        }
        else if(position==1)
        {
            moviViewpagetwo.GetPostPagetwo(pages.get(position));
            moviViewpagetwo.Moviestwo.observe((FragmentActivity) context, new Observer<DataClientServer>() {
                @Override
                public void onChanged(DataClientServer dataServer) {
                    adaptertwo.setMovies(dataServer.getTvShows());
                }
            });
            holder.recyclerView.setAdapter(adaptertwo);
        }
        else {
            moviViewpagethree.GetPostPageThree(pages.get(position));
            moviViewpagethree.moviethree.observe((FragmentActivity) context, new Observer<DataClientServer>() {
                @Override
                public void onChanged(DataClientServer dataServer) {
                    adapterthree.setMovies(dataServer.getTvShows());
                }
            });
            holder.recyclerView.setAdapter(adapterthree);
        }

        numchildlist=adapterone.getItemCount();
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MovieShow.class);
                intent.putExtra("Page",position);
                context.startActivity(intent);
            }
        });

    }

    public int getNumchildlist() {
        return numchildlist;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);

            recyclerView=itemView.findViewById(R.id.recyclerItemparent);
            imageView=itemView.findViewById(R.id.imagearrowforward);

        }
    }
}
