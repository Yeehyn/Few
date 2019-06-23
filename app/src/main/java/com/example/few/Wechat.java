package com.example.few;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Wechat extends AppCompatActivity {
    ListView rss3;
    ArrayList<String> title3;
    ArrayList<String> link3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat);

        rss3 = (ListView) findViewById(R.id.rss3);

        title3 = new ArrayList<String>();
        link3 = new ArrayList<String>();

        new ProcessInBackground().execute();

        rss3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                Uri uri = Uri.parse(link1.get(position));
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                Uri uri = Uri.parse(link3.get(position));
                String url=uri.toString();
                Intent intent = new Intent(view.getContext(), Web.class);
                intent.putExtra("newsUrl",url);
                startActivity(intent); }}); }

    public class ProcessInBackground extends AsyncTask<Integer, Void, String> {
        ProgressDialog progressDialog = new ProgressDialog(Wechat.this);
        Exception exception = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("稍等");
            progressDialog.show(); }

        @Override
        protected String doInBackground(Integer... integers) {
            try{
                URL url = new URL("https://s2.rsshub.app/jike/topic/58cce6ca5690040cf4baedf1");
                HttpsURLConnection connection=(HttpsURLConnection)url.openConnection();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(connection.getInputStream(), "UTF_8");
                boolean insideItem = false;
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true; }
                        else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                title3.add(xpp.nextText()); } }
                        else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                link3.add(xpp.nextText()); } } }
                    else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false; }
                    eventType = xpp.next();} }
            catch (XmlPullParserException e) {
                e.printStackTrace(); }
            catch (MalformedURLException e) {
                e.printStackTrace(); }
            catch (IOException e) {
                e.printStackTrace(); }
            return null; }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Wechat.this, android.R.layout.simple_list_item_1, title3);
            rss3.setAdapter(adapter);
            progressDialog.dismiss(); }}}