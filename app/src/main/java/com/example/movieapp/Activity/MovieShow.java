package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.movieapp.Adapter.mainviewItems;
import com.example.movieapp.Apiserver.RetrofitClient;
import com.example.movieapp.Data.DataClientServer;
import com.example.movieapp.Data.tvshow;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieShow extends AppCompatActivity {

    int pageone=1;
    int pagetwo =4;
    int pagethree=8;
    List<tvshow> movies;
    mainviewItems adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_show);

        ImageView arrowBack=findViewById(R.id.arrowback);
         recyclerView=findViewById(R.id.recyclergridlayout);
        NestedScrollView nestedScrollView=findViewById(R.id.nestedscroll);

        adapter=new mainviewItems(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        movies=new ArrayList<>();
        adapter.setMovies(movies);
        recyclerView.setAdapter(adapter);

        Intent intent=getIntent();
        if(intent!=null)
        {
            int pagenumber=intent.getIntExtra("Page",-1);
            if(pagenumber!=-1)
            {
                if(pagenumber==0 )
                {
                    GetPost(pageone);
                    pageone++;
                }
                else if(pagenumber==1)
                {
                    GetPost(pagetwo);
                    pagetwo++;

                }
                else if(pagenumber==2)
                {
                    GetPost(pagethree);
                    pagethree++;
                }

                nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        if(scrollY==v.getChildAt(0).getMeasuredHeight()-v.getMeasuredHeight())
                        {
                            if(pagenumber==0 && pageone<4)
                            {
                                GetPost(pageone);
                                pageone++;
                            }
                            else if(pagenumber==1 && pagetwo <7)
                            {
                                GetPost(pagetwo);
                                pagetwo++;
                            }
                            else if(pagenumber==2 && pagethree<9)
                            {
                                GetPost(pagethree);
                                pagethree++;
                            }
                        }
                    }
                });
            }
        }

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
    public void GetPost(int p)
    {
        RetrofitClient.getBuilder().getServer(p).enqueue(new Callback<DataClientServer>() {
            @Override
            public void onResponse(Call<DataClientServer> call, Response<DataClientServer> response) {
               setDate(response.body().getTvShows());

            }
            @Override
            public void onFailure(Call<DataClientServer> call, Throwable t) {

            }
        });
    }
    public void setDate(List<tvshow> list)
    {
        for (tvshow t:list) {
            movies.add(t);
            adapter.notifyDataSetChanged();
        }

    }

}