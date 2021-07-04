package com.example.movieapp.Apiserver;

import com.example.movieapp.Data.DataClientServer;
import com.example.movieapp.Data.TvshowResponse;
import com.example.movieapp.Data.tvDetails;
import com.example.movieapp.Data.tvshow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface fuctionserver {

    @GET("most-popular")
    public Call<DataClientServer> getPost(@Query("page") int postId );

    @GET("search")
    public Call<DataClientServer> getPost(@Query("q") String q, @Query("page") int p);

    @GET("show-details")
    public Call<TvshowResponse> getDetails(@Query("q") int id);


}
