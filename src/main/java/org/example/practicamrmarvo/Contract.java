package org.example.practicamrmarvo;

import java.time.LocalDate;

public class Contract {
    private String contractNumber;
    private LocalDate data;
    private String client; // Representing client as a String
    private String depozit; // Representing depozit as a String
    private double sumaTotala;
    private int termenPastrare;
    private double procentDobanda;

    public Contract(String contractNumber, LocalDate data, String client, String depozit, double sumaTotala, int termenPastrare, double procentDobanda) {
        this.contractNumber = contractNumber;
        this.data = data;
        this.client = client;
        this.depozit = depozit;
        this.sumaTotala = sumaTotala;
        this.termenPastrare = termenPastrare;
        this.procentDobanda = procentDobanda;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDepozit() {
        return depozit;
    }

    public void setDepozit(String depozit) {
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
        return "Contract [numarContract=" + contractNumber + ", data=" + data + ", client=" + client + ", depozit=" + depozit + ", sumaTotala=" + sumaTotala + ", termenPastrare=" + termenPastrare + ", procentDobanda=" + procentDobanda + "]";
    }
}
