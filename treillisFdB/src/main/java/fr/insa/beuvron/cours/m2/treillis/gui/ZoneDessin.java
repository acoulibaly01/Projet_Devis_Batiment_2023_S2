/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.insa.beuvron.cours.m2.treillis.gui;

import fr.insa.beuvron.cours.m2.treillis.model.Barre;
import fr.insa.beuvron.cours.m2.treillis.model.Noeud;
import fr.insa.beuvron.cours.m2.treillis.model.Treillis;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author Amadou Coulibaly
 */
public class ZoneDessin extends Pane {

    public static final double RAYON = 5;

    private MainPane main;

    public ZoneDessin(MainPane main) {
        this.main = main;
        dessineTout();
    }

    public void dessineTout() {
        this.getChildren().clear();
        Treillis m = this.main.getModel();
        System.out.println("noeuds : " + m.getLesNoeuds());
        for (int i = 0; i < m.getLesNoeuds().size(); i++) {
            Noeud n = m.getLesNoeuds().get(i);
            Circle c = new Circle(n.calPx(), n.calPy(), RAYON);
            this.getChildren().add(c);
        }
        for (int i = 0; i < m.getLesBarres().size(); i++) {
            Barre b = m.getLesBarres().get(i);
            Line ll = new Line(b.getNoeud1().calPx(),
                    b.getNoeud1().calPy(),
                    b.getNoeud2().calPx(),
                    b.getNoeud2().calPy());
            this.getChildren().add(ll);
        }
    }

}
