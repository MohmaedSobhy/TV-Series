package com.example.movieapp.FRAGMENT;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.movieapp.Adapter.base_home_items;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private Context context;
    private boolean check;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        ImageView imageView=view.findViewById(R.id.noInternet);

        if(check)
        {
            LinearLayout linearLayout=view.findViewById(R.id.datalinear);
            imageView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
            RecyclerView recyclerView=view.findViewById(R.id.parentrecycler);
            base_home_items adapter=new base_home_items(context);
            List<Integer> pages=new ArrayList<>();
            pages.add(1);
            pages.add(4);
            pages.add(8);
            adapter.setPages(pages);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            linearLayout.setVisibility(View.VISIBLE);

            ImageSlider slider=view.findViewById(R.id.slider);
            List<SlideModel> slideModels=new ArrayList<>();
            slideModels.add(new SlideModel("https://img1.hotstarext.com/image/upload/f_auto/sources/r1/cms/prod/4909/474909-h","Game of Thrones"));
            slideModels.add(new SlideModel("https://images-na.ssl-images-amazon.com/images/I/91CgPC6lM9L._RI_.jpg","Super Girl"));
            slideModels.add(new SlideModel("https://miro.medium.com/max/2560/1*98iiAnkKsikPsXfNBvz0qQ.png","Hundred"));
            slideModels.add(new SlideModel("https://images-na.ssl-images-amazon.com/images/S/sgp-catalog-images/region_US/bbc-AVBC120X-Full-Image_GalleryCover-en-US-1528300081902._UY500_UX667_RI_VY47o7gxqHqk4zSMcpxKJurzmTCmkG9L_TTW_.jpg","Sherlock"));
            slider.setImageList(slideModels,true);
        }
        else{
            imageView.setVisibility(View.VISIBLE);
        }




        return view;
    }


    public Home(Context context, boolean check) {
        this.context = context;
        this.check = check;
    }

}