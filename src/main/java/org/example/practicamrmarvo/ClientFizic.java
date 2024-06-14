package org.example.practicamrmarvo;

class ClientFizic extends Client {
    private String nume;
    private String codPersonal;
    private int numarDepozite;

    public ClientFizic(String nume, String codPersonal, String adresa, String telefon, int numarDepozite) {
        super(adresa, telefon);
        this.nume = nume;
        this.codPersonal = codPersonal;
        this.numarDepozite = numarDepozite;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCodPersonal() {
        return codPersonal;
    }

    public void setCodPersonal(String codPersonal) {
        this.codPersonal = codPersonal;
    }

    public int getNumarDepozite() {
        return numarDepozite;
    }

    public void setNumarDepozite(int numarDepozite) {
        this.numarDepozite = numarDepozite;
    }

    @Override
    public void afisareDetalii() {
        System.out.println("Nume: " + nume);
        System.out.println("Cod Personal: " + codPersonal);
        System.out.println("Adresa: " + getAdresa());
        System.out.println("Telefon: " + getTelefon());
        System.out.println("Numar Depozite: " + numarDepozite);
    }
}