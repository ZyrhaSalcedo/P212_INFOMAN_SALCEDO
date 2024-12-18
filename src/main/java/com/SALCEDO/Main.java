package com.SALCEDO;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        primaryStage.setTitle("Student Information");


        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.setMinHeight(400);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

