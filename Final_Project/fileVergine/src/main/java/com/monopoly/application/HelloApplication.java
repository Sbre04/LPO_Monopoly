package com.monopoly.application;

import com.monopoly.controller.HelloController;
import com.monopoly.model.Player;
import com.monopoly.model.Proprieta;
import javafx.application.Application;
import java.io.IOException;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe principale per il gioco Monopoly
 * HelloApplication.java si occupa di gestire l'avvio della parte grafica del gioco
 */

public class HelloApplication extends Application
{

	/**
	 * Metodo per avviare il gioco
	 * @param stage Finestra Principale
	 * @throws IOException Nel caso ci siano errori nel caricamento del file FXML (Risorse)
	 */
	@Override
	public void start(Stage stage) throws IOException {
		Proprieta.Property.createProperties();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/monopoly/view/hello-view.fxml"));
		AnchorPane scenaPrincipale = fxmlLoader.load();
		HelloController controller = fxmlLoader.getController();
		PopUp(stage,controller);
 		/**Definisce un immagine per il bottone che mostrerà le info dei giocatori*/
		Image Icona = new Image(getClass().getResourceAsStream("/com/monopoly/view/Dice/iconaPulsante.png"));
		/**Imposta l'immagine definita prima come icona del pulsante*/
		ImageView IconaPulsante = new ImageView(Icona);

		Scene scene = new Scene(scenaPrincipale, 550, 500);
		/**Setta impostazioni grafiche per la scena del gioco*/
		stage.setTitle("Monopoly");
		stage.setHeight(560);
		stage.setWidth(550);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		Button infoButton = new Button("",IconaPulsante);
		/**Definisce uno stile per il pulsante che mostrara le info dei giocatori (Nome, Soldi e Proprietà)*/
		infoButton.setStyle("-fx-padding: 5; -fx-font-size: 14;");
		infoButton.setOnAction(e -> mostraInfoGiocatori());

		scenaPrincipale.getChildren().add(infoButton);
		AnchorPane.setBottomAnchor(infoButton, 100.0);
		AnchorPane.setLeftAnchor(infoButton, 145.0);
	}

	/**
	 * Finestra che si occupa di mostrare le info dei giocatori (Nome,Soldi e proprietà acquistate)
	 * Questa si aggiornerà in tempo reale quando: Cambierà il valore dei soldi o Cambieranno le proprietà acquistate
	 */
	public void mostraInfoGiocatori(){
		Stage MostraInfo = new Stage();
		/**Stile della finestra che soi aprirà per vedere le info dei giocagtori (Nome, Soldi e Proprietà)*/
		MostraInfo.setTitle("Informazioni Giocatori");
		MostraInfo.setResizable(true);
		VBox infoGiocatori = new VBox(13);
		infoGiocatori.setStyle("-fx-padding: 5; -fx-background-color: linear-gradient(to top, #2ecc71, #ffffff); -fx-background-radius: 10px;");
		infoGiocatori.setPrefWidth(250);
		infoGiocatori.setStyle("-fx-background-radius: 10px;");
		infoGiocatori.setPrefHeight(500);


		for(int j = 0; j<player;j++){
			if(Player.giocatori[j] != null) {
				Label informazioni = new Label(Player.giocatori[j].getInfo());
				informazioni.setStyle("-fx-padding: 5; -fx-font-size: 12px; -fx-text-fill: #000000; -fx-font-family: 'Roboto'; -fx-background-color: #ffffff; -fx-border-radius: 8px;");
				informazioni.setWrapText(true);
				informazioni.setMaxWidth(400);
				informazioni.setPrefWidth(400);
				informazioni.setMaxHeight(150);
				informazioni.setPrefHeight(150);

				infoGiocatori.getChildren().add(informazioni);

				int finalJ = j;
				Player.giocatori[j].getProprietaAcquistate().addListener((ListChangeListener<String>) change -> {
					// Quando la lista delle proprietà cambia (aggiunta o rimozione)
					informazioni.setText(Player.giocatori[finalJ].getInfo());
				});
				Player.giocatori[j].soldiProperty().addListener((observable, oldValue, newValue) -> {
					// Quando i soldi cambiano, aggiorna l'informazioni
					informazioni.setText(Player.giocatori[finalJ].getInfo());
				});
			}
		}

		Scene ScenaInformazioni = new Scene(infoGiocatori,400,400);
		MostraInfo.setScene(ScenaInformazioni);
		MostraInfo.show();

	}

	/**Array di ImageView per rappresentare le pedine dei giocatori*/
	public static ImageView[] pedineGiocatori;
	/**Numero totale dei giocatori nella partita*/
	public static int player;
	/**Array che contiene i nomi dei giocatori*/
	public static String[] NomiGiocatori;

	/**
	 * Popup iniziale per inserire il numero di giocatori con i rispettivi nomi
	 * @param stage Finestra principale (prima finestra che si apre una volta lanciato il gioco)
	 * @param controller Controller associato alla vista principale
	 */
	private void PopUp(Stage stage, HelloController controller){
		Stage PopUp1 = new Stage();
		PopUp1.initModality(Modality.APPLICATION_MODAL);
		PopUp1.setTitle("MONOPOLY");
		/**Creazione e stilizzazione della label e dello spazio per inserire il numero di giocatori*/
		Label NumGiocatori = new Label("Numero \nGiocatori: ");
		NumGiocatori.setStyle("-fx-font-size: 14px; -fx-text-fill: #2ecc71; -fx-font-family: 'Roboto';");
		TextField NumGiocIn = new TextField();
		NumGiocIn.setStyle("-fx-border-color: #2ecc71; -fx-font-size: 14px; -fx-font-family: 'Roboto';");



		GridPane LayoutPU = new GridPane();
		LayoutPU.setPadding(new Insets(30, 20, 20, 20));
		LayoutPU.setVgap(10);
		LayoutPU.setHgap(10);
		LayoutPU.add(NumGiocatori, 0, 0);
		LayoutPU.add(NumGiocIn, 1, 0);

		/**Creazione e stilizzazione del bottone per confermamre i giocatori inseriti*/
		Button ConfermaPlayer = new Button("Conferma");
		ConfermaPlayer.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Roboto'; -fx-border-radius: 5px; -fx-padding: 10 20;");
		LayoutPU.add(ConfermaPlayer,1,1);

		Label ListaGiocatori = new Label();
		ListaGiocatori.setStyle("-fx-font-size: 14px; -fx-text-fill: #2ecc71;");
		LayoutPU.add(ListaGiocatori, 0, 10, 2, 1);

		/**Pannello a scorrimento in caso i giocatori non entrino graficamente nella finestra*/
		ScrollPane scorrimento = new ScrollPane();
		scorrimento.setContent(LayoutPU);
		scorrimento.setFitToWidth(true);
		scorrimento.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scorrimento.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

		PopUp1.setOnCloseRequest(event -> {
			System.exit(0);
		});

		/**Codice che verrà svolto una volta cliccato il bottone di conferma (Quello per confermare il numero di player)*/
		ConfermaPlayer.setOnAction(event -> {

				player = Integer.parseInt(NumGiocIn.getText());
				pedineGiocatori = new ImageView[player];
				TextField[] inputGiocatori = new TextField[player];
				NomiGiocatori = new String[player];

				NumGiocIn.setDisable(true);

				/**Prende i nomi dei giocatori*/
				for (int i = 0;i<player;i++){
					Label Nome = new Label( "Player: " + (i+1));
					Nome.setStyle("-fx-font-size: 12px; -fx-text-fill: #2ecc71; -fx-font-family: 'Roboto';");
					Nome.setTextAlignment(TextAlignment.LEFT);
					inputGiocatori[i] = new TextField();
					inputGiocatori[i].setStyle("-fx-border-color: #2ecc71; -fx-font-size: 12px; -fx-font-family: 'Roboto';");

					//GridPane.setMargin(Nome, new Insets(0, 10, 0, 0));
					//GridPane.setMargin(inputGiocatori[i], new Insets(0, 0, 0, 10));

					LayoutPU.add(Nome, 0, i + 2);
					LayoutPU.add(inputGiocatori[i], 1, i + 2);
					GridPane.setRowIndex(ConfermaPlayer,i+3);
				}
				Scene scenaPU = PopUp1.getScene();
				scenaPU.setRoot(scorrimento);
				ConfermaPlayer.setText("Salva i Nomi");
				/**Codice che verrà svolto una volta cliccato il bottone di conferma (Quello per confermare il nome dei player)*/
				ConfermaPlayer.setOnAction(event1 -> {
					boolean cisonotutti = true;
						/**Controlla se i nomi non sono vuoti*/
						for (int i = 0; i < player; i++) {
							if (inputGiocatori[i].getText().trim().isEmpty()) {
								Label Errore = new Label("Inserisci un nome per il giocatore:" + i);
								Errore.setStyle("-fx-text-fill: #ec2027; -fx-font-size: 12px; -fx-font-family: 'Roboto';");
								LayoutPU.add(Errore, 1, i + 6);
								cisonotutti = false;
							}

						}
						/**Assegna il nome all oggetto del giocatore*/
						if (cisonotutti){
							for (int i= 0; i<player; i++){
								Player.creaGiocatori(inputGiocatori[i].getText(), i);
								/**Definisce una pedina per ogni player*/
								Image pedina = new Image(getClass().getResourceAsStream("/com/monopoly/view/Dice/pedine/pedina" + (i + 1) + ".png"));
								/**Assegna una pedina definita prima al player*/
								ImageView mostrapedina = new ImageView(pedina);
								pedineGiocatori[i] = mostrapedina;
								pedineGiocatori[i].setFitHeight(40);
								pedineGiocatori[i].setFitWidth(40);
								pedineGiocatori[i].setLayoutX(490);
								pedineGiocatori[i].setLayoutY(480);
								controller.root.getChildren().add(pedineGiocatori[i]);
							}
							PopUp1.close();
						}


					if (cisonotutti){
						for (int i = 0; i<player;i++){
							Player.creaGiocatori(inputGiocatori[i].getText(), i);
							System.out.println("Giocatore " + i + ": " + Player.giocatori[i].getNome());
							PopUp1.close();
						}
					}
				});
        });

		Scene scenaPU = new Scene(LayoutPU, 300, 300);
		scenaPU.getRoot().setStyle("-fx-background-color: #ecf0f1;");
		PopUp1.setScene(scenaPU);
		PopUp1.showAndWait();
	}


	/**
	 * Avvio vero e proprio del gioco
	 * @param args argomenti dalla riga di comando
	 */
	public static void main(String[] args)
	{
		launch();
		//HelloApplication game = new HelloApplication();
		//game.runGame();
	}
}
