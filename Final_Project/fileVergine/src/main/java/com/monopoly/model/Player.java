package com.monopoly.model;

import com.monopoly.application.HelloApplication;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player{

    String nome;
   IntegerProperty soldi;
   boolean prigione;
   int posizione;
   int turniInPrigione;


   /**
    * Lista che tiene traccia delle proprieta di un giocatore aggiornando anche i valori su altre finestre
    */
   ObservableList<String> proprietaAcquistate;


    /**
     * Costruttore di Property.
     * @param nome Nome del giocatore.
     * @param soldi Soldi del giocatore.
     * @param prigione verifica se il player è in prigione o no.
     * @param posizione verifica posizione del player.
     * @param turniInPrigione Contatore per i turni da saltare.
     */

   public Player(String nome ,int soldi, boolean prigione, int posizione,int turniInPrigione){
       this.nome = nome;
       this.soldi = new SimpleIntegerProperty(soldi);
       this.prigione = prigione;
       this.posizione = posizione;
       this.proprietaAcquistate = FXCollections.observableArrayList();
       this.turniInPrigione = 0;

   }


   public static Player[] giocatori = new Player[HelloApplication.player];

    /**
     * Metodo per la creazione dei giocatori
     * @param nome Nome del giocatore
     * @param i indice del giocatore da creare
     */
   public static void creaGiocatori(String nome, int i){
       //for(int i = 0; i<HelloApplication.player;i++){
           giocatori[i] = new Player(nome ,800,false,0,0);

       //}
   }

    /**
     * Setter per la posizione del giocatore
     * @param posizione1 posizione del giocatore
     */
   public void setPosizione(int posizione1){
       this.posizione = posizione1;
   }

    /**
     * Getter per il valore della prigione (true,false)
     * @return true o false
     */
   public Boolean getPrigione(){
       return this.prigione;
   }

    /**
     * Setter per impostare la prigione a false
     */
    public void prigioneFalse(){
        this.prigione = false;
    }

    /**
     * Setter per impostare la prigione a true
     */
    public void prigioneTrue(){
        this.prigione = true;
    }

    /**
     * Getter per i turni in prigione
     * @return turni in prigione
     */
    public int getTurniPrigione(){
        return this.turniInPrigione;
    }

    /**
     * Metodo per decrementare i turni in prigione
     */
    public void decrementaTurniInPrigione() {
        if (turniInPrigione > 0) {
            this.turniInPrigione--;
        }
    }

    /**
     * Metodo per aggiungere turni in prigione
     */
    public void aggiungiTurniInPrigione() {
        this.turniInPrigione = 2;
    }

    /**
     * Setter per i soldi
     * @param soldi1 nuovo conto del player
     */
   public void setSoldi(int soldi1){
       this.soldi.set(soldi1);
   }

    /**
     * Getter per prendere quanti i soldi del player
     * @return
     */
    public int getSoldi(){
        return this.soldi.get();
    }

    /**
     * Getter per prendere il nome di un player
     * @return
     */
    public String getNome(){
        return this.nome;
    }

    /**
     * Getter per prendere la posizione di un  player
     * @return
     */
   public int getPosizione(){
       return this.posizione;
   }

    /**
     * Getter per prendere tutte le proprietà acquistate dalla observable list
     * @return
     */
   public ObservableList<String> getProprietaAcquistate(){
       return proprietaAcquistate;
   }

    /**
     * Settter per aggiungere una proprietà
     * @param proprieta1 nome della nuova proprietà
     */
   public void aggiungiProprieta(String proprieta1){
       this.proprietaAcquistate.add(proprieta1);
   }

    /**
     * Verifica se un player possiede una certa proprietà
     * @param nomeProprieta nome della proprietà che deve avere
     * @return
     */
   public boolean possiedeProprieta(String nomeProprieta) {
        return proprietaAcquistate.contains(nomeProprieta);
    }

    /**
     * Getter per i soldi di un player
     * @return
     */
    public IntegerProperty soldiProperty() {
        return soldi;
    }

    /**
     * Getter per tutte le info di un player
     * @return
     */
    public String getInfo(){
       return "Nome: " + this.nome + " \nSoldi: " + this.getSoldi() + " \nProprietà:\n " + this.proprietaAcquistate;
    }




}