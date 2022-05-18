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
public class TriangleTerrain extends Identifiable {

    private Point[] sommets;
    private SegmentTerrain[] segments;

    /**
     *
     * @param id
     * @param p1
     * @param p2
     * @param p3
     */
    public TriangleTerrain(int id, Point p1, Point p2, Point p3) {
        super(id);
        this.sommets = new Point[]{p1, p2, p3};
        this.segments = new SegmentTerrain[this.sommets.length];
        for (int i = 0; i < this.sommets.length; i++) {
            this.segments[i] = new SegmentTerrain(this,
                    this.sommets[i], this.sommets[(i + 1) % this.sommets.length]);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TriangleTerrain{" +
                this.sommets[0] +
                " -- " + this.sommets[1]+
                " -- " + this.sommets[2]+ " }";
    }
    
    

    void save(Writer out) throws IOException {
        out.append("Triangle" + ";" + this.getId()
                + ";" + this.sommets[0] + ";" + this.sommets[1] + ";" + this.sommets[2] + "\n");
    }

    /**
     *
     * @param p
     * @return
     */
    public int indexPoint(Point p) {
        for (int i = 0; i < this.sommets.length; i++) {
            if (this.sommets[i] == p) {
                return i;
            }
        }
        throw new Error("le point " + p + " n'est pas un sommet du triangle " + this);
    }
    
    /**
     *
     * @param numPoint
     * @return
     */
    public Point getPoint(int numPoint) {
        return this.sommets[numPoint];
    }

    /**
     * retourne le segment (coté) du triangle commençant au point p.
     * @param numPoint
     * @return 
     */
    public SegmentTerrain getSegment(int numPoint) {
        return this.segments[numPoint];
    }

}
