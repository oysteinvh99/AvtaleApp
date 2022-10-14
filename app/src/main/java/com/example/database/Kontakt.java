package com.example.database;

public class Kontakt {
    public  long KonID;
    public String Navn;
    public String Telefon;

    public long getKonID() {
        return KonID;
    }

    public void setKonID(long konID) {
        KonID = konID;
    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public Kontakt(long konID, String navn, String telefon) {
        KonID = konID;
        Navn = navn;
        Telefon = telefon;
    }

    public Kontakt(String navn, String telefon) {
        Navn = navn;
        Telefon = telefon;
    }

    public Kontakt() {
    }
}
