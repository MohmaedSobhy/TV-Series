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

public class ItemsearchAdapter extends RecyclerView.Adapter<ItemsearchAdapter.itemHolder> {
    Context context;
    List<tvshow> lists=new ArrayList<>();
    final static String id="id";
    final static String name="name";
    final static String country="country";
    final static String network="network";
    final static String imageLink="Image";
    final static String startDate="start";
    public ItemsearchAdapter(Context context) {
        this.context = context;
    }

    public void setLists(List<tvshow> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public itemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_card,parent,false);
        return new itemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemHolder holder, int position) {
        Glide.with(context).asBitmap().load(lists.get(position).getImageThumbnailPath()).into(holder.imageView);
        holder.name.setText(lists.get(position).getName());
        holder.network.setText(lists.get(position).getNetwork());
        holder.startdate.setText(lists.get(position).getStartDate());
        holder.country.setText(lists.get(position).getCountry());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, tvShowDetails.class);
                intent.putExtra(id,lists.get(position).getId());
                intent.putExtra(name,lists.get(position).getName());
                intent.putExtra(country,lists.get(position).getCountry());
                intent.putExtra(network,lists.get(position).getNetwork());
                intent.putExtra(imageLink,lists.get(position).getImageThumbnailPath());
                intent.putExtra(startDate,lists.get(position).getStartDate());
                context.startActivity(intent);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class itemHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,network,startdate,country;
        ConstraintLayout card;
        public itemHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image_view_item);
            name=itemView.findViewById(R.id.textitemname);
            network=itemView.findViewById(R.id.textnetwork);
            startdate=itemView.findViewById(R.id.item_start_date);
            country=itemView.findViewById(R.id.textcountry);
            card=itemView.findViewById(R.id.constraintcard);

        }
    }
}
