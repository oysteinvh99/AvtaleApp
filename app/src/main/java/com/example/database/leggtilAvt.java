package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class leggtilAvt extends AppCompatActivity {
    ArrayList<Integer>persid;
    DBHandler dbHelper;
    SQLiteDatabase db;
    EditText navn;
    EditText dato;
    EditText klokke;
    EditText sted;
    EditText melding;
    TextView visDeltakere;
    int avtid;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Navn = "nameKey";
    public static final String Klokke = "klokkeKey";
    public static final String Dato = "datoKey";
    public static final String Sted = "stedKey";
    public static final String Melding = "meldingKey";
    boolean Sjekk=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leggtil_avt);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        persid=new ArrayList<Integer>();
        if( getIntent().hasExtra("liste"))
        {
           persid= (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("liste").clone();
        }
        if( getIntent().hasExtra("Sjekk"))
        {
            Sjekk=getIntent().getBooleanExtra("Sjekk",false);

        }
        navn=(EditText)findViewById(R.id.navnA);
        dato=(EditText)findViewById(R.id.datoA);
        klokke=(EditText)findViewById(R.id.klokkeA);
        sted=(EditText)findViewById(R.id.stedA);
        melding=(EditText)findViewById(R.id.meldingA);
        visDeltakere=(TextView)findViewById(R.id.visdeltakere);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();

        if (persid.size()>0){
            visDeltagere();

        }
        if (Sjekk){
            getData(Sjekk);
        }

    }
    public void leggtilDeltaker(View view){
        Intent intent = new Intent(leggtilAvt.this, recycleCheck.class);
        if (persid.size()>0) {
            intent.putIntegerArrayListExtra("liste", persid);
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Navn,navn.getText().toString());
        editor.putString(Dato,dato.getText().toString());
        editor.putString(Klokke,klokke.getText().toString());
        editor.putString(Klokke,sted.getText().toString());
        editor.putString(Klokke,melding.getText().toString());
        editor.commit();
        // Log.d("List", persid.toString());
        startActivity(intent);



    }
    public void visDeltagere(){
        String holder="";
        for (int id: persid){
            Kontakt kontakt=new Kontakt();
            kontakt=dbHelper.finnEnKontakt(db,(long) id);
            holder+=kontakt.Navn+", "+kontakt.Telefon+"\n";
        }
        visDeltakere.setText(holder.toString());

    }
    public void leggtil(View view){
        Avtaler avtale=new Avtaler();
        avtale.setNavn(navn.getText().toString());
        avtale.setDato(dato.getText().toString());
        avtale.setKlokke(klokke.getText().toString());
        avtale.setSted(sted.getText().toString());
        avtale.setMelding(melding.getText().toString());
        avtid=dbHelper.leggTilAvtale(db,avtale);
        for (int id: persid){
            Deltakere deltaker=new Deltakere();
            deltaker.setAvtID(avtid);
            deltaker.setKonID(id);
            dbHelper.leggTilDeltaker(db,deltaker);
            Intent intent=new Intent(leggtilAvt.this,visAvtaler.class);
            startActivity(intent);
            finish();

        }





    }
    public void getData(boolean sjekk){
        if (sjekk) {

            if (sharedpreferences.contains(Navn)) {
                navn.setText(sharedpreferences.getString(Navn, ""));
            }
            if (sharedpreferences.contains(Dato)) {
                dato.setText(sharedpreferences.getString(Dato, ""));
            }
            if (sharedpreferences.contains(Sted)) {
                sted.setText(sharedpreferences.getString(Sted, ""));
            }
            if (sharedpreferences.contains(Klokke)) {
                klokke.setText(sharedpreferences.getString(Klokke, ""));
            }
            if (sharedpreferences.contains(Melding)) {
                melding.setText(sharedpreferences.getString(Melding,""));
            }
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("navn",navn.getText().toString());
        outState.putString("dato",dato.getText().toString());
        outState.putString("klokke",klokke.getText().toString());
        outState.putString("sted",sted.getText().toString());
        outState.putString("melding",melding.getText().toString());




    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        navn.setText(savedInstanceState.getString("navn"));
        dato.setText(savedInstanceState.getString("dato"));
        klokke.setText(savedInstanceState.getString("klokke"));
        sted.setText(savedInstanceState.getString("sted"));
        melding.setText(savedInstanceState.getString("melding"));
        }
}