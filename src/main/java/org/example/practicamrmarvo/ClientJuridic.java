package org.example.practicamrmarvo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientJuridic extends Client {
    private StringProperty codFiscal;
    private StringProperty denumire;
    private StringProperty tipProprietate;
    private StringProperty adresa;
    private StringProperty telefon;
    private StringProperty numeAdministrator;
    private StringProperty persoanaContact;

    public ClientJuridic(String codFiscal, String denumire, String tipProprietate, String adresa, String telefon,
                         String numeAdministrator, String persoanaContact) {
        super(adresa, telefon);
        this.codFiscal = new SimpleStringProperty(codFiscal);
        this.denumire = new SimpleStringProperty(denumire);
        this.tipProprietate = new SimpleStringProperty(tipProprietate);
        this.adresa = new SimpleStringProperty(adresa);
        this.telefon = new SimpleStringProperty(telefon);
        this.numeAdministrator = new SimpleStringProperty(numeAdministrator);
        this.persoanaContact = new SimpleStringProperty(persoanaContact);
    }

    // JavaFX property getters
    public StringProperty codFiscalProperty() {
        return codFiscal;
    }

    public StringProperty denumireProperty() {
        return denumire;
    }

    public StringProperty tipProprietateProperty() {
        return tipProprietate;
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public StringProperty telefonProperty() {
        return telefon;
    }

    public StringProperty numeAdministratorProperty() {
        return numeAdministrator;
    }

    public StringProperty persoanaContactProperty() {
        return persoanaContact;
    }

    // Regular getters and setters
    public String getCodFiscal() {
        return codFiscal.get();
    }

    public void setCodFiscal(String codFiscal) {
        this.codFiscal.set(codFiscal);
    }

    public String getDenumire() {
        return denumire.get();
    }

    public void setDenumire(String denumire) {
        this.denumire.set(denumire);
    }

    public String getTipProprietate() {
        return tipProprietate.get();
    }

    public void setTipProprietate(String tipProprietate) {
        this.tipProprietate.set(tipProprietate);
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

    public String getNumeAdministrator() {
        return numeAdministrator.get();
    }

    public void setNumeAdministrator(String numeAdministrator) {
        this.numeAdministrator.set(numeAdministrator);
    }

    public String getPersoanaContact() {
        return persoanaContact.get();
    }

    public void setPersoanaContact(String persoanaContact) {
        this.persoanaContact.set(persoanaContact);
    }

    @Override
    public void afisareDetalii() {
        System.out.println("Cod Fiscal: " + codFiscal.get());
        System.out.println("Denumire: " + denumire.get());
        System.out.println("Tip Proprietate: " + tipProprietate.get());
        System.out.println("Adresa: " + adresa.get());
        System.out.println("Telefon: " + telefon.get());
        System.out.println("Nume Administrator: " + numeAdministrator.get());
        System.out.println("Persoana Contact: " + persoanaContact.get());
    }
}
