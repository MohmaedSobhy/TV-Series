package com.example.movieapp.Apiserver;

import com.example.movieapp.Data.DataClientServer;
import com.example.movieapp.Data.TvshowResponse;
import com.example.movieapp.Data.tvDetails;
import com.example.movieapp.Data.tvshow;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

   private static final String url="https://www.episodate.com/api/";
   private static RetrofitClient builder;
   private fuctionserver server;

    private  RetrofitClient() {
        Retrofit build=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        server=build.create(fuctionserver.class);
    }

    public static RetrofitClient getBuilder() {
        if(builder==null)
            builder=new RetrofitClient();

        return builder;
    }

    public Call<DataClientServer> getServer(int p) {
        return server.getPost(p);
    }

    public Call<DataClientServer> getitems(String q, int p)
    {
        return server.getPost(q,p);
    }

    public Call<TvshowResponse> getDetails(int id){
        return server.getDetails(id);
    }


}
