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

/**
 *
 * @author francois
 */
public class SegmentTerrain {
    
    private TriangleTerrain contenant;
    private Point deb;
    private Point fin;

    /**
     *
     * @param contenant
     * @param deb
     * @param fin
     */
    public SegmentTerrain(TriangleTerrain contenant, Point deb, Point fin) {
        this.contenant = contenant;
        this.deb = deb;
        this.fin = fin;
    }
    
    @Override
    public String toString() {
        return "SegmentTerrain{" + this.deb + " --> " + this.fin + "}";
    }

    /**
     * @return the contenant
     */
    public TriangleTerrain getContenant() {
        return contenant;
    }

    /**
     * @return the deb
     */
    public Point getDeb() {
        return deb;
    }

    /**
     * @return the fin
     */
    public Point getFin() {
        return fin;
    }
    
    
    
}
