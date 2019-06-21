package com.example.few;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.Holder>{
    private Context context;

    public AdapterRecyclerView(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.recyclerview, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.titleview.setText(News.newslist.get(position).getNewsTitle());
        holder.detailview.setText(News.newslist.get(position).getNewsDetail());
        Glide.with(context).load(News.newslist.get(position).getNewsImageUrl()).into(holder.imageview);

        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Web.class);
                myIntent.putExtra("newsUrl",News.newslist.get(position).getNewsUrl());
                v.getContext().startActivity(myIntent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "This is shared from Few app";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return News.newslist.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView titleview;
        TextView detailview;
        TextView hiddenurl;
        Button explore;
        Button share;

        public Holder(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            titleview = itemView.findViewById(R.id.titletextview);
            detailview = itemView.findViewById(R.id.detailtextview);
            explore = itemView.findViewById(R.id.explorebutton);
            share = itemView.findViewById(R.id.sharebutton);
            hiddenurl = itemView.findViewById(R.id.hiddenurltextview);
        }
    }
}