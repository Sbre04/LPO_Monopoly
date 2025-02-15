package com.monopoly.model;


public class Imp {
    public static final Imp[] ITEMS = {
            new Imp("Interessi Bancari", "La banca vi paga gli interessi sul vostro conto corrente: ritirate 50 lire.", 50, 0,0),
            new Imp("Premio Lotteria", "Avete vinto un premio di consolazione alla lotteria: ritirate 10 euro.", 10, 0,0),
            new Imp("Vendita Azioni", "Avete venduto delle azioni: ritirate 25 euro.", 25, 0,0),
            new Imp("Terno al Lotto", "Avete vinto un terno al lotto: ritirate 50 euro.", 50, 0,0),
            new Imp("Compleanno", "È il vostro compleanno: ciascun giocatore vi deve 10 euro.", 10, 0,0),
            new Imp("Multa", "Multa di 50 euro.", 0, 50,0),
            new Imp("Tassa di Circolazione", "Dovete pagare una tassa di circolazione di 10 euro.", 0, 10,0),
            new Imp("Guida in Stato di Ebbrezza", "Siete stati multati per guida in stato di ebbrezza: pagate 10 euro.", 0, 10,0),
            new Imp("Uscita Gratis", "Uscite gratis di prigione.", 1, 0,0),
    };
    String titolo;
    String corpo;
    int soldiVinti;
    int soldiPersi;
    int Spostati;

    public Imp(String titolo, String corpo, int soldiVinti, int soldiPersi, int Spostati){
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
