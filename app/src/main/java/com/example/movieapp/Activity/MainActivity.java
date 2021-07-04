package com.example.movieapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapp.FRAGMENT.Home;
import com.example.movieapp.FRAGMENT.Search;
import com.example.movieapp.FRAGMENT.Setting;
import com.example.movieapp.FRAGMENT.WhishList;
import com.example.movieapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.NetworkInterface;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        BottomNavigationView bottom=findViewById(R.id.bottom);
        getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,new Home(MainActivity.this,checkInternet())).commit();

       bottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               Fragment fragment=null;
               if(item.getItemId()==R.id.one)
               {
                   fragment=new Home(MainActivity.this,checkInternet());

               }
               else if (item.getItemId()==R.id.two) {
                   fragment=new Search(MainActivity.this,checkInternet());

               }
               else if (item.getItemId()==R.id.three) {
                   fragment=new WhishList(MainActivity.this);
               }
               else if (item.getItemId()==R.id.four) {
                   fragment=new Setting(MainActivity.this,checkInternet());
               }
               getSupportFragmentManager().beginTransaction().replace(R.id.framLayout,fragment).commit();
               return true;
           }
       });
    }

    boolean checkInternet()
    {
        try {
            ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo network=null;
            if(manager!=null)
                network=manager.getActiveNetworkInfo();
            return network!=null && network.isConnected();
        }
        catch (NullPointerException e){
            return false;
        }
    }

}