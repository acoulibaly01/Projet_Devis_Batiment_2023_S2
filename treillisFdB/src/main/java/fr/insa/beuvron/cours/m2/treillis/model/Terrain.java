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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author francois
 */
public class Terrain {

    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    private List<TriangleTerrain> triangles;

    /**
     *
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @param triangles
     */
    public Terrain(double minX, double maxX, double minY, double maxY, List<TriangleTerrain> triangles) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.triangles = triangles;
    }

    /**
     *
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     */
    public Terrain(double minX, double maxX, double minY, double maxY) {
        this(minX, maxX, minY, maxY, new ArrayList<TriangleTerrain>());
    }
    
    /** todoDoc. */
    public Terrain() {
        this(0,0,0,0);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String res = "Terrain{zone constructible : " + "minX=" + minX + ", maxX=" + maxX + 
                ", minY=" + minY  + ", maxY=" + maxY + 
                "\n triangles : \n";
        for (TriangleTerrain tt : this.triangles) {
            res = res + "  " + tt + "\n";
        }
        return res + "}";
    }
    
    

    /**
     * @return the minX
     */
    public double getMinX() {
        return minX;
    }

    /**
     * @param minX the minX to set
     */
    public void setMinX(double minX) {
        this.minX = minX;
    }

    /**
     * @return the maxX
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * @param maxX the maxX to set
     */
    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    /**
     * @return the minY
     */
    public double getMinY() {
        return minY;
    }

    /**
     * @param minY the minY to set
     */
    public void setMinY(double minY) {
        this.minY = minY;
    }

    /**
     * @return the maxY
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * @param maxY the maxY to set
     */
    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    /**
     *
     * @param t
     */
    public void addTriangle(TriangleTerrain t) {
        this.triangles.add(t);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public TriangleTerrain trouveTriangle(int id) {
        for (TriangleTerrain tt : this.triangles) {
            if (tt.getId() == id) {
                return tt;
            }
        }
        throw new Error("Pas de triangle avec id " + id + " dans terrain " + this);
    }

    void save(Writer out) throws IOException {
        out.append("ZoneConstructible" + ";" + this.minX + ";" + this.maxX
                + ";" + this.minY + ";" + this.maxY + "\n");
        for (TriangleTerrain t : this.triangles) {
            t.save(out);
        }
        out.append("FINTRIANGLES\n");
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     * @throws SyntaxException
     */
    public static Terrain read(LineReader in) throws IOException, SyntaxException {
        String line = in.nextLineNoComment();
        String[] decomp = line.split(";");
        if (!decomp[0].equals("ZoneConstructible")) {
            in.throwSyntaxExceptionWithLineNumber("ZoneConstructible attendu");
        }
        if (decomp.length < 5) {
            in.throwSyntaxExceptionWithLineNumber("ZoneConstructible mal formée");
        }
        double minX, maxX, minY, maxY;
        try {
            minX = Double.parseDouble(decomp[1]);
            maxX = Double.parseDouble(decomp[2]);
            minY = Double.parseDouble(decomp[3]);
            maxY = Double.parseDouble(decomp[4]);
        } catch (NumberFormatException ex) {
            throw new SyntaxException("ZoneConstructible nombre invalide : \"" + line + "\"");
        }
        Terrain res = new Terrain(minX, maxX, minY, maxY);
            line = in.nextLineNoComment();
            if (! line.equals("TRIANGLES")) {
                throw new SyntaxException("TRIANGLES attendu");
            }
        do {
            line = in.nextLineNoComment();
            if (! line.equals("FINTRIANGLES")) {
                decomp = line.split(";");
                if (!decomp[0].equals("Triangle")) {
                    throw new SyntaxException("Triangle attendu; trouvé : \"" + line + "\"");
                }
                if (decomp.length != 5) {
                    throw new SyntaxException("Triangle mal formée sur ligne : \"" + line + "\"");
                }
                int id;
                Point p1, p2, p3;
                try {
                    id = Integer.parseInt(decomp[1]);
                } catch (NumberFormatException ex) {
                    throw new SyntaxException("Triangle : id invalide : \"" + line + "\"");
                }
                p1 = Point.parsePoint(decomp[2]);
                p2 = Point.parsePoint(decomp[2]);
                p3 = Point.parsePoint(decomp[2]);
                TriangleTerrain tt = new TriangleTerrain(id, p1, p2, p3);
                res.addTriangle(tt);
            }
        } while (!line.equals("FINTRIANGLES"));
        return res;
    }

}
