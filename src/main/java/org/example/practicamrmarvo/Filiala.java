package org.example.practicamrmarvo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Filiala {
    private final StringProperty nume;
    private final StringProperty adresa;
    private final StringProperty telefon;

    public Filiala(String nume, String adresa, String telefon) {
        this.nume = new SimpleStringProperty(nume);
        this.adresa = new SimpleStringProperty(adresa);
        this.telefon = new SimpleStringProperty(telefon);
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public String getNume() {
        return nume.get();
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getAdresa() {
        return adresa.get();
    }

    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String telefon) {
        this.telefon.set(telefon);
    }

    @Override
    public String toString() {
        return "Filiala [nume=" + nume.get() + ", adresa=" + adresa.get() + ", telefon=" + telefon.get() + "]";
    }
}
