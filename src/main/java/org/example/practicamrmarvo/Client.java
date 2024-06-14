package org.example.practicamrmarvo;

import java.util.*;
import java.time.LocalDate;

abstract class Client {
    private String adresa;
    private String telefon;

    public Client(String adresa, String telefon) {
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public abstract void afisareDetalii();
}
