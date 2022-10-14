package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class visAvtaler extends AppCompatActivity implements AvtaleAdapter.ItemClickListener {
    DBHandler dbHelper;
    SQLiteDatabase db;
    AvtaleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_avtaler);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
        visAlle();
    }
    public void visAlle(){
        ArrayList<Avtaler> avtalerArrayList = new ArrayList<Avtaler>();
        avtalerArrayList=dbHelper.finnAlleAvtaler(db);

// Create the adapter to convert the array to views
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvAvtaler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AvtaleAdapter(this, avtalerArrayList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(visAvtaler.this, VisEnAvtale.class);
        intent.putExtra("AvtID", adapter.getItem(position).AvtID);
        startActivity(intent);
        finish();
    }
    public void leggtil(View v) {
        Intent intent=new Intent(visAvtaler.this,leggtilAvt.class);
        startActivity(intent);
        finish();
    }
    public void tilbake(View v){
        Intent intent=new Intent(visAvtaler.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}