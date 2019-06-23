package com.example.few;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder>{
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context; }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView imageview;
        TextView titleview;
        TextView detailview;
        TextView hiddenurl;
        Button explore;
//        Button share;

        public Holder(View itemView) {
            super(itemView);
            imageview = itemView.findViewById(R.id.imageview);
            titleview = itemView.findViewById(R.id.titletextview);
            detailview = itemView.findViewById(R.id.detailtextview);
            hiddenurl = itemView.findViewById(R.id.hiddenurltextview);

            explore = itemView.findViewById(R.id.explorebutton);
            explore.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG);
            explore.getPaint().setAntiAlias(true);

//            share = itemView.findViewById(R.id.sharebutton);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.recyclerview, parent, false);
        return new Holder(view); }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.titleview.setText(News.newslist.get(position).getNewsTitle());
        holder.detailview.setText(News.newslist.get(position).getNewsDetail());
        Glide.with(context).load(News.newslist.get(position).getNewsImageUrl()).into(holder.imageview);

        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),Web.class);
                myIntent.putExtra("newsUrl",News.newslist.get(position).getNewsUrl());
                v.getContext().startActivity(myIntent);
            }
        });

//        holder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//        sharingIntent.setType("text/plain");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject：");
//                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is shared from UN1 app");
//                v.getContext().startActivity(Intent.createChooser(sharingIntent, "Via"));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return News.newslist.size();
    }
}
