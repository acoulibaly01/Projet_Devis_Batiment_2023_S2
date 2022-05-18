/*
Copyright 2000-2014 Francois de Bertrand de Beuvron

This file is part of CoursBeuvron.

CoursBeuvron is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CoursBeuvron is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CoursBeuvron.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.insa.beuvron.cours.m2.treillis.interfaceText;

import fr.insa.beuvron.cours.m2.treillis.model.SyntaxException;
import fr.insa.beuvron.cours.m2.treillis.model.Treillis;
import fr.insa.beuvron.utils.ConsoleFdB;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francois
 */
public class MenusText {

    /**
     *
     * @return
     */
    public static File demandeFichier() {
        Path curDefaultPath = Paths.get("");
        System.out.println("default path : " + curDefaultPath);
        boolean encore = true;
        Path fich = null;
        while (encore) {
            String ps = ConsoleFdB.entreeString("enter file path : ");
                fich = Paths.get(ps);
                if (fich.toFile().canRead()) {
                    encore = false;
                } else {
                    System.out.println("Fichier " + fich.toString() + " non existant ou non lisible");
                }
            }
            return fich.toFile();
        }

    /** todoDoc. */
    public static void menuPrincipal() {
        int rep = -1;
        Treillis curTreillis = new Treillis();
        while (rep != 0) {
            int i = 1;
            System.out.println(i+") nouveau treilli vide");
            i++;
            System.out.println(i+") treilli portique test");
            i ++;
            System.out.println(i+") afficher treilli courant (format toString)");
            i ++;
            System.out.println(i+") afficher treilli courant (format save)");
            i ++;
            System.out.println(i+") lire treilli dans fichier");
            i++;
            System.out.println(i+") gérer treilli courant");
            rep = ConsoleFdB.entreeEntier("votre choix : (1-" + i
                    + "(0 pour quitter) : ");
            i = 1;
            if (rep == i++) {
                curTreillis = new Treillis();
            } else if (rep == i++) {
                curTreillis = Treillis.treillisPortiqueSujet();
            } else if (rep == i++) {
                System.out.println("treillis courant : \n"+curTreillis);
            } else if (rep == i++) {
                System.out.println("format save treillis courant : ");
                StringWriter out = new StringWriter();
                try {
                    curTreillis.save(out);
                    System.out.println(out.toString());
                } catch (IOException ex) {
                    System.out.println("Erreur : " + ex.getLocalizedMessage());
                }
                System.out.println();
            } else if (rep == i++) {
                boolean again = true;
                while (again) {
                    File toRead = demandeFichier();
                    again = false;
                    try {
                        curTreillis = Treillis.read(new FileReader(toRead));
                   } catch (IOException ex) {
                        System.out.println("Problem de lecture : " + ex.getLocalizedMessage());
                    } catch (SyntaxException ex) {
                        System.out.println("Erreur de syntaxe : " + ex.getLocalizedMessage());;
                    }
                }
            } else if (rep == i++) {
            }
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println(Treillis.treillisPortiqueSujet());
        menuPrincipal();
    }
}
