package com.example.database;

import static org.xmlpull.v1.XmlPullParser.TEXT;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    static String TABLE_KONTAKTER = "Kontakter";
    static String KEY_ID_KON = "KonID";
    static String KEY_NAME_KON = "Navn";
    static String KEY_PH_NO_KON = "Telefon";
    static String TABLE_AVTALER = "Avtaler";
    static String KEY_ID_AVT = "AvtID";
    static String KEY_NAVN_AVT = "Navn";
    static String KEY_MELDING_AVT = "Melding";
    static String KEY_KL_AVT = "Klokke";
    static String KEY_STED_AVT = "Sted";
    static String KEY_DATO_AVT = "Dato";
    static String TABLE_DELTAKERE = "Deltakere";
    static String KEY_ID_DEL = "DelID";
    static String KEY_FKKON_DEL = "KonID";
    static String KEY_FKAVT_DEL = "AvtID";
    static int DATABASE_VERSION = 3;
    static String DATABASE_NAME = "Telefonkontakt10";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String LAG_TABELL = "CREATE TABLE " + TABLE_KONTAKTER + " (" + KEY_ID_KON +
                " INTEGER PRIMARY KEY, " + KEY_NAME_KON + " TEXT, " + KEY_PH_NO_KON +
                " TEXT " + ")";
        Log.d("SQL", LAG_TABELL);
        db.execSQL(LAG_TABELL);


        String LAG_TABELL1 = "CREATE TABLE " + TABLE_AVTALER + " (" +
                KEY_ID_AVT + " INTEGER PRIMARY KEY, " +
                KEY_NAVN_AVT + " TEXT, " +
                KEY_DATO_AVT + " TEXT, " +
                KEY_KL_AVT + " TEXT, " +
                KEY_STED_AVT + " TEXT, " +
                KEY_MELDING_AVT + " TEXT " +
                ")";

        Log.d("SQL", LAG_TABELL1);
        db.execSQL(LAG_TABELL1);

        String LAG_TABELL2 = "CREATE TABLE " + TABLE_DELTAKERE + " (" +
                KEY_ID_DEL + " INTEGER PRIMARY KEY, " +
                KEY_FKAVT_DEL + " INTEGER, " +
                KEY_FKKON_DEL + " INTEGER, " +
                "FOREIGN KEY" + "(" + KEY_FKAVT_DEL + ")" + " REFERENCES " + TABLE_AVTALER + " (" + KEY_ID_AVT + "), " +
                "FOREIGN KEY" + "(" + KEY_FKKON_DEL + ")" + " REFERENCES " + TABLE_KONTAKTER + " (" + KEY_ID_KON + ") " +

                ")";

        Log.d("SQL", LAG_TABELL2);
        db.execSQL(LAG_TABELL2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KONTAKTER);
        db.execSQL("DROP TABLE IF EXISTS " + "Avtaler");
        onCreate(db);
    }

    public void leggTilKontakt(SQLiteDatabase db, Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_KON, kontakt.getNavn());
        values.put(KEY_PH_NO_KON, kontakt.getTelefon());
        db.insert(TABLE_KONTAKTER, null, values);
    }

    public ArrayList<Kontakt> finnAlleKontakter(SQLiteDatabase db) {
        ArrayList<Kontakt> kontaktListe = new ArrayList<Kontakt>();
        String selectQuery = "SELECT * FROM " + TABLE_KONTAKTER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Kontakt kontakt = new Kontakt();
                kontakt.setKonID(cursor.getLong(0));
                kontakt.setNavn(cursor.getString(1));
                kontakt.setTelefon(cursor.getString(2));
                kontaktListe.add(kontakt);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return kontaktListe;
    }

    public ArrayList<KontaktSjekket> finnAlleKontakterS(SQLiteDatabase db) {
        ArrayList<KontaktSjekket> kontaktListe = new ArrayList<KontaktSjekket>();
        String selectQuery = "SELECT * FROM " + TABLE_KONTAKTER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                KontaktSjekket kontakt = new KontaktSjekket();
                kontakt.setKonID(cursor.getLong(0));
                kontakt.setNavn(cursor.getString(1));
                kontakt.setTelefon(cursor.getString(2));
                kontaktListe.add(kontakt);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return kontaktListe;
    }

    public ArrayList<Avtaler> finnAlleAvtaler(SQLiteDatabase db) {
        ArrayList<Avtaler> avtaleListe = new ArrayList<Avtaler>();
        String selectQuery = "SELECT * FROM " + TABLE_AVTALER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Avtaler avtale = new Avtaler();
                avtale.setAvtID(cursor.getInt(0));
                avtale.setSted(cursor.getString(1));
                avtale.setDato(cursor.getString(2));
                avtale.setKlokke(cursor.getString(3));
                avtaleListe.add(avtale);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return avtaleListe;
    }

    public void slettKontakt(SQLiteDatabase db, Long inn_id) {
        db.delete(TABLE_KONTAKTER, KEY_ID_KON + " =? ", new String[]{
                Long.toString(inn_id)});
    }

    public int oppdaterKontakt(SQLiteDatabase db, Kontakt kontakt) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME_KON, kontakt.getNavn());
        values.put(KEY_PH_NO_KON, kontakt.getTelefon());
        int endret = db.update(TABLE_KONTAKTER, values, KEY_ID_KON + "= ?",
                new String[]{String.valueOf(kontakt.getKonID())});
        return endret;
    }

    public Kontakt finnEnKontakt(SQLiteDatabase db, long inn_id) {
        Kontakt kontakt = new Kontakt();
        String selectQuery = "Select * from " + TABLE_KONTAKTER +
                " where " + KEY_ID_KON + " = " + inn_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            kontakt.setKonID(cursor.getLong(0));
            kontakt.setNavn(cursor.getString(1));
            kontakt.setTelefon(cursor.getString(2));
        }
        return kontakt;


    }

    public Avtaler finnEnAvtaler(SQLiteDatabase db, int inn_id) {
        Avtaler avtale = new Avtaler();
        String selectQuery = "Select * from " + TABLE_AVTALER +
                " where " + KEY_ID_AVT + " = " + inn_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            avtale.setAvtID(cursor.getInt(0));
            avtale.setNavn(cursor.getString(1));
            avtale.setDato(cursor.getString(2));
            avtale.setKlokke(cursor.getString(3));
            avtale.setSted(cursor.getString(4));
            avtale.setMelding(cursor.getString(5));
        }
        return avtale;
    }

    public ArrayList<Kontakt> finnDeltakere(SQLiteDatabase db, int inn_id) {
        ArrayList<Kontakt> kontaktListe = new ArrayList<Kontakt>();
        String selectQuery = "Select " + TABLE_KONTAKTER + "." + KEY_NAME_KON +
                ", " + TABLE_KONTAKTER + "." + KEY_PH_NO_KON + " from " + TABLE_KONTAKTER + ", " + TABLE_DELTAKERE +
                " where " + TABLE_DELTAKERE + "." + KEY_FKKON_DEL + "=" + TABLE_KONTAKTER + "." + KEY_ID_KON +
                " and " + TABLE_DELTAKERE + "." + KEY_FKAVT_DEL + "=" + inn_id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Kontakt kontakt = new Kontakt();
                kontakt.setNavn(cursor.getString(0));
                kontakt.setTelefon(cursor.getString(1));
                kontaktListe.add(kontakt);
            }
            while (cursor.moveToNext());
            cursor.close();
        }
        return kontaktListe;


    }

    public int leggTilAvtale(SQLiteDatabase db, Avtaler avtaler) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAVN_AVT, avtaler.getNavn());
        values.put(KEY_DATO_AVT, avtaler.getDato());
        values.put(KEY_KL_AVT, avtaler.getKlokke());
        values.put(KEY_STED_AVT, avtaler.getSted());
        values.put(KEY_MELDING_AVT, avtaler.getMelding());
        db.insert(TABLE_AVTALER, null, values);
        int id=0;
        String selectQuery =  "SELECT last_insert_rowid() from "+TABLE_AVTALER;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        Log.d("id", String.valueOf(id));
        return id;


    }

    public void leggTilDeltaker(SQLiteDatabase db, Deltakere deltaker) {
        ContentValues values = new ContentValues();
        values.put(KEY_FKAVT_DEL, deltaker.getAvtID());
        values.put(KEY_FKKON_DEL, deltaker.getKonID());
        db.insert(TABLE_DELTAKERE, null, values);

    }

}