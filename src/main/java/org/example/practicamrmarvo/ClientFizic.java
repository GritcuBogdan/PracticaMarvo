package org.example.practicamrmarvo;

import javafx.beans.property.*;

public class ClientFizic extends Client {
    private StringProperty nume;
    private StringProperty codPersonal;
    private StringProperty adresa;
    private StringProperty telefon;
    private IntegerProperty numarDepozite;

    public ClientFizic(String nume, String codPersonal, String adresa, String telefon, int numarDepozite) {
        super(adresa, telefon);
        this.nume = new SimpleStringProperty(nume);
        this.codPersonal = new SimpleStringProperty(codPersonal);
        this.adresa = new SimpleStringProperty(adresa);
        this.telefon = new SimpleStringProperty(telefon);
        this.numarDepozite = new SimpleIntegerProperty(numarDepozite);
    }

    // JavaFX property getters
    public StringProperty numeProperty() {
        return nume;
    }

    public StringProperty codPersonalProperty() {
        return codPersonal;
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public IntegerProperty numarDepoziteProperty() {
        return numarDepozite;
    }

    // Regular getters and setters
    public String getNume() {
        return nume.get();
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getCodPersonal() {
        return codPersonal.get();
    }

    public void setCodPersonal(String codPersonal) {
        this.codPersonal.set(codPersonal);
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

    public int getNumarDepozite() {
        return numarDepozite.get();
    }

    public void setNumarDepozite(int numarDepozite) {
        this.numarDepozite.set(numarDepozite);
    }

    @Override
    public void afisareDetalii() {
        System.out.println("Nume: " + nume.get());
        System.out.println("Cod Personal: " + codPersonal.get());
        System.out.println("Adresa: " + adresa.get());
        System.out.println("Telefon: " + telefon.get());
        System.out.println("Numar Depozite: " + numarDepozite.get());
    }
}
