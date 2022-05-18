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

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class Point {

    private double px;
    private double py;

    /**
     *
     * @param px
     * @param py
     */
    public Point(double px, double py) {
        this.px = px;
        this.py = py;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.px,this.py);
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
        final Point other = (Point) obj;
        return this.px == other.px && this.py == other.py;
    }

    public static Optional<Point> parse(String extVect) {
        if(extVect.charAt(0) != '(' || extVect.charAt(extVect.length()-1) != ')') {
            return Optional.empty();
        }
        String[] coords = extVect.substring(1, extVect.length()-1).split(",");
        if (coords.length != 2) {
            return Optional.empty();
        }
        try {
            double px = Double.parseDouble(coords[0]);
            double py = Double.parseDouble(coords[1]);
            return Optional.of(new Point(px, py));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }
    
    /**
     * @return the px
     */
    public double getPx() {
        return px;
    }

    /**
     * @param px the px to set
     */
    public void setPx(double px) {
        this.px = px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    /**
     * @param py the py to set
     */
    public void setPy(double py) {
        this.py = py;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "(" + px + "," + py + ')';
    }

    static Point parsePoint(String sp) throws SyntaxException {
        if (sp.length() < 3) {
            throw new SyntaxException("Point invalide : " + sp);
        }
        if (sp.charAt(0) != '(' || sp.charAt(sp.length() - 1) != ')') {
            throw new SyntaxException("Point invalide : " + sp);
        }
        String sp2 = sp.substring(1, sp.length() - 1);
        String[] decomp = sp2.split(",");
        if (decomp.length != 2) {
            throw new SyntaxException("Point invalide : " + sp);
        }
        double px,py;
        try {
            px = Double.parseDouble(decomp[0]);
            py = Double.parseDouble(decomp[1]);
        }
        catch (NumberFormatException ex) {
            throw new SyntaxException("Point invalide : " + sp);
        }
        return new Point(px, py);
    }

}
