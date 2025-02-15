/**
 * Package com.monopoly.model
 */
package com.monopoly.model;

import com.monopoly.application.HelloApplication;
import com.monopoly.controller.HelloController;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * Eccezione personalizzata che segnala una mancanza di fondi da parte del giocatore.
 * Viene lanciata se non si dispone di abbastanza soldi per completare un'azione.
 */
class soldiInsuffiscientiException extends Exception {
    /**
     * Costruttore di soldiInsuffiscientiException.
     * @param player1 Il giocatore che non dispone di fondi a sufficienza.
     * @throws playerEliminatoException Se il giocatore non ha proprietà vendibili e viene eliminato.
     */
    public soldiInsuffiscientiException(Player player1) throws playerEliminatoException {
        super("Non hai abbastanza soldi per completare l'azione.");
        /*
        int indiceProprieta = 0;
        // Controlla se il giocatore possiede proprietà da vendere
        if (player1.getProprietaAcquistate() != null){
            Alert vendi = new Alert(Alert.AlertType.INFORMATION);
            vendi.setTitle("Vendi proprieta");
            vendi.setHeaderText("Vuoi vendere una o più proprietà per recuperare denaro?");
            ButtonType scelta = vendi.showAndWait().orElse(ButtonType.NO);

            if(scelta == ButtonType.YES) {
                for(int i=0; i < player1.getProprietaAcquistate().size(); i++) {
                    for(int j=0; j< Proprieta.Property.properties.length; j++) {
                        if(player1.possiedeProprieta(Proprieta.Property.properties[j].nome)) {
                            indiceProprieta = Proprieta.Property.properties[j].posizione;
                        }
                    }
                    Alert vendiProprieta = new Alert(Alert.AlertType.CONFIRMATION);
                    vendiProprieta.setTitle("Vendi Proprietà");
                    vendiProprieta.setHeaderText("Vuoi vendere la proprietà " + player1.getProprietaAcquistate().get(i) + " ?");
                    ButtonType scelta1 = vendiProprieta.showAndWait().orElse(ButtonType.NO);
                    if(scelta1 == ButtonType.YES) {
                        System.out.println("La proprietà " + player1.getProprietaAcquistate().get(i) + " è stata venduta per " + (Proprieta.Property.properties[indiceProprieta].prezzo + "€."));

                        // Aggiungere il denaro al giocatore
                        Player.giocatori[HelloController.indicePedina].setSoldi(
                                Player.giocatori[HelloController.indicePedina].getSoldi() + (Proprieta.Property.properties[indiceProprieta].prezzo)
                        );

                        // Rimuovere la proprietà dalla lista
                        player1.rimuoviProprieta(Proprieta.Property.properties[indiceProprieta].nome);
                        Proprieta.Property.properties[indiceProprieta].Giacomprato = false;
                    }
                }
            }
        }
        */
        System.out.println("Il giocatore non ha proprietà da vendere! Eliminazione in corso...");
        throw new playerEliminatoException(HelloController.indicePedina, "Sei stato eliminato per mancanza di fondi.");
    }
}

/**
 * Eccezione personalizzata che segnala l'eliminazione di un giocatore.
 * Viene lanciata quando un giocatore non può più proseguire la partita.
 */
class playerEliminatoException extends Exception {
    /**
     * Costruttore di playerEliminatoException.
     * @param indice Indice del giocatore all'interno dell'array Player.giocatori.
     * @param message Messaggio esplicativo dell'eccezione.
     */
    public playerEliminatoException(int indice, String message) {
        super(message);
        for (int i = indice; i < HelloApplication.player - 1; i++) {
            Player.giocatori[i] = Player.giocatori[i + 1];
        }
        Player.giocatori[HelloApplication.player - 1] = null;
        HelloApplication.player--;
    }
}

/**
 * Classe Proprieta che racchiude la classe annidata Property.
 * Contiene la logica relativa alla creazione, gestione e acquisto delle proprietà.
 */
public class Proprieta {

