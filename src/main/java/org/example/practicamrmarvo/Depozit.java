package org.example.practicamrmarvo;

public class Depozit {
    private String tip;
    private String denumire;
    private double procentAnual;
    private String valuta;

    public Depozit() {}

    public Depozit(String tip, String denumire, double procentAnual, String valuta) {
        this.tip = tip;
        this.denumire = denumire;
        this.procentAnual = procentAnual;
        this.valuta = valuta;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public double getProcentAnual() {
        return procentAnual;
    }

    public void setProcentAnual(double procentAnual) {
        this.procentAnual = procentAnual;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    @Override
    public String toString() {
        return "Depozit [tip=" + tip + ", denumire=" + denumire + ", procentAnual=" + procentAnual + ", valuta=" + valuta + "]";
    }
}
