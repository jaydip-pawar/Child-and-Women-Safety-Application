package com.example.womensecurity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class VideosActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView pc1,pc2,pc3,pc4,pc5,pc6,pc7,pc8,pc9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pc1=(ImageView)findViewById(R.id.pc1);
        pc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc2=(ImageView)findViewById(R.id.pc2);
        pc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc3=(ImageView)findViewById(R.id.pc3);
        pc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc4=(ImageView)findViewById(R.id.pc4);
        pc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video4Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc5=(ImageView)findViewById(R.id.pc5);
        pc5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video5Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc6=(ImageView)findViewById(R.id.pc6);
        pc6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video6Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc7=(ImageView)findViewById(R.id.pc7);
        pc7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video7Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc8=(ImageView)findViewById(R.id.pc8);
        pc8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video8Activity.class);
                startActivity(intent);
                finish();
            }
        });

        pc9=(ImageView)findViewById(R.id.pc9);
        pc9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(VideosActivity.this, Video9Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent intent=new Intent(VideosActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(VideosActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
