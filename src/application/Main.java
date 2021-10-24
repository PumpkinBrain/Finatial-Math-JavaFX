package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Logic log = new Logic();
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Calculadora Matematica Financeira");
		
	//text
		Label display = new Label();
		display.setText("Insira o Pv");
		
		TextField userInput = new TextField();
		
	//buttons
	//buttons that submit data will call log.userInput() after respective method execution to update the display Label
		Button dButton = new Button("Dia");
		dButton.setOnAction(e -> {
			log.measurement('d');
			log.userInput(userInput, display);
		});
		
		Button mButton = new Button("Mês");
		mButton.setOnAction(e -> {
			log.measurement('m');
			log.userInput(userInput, display);
		});
		
		Button yButton = new Button("Ano");
		yButton.setOnAction(e -> {
			log.measurement('y');
			log.userInput(userInput, display);
		});
		
		Button cButton = new Button("Limpar");
		cButton.setOnAction(e -> {
			log.clear(display, userInput);
		});
		
		Button okButton = new Button("Ok");
		okButton.setOnAction(e -> {
		//button will be disabled in states 2, 4 and 5
			if((Data.state != 2) && (Data.state != 4) && (Data.state!= 6)) {
				log.userInput(userInput, display);
				Data.state++;
				log.userInput(userInput, display); //method will be called again to update display Label
			}
		});
		
		Button simpleButton = new Button("Simples");
		simpleButton.setOnAction(e -> {
			log.showResultSimple(display);
		});
		
		Button compoundButton = new Button("Composto");
		compoundButton.setOnAction(e -> {
			log.showResultCompound(display);
		});
		
	//design
		VBox texts = new VBox(5);
		texts.getChildren().addAll(display, userInput);
		texts.setPadding(new Insets(5,0,0,0));
		
		HBox timeButtons = new HBox(2);
		timeButtons.getChildren().addAll(dButton, mButton, yButton);
		timeButtons.setPadding(new Insets(5,0,0,0));
		
		HBox functionButtons = new HBox(2);
		functionButtons.getChildren().addAll(cButton, okButton);
		functionButtons.setPadding(new Insets(5,0,0,0));
		
		HBox operationButtons = new HBox(2);
		operationButtons.getChildren().addAll(simpleButton, compoundButton);
		operationButtons.setPadding(new Insets(5,0,0,0));
		
		GridPane layout = new GridPane();
		GridPane.setConstraints(texts, 0, 0);
		GridPane.setConstraints(timeButtons, 0, 1);
		GridPane.setConstraints(functionButtons, 0, 2);
		GridPane.setConstraints(operationButtons, 0, 3);
		layout.setPadding(new Insets(5, 5, 5, 5));
		layout.getChildren().addAll(texts, timeButtons, functionButtons, operationButtons);
		
	//scene and window initialization
		Scene scene = new Scene(layout, 300, 300);
		window.setMinHeight(200);
		window.setMinWidth(200);
		window.setScene(scene);
		window.show();
	}
}
