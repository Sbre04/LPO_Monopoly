package com.monopoly.controller;

import com.monopoly.application.HelloApplication;
import com.monopoly.model.Imp;
import com.monopoly.model.Player;
import com.monopoly.model.Prob;
import com.monopoly.model.Proprieta;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class HelloController {

    @FXML
    public AnchorPane root;
    @FXML
    private ImageView tabellone;
    @FXML
    Button myButton;
    @FXML
    Button ImpButton;
    @FXML
    Button ProbButton;
    @FXML
    private ImageView DiceImage;
    @FXML
    private ImageView Dice2Image;
    int SommaDadi;
    int asseX = 0;
    int asseY = 0;
    int j = 45;
    int[] cord = new int[11];
    public static int posizione = 0;
    boolean[] lato = new boolean[4];
    public static int indicePedina;


    /**
     * Mostra i dadi a schermo e chiama la funzione initialize che si occupa del movimento
     */
    @FXML
    public void displayImage(){
        /**
         * Genera un numero casuale da attribuire ai dati*/
        Random random = new Random();
        int randomNumber = random.nextInt(1,6)+1;

        /**
         * Definisce un immagine casuale in base al numero random generato*/
        Image Dice1 = new Image(getClass().getResourceAsStream("/com/monopoly/view/Dice/" + randomNumber + ".png"));
        /**Assegna l'immagine al dado*/
        DiceImage.setImage(Dice1);

        /**
         * Stesso ragionamento per il primo dato
         * 1)Genera un numero casuale
         * 2)Definisce l'immagine
         * 3)Assegna l'immagine al dado*/
        Random random2 = new Random();
        int randomNumber2 = random.nextInt(1,6)+1;
        Image Dice2 = new Image(getClass().getResourceAsStream("/com/monopoly/view/Dice/" + randomNumber2 + ".png"));
        Dice2Image.setImage(Dice2);

        /**Imposta SommaDadi = alla somma dei due numeri randomici generati prima*/
        SommaDadi = randomNumber + randomNumber2;

        /**Ogni volta che si clicca sul pulsante per tirare i dadi incrementa indicepedina*/
        initialize(null, null,indicePedina);
        indicePedina++;



        /**Quando indicepedina raggiunge il numero di player si resetta*/
        if(indicePedina == HelloApplication.player){
            indicePedina = 0;
        }
    }



    /**
     *Metodo che si occupa di gestire sia la grafica che la logica degli imprevisti
     */
    @FXML
    public static void callImp() {
        /**Crea un alert per l'imprevisto*/
        Alert imprevisti = new Alert(Alert.AlertType.INFORMATION);
        /**Genera un array di imprevisti devifiniti nella classe imp.java*/
        Imp[] imp =  Imp.ITEMS;
        /**Genera un numero casuale per prendere un imprevisto casuale*/
        Random randomimp = new Random();
        int numeroCasuale = randomimp.nextInt(imp.length);
        /**Assegna il numero casuale generato all'array degli imprevisti cosi da selezionarne uno in particolare*/
        Imp ImpCasuale = imp[numeroCasuale];

        /**Impostiamo un titolo all'alert*/
        imprevisti.setTitle("Imprevisti");
        /**Prendiamo il testo dell'imprevisto e lo utiliziamo come Header dell'alert*/
        imprevisti.setHeaderText(ImpCasuale.getTitolo());
        /**Gestiamo la logica dell'imprevisto*/
        if(ImpCasuale.getSoldiVinti()<ImpCasuale.getSoldiPersi()){
            imprevisti.setContentText(ImpCasuale.getCorpo() + " Hai perso: " + ImpCasuale.getSoldiPersi() + " euro");
            Player.giocatori[indicePedina].setSoldi(Player.giocatori[indicePedina].getSoldi() - ImpCasuale.getSoldiPersi());

        } else if (ImpCasuale.getSoldiVinti()>ImpCasuale.getSoldiPersi()) {
            imprevisti.setContentText(ImpCasuale.getCorpo() + " Hai guadagnato "+ImpCasuale.getSoldiVinti() + " euro");
            Player.giocatori[indicePedina].setSoldi(Player.giocatori[indicePedina].getSoldi() + ImpCasuale.getSoldiVinti());

        }
        else {
            imprevisti.setContentText(ImpCasuale.getCorpo());
        }
        /**Aspettiamo una conferma prima di far chiudere l'alert*/
        imprevisti.showAndWait();

    }

    /**
     *Metodo che si occupa di gestire sia la grafica che la logica delle probabilità
     */
    @FXML
    public static void callProb() {
        /**Creiamo un alert per la probabilità*/
        Alert probabilita = new Alert(Alert.AlertType.INFORMATION);
        /**Generiamo un array di probabilità definiti nella classe prob.java*/
        Prob[] prob = Prob.ITEMS;
        /**Generiamo un numero casuale per prendere una probabilità dall'array*/
        Random randomprob = new Random();
        int numeroCasuale2 = randomprob.nextInt(prob.length);
        /**Assegna il numero casuale generato all'array delle probabilità cosi da selezionarne uno in particolare*/
        Prob probCasuale = prob[numeroCasuale2];

        /**Settiamo il titolo per l'alert delle probabilità*/
        probabilita.setTitle("Probabilità");
        /**Prendiamo il testo della probabilità e lo utiliziamo come Header dell'alert*/
        probabilita.setHeaderText(probCasuale.getTitolo());
        /**Gestiamo la logica della probabilità*/
        if(probCasuale.getSoldiVinti()<probCasuale.getSoldiPersi()){
            probabilita.setContentText(probCasuale.getCorpo() + " Non hai guadagnato nulla ma hai perso: " + probCasuale.getSoldiPersi() + " euro");
            Player.giocatori[indicePedina].setSoldi(Player.giocatori[indicePedina].getSoldi() - probCasuale.getSoldiPersi());
        } else if (probCasuale.getSoldiVinti()>probCasuale.getSoldiPersi()) {
            probabilita.setContentText(probCasuale.getCorpo() + " Hai guadagnato "+probCasuale.getSoldiVinti() + " euro");
            Player.giocatori[indicePedina].setSoldi(Player.giocatori[indicePedina].getSoldi() - probCasuale.getSoldiVinti());
        }
        else {
            probabilita.setContentText(probCasuale.getCorpo());
        }

        probabilita.showAndWait();

    }

    @FXML
    private ImageView pedina;


    /**
     * Metodo che gestisce il movimento delle pedine.
     *
     * @param url           Percorso utilizzato per risolvere percorsi relativi alle risorse.
     * @param resourceBundle Bundle di risorse utilizzato per l'internazionalizzazione.
     * @param indicePedina  Indice della pedina del giocatore.
     */
    public void initialize(URL url, ResourceBundle resourceBundle,int indicePedina) {

        /**Inizializiamo il movimento della pedina*/

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(HelloApplication.pedineGiocatori[0]);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(1);

        /** Array per i valori di spostamento per ogni casella*/
        for (int i = 0; i < cord.length; i++) {
            cord[i] = i * j;
        }

        /**Inizializza i lati a false*/
        for (int i = 0; i < lato.length; i++) {
            lato[i] = false;
        }

        /**Calcola la posizione totale sul tabellone*/

        if(!Player.giocatori[indicePedina].getPrigione()) {
            posizione = (Player.giocatori[indicePedina].getPosizione() + SommaDadi) % 40;
        }
        else{
            posizione = Player.giocatori[indicePedina].getPosizione();
        }

        Player.giocatori[indicePedina].setPosizione(posizione);


        if(Player.giocatori[indicePedina].getPrigione() && Player.giocatori[indicePedina].getTurniPrigione()!=0){
            Player.giocatori[indicePedina].decrementaTurniInPrigione();
            if(Player.giocatori[indicePedina].getTurniPrigione()==0){
                Player.giocatori[indicePedina].prigioneFalse();
            }
        }

        /**Determina le coordinate (X, Y) in base alla posizione sul tabellone*/
        else if ((posizione >= 0 && posizione <= 10) && !Player.giocatori[indicePedina].getPrigione()) {
            /**Lato in basso o lato da cui partono le pedine*/
            asseX = cord[posizione];
            asseY = 0;
            lato[0] = true;
            /**Modo per aspettare prima che faccia un asse e poi l'altro*/
            TranslateTransition muoviAsseX = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseX.setToX(-asseX);
            PauseTransition cambioLato = new PauseTransition(Duration.seconds(0.1));
            TranslateTransition muoviAsseY = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseY.setToY(-asseY);
            SequentialTransition MovimentoDaY = new SequentialTransition(muoviAsseY,cambioLato,muoviAsseX);
            MovimentoDaY.play();




            /**Gestione della logica per il lato sinistro o primo lato verticale*/
        } else if ((posizione > 10 && posizione <= 20) && !Player.giocatori[indicePedina].getPrigione()) {

            asseX = cord[10];
            asseY = (cord[posizione - 10]);
            lato[1] = true;
            TranslateTransition muoviAsseX = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseX.setToX(-asseX);

            PauseTransition cambioLato = new PauseTransition(Duration.seconds(0.1));

            TranslateTransition muoviAsseY = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseY.setToY(-asseY);

            SequentialTransition MovimentoDaX = new SequentialTransition(muoviAsseX,cambioLato,muoviAsseY);

            /**
             * Avvia il movimento
             */
            MovimentoDaX.play();



        /**Gestione della logica per il lato in alto o secondo lato orizzonatele sx to dx*/
        } else if ((posizione > 20 && posizione <= 30) && !Player.giocatori[indicePedina].getPrigione()) {
            asseX = cord[10 - (posizione - 20)];
            asseY = cord[10];
            lato[2] = true;

            TranslateTransition muoviAsseX = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseX.setToX(-asseX);

            PauseTransition cambioLato = new PauseTransition(Duration.seconds(0.1));

            TranslateTransition muoviAsseY = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseY.setToY(-asseY);

            SequentialTransition MovimentoDaY = new SequentialTransition(muoviAsseY,cambioLato,muoviAsseX);

            /**
             * Avvia il movimento
             */
            MovimentoDaY.play();

            /**
             * Gestione della logica per impostare lo stato della prigione a true per un giocatore
             */
            if (posizione==30){
                Player.giocatori[indicePedina].prigioneTrue();
                Player.giocatori[indicePedina].aggiungiTurniInPrigione();
            }
        }
        /**Gestione della logica per il lato destro o secondo lato verticale*/
        else if ((posizione > 30 && posizione < 40) && !Player.giocatori[indicePedina].getPrigione()) {
            asseX = 0;
            asseY = cord[10 - (posizione - 30)];
            lato[3] = true;

            TranslateTransition muoviAsseX = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseX.setToX(-asseX);

            PauseTransition cambioLato = new PauseTransition(Duration.seconds(0.1));

            TranslateTransition muoviAsseY = new TranslateTransition(Duration.seconds(0.6),HelloApplication.pedineGiocatori[indicePedina]);
            muoviAsseY.setToY(-asseY);

            SequentialTransition MovimentoDaX = new SequentialTransition(muoviAsseX,cambioLato,muoviAsseY);
            MovimentoDaX.play();

        }

        /**
         * Aggiungere 200 euro al giocatore quando passa dal via
         */
        if(lato[0] && !lato[3]){
            Player.giocatori[indicePedina].setSoldi(Player.giocatori[indicePedina].getSoldi() + 200);
            System.out.println(Player.giocatori[indicePedina].getSoldi());
        }
        try {
            Proprieta.Property.properties[posizione].buy(posizione);
        }
        catch (Exception e) {
            System.out.println(e);
        }



    }

}
