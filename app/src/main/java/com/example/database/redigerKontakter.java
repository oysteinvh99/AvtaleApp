package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class redigerKontakter extends AppCompatActivity {
    EditText navn;
    EditText nummer;
    DBHandler dbHelper;
    SQLiteDatabase db;
    long id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getLongExtra("KonID",0);
        setContentView(R.layout.activity_rediger_kontakter);
        navn=(EditText) findViewById(R.id.navn);
        nummer=(EditText) findViewById(R.id.telefon);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
        settVerdier(id);
    }
    public void settVerdier(long ID){
        Kontakt kontakt=dbHelper.finnEnKontakt(db,ID );
        navn.setText(kontakt.Navn);
        nummer.setText(kontakt.Telefon);


    }
    public void slett(View v) {
        Long kontaktid = id;
        dbHelper.slettKontakt(db,kontaktid);
        Intent intent=new Intent(redigerKontakter.this,VisKontakter.class);
        startActivity(intent);
    }
    public void oppdater(View v) {
            Kontakt kontakt = new Kontakt();
            kontakt.setNavn(navn.getText().toString());
            kontakt.setTelefon(nummer.getText().toString());
            kontakt.setKonID(id);
            dbHelper.oppdaterKontakt(db, kontakt);
        Intent intent =new Intent(redigerKontakter.this, VisKontakter.class);
        startActivity(intent);
        finish();
    }
}