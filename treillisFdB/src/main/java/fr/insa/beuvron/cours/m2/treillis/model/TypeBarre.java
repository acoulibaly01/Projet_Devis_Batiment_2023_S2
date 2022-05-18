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
public class TypeBarre extends Identifiable {

    private double coutAuMetre;
    private double longueurMin;
    private double longueurMax;
    private double resistanceMaxTraction;
    private double resistanceMaxCompression;

    /**
     *
     * @param id
     * @param coutAuMetre
     * @param longueurMin
     * @param longueurMax
     * @param resistanceMaxTension
     * @param resistanceMaxCompression
     */
    public TypeBarre(int id, double coutAuMetre,
            double longueurMin, double longueurMax,
            double resistanceMaxTension, double resistanceMaxCompression) {
        super(id);
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTraction = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TypeBarre{" + this.getId() + 
                "\n  coutAuMetre=" + coutAuMetre + 
                "\n  longueurMin=" + longueurMin + 
                "\n  longueurMax=" + longueurMax + 
                "\n  resistanceMaxTraction=" + resistanceMaxTraction +
                "\n  resistanceMaxCompression=" + resistanceMaxCompression +
                "\n}";
    }
    
    

    void save(Writer out) throws IOException {
        out.append("TypeBarre" + ";" + this.getId() +
                ";" + this.coutAuMetre +
                ";" + this.longueurMin + ";" + this.longueurMax +
                ";" + this.resistanceMaxTraction + ";" + this.resistanceMaxCompression + "\n");

    }

    /**
     * @return the coutAuMetre
     */
    public double getCoutAuMetre() {
        return coutAuMetre;
    }

    /**
     * @param coutAuMetre the coutAuMetre to set
     */
    public void setCoutAuMetre(double coutAuMetre) {
        this.coutAuMetre = coutAuMetre;
    }

    /**
     * @return the longueurMin
     */
    public double getLongueurMin() {
        return longueurMin;
    }

    /**
     * @param longueurMin the longueurMin to set
     */
    public void setLongueurMin(double longueurMin) {
        this.longueurMin = longueurMin;
    }

    /**
     * @return the longueurMax
     */
    public double getLongueurMax() {
        return longueurMax;
    }

    /**
     * @param longueurMax the longueurMax to set
     */
    public void setLongueurMax(double longueurMax) {
        this.longueurMax = longueurMax;
    }

    /**
     * @return the resistanceMaxTraction
     */
    public double getResistanceMaxTraction() {
        return resistanceMaxTraction;
    }

    /**
     * @param resistanceMaxTraction the resistanceMaxTraction to set
     */
    public void setResistanceMaxTraction(double resistanceMaxTraction) {
        this.resistanceMaxTraction = resistanceMaxTraction;
    }

    /**
     * @return the resistanceMaxCompression
     */
    public double getResistanceMaxCompression() {
        return resistanceMaxCompression;
    }

    /**
     * @param resistanceMaxCompression the resistanceMaxCompression to set
     */
    public void setResistanceMaxCompression(double resistanceMaxCompression) {
        this.resistanceMaxCompression = resistanceMaxCompression;
    }

}
