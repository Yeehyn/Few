package com.example.few;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListViewAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }

    public final class Plugin{
        public ImageView image;
        public TextView title;
        public TextView link;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plugin plugin=null;
        if(convertView==null){
            plugin=new Plugin();
            convertView=layoutInflater.inflate(R.layout.listviewlayout, null);
            plugin.image=(ImageView)convertView.findViewById(R.id.image);
            plugin.title=(TextView)convertView.findViewById(R.id.title);
            plugin.link=(TextView)convertView.findViewById(R.id.link);
            convertView.setTag(plugin);
        }else{
            plugin=(Plugin)convertView.getTag();
        }
        plugin.image.setBackgroundResource((Integer)data.get(position).get("image"));
        plugin.title.setText((String)data.get(position).get("title"));
        plugin.link.setText((String)data.get(position).get("link"));
        return convertView;
    }
}
