package com.example.few;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {
    static List<NewsObject> newslist = new ArrayList<>();
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        rq = Volley.newRequestQueue(this);
        String url = "https://newsapi.org/v2/top-headlines?country=cn&category=technology&apiKey=cb67547ff7b8438194ac27cdb08cd24d";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArr = response.getJSONArray("articles");
                    for (int i = 0; i < jsonArr.length(); i++) {
                        JSONObject newsDetail = jsonArr.getJSONObject(i);
                        NewsObject news = new NewsObject();

                        String imtUrl = newsDetail.getString("urlToImage");
                        String title = newsDetail.getString("title");
                        String detail = newsDetail.getString("description");
                        String newsUrl = newsDetail.getString("url");
                        String content = newsDetail.getString("content");

                        news.setNewsImageUrl(imtUrl);
                        news.setNewsTitle(title);
                        news.setNewsDetail(detail);
                        news.setNewsUrl(newsUrl);
                        news.setContent(content);
                        newslist.add(news); }
                    Intent loadMainActivity = new Intent(News.this, MainActivity.class);
                    startActivity(loadMainActivity);
                    finish(); }
                catch (JSONException e) {
                    e.printStackTrace(); } }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace(); }});
        rq.add(request); }}