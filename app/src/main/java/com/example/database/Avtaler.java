package com.example.database;

import java.util.Date;
import java.util.List;

public class Avtaler {
    public int AvtID;
    public String Navn;
    public String Melding;
    public String Dato;
    public String Klokke;
    public String Sted;


    public int getAvtID() {
        return AvtID;
    }

    public void setAvtID(int avtID) {
        AvtID = avtID;
    }

    public String getNavn() {
        return Navn;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public String getMelding() {
        return Melding;
    }

    public void setMelding(String melding) {
        Melding = melding;
    }

    public String getDato() {
        return Dato;
    }

    public void setDato(String dato) {
        Dato = dato;
    }

    public String getKlokke() {
        return Klokke;
    }

    public void setKlokke(String klokke) {
        Klokke = klokke;
    }

    public String getSted() {
        return Sted;
    }

    public void setSted(String sted) {
        Sted = sted;
    }

}
