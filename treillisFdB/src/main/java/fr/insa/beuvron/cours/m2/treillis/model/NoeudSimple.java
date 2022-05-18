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
public class NoeudSimple extends Noeud {

    private final Point position;

    /**
     *
     * @param id
     * @param position
     */
    public NoeudSimple(int id, Point position,Vecteur2D forceExterieure) {
        super(id,forceExterieure);
        this.position = position;
    }
    
    @Override
    public String toString() {
        return "NoeudSimple{" + this.getId() +
                " en " + this.position + "}";
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void save(Writer out) throws IOException {
        out.append("NoeudSimple" + ";" + this.getId()
                + ";" + this.position.toString() + "\n");
    }

    @Override
    public double calPx() {
        return this.position.getPx();
    }

    @Override
    public double calPy() {
        return this.position.getPy();
    }
}
