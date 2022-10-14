package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class recycleCheck extends AppCompatActivity implements KontaktErSjekketAdapter.ItemClickListener {
    DBHandler dbHelper;
    SQLiteDatabase db;
    KontaktErSjekketAdapter adapter;
    ArrayList<Integer>idholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_check);
        idholder=new ArrayList<>();
        if( getIntent().hasExtra("liste"))
        {
            idholder= (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("liste").clone();        }
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
        visAlle();
    }
    public void visAlle(){
        ArrayList<KontaktSjekket> kontaktArrayList = new ArrayList<KontaktSjekket>();
        kontaktArrayList=dbHelper.finnAlleKontakterS(db);

// Create the adapter to convert the array to views
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvKontakterS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KontaktErSjekketAdapter(this, kontaktArrayList);
        adapter.setClickListener(this);
        adapter.sjekketBokser(idholder);
        recyclerView.setAdapter(adapter);

    }
    @Override
    public void onItemClick(View view, int position) {

    }
    public void logg(View v){
        ArrayList<Integer> liste=adapter.getPersonIden();
        Log.d("List", adapter.getPersonIden().toString());
        Intent intent=new Intent(recycleCheck.this,leggtilAvt.class);
        intent.putIntegerArrayListExtra("liste",liste);
        intent.putExtra("Sjekk",true);
        startActivity(intent);
        finish();
    }
}