package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VisEnAvtale extends AppCompatActivity {
    DBHandler dbHelper;
    SQLiteDatabase db;
    int id;
    TextView navn;
    TextView sted;
    TextView dato;
    TextView klokke;
    TextView melding;
    TextView deltaker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vis_en_avtale);
        id = getIntent().getIntExtra("AvtID",1);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase();
        navn=(TextView) findViewById(R.id.navnAvt);
        melding=(TextView) findViewById(R.id.meldingAvt);
        sted=(TextView) findViewById(R.id.stedAvt);
        dato=(TextView) findViewById(R.id.datoAvt);
        klokke=(TextView) findViewById(R.id.klokkeAvt);
        deltaker=(TextView) findViewById(R.id.DeltakereAvt);
        settVerdier(id);
    }
    public void settVerdier(int id){
        Avtaler avtaler=dbHelper.finnEnAvtaler(db,id);
        navn.setText(avtaler.Navn);
        melding.setText(avtaler.Melding);
        sted.setText(avtaler.Sted);
        dato.setText(avtaler.Dato);
        klokke.setText(avtaler.Klokke);
        finnDeltakere(id);


    }
    public void finnDeltakere(int id){
     ArrayList<Kontakt> kontakter=dbHelper.finnDeltakere(db,id);
            String tekst = "";
            for (Kontakt kontakt : kontakter) {
                tekst += "Navn: " +
                        kontakt.getNavn() +"\n"+ "Telefon: " +
                        kontakt.getTelefon() + "\n";
            }
            deltaker.setText(tekst);


    }
}