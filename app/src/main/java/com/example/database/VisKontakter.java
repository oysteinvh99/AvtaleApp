package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class VisKontakter extends AppCompatActivity implements kontaktAdapter.ItemClickListener {
    DBHandler dbHelper;
    SQLiteDatabase db;
    kontaktAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_kontakter);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
        visAlle();
    }
    public void visAlle(){
        ArrayList<Kontakt> kontaktArrayList = new ArrayList<Kontakt>();
        kontaktArrayList=dbHelper.finnAlleKontakter(db);
        RecyclerView recyclerView = findViewById(R.id.rvKontakter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new kontaktAdapter(this, kontaktArrayList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(VisKontakter.this, redigerKontakter.class);
        intent.putExtra("KonID", adapter.getItem(position).KonID);
        startActivity(intent);
        finish();
    }
    public void leggtil(View v) {
        Intent intent=new Intent(VisKontakter.this,leggtil.class);
        startActivity(intent);
        finish();
    }
    public void tilbake(View view){
        Intent intent=new Intent(VisKontakter.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}