package com.example.database;

public class KontaktSjekket extends Kontakt{
    Kontakt kontakt;
    Boolean erSjekket;

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public Boolean getErSjekket() {
        return erSjekket;
    }

    public void setErSjekket(Boolean erSjekket) {
        this.erSjekket = erSjekket;
    }
}
