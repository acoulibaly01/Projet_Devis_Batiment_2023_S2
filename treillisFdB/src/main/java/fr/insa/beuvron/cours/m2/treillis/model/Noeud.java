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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francois
 */
public abstract class Noeud extends Identifiable {

    private Vecteur2D forceExterieure;
    private List<Barre> barresDepart;
    private List<Barre> barresArrivee;
    
    public Noeud(int id,Vecteur2D forceExterieure) {
        super(id);
        this.forceExterieure = forceExterieure;
        this.barresDepart = new ArrayList<>();
        this.barresArrivee = new ArrayList<>();
    }

    public Vecteur2D getForceExterieure() {
        return forceExterieure;
    }

    public List<Barre> getBarresDepart() {
        return barresDepart;
    }

    public List<Barre> getBarresArrivee() {
        return barresArrivee;
    }
    
    

    /**
     *
     * @param out
     * @throws IOException
     */
    public abstract void save(Writer out) throws IOException;

    public abstract double calPx();

    public abstract double calPy();

}
