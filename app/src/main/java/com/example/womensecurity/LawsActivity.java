package com.example.womensecurity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class LawsActivity extends AppCompatActivity {

    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);

        Toolbar toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv1=findViewById(R.id.text_law1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv2=findViewById(R.id.text_law2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law2Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv3=findViewById(R.id.text_law3);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv4=findViewById(R.id.text_law4);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law4Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv5=findViewById(R.id.text_law5);
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law5Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv6=findViewById(R.id.text_law6);
        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law6Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv7=findViewById(R.id.text_law7);
        tv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law7Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv8=findViewById(R.id.text_law8);
        tv8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law8Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv9=findViewById(R.id.text_law9);
        tv9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law9Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv10=findViewById(R.id.text_law10);
        tv10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law10Activity.class);
                startActivity(intent);
                finish();
            }
        });

        tv11=findViewById(R.id.text_law11);
        tv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LawsActivity.this, Law11Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent intent=new Intent(LawsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(LawsActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
