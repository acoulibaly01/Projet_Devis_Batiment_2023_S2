/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.cours.m2.treillis.gui;

import fr.insa.beuvron.cours.m2.treillis.model.Treillis;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author francois
 */
public class MainFx extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Treillis model = new Treillis();
        Treillis model = Treillis.treillisPortiqueSujet();
        System.out.println("model : " + model);
        MainPane main = new MainPane(model);
        Scene sc = new Scene(main,600,400);
        stage.setScene(sc);
        stage.setTitle("Treillis");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
