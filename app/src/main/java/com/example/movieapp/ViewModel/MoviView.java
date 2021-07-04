package com.example.movieapp.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.Apiserver.RetrofitClient;
import com.example.movieapp.Data.DataClientServer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviView extends ViewModel  {

  public   MutableLiveData<DataClientServer> moviesone=new MutableLiveData<>();
  public MutableLiveData<DataClientServer> Moviestwo=new MutableLiveData<>();
  public MutableLiveData<DataClientServer> moviethree=new MutableLiveData<>();
  public MutableLiveData<DataClientServer> movieitems=new MutableLiveData<>();
  public int code=0;

    public int getCode() {
        return code;
    }

    public void GetPostPageOne(int p ){
        RetrofitClient.getBuilder().getServer(p).enqueue(new Callback<DataClientServer>() {
            @Override
            public void onResponse(Call<DataClientServer> call, Response<DataClientServer> response) {
                moviesone.setValue(response.body());
                code=response.code();
            }
            @Override
            public void onFailure(Call<DataClientServer> call, Throwable t) {

            }
        });
    }
    public void GetPostPagetwo(int p ){
        RetrofitClient.getBuilder().getServer(p).enqueue(new Callback<DataClientServer>() {
            @Override
            public void onResponse(Call<DataClientServer> call, Response<DataClientServer> response) {
                Moviestwo.setValue(response.body());
                code=response.code();
            }
            @Override
            public void onFailure(Call<DataClientServer> call, Throwable t) {

            }
        });
    }
    public void GetPostPageThree(int p) {
        RetrofitClient.getBuilder().getServer(p).enqueue(new Callback<DataClientServer>() {
            @Override
            public void onResponse(Call<DataClientServer> call, Response<DataClientServer> response) {
                moviethree.setValue(response.body());
                code=response.code();
            }
            @Override
            public void onFailure(Call<DataClientServer> call, Throwable t) {

            }
        });
    }

    public void GetResult(String q,int p)
    {
        RetrofitClient.getBuilder().getitems(q,p).enqueue(new Callback<DataClientServer>() {
            @Override
            public void onResponse(Call<DataClientServer> call, Response<DataClientServer> response) {
                movieitems.setValue(response.body());
                code=response.code();
            }

            @Override
            public void onFailure(Call<DataClientServer> call, Throwable t) {

            }
        });

    }




}
