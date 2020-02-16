package guireg;

import java.io.File;
import java.io.IOException;
import datastructure.EnduroTime;
import parse.Register;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuiReg extends Application {

	public static void main(String[] args)  {
		launch(args);
	}

	@Override
	public void start(Stage PrimaryStage) throws Exception, IOException {
		PrimaryStage.setTitle("Register");
		GridPane layout = new GridPane();
		layout.setVgap(10);
		layout.setPadding(new Insets(10));
		Button button = new Button("Register");
		TextField regNumber = new TextField("");
		Register reg = new Register(new File("Registration"));
		reg.deleteFile("Registration");
		
		
		VBox vb = new VBox();
		Text rt = new Text("Write the registrationnumber Below");
		button.setOnAction(e -> {	
		
		
		
			try {
				reg.addEntry(Integer.parseInt(regNumber.getText()), EnduroTime.now().toString());		
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			vb.getChildren().add(new Text(regNumber.getText() + ": " + EnduroTime.now().toString()));
			regNumber.clear();
		
		}
				);
		
		layout.getChildren().addAll(button, regNumber, rt, vb);
		GridPane.setRowIndex(rt, 0);
		GridPane.setRowIndex(regNumber, 1);
		GridPane.setRowIndex(button, 2);
		GridPane.setRowIndex(vb, 3);
		
		Scene scene = new Scene(layout, 300, 400);
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		}
	
}