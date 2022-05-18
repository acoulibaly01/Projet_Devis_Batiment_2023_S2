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
public abstract class Appui extends Noeud {

    private SegmentTerrain sur;
    private double positionSurSegment;

    /**
     *
     * @param id
     * @param sur
     * @param positionSurSegment
     */
    public Appui(int id,Vecteur2D forceExterieure, SegmentTerrain sur, double positionSurSegment) {
        super(id,forceExterieure);
        this.sur = sur;
        this.positionSurSegment = positionSurSegment;
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    @Override
    public void save(Writer out) throws IOException {
        TriangleTerrain triangle = this.sur.getContenant();
        Point depart = this.sur.getDeb();
        out.append(this.getClass().getSimpleName() + ";" + this.getId()
                + ";" + triangle.getId()
                + ";" + triangle.indexPoint(depart)
                + ";" + this.positionSurSegment + "\n");
    }

    @Override
    public double calPx() {
        double deb = this.sur.getDeb().getPx();
        double fin = this.sur.getFin().getPx();
        return deb + (fin-deb) * positionSurSegment;
    }
    
    @Override
    public double calPy() {
        double deb = this.sur.getDeb().getPy();
        double fin = this.sur.getFin().getPy();
        return deb + (fin-deb) * positionSurSegment;
    }
    
    /**
     * @return the sur
     */
    public SegmentTerrain getSur() {
        return sur;
    }

    /**
     * @return the positionSurSegment
     */
    public double getPositionSurSegment() {
        return positionSurSegment;
    }

}
