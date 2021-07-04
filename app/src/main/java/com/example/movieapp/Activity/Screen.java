package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.movieapp.R;

public class Screen extends AppCompatActivity {

    private static  final String EMAIL="email";
    private static final String PASSWORD="password";
    private static final String Mypref="mypref";

    SharedPreferences sharedPreferences;
    int x=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        LottieAnimationView animationView=findViewById(R.id.animation);
        animationView.playAnimation();
        Handler handler=new Handler();

        sharedPreferences=getSharedPreferences(Mypref,MODE_PRIVATE);
        String email=sharedPreferences.getString(EMAIL,null);
        String password=sharedPreferences.getString(PASSWORD,null);



        if(email!=null && password!=null)
             x=1;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(x==1)
                {
                    startActivity(new Intent(Screen.this,MainActivity.class));
                }
                else{
                    startActivity(new Intent(Screen.this,login.class));
                }

               finish();
            }
        },3102);


    }
}