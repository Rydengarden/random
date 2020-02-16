package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import analyseresult.Result;
import datastructure.EnduroMap;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import parse.ParseInPerson;
import parse.ParseInRegister;

public class Gui extends Application {
	FileChooser filechooser = new FileChooser();
	EnduroMap ea = new EnduroMap();
	ParseInPerson piP = new ParseInPerson(ea);
	ParseInRegister piR = new ParseInRegister(ea);
	Result res = new Result(ea);
	File startFileName;
	File goalFileName;
	File personFileName;
	String resultFileName;

	public static void main(final String[] args) {
		launch(args);
	}
	
	private synchronized void work() throws InterruptedException {
		while(true) {
			wait();
			try {
				if(personFileName != null) piP.parseNameFile(personFileName);
				if(startFileName != null) piR.parseStartFile(startFileName);
				if(goalFileName != null) piR.parseEndFile(goalFileName);
				if(resultFileName != null) res.generateMinimalOneLapResult(resultFileName);
			} catch (final FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private synchronized void ready() {
		notifyAll();
	}

	@Override
	public void start(final Stage PrimaryStage) throws Exception {
		PrimaryStage.setTitle("Enduro Watch");
		final Button startButton = new Button("Select Startfile");
		final Button goalButton = new Button("Select Goalfile");
		final Button nameButton = new Button("Select Namefile");
		final Button resultButton = new Button("Generate result");
		VBox vb = new VBox();
		VBox startFileBox = new VBox(); 
		VBox goalFileBox = new VBox(); 
		VBox personFileBox = new VBox();
		Text erroMesage = new Text("Please make sure you have selected all files");
		Font fontButton = new Font(30);
		Font fontMessage = new Font(20);

		startButton.setFont(fontButton);
		goalButton.setFont(fontButton);
		nameButton.setFont(fontButton);
		resultButton.setFont(fontButton);
		erroMesage.setFont(fontMessage);
		erroMesage.setFill(Color.RED);
		startFileBox.setPadding(new Insets(15));
		goalFileBox.setPadding(new Insets(15));
		personFileBox.setPadding(new Insets(15));
		

		startButton.setOnAction(e -> {
			startFileName = filechooser.showOpenDialog(PrimaryStage);
			Text startFile = new Text("Start file: " + startFileName.getName());
			startFile.setFont(fontMessage);
	
			startFileBox.getChildren().remove(startFile);
			startFileBox.getChildren().add(startFile);


		});

		goalButton.setOnAction(e -> {
			goalFileName = filechooser.showOpenDialog(PrimaryStage);
			Text goalFile = new Text("Goal file: " + goalFileName.getName());
			goalFile.setFont(fontMessage);
	
			goalFileBox.getChildren().remove(goalFile);
			goalFileBox.getChildren().add(goalFile);


		});
		nameButton.setOnAction(e -> {
			personFileName = filechooser.showOpenDialog(PrimaryStage);
			Text nameFile = new Text("Name file: " + personFileName.getName());
			nameFile.setFont(fontMessage);
	
			personFileBox.getChildren().remove(nameFile);
			personFileBox.getChildren().add(nameFile);
		});
		
		resultButton.setOnAction(e -> {
			final File temp = filechooser.showSaveDialog(PrimaryStage);
			if(temp != null && startFileName != null && goalFileName != null && personFileName != null) {
				resultFileName = temp.toPath().toString();
				vb.getChildren().remove(erroMesage);
			}	
			else {
				vb.getChildren().remove(erroMesage);
				vb.getChildren().add(erroMesage);
			}
			ready();
		});
		
		final GridPane layout = new GridPane();
		layout.setVgap(10);
		layout.setPadding(new Insets(10));
		GridPane.setRowIndex(startButton, 0);
		GridPane.setRowIndex(goalButton, 1);
		GridPane.setRowIndex(nameButton, 2);
		GridPane.setRowIndex(resultButton, 4);
		GridPane.setRowIndex(vb, 5);
		GridPane.setColumnIndex(vb, 1);
		GridPane.setColumnIndex(startFileBox, 1);
		GridPane.setRowIndex(startFileBox, 0);
		GridPane.setColumnIndex(goalFileBox, 1);
		GridPane.setRowIndex(goalFileBox, 1);
		GridPane.setColumnIndex(personFileBox, 1);
		GridPane.setRowIndex(personFileBox, 2);

		layout.getChildren().addAll(startButton, goalButton, nameButton, resultButton, vb, startFileBox, goalFileBox, personFileBox);
		final Scene scene = new Scene(layout, 600, 400);
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		
		final Thread t = new Thread(() ->  {
			try {
				work();
			} catch (final InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		t.start();
	}

}
