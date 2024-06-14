package org.example.practicamrmarvo;

import java.time.LocalDate;

class Contract {
    private String numarContract;
    private LocalDate data;
    private Client client;
    private Depozit depozit;
    private double sumaTotala;
    private int termenPastrare;
    private double procentDobanda;

    public Contract(String numarContract, LocalDate data, Client client, Depozit depozit, double sumaTotala, int termenPastrare, double procentDobanda) {
        this.numarContract = numarContract;
        this.data = data;
        this.client = client;
        this.depozit = depozit;
        this.sumaTotala = sumaTotala;
        this.termenPastrare = termenPastrare;
        this.procentDobanda = procentDobanda;
    }

    public String getNumarContract() {
        return numarContract;
    }

    public void setNumarContract(String numarContract) {
        this.numarContract = numarContract;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Depozit getDepozit() {
        return depozit;
    }

    public void setDepozit(Depozit depozit) {
        this.depozit = depozit;
    }

    public double getSumaTotala() {
        return sumaTotala;
    }

    public void setSumaTotala(double sumaTotala) {
        this.sumaTotala = sumaTotala;
    }

    public int getTermenPastrare() {
        return termenPastrare;
    }

    public void setTermenPastrare(int termenPastrare) {
        this.termenPastrare = termenPastrare;
    }

    public double getProcentDobanda() {
        return procentDobanda;
    }

    public void setProcentDobanda(double procentDobanda) {
        this.procentDobanda = procentDobanda;
    }

    public double calculeazaVenit() {
        return sumaTotala * (procentDobanda / 100) * termenPastrare / 12;
    }

    @Override
    public String toString() {
        return "Contract [numarContract=" + numarContract + ", data=" + data + ", client=" + client + ", depozit=" + depozit + ", sumaTotala=" + sumaTotala + ", termenPastrare=" + termenPastrare + ", procentDobanda=" + procentDobanda + "]";
    }
}