    /**
     * Classe interna Property che rappresenta la singola proprietà sul tabellone.
     */
    public static class Property {
        int posizione;
        String nome;
        String colore;
        int prezzo;
        boolean Giacomprato;
        boolean NonAcquistabile;
        boolean imprevisto;
        boolean probabilita;
        boolean prigione;
        int costoAffitto;
        int costo1Casa;
        int costo2Casa;
        int costo3Casa;
        int costo4Casa;
        int costoAlbergo;
        boolean casa1, casa2, casa3, casa4, albergo;

        /**
         * Costruttore di Property.
         * @param nome Nome della proprietà.
         * @param colore Colore della proprietà.
         * @param prezzo Prezzo base della proprietà.
         * @param costoAffitto Costo d'affitto base.
         * @param costo1Casa Costo d'affitto con una casa.
         * @param costo2Casa Costo d'affitto con due case.
         * @param costo3Casa Costo d'affitto con tre case.
         * @param costo4Casa Costo d'affitto con quattro case.
         * @param costoAlbergo Costo d'affitto con un albergo.
         * @param posizione Posizione sul tabellone della proprietà.
         * @param NonAcquistabile Variabile che indica se la proprietà non è acquistabile.
         * @param imprevisto Variabile che indica se la casella è un "imprevisto".
         * @param probabilita Variabile che indica se la casella è una "probabilità".
         * @param prigione Variabile che indica se la casella è la prigione.
         * @param giacomprato Variabile che indica se la proprietà è stata acquistata.
         * @param casa1 Indica se è presente la prima casa.
         * @param casa2 Indica se è presente la seconda casa.
         * @param casa3 Indica se è presente la terza casa.
         * @param casa4 Indica se è presente la quarta casa.
         * @param albergo Indica se è presente l'albergo.
         */
        public Property(
                String nome,
                String colore,
                int prezzo,
                int costoAffitto,
                int costo1Casa,
                int costo2Casa,
                int costo3Casa,
                int costo4Casa,
                int costoAlbergo,
                int posizione,
                boolean NonAcquistabile,
                boolean imprevisto,
                boolean probabilita,
                boolean prigione,
                boolean giacomprato,
                boolean casa1,
                boolean casa2,
                boolean casa3,
                boolean casa4,
                boolean albergo
        ) {
            this.nome = nome;
            this.colore = colore;
            this.prezzo = prezzo;
            this.costoAffitto = costoAffitto;
            this.costo1Casa = costo1Casa;
            this.costo2Casa = costo2Casa;
            this.costo3Casa = costo3Casa;
            this.costo4Casa = costo4Casa;
            this.costoAlbergo = costoAlbergo;
            this.posizione = posizione;
            this.Giacomprato = giacomprato;
            this.NonAcquistabile = NonAcquistabile;
            this.imprevisto = imprevisto;
            this.probabilita = probabilita;
            this.prigione = prigione;
            this.casa1 = casa1;
            this.casa2 = casa2;
            this.casa3 = casa3;
            this.casa4 = casa4;
            this.albergo = albergo;
        }

