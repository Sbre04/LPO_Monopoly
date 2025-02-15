package com.monopoly.model;


public class Prob {

    public static final Prob[] ITEMS = {
            new Prob("Interessi Bancari", "La banca vi paga gli interessi sul vostro conto corrente: ritirate 10 euro.", 10, 0,0),
            new Prob("Premio Lotteria", "Avete vinto un premio di consolazione alla lotteria: 20 euro.", 20, 0,0),
            new Prob("Vendita Azioni", "Avete venduto delle azioni: ritirate 50 euro.", 50, 0,0),
            new Prob("Terno al Lotto", "Avete vinto un terno al lotto: ritirate 100 euro.", 100, 0,0),
            new Prob("Compleanno", "È il vostro compleanno: ecco un regalo da 15 euro.", 15, 0,0),
            new Prob("Multa", "Multa di 50 euro.", 0, 50,0),
            new Prob("Tassa di Circolazione", "Dovete pagare una tassa di circolazione di 15 euro.", 0, 15,0),
            //new Prob("Vicolo Corto", "Andate fino a Vicolo Corto.", 0, 0,1),
            new Prob("Guida in Stato di Ebbrezza", "Siete stati multati per guida in stato di ebbrezza: pagate 100 euro.", 0, 100,0),
            new Prob("Uscita Gratis", "Uscite gratis di prigione.", 0, 0,0),
    };


    String titolo;
    String corpo;
    int soldiVinti;
    int soldiPersi;
    int Spostati;


    public Prob(String titolo, String corpo, int soldiVinti, int soldiPersi, int Spostati) {
        this.titolo = titolo;
        this.corpo = corpo;
        this.soldiVinti = soldiVinti;
        this.soldiPersi = soldiPersi;
        this.Spostati = Spostati;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getCorpo() {
        return corpo;
    }

    public int getSoldiVinti() {
        return soldiVinti;
    }

    public int getSoldiPersi() {
        return soldiPersi;
    }

    public int getSpostati() {
        return Spostati;
    }


}

