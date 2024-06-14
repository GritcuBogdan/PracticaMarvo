package org.example.practicamrmarvo;

class ClientJuridic extends Client {
    private String codFiscal;
    private String denumire;
    private String tipProprietate;
    private String numeAdministrator;
    private String persoanaContact;

    public ClientJuridic(String codFiscal, String denumire, String tipProprietate, String adresa, String telefon, String numeAdministrator, String persoanaContact) {
        super(adresa, telefon);
        this.codFiscal = codFiscal;
        this.denumire = denumire;
        this.tipProprietate = tipProprietate;
        this.numeAdministrator = numeAdministrator;
        this.persoanaContact = persoanaContact;
    }

    public String getCodFiscal() {
        return codFiscal;
    }

    public void setCodFiscal(String codFiscal) {
        this.codFiscal = codFiscal;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTipProprietate() {
        return tipProprietate;
    }

    public void setTipProprietate(String tipProprietate) {
        this.tipProprietate = tipProprietate;
    }

    public String getNumeAdministrator() {
        return numeAdministrator;
    }

    public void setNumeAdministrator(String numeAdministrator) {
        this.numeAdministrator = numeAdministrator;
    }

    public String getPersoanaContact() {
        return persoanaContact;
    }

    public void setPersoanaContact(String persoanaContact) {
        this.persoanaContact = persoanaContact;
    }

    @Override
    public void afisareDetalii() {
        System.out.println("Cod Fiscal: " + codFiscal);
        System.out.println("Denumire: " + denumire);
        System.out.println("Tip Proprietate: " + tipProprietate);
        System.out.println("Adresa: " + getAdresa());
        System.out.println("Telefon: " + getTelefon());
        System.out.println("Nume Administrator: " + numeAdministrator);
        System.out.println("Persoana Contact: " + persoanaContact);
    }
}