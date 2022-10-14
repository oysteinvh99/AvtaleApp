package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText navninn;
    EditText telefoninn;
    EditText idinn;
    TextView utskrift;
    DBHandler dbHelper;
    SQLiteDatabase db;
    ListView Navn;
    kontaktAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* navninn = (EditText) findViewById(R.id.navn);
        telefoninn = (EditText) findViewById(R.id.telefon);
        idinn = (EditText) findViewById(R.id.min_id);
        utskrift = (TextView) findViewById(R.id.utskrift);
        dbHelper = new DBHandler(this);
        db=dbHelper.getWritableDatabase(); */
    }
    public void leggtil(View v) {
        Kontakt kontakt = new Kontakt(navninn.getText().toString(),
                telefoninn.getText().toString());
        dbHelper.leggTilKontakt(db,kontakt);
    }
    public void visall1e(View v) {
            /*String tekst = "";
            List<Kontakt> kontakter = dbHelper.finnAlleKontakter(db);
            for (Kontakt kontakt : kontakter) {
                tekst = tekst + "Id: " + kontakt.getKonID() + ",Navn: " +
                        kontakt.getNavn() + " ,Telefon: " +
                        kontakt.getTelefon() + "\n";
            }
            utskrift.setText(tekst); */
        }
        public void visAvtaler(View v){
        Intent intent=new Intent(MainActivity.this, visAvtaler.class);
        startActivity(intent);
        finish();

        }

    public void slett(View v) {
        Long kontaktid = (Long.parseLong(idinn.getText().toString()));
        dbHelper.slettKontakt(db,kontaktid);
    }
    public void oppdater(View v) {
        Intent intent =new Intent(MainActivity.this, VisKontakter.class);
        startActivity(intent);
            /*Kontakt kontakt = new Kontakt();
            kontakt.setNavn(navninn.getText().toString());
            kontakt.setTelefon(telefoninn.getText().toString());
            kontakt.setKonID(Long.parseLong(idinn.getText().toString()));
            dbHelper.oppdaterKontakt(db, kontakt);*/
        }




    }
