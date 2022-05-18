/*
Copyright 2000- Francois de Bertrand de Beuvron

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

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class Vecteur2D {
    private double vx;
    private double vy;

    public Vecteur2D(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public int hashCode() {
         return Objects.hash(this.vx,this.vy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vecteur2D other = (Vecteur2D) obj;
        return this.vx == other.vx && this.vy == other.vy;
    }

    public static Optional<Vecteur2D> parse(String extVect) {
        if(extVect.charAt(0) != '[' || extVect.charAt(extVect.length()-1) != ']') {
            return Optional.empty();
        }
        String[] coords = extVect.substring(1, extVect.length()-1).split(",");
        if (coords.length != 2) {
            return Optional.empty();
        }
        try {
            double vx = Double.parseDouble(coords[0]);
            double vy = Double.parseDouble(coords[1]);
            return Optional.of(new Vecteur2D(vx, vy));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
    
    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(double vy) {
        this.vy = vy;
    }
    
    
    
}
