package com.example.movieapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapp.Activity.tvShowDetails;
import com.example.movieapp.Data.tvshow;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class mainviewItems extends RecyclerView.Adapter<mainviewItems.ItemHolder> {
    private Context context;
    private List<tvshow> movies=new ArrayList<>();
    final static String id="id";
    final static String name="name";
    final static String country="country";
    final static String network="network";
    final static String imageLink="Image";
    final static String startDate="start";


    public mainviewItems(Context context) {
        this.context = context;
    }

    public void setMovies(List<tvshow> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_child_item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

           holder.name.setText(movies.get(position).getName());
         Glide.with(context).asBitmap().load(movies.get(position).getImageThumbnailPath()).into(holder.image);
         holder.card.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                Intent intent=new Intent(context, tvShowDetails.class);
                 intent.putExtra(id,movies.get(position).getId());
                 intent.putExtra(name,movies.get(position).getName());
                 intent.putExtra(country,movies.get(position).getCountry());
                 intent.putExtra(network,movies.get(position).getNetwork());
                 intent.putExtra(imageLink,movies.get(position).getImageThumbnailPath());
                 intent.putExtra(startDate,movies.get(position).getStartDate());
                 context.startActivity(intent);

                 context.startActivity(intent);
             }
         });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ItemHolder  extends RecyclerView.ViewHolder{

        TextView name;
        ImageView image;
        ConstraintLayout card;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.movie_name);
            image=itemView.findViewById(R.id.item_movie_image);
            card=itemView.findViewById(R.id.card);
        }
    }
}
