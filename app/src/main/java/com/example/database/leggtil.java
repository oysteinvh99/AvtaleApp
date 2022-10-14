package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class leggtil extends AppCompatActivity {
    EditText navn;
    EditText nummer;
    DBHandler dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leggtil);
        navn=(EditText)findViewById(R.id.navn);
        nummer=(EditText)findViewById(R.id.telefon);

        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
    }
    public void leggtil(View v) {
        Kontakt kontakt = new Kontakt(navn.getText().toString(),
                nummer.getText().toString());
        dbHelper.leggTilKontakt(db,kontakt);
        Intent intent=new Intent(leggtil.this,VisKontakter.class);
        startActivity(intent);
        finish();
    }
    public void tilbake(View v){
        Intent intent=new Intent(leggtil.this,MainActivity.class);
        startActivity(intent);
        finish();

    }
}