/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.cours.m2.treillis.gui;

import fr.insa.beuvron.cours.m2.treillis.model.Treillis;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author francois
 */
public class MainPane extends BorderPane {  
    
    private Treillis model;
    private ZoneDessin dessin;
    
    public MainPane(Treillis model) {
        this.model = model;
        Button b = new Button("todo");
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setTop(b);
        this.dessin = new ZoneDessin(this);
        this.setCenter(this.dessin);
        
    }

    /**
     * @return the model
     */
    public Treillis getModel() {
        return model;
    }

 
}