        /**
         * Metodo che gestisce l'acquisto di una proprietà, il pagamento delle tasse o dell'affitto
         * e la costruzione di case/alberghi.
         * @param posizioneProprieta La posizione della proprietà in cui si trova sul tabellone.
         * @throws soldiInsuffiscientiException Eccezione se il giocatore non ha fondi a sufficienza.
         * @throws playerEliminatoException Eccezione se il giocatore viene eliminato.
         */
        public void buy(int posizioneProprieta) throws soldiInsuffiscientiException, playerEliminatoException {

            // Controlliamo se la proprietà è acquistabile
            if (properties[posizioneProprieta].Giacomprato == false &&
                    NonAcquistabile == false &&
                    Player.giocatori[HelloController.indicePedina].getSoldi() >= properties[posizioneProprieta].prezzo
            ) {
                // Creiamo l'alert
                Alert acquistaProprietà = new Alert(Alert.AlertType.CONFIRMATION);
                acquistaProprietà.setTitle("Acquista Proprietà");
                acquistaProprietà.setHeaderText("Vuoi acquistare la proprietà " + properties[posizioneProprieta].nome + " ?");
                ButtonType scelta = acquistaProprietà.showAndWait().orElse(ButtonType.NO);

                // Controlliamo se il giocatore ha scelto di acquistarla
                if (scelta.getText().equals("OK")) {
                    properties[posizioneProprieta].Giacomprato = true;
                    System.out.println(Player.giocatori[HelloController.indicePedina].nome + " ha acquistato la proprieta");
                    // Aggiungiamo la proprietà alla lista proprietà del giocatore
                    Player.giocatori[HelloController.indicePedina].aggiungiProprieta(String.valueOf(properties[posizioneProprieta].nome));

                    // Sottraiamo i soldi del giocatore con il valore della proprietà
                    Player.giocatori[HelloController.indicePedina].setSoldi(
                            Player.giocatori[HelloController.indicePedina].getSoldi() - properties[posizioneProprieta].prezzo
                    );

                    //Stampiamo la lista delle propieta e i soldi del giocatore
                    System.out.println(Player.giocatori[HelloController.indicePedina].getProprietaAcquistate());
                    System.out.println(Player.giocatori[HelloController.indicePedina].getSoldi());

                } else {
                    System.out.println("Non hai acquistato la proprieta! \n");
                }
            }

            //Paga tasse
            else if (properties[posizioneProprieta].colore.equals("Tassa") &&
                    Player.giocatori[HelloController.indicePedina].getSoldi() >= properties[posizioneProprieta].prezzo
            ) {
                Player.giocatori[HelloController.indicePedina].setSoldi(
                        Player.giocatori[HelloController.indicePedina].getSoldi() - properties[posizioneProprieta].prezzo
                );
                System.out.println("Hai pagato una tassa di: " + properties[posizioneProprieta].prezzo);
            }

            //Se capitiamo su un imprevisto chiamiamo il metodo callImp
            else if (imprevisto == true) {
                HelloController.callImp();
            }

            //Se capitiamo su una probabilità chiamiamo il metodo callProb
            else if (probabilita == true) {
                HelloController.callProb();
            }

            //Verifica prima di aggiungere case
            else if (
                    Player.giocatori[HelloController.indicePedina].possiedeProprieta(String.valueOf(properties[posizioneProprieta].nome)) &&
                            (!casa1 || !casa2 || !casa3 || !casa4) &&
                            !properties[posizioneProprieta].colore.equals("None")
            ) {
                Alert aggiungiCasa = new Alert(Alert.AlertType.CONFIRMATION);
                aggiungiCasa.setTitle("Aggiungi una casa");
                aggiungiCasa.setHeaderText("Vuoi aggiungere una casa?");
                ButtonType scelta = aggiungiCasa.showAndWait().orElse(ButtonType.NO);
                // Dobbiamo aggiungere il costo di una casa e verificare
                if (scelta.getText().equals("OK")) {
                    if (!properties[posizioneProprieta].casa1) {
                        properties[posizioneProprieta].setCasa1(true);
                        CostoCasa(posizioneProprieta, 1);

                    } else if (properties[posizioneProprieta].casa1) {
                        properties[posizioneProprieta].setCasa2(true);
                        CostoCasa(posizioneProprieta, 2);

                    } else if (properties[posizioneProprieta].casa2) {
                        properties[posizioneProprieta].setCasa3(true);
                        CostoCasa(posizioneProprieta, 3);

                    } else if (properties[posizioneProprieta].casa3) {
                        properties[posizioneProprieta].setCasa4(true);
                        CostoCasa(posizioneProprieta, 4);
                    }
                }
            }

            //Verifica prima di aggiungere un albergo
            else if (
                    Player.giocatori[HelloController.indicePedina].possiedeProprieta(String.valueOf(properties[posizioneProprieta].nome)) &&
                            (casa1 && casa2 && casa3 && casa4)
            ) {
                Alert aggiungiCasa = new Alert(Alert.AlertType.CONFIRMATION);
                aggiungiCasa.setTitle("Aggiungi un albergo");
                aggiungiCasa.setHeaderText("Vuoi aggiungere un albergo?");
                ButtonType scelta = aggiungiCasa.showAndWait().orElse(ButtonType.NO);
                if (scelta.getText().equals("OK")) {
                    properties[posizioneProprieta].setAlbergo(true);
                    System.out.println("Hai aggiunto un albergo ora l'affitto è di: " + properties[posizioneProprieta].costoAlbergo + " euro");
                }
            }

            //Verifica per pagare l'affitto
            if (
                    properties[posizioneProprieta].Giacomprato &&
                            !Player.giocatori[HelloController.indicePedina].possiedeProprieta(String.valueOf(properties[posizioneProprieta].nome))
            ) {
                if (!properties[posizioneProprieta].casa1) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costo1Casa) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costoAffitto
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costoAffitto
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costoAffitto
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costoAffitto
                                );
                            }
                        }
                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }

                } else if (properties[posizioneProprieta].casa1) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costo1Casa) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costo1Casa
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costo1Casa
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costo1Casa
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costo1Casa
                                );
                            }
                        }
                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }

                } else if (properties[posizioneProprieta].casa2) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costo2Casa) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costo2Casa
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costo2Casa
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costo2Casa
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costo2Casa
                                );
                            }
                        }
                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }

                } else if (properties[posizioneProprieta].casa3) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costo3Casa) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costo3Casa
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costo3Casa
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costo3Casa
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costo3Casa
                                );
                            }
                        }
                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }

                } else if (properties[posizioneProprieta].casa4) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costo4Casa) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costo4Casa
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costo4Casa
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costo4Casa
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costo4Casa
                                );
                            }
                        }

                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }

                } else if (properties[posizioneProprieta].albergo) {
                    if (Player.giocatori[HelloController.indicePedina].soldiProperty().get() >= properties[posizioneProprieta].costoAlbergo) {
                        Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                                Player.giocatori[HelloController.indicePedina].soldiProperty().get() - properties[posizioneProprieta].costoAlbergo
                        );
                        System.out.println(
                                "Il giocatore: " + Player.giocatori[HelloController.indicePedina].getNome() +
                                        " ha pagato: " + properties[posizioneProprieta].costoAlbergo
                        );

                        for (int i = 0; i < HelloApplication.player; i++) {
                            if (Player.giocatori[i].getProprietaAcquistate().contains(String.valueOf(properties[posizioneProprieta].nome))) {
                                Player.giocatori[i].soldiProperty().set(
                                        Player.giocatori[i].soldiProperty().get() + properties[posizioneProprieta].costoAlbergo
                                );
                                System.out.println(
                                        "Il giocatore: " + Player.giocatori[i].getNome() +
                                                " ha guadagnato: " + properties[posizioneProprieta].costoAlbergo
                                );
                            }
                        }
                    } else {
                        throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                    }
                }
            }
        }

        /**
         * Array di tutte le proprietà presenti sul tabellone.
         */
        public static Property[] properties = new Property[40];

        /**
         * Metodo che crea e inizializza tutte le proprietà del tabellone.
         */
        public static void createProperties() {
            properties[0] = new Property("Via", "red", 0,0, 0,0,0,0,0,0,true,false,false,false, false,true,true,true,true,true);
            properties[1] = new Property("Vicolo Corto", "Marrone", 60,2, 10,30,90,160,250,1,false,false,false,false, false,false,false,false,false,false);
            properties[2] = new Property("Probabilità", "None", 0,0, 0,0,0,0,0,2,true,false,true,false, false,true,true,true,true,true);
            properties[3] = new Property("Vicolo Stretto", "Marrone", 60,4, 20,60,180,320,450,3,false,false,false,false, false,false,false,false,false,false);
            properties[4] = new Property("Tassa Patrimoniale", "Tassa", 200,0,0,0,0,0,0,4,true,false,false,false, false,true,true,true,true,true);
            properties[5] = new Property("Stazione Sud", "Nero", 200,25, 0,0,0,0,0,5,false,false,false,false, false,false,false,false,false,false);
            properties[6] = new Property("Bastioni Gran Sasso", "Azzurro", 100,6, 30,90,270,400,550,6,false,false,false,false, false,false,false,false,false,false);
            properties[7] = new Property("Imprevisti", "None", 0,0, 0,0,0,0,0,7,true,true,false,false, false,true,true,true,true,true);
            properties[8] = new Property("Viale Monterosa", "Azzurro", 100,6, 30,90,270,400,550,8,false,false,false,false, false,false,false,false,false,false);
            properties[9] = new Property("Viale Vesuvio", "Azzurro", 120,8, 40,100,300,450,600,9,false,false,false,false, false,false,false,false,false,false);
            properties[10] = new Property("Transito/Prigione", "None", 0,0, 0,0,0,0,0,10,true,false,false,false, false,true,true,true,true,true);
            properties[11] = new Property("Via Accademia", "Fucsia", 140,10,50,150,450,625,750,11,false,false,false,false, false,false,false,false,false,false);
            properties[12] = new Property("Società Elettrica", "None", 150,15, 0,0,0,0,0,12,false,false,false,false, false,false,false,false,false,false);
            properties[13] = new Property("Corso Ateneo", "Fucsia", 140,10, 50,150,450,625,750,13,false,false,false,false, false,false,false,false,false,false);
            properties[14] = new Property("Piazza Università", "Fucsia", 160,12,60,180,500,700,900,14,false,false,false,false, false,false,false,false,false,false);
            properties[15] = new Property("Stazione Ovest", "None", 200,25,0,0,0,0,0,15,false,false,false,false, false,false,false,false,false,false);
            properties[16] = new Property("Via Verdi", "Arancione", 180,14, 70,200,550,750,950,16,false,false,false,false, false,false,false,false,false,false);
            properties[17] = new Property("Probabilità", "None", 0,0, 0,0,0,0,0,17,true,false,true,false, false,true,true,true,true,true);
            properties[18] = new Property("Corso Raffello", "Arancione", 180,14, 70,200,550,750,950,18,false,false,false,false, false,false,false,false,false,false);
            properties[19] = new Property("Piazza dante", "Arancione", 200,16, 80,220,600,800,1000,19,false,false,false,false, false,false,false,false,false,false);
            properties[20] = new Property("Parcheggio", "None", 0,0, 0,0,0,0,0,20,true,false,false,false, false,true,true,true,true,true);
            properties[21] = new Property("Via Marco Polo", "Rosso", 220,18, 90,250,700,875,1050,21,false,false,false,false, false,false,false,false,false,false);
            properties[22] = new Property("Imprevisti", "None", 0, 0,0,0,0,0,0,22,true,true,false,false, false,true,true,true,true,true);
            properties[23] = new Property("Corso Magellano", "Rosso", 220,18, 90,250,700,875,1050,23,false,false,false,false, false,false,false,false,false,false);
            properties[24] = new Property("Largo Colombo", "Rosso", 240, 20,100,300,750,925,1100,24,false,false,false,false, false,false,false,false,false,false);
            properties[25] = new Property("Stazione Nord", "None", 200,25, 0,0,0,0,0,25,false,false,false,false, false,false,false,false,false,false);
            properties[26] = new Property("Viale Costantino", "Giallo", 260,22, 110,330,800,975,1150,26,false,false,false,false, false,false,false,false,false,false);
            properties[27] = new Property("Viale Traiano", "Giallo", 260,22, 110,330,800,975,1150,27,false,false,false,false, false,false,false,false,false,false);
            properties[28] = new Property("Società acqua potabile", "None", 150, 15,0,0,0,0,0,28,false,false,false,false, false,false,false,false,false,false);
            properties[29] = new Property("Piazza giulio Cesare", "Giallo", 280, 24,120,360,850,1025,1200,29,false,false,false,false, false,false,false,false,false,false);
            properties[30] = new Property("In Prigione", "None", 0,0, 0,0,0,0,0,30,true,false,false,true, false,true,true,true,true,true);
            properties[31] = new Property("Via Roma", "Verde", 300,26, 130,390,900,1100,1275,31,false,false,false,false, false,false,false,false,false,false);
            properties[32] = new Property("Corso Impero", "Verde", 300, 26,130,390,900,1100,1275,32,false,false,false,false, false,false,false,false,false,false);
            properties[33] = new Property("Probabilità", "None", 0, 0,0,0,0,0,0,33,true,false,true,false, false,true,true,true,true,true);
            properties[34] = new Property("Largo Augusto", "Verde", 320,28, 150,450,1000,1200,1400,34,false,false,false,false, false,false,false,false,false,false);
            properties[35] = new Property("Stazione Est", "None", 200,25, 0,0,0,0,0,35,false,false,false,false, false,false,false,false,false,false);
            properties[36] = new Property("Imprevisti", "None", 0,0, 0,0,0,0,0,36,true,true,false,false, false,true,true,true,true,true);
            properties[37] = new Property("Viale Dei Giardini", "Blu", 350,35, 175,500,1100,1300,1500,37,false,false,false,false, false,false,false,false,false,false);
            properties[38] = new Property("Tassa Di Lusso", "Tassa", 100, 0,0,0,0,0,0,38,true,false,false,false, false,true,true,true,true,true);
            properties[39] = new Property("Parco Della Vittoria", "Blu", 400,50, 200,600,1400,1700,2000,39,false,false,false,false, false,false,false,false,false,false);
        }

        /**
         * Imposta il costo affitto.
         * @param costoAffitto1 Nuovo valore per il costo d'affitto.
         */
        public void setCostoAffitto(int costoAffitto1){
            this.prezzo = costoAffitto1;
        }

        /**
         * Restituisce il costo d'affitto.
         * @return Costo d'affitto.
         */
        public int getCostoAffitto(){
            return this.prezzo;
        }

        /**
         * Imposta la presenza della prima casa.
         * @param setcasa1 true se la prima casa è presente, false altrimenti.
         */
        public void setCasa1(boolean setcasa1){
            this.casa1 = setcasa1;
        }

        /**
         * Imposta la presenza della seconda casa.
         * @param setcasa2 true se la seconda casa è presente, false altrimenti.
         */
        public void setCasa2(boolean setcasa2){
            this.casa1 = setcasa2;
        }

        /**
         * Imposta la presenza della terza casa.
         * @param setcasa3 true se la terza casa è presente, false altrimenti.
         */
        public void setCasa3(boolean setcasa3){
            this.casa1 = setcasa3;
        }

        /**
         * Imposta la presenza della quarta casa.
         * @param setcasa4 true se la quarta casa è presente, false altrimenti.
         */
        public void setCasa4(boolean setcasa4){
            this.casa1 = setcasa4;
        }

        /**
         * Imposta la presenza dell'albergo.
         * @param setalbergo true se l'albergo è presente, false altrimenti.
         */
        public void setAlbergo(boolean setalbergo){
            this.casa1 = setalbergo;
        }

        /**
         * Scala i soldi del giocatore quando compra una casa
         * @param PosperColore Posizione della proprietà sul tabellone.
         * @param n Numero di case da aggiungere.
         * @throws soldiInsuffiscientiException Se il giocatore non ha soldi a sufficienza.
         * @throws playerEliminatoException Se il giocatore viene eliminato.
         */
        public void CostoCasa(int PosperColore, int n) throws soldiInsuffiscientiException, playerEliminatoException {
            if(properties[PosperColore].colore.equals("Marrone") ||
                    properties[PosperColore].colore.equals("Azzurro") ||
                    properties[PosperColore].colore.equals("Fucsia"))
            {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (50*n)){
                    properties[PosperColore].setCasa1(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (50 * n)
                    );
                    System.out.println("Hai aggiunto" + n + "casa ora l'affitto è di: " + properties[PosperColore].costo1Casa + " euro");
                }
            }
            else if(properties[PosperColore].colore.equals("Arancione") ||
                    properties[PosperColore].colore.equals("Rosso"))
            {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (100*n)){
                    properties[PosperColore].setCasa1(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (100 * n)
                    );
                    System.out.println("Hai aggiunto" + n + "casa ora l'affitto è di: " + properties[PosperColore].costo1Casa + " euro");
                }
            }
            else if(properties[PosperColore].colore.equals("Giallo") ||
                    properties[PosperColore].colore.equals("Verde"))
            {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (150*n)){
                    properties[PosperColore].setCasa1(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (150 * n)
                    );
                    System.out.println("Hai aggiunto" + n + "casa ora l'affitto è di: " + properties[PosperColore].costo1Casa + " euro");
                }
            }
            else if(properties[PosperColore].colore.equals("Blu") ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (200*n)){
                    properties[PosperColore].setCasa1(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (200 * n)
                    );
                    System.out.println("Hai aggiunto" + n + "casa ora l'affitto è di: " + properties[PosperColore].costo1Casa + " euro");
                }
            }

            /**Impostiamo la seconda casa*/
            if(
                    (properties[PosperColore].colore.equals("Marrone") ||
                            properties[PosperColore].colore.equals("Azzurro") ||
                            properties[PosperColore].colore.equals("Fucsia")) &&
                            properties[PosperColore].casa1
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (50*n)){
                    properties[PosperColore].setCasa2(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (50 * n)
                    );
                }
            }
            else if(
                    (properties[PosperColore].colore.equals("Arancione") ||
                            properties[PosperColore].colore.equals("Rosso")) &&
                            properties[PosperColore].casa1
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (100*n)){
                    properties[PosperColore].setCasa2(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (100 * n)
                    );
                }
            }
            else if(
                    (properties[PosperColore].colore.equals("Giallo") ||
                            properties[PosperColore].colore.equals("Verde")) &&
                            properties[PosperColore].casa1
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (150*n)){
                    properties[PosperColore].setCasa2(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (150 * n)
                    );
                }
            }
            else if(properties[PosperColore].colore.equals("Blu") && properties[PosperColore].casa1) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (200*n)){
                    properties[PosperColore].setCasa2(false);
                    /*System.out.println("Non Hai abbastanza soldi per comprare la casa");*/
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (200 * n)
                    );
                }
            }

            /**Impostiamo la terza casa*/
            if(
                    (properties[PosperColore].colore.equals("Marrone") ||
                            properties[PosperColore].colore.equals("Azzurro") ||
                            properties[PosperColore].colore.equals("Fucsia")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (50*n)){
                    properties[PosperColore].setCasa3(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (50 * n)
                    );
                }

            }
            else if(
                    (properties[PosperColore].colore.equals("Arancione") ||
                            properties[PosperColore].colore.equals("Rosso")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (100*n)){
                    properties[PosperColore].setCasa3(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (100 * n)
                    );
                }
            }
            else if(
                    (properties[PosperColore].colore.equals("Giallo") ||
                            properties[PosperColore].colore.equals("Verde")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (150*n)){
                    properties[PosperColore].setCasa3(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (150 * n)
                    );
                }
            }
            else if(
                    properties[PosperColore].colore.equals("Blu") &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (200*n)){
                    properties[PosperColore].setCasa3(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (200 * n)
                    );
                }
            }

            /**Impostiamo la quarta casa*/
            if(
                    (properties[PosperColore].colore.equals("Marrone") ||
                            properties[PosperColore].colore.equals("Azzurro") ||
                            properties[PosperColore].colore.equals("Fucsia")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2 && properties[PosperColore].casa3)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (50*n)){
                    properties[PosperColore].setCasa4(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].getSoldi() - (50 * n)
                    );
                }

            }
            else if(
                    (properties[PosperColore].colore.equals("Arancione") ||
                            properties[PosperColore].colore.equals("Rosso")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2 && properties[PosperColore].casa3)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (100*n)){
                    properties[PosperColore].setCasa4(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (100 * n)
                    );
                }
            }
            else if(
                    (properties[PosperColore].colore.equals("Giallo") ||
                            properties[PosperColore].colore.equals("Verde")) &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2 && properties[PosperColore].casa3)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (150*n)){
                    properties[PosperColore].setCasa4(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (150 * n)
                    );
                }
            }
            else if(
                    properties[PosperColore].colore.equals("Blu") &&
                            (properties[PosperColore].casa1 && properties[PosperColore].casa2 && properties[PosperColore].casa3)
            ) {
                if(Player.giocatori[HelloController.indicePedina].soldiProperty().get() < (200*n)){
                    properties[PosperColore].setCasa4(false);
                    //System.out.println("Non Hai abbastanza soldi per comprare la casa");
                    throw new soldiInsuffiscientiException(Player.giocatori[HelloController.indicePedina]);
                } else {
                    Player.giocatori[HelloController.indicePedina].soldiProperty().set(
                            Player.giocatori[HelloController.indicePedina].soldiProperty().get() - (200 * n)
                    );
                }
            }
        }
    }
}