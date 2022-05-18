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
package fr.insa.beuvron.cours.m2.treillis.model;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author francois
 */
public class Barre extends Identifiable {

    /**
     * @return the type
     */
    public TypeBarre getType() {
        return type;
    }

    /**
     * @return the noeud1
     */
    public Noeud getNoeud1() {
        return noeud1;
    }

    /**
     * @return the noeud2
     */
    public Noeud getNoeud2() {
        return noeud2;
    }

    private TypeBarre type;
    private Noeud noeud1;
    private Noeud noeud2;

    /**
     *
     * @param id
     * @param type
     * @param noeud1
     * @param noeud2
     */
    public Barre(int id, TypeBarre type, Noeud noeud1, Noeud noeud2) {
        super(id);
        this.type = type;
        this.noeud1 = noeud1;
        this.noeud2 = noeud2;
        this.noeud1.getBarresDepart().add(this);
        this.noeud2.getBarresArrivee().add(this);
    }
    
    @Override
    public String toString() {
        return "Barre{" + this.getId() 
                + " type=" + this.getType().getId() 
                + " entre noeuds " + this.getNoeud1().getId() + " et " + this.getNoeud2().getId()
                + "}";
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    public void save(Writer out) throws IOException {
        out.append("Barre" + ";" + this.getId()
                + ";" + this.getType().getId()
                + ";" + this.getNoeud1().getId()
                + ";" + this.getNoeud2().getId() + "\n");
    }


}
