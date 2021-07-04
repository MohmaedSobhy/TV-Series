package com.example.movieapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.movieapp.Apiserver.RetrofitClient;
import com.example.movieapp.Data.DataCenter;
import com.example.movieapp.Data.TvshowResponse;
import com.example.movieapp.Data.tvDetails;
import com.example.movieapp.Data.tvshow;
import com.example.movieapp.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class tvShowDetails extends AppCompatActivity {

    TextView tvname, countryname, networklink,Discription;
    ImageView imagecover,uparrow,downarrow;
    RelativeLayout relativediscription,parentRelative;
    Button favorite,website;
    boolean exits=false;

    String link;

    final static String id="id";
    final static String name="name";
    final static String country="country";
    final static String network="network";
    final static String imageLink="Image";
    final static String startDate="start";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);


        tvname=findViewById(R.id.tv_name);
        countryname =findViewById(R.id.countrytext);
        networklink =findViewById(R.id.networktext);
        Discription=findViewById(R.id.Discription);
        downarrow=findViewById(R.id.downarrow);
        imagecover=findViewById(R.id.image_cover);
        uparrow=findViewById(R.id.uparrow);
        relativediscription=findViewById(R.id.showRelative);
        parentRelative=findViewById(R.id.parentRelative);
        favorite=findViewById(R.id.addtofavourite);
        website=findViewById(R.id.visitewebsite);



        Intent intent=getIntent();
        tvshow tv;
        if(intent==null)
        {
            return;
        }
        else if(intent.getIntExtra(id,-1)==-1) {
            return;
        }
        else if(intent.getStringExtra(name)==null || intent.getStringExtra(country)==null || intent.getStringExtra(imageLink)==null
        || intent.getStringExtra(network)==null || intent.getStringExtra(startDate)==null)
            return;
        else {
             tv=new tvshow(intent.getIntExtra(id,-1),intent.getStringExtra(name),intent.getStringExtra(country),
                     intent.getStringExtra(network),intent.getStringExtra(imageLink)
             ,intent.getStringExtra(startDate));
        }

        RetrofitClient.getBuilder().getDetails(intent.getIntExtra(id,-1)).enqueue(new Callback<TvshowResponse>() {
                   @Override
                   public void onResponse(Call<TvshowResponse> call, Response<TvshowResponse> response) {
                       setData(response.body().getTv());
                   }

                   @Override
                   public void onFailure(Call<TvshowResponse> call, Throwable t) {

                   }
               });

            tvname.setText(intent.getStringExtra(name));
            countryname.setText(intent.getStringExtra(country));
            networklink.setText(intent.getStringExtra(network));
            Glide.with(tvShowDetails.this).asBitmap().load(intent.getStringExtra(imageLink)).into(imagecover);



        downarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativediscription.setVisibility(View.VISIBLE);
                downarrow.setVisibility(View.GONE);
                uparrow.setVisibility(View.VISIBLE);
            }
        });
        uparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativediscription.setVisibility(View.GONE);
                downarrow.setVisibility(View.VISIBLE);
                uparrow.setVisibility(View.GONE);
            }
        });

        for (tvshow t:DataCenter.getInstance(tvShowDetails.this).getFunction().getAll()) {

            if(t.getId()==intent.getIntExtra(id,-1))
            {
                exits=true;
                break;
            }
        }


          if(exits)
              favorite.setText("Remove");


        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exits)
                {
                    DataCenter.getInstance(tvShowDetails.this).getFunction().DeleteItme(tv);
                    favorite.setText("Whislist");
                    exits=false;
                }
                else{
                    DataCenter.getInstance(tvShowDetails.this).getFunction().InsertTv(tv);
                     favorite.setText("Remove");
                     exits=true;
                }
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri=Uri.parse(link);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }
        });

    }




    private void setData(tvDetails tv) {

        Discription.setText(tv.getDescription());
        link=tv.getUrl();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(tvShowDetails.this,MainActivity.class));
    }
}