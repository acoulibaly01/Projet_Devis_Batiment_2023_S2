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
public class AppuiSimple extends Appui {

    /**
     *
     * @param id
     * @param sur
     * @param positionSurSegment
     */
    public AppuiSimple(int id,Vecteur2D forceExterieure, SegmentTerrain sur, double positionSurSegment) {
        super(id,forceExterieure, sur, positionSurSegment);
    }
    
    @Override
    public String toString() {
        return "AppuiSimple{" + this.getId() 
                + " sur " + this.getSur() 
                + " alpha = " + this.getPositionSurSegment() 
                + " ==> pos = (" + this.calPx() + "," + this.calPy() + ")}";
    }
   
    
}
