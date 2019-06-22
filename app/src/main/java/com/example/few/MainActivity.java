package com.example.few;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.core.view.GravityCompat;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerview;
    Toolbar toolbar;
    DrawerLayout drawerlayout;
    NavigationView navigationview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(new AdapterRecyclerView(MainActivity.this) {});

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        drawerlayout = findViewById(R.id.drawer_layout);

        navigationview = findViewById(R.id.nav_view);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerlayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.lecture: {
                        Intent intent = new Intent(MainActivity.super.getBaseContext(),Lecture.class);
                        startActivity(intent);
                        break; }

                    case R.id.college: {
                        Intent intent = new Intent(MainActivity.super.getBaseContext(),College.class);
                        startActivity(intent);
                        break; }

                    case R.id.wechat: {
                        Intent intent = new Intent(MainActivity.super.getBaseContext(),Wechat.class);
                        startActivity(intent);
                        break; }

                    case R.id.share: {
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Wish you like UN1 !");
                        startActivity(Intent.createChooser(sharingIntent, "Via"));
                        break; }

                    default: {
                        Toast.makeText(getApplicationContext(),item.getTitle(),Toast.LENGTH_SHORT).show(); } }
                return true; }}); }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                drawerlayout.openDrawer(GravityCompat.START);
                return true; } }
        return super.onOptionsItemSelected(item); }}
