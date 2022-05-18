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

import fr.insa.beuvron.utils.StringUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author francois
 */
public class Treillis {

    private Terrain leTerrain;
    private CatalogueBarres typesBarre;
    private List<Noeud> lesNoeuds;
    private List<Barre> lesBarres;

    /**
     *
     * @param leTerrain
     * @param typesBarre
     */
    public Treillis(Terrain leTerrain, CatalogueBarres typesBarre) {
        this.leTerrain = leTerrain;
        this.typesBarre = typesBarre;
        this.lesNoeuds = new ArrayList<>();
        this.lesBarres = new ArrayList<>();
    }

    /**
     * todoDoc.
     */
    public Treillis() {
        this(new Terrain(), new CatalogueBarres());
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String res = "Treillis{\n"
                + "---------- terrain ------------\n"
                + StringUtil.specialIndent(this.leTerrain.toString(), "  ") + "\n"
                + "---------- types barre ------------\n"
                + StringUtil.specialIndent(this.typesBarre.toString(), "  ") + "\n"
                + "---------- noeuds ------------\n";
        for (Noeud n : this.lesNoeuds) {
            res = res + StringUtil.specialIndent(n.toString(), "  ") + "\n";
        }
        res = res + "---------- barres ------------\n";
        for (Barre b : this.lesBarres) {
            res = res + StringUtil.specialIndent(b.toString(), "  ") + "\n";
        }
        return res + "}";
    }

    /**
     *
     * @param n
     */
    public void addNoeud(Noeud n) {
        this.lesNoeuds.add(n);
    }

    /**
     *
     * @param idNoeud
     * @return
     */
    public Optional<Noeud> trouveNoeudParId(int idNoeud) {
        for (Noeud n : this.lesNoeuds) {
            if (n.getId() == idNoeud) {
                return Optional.of(n);
            }
        }
        return Optional.empty();
    }

    /**
     *
     * @param b
     */
    public void addBarre(Barre b) {
        this.lesBarres.add(b);
    }

    /**
     *
     * @param out
     * @throws IOException
     */
    public void save(Writer out) throws IOException {
        this.leTerrain.save(out);
        this.typesBarre.save(out);
        out.append("NOEUDS\n");
        for (Noeud n : this.lesNoeuds) {
            n.save(out);
        }
        out.append("FINNOEUDS\n");
        out.append("BARRES\n");
        for (Barre b : this.lesBarres) {
            b.save(out);
        }
        out.append("FINBARRES\n");
    }

    public static Treillis read(Reader r) throws IOException, SyntaxException {
        return read(new LineReader(r));
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     * @throws SyntaxException
     */
    public static Treillis read(LineReader in) throws IOException, SyntaxException {
        Terrain t = Terrain.read(in);
        CatalogueBarres tb = CatalogueBarres.read(in);
        Treillis res = new Treillis(t, tb);
        String line = in.nextLineNoComment();
        if (!line.equals("NOEUDS")) {
            in.throwSyntaxExceptionWithLineNumber("NOEUDS attendus");
        }
        while (!(line = in.nextLineNoComment()).equals("FINNOEUDS")) {
            String[] decomp = line.split(";");
            if (!decomp[0].equals("NoeudSimple")
                    && !decomp[0].equals("AppuiSimple")
                    && !decomp[0].equals("AppuiDouble")) {
                in.throwSyntaxExceptionWithLineNumber("type de noeud invalide : " + decomp[0]);
            }
            if (decomp.length < 4) {
                in.throwSyntaxExceptionWithLineNumber("description de noeud incomplete");
            }
            int id = 0;
            try {
                id = Integer.parseInt(decomp[1]);
            } catch (NumberFormatException ex) {
                in.throwSyntaxExceptionWithLineNumber("invalid id noeud : " + decomp[1]);
            }
            if (!res.trouveNoeudParId(id).isEmpty()) {
                in.throwSyntaxExceptionWithLineNumber("id noeud existe deja : " + id);
            }
            
            Optional<Point> pos = Point.parse(decomp[2]);
            if (pos.isEmpty()) {
                in.throwSyntaxExceptionWithLineNumber("invalid position : " + decomp[2]);
            }
            Optional<Vecteur2D> force = Vecteur2D.parse(decomp[3]);
            if (force.isEmpty()) {
                in.throwSyntaxExceptionWithLineNumber("invalid vecteur : " + decomp[3]);
            }
            Noeud curN;
            if (decomp[0].equals("NoeudSimple")) {
                curN = new NoeudSimple(id, pos.get(), force.get());
            } else if (decomp[0].equals("AppuiSimple")) {
                // TODO : revoir definition des NoeudAppui pour être plus proche du format texte
                Optional<SegmentTerrain> proche = res.findSegmentTerrain(pos.get());
            }
            
        }
        if (!line.equals("BARRES")) {
            in.throwSyntaxExceptionWithLineNumber("BARRES attendus");
        }
        while (!(line = in.nextLineNoComment()).equals("FINBARRES")) {
            String[] decomp = line.split(";");
            if (!decomp[0].equals("Barre")) {
                in.throwSyntaxExceptionWithLineNumber("Barre attendue");
            }
            if (decomp.length < 5) {
                throw new SyntaxException("Barre mal formée");
            }
            int id, idType, idN1, idN2;
            try {
                int i = 1;
                id = Integer.parseInt(decomp[i++]);
                idType = Integer.parseInt(decomp[i++]);
                idN1 = Integer.parseInt(decomp[i++]);
                idN2 = Integer.parseInt(decomp[i++]);
            } catch (NumberFormatException ex) {
                throw new SyntaxException("Barre : un id invalide");
            }
            TypeBarre bt = res.getTypesBarre().trouveTypeBarre(idType);
            Optional<Noeud> n1 = res.trouveNoeudParId(idN1);
            if (n1.isEmpty()) {
                in.throwSyntaxExceptionWithLineNumber("id noeud " + idN1 + " n'existe pas");
            }
            Optional<Noeud> n2 = res.trouveNoeudParId(idN2);
            if (n2.isEmpty()) {
                in.throwSyntaxExceptionWithLineNumber("id noeud " + idN2 + " n'existe pas");
            }
            res.addBarre(new Barre(id, bt, n1.get(), n2.get()));
        }
        // seuls les lignes commentaires sont encore acceptés
        return res;
    }

    /**
     *
     * @return
     */
    public static Treillis treillisPortiqueSujet() {
        Point pt11 = new Point(200, 0);
        Point pt12 = new Point(200, 400);
        Point pt13 = new Point(0, 200);
        TriangleTerrain tt1 = new TriangleTerrain(1, pt11, pt12, pt13);
        Terrain t = new Terrain(-10, 300, -10, 300);
        t.addTriangle(tt1);
        TypeBarre tb1 = new TypeBarre(1, 100, 1, 5, 1000, 2000);
        CatalogueBarres cb = new CatalogueBarres(new TypeBarre[]{tb1});
        Treillis tr = new Treillis(t, cb);
        Noeud s1 = new AppuiDouble(1,new Vecteur2D(0, 0), tt1.getSegment(0), 3.0 / 4);
        Noeud s2 = new AppuiSimple(2,new Vecteur2D(0, 0), tt1.getSegment(0), 1.0 / 4);
        Noeud s3 = new NoeudSimple(3,new Point(400, 200),new Vecteur2D(0, -1000));
        tr.addNoeud(s1);
        tr.addNoeud(s2);
        tr.addNoeud(s3);
        Barre b1 = new Barre(1, tb1, s1, s3);
        Barre b2 = new Barre(2, tb1, s2, s3);
        Barre b3 = new Barre(3, tb1, s1, s2);
        tr.addBarre(b1);
        tr.addBarre(b2);
        tr.addBarre(b3);
        return tr;
    }

    /**
     * todoDoc.
     */
    public static void testSave() {
        Treillis tr = treillisPortiqueSujet();
        StringWriter out = new StringWriter();
        try {
            tr.save(out);
            System.out.println("//--- fichier treillis pour exemple portique du sujet ---");
            System.out.println(out.toString());
        } catch (IOException ex) {
            throw new Error(ex);
        }
    }

    /**
     * todoDoc.
     */
    public static void testSaveReadSave() {
        Treillis tr = treillisPortiqueSujet();
        StringWriter out = new StringWriter();
        try {
            tr.save(out);
            System.out.println("//--- fichier treillis premier save ---");
            System.out.println(out.toString());
            StringReader sr = new StringReader(out.toString());
            Treillis tr2 = Treillis.read(sr);
            StringWriter out2 = new StringWriter();
            tr2.save(out2);
            System.out.println("//--- fichier treillis apres read+ 2ième save ---");
            System.out.println(out2.toString());
        } catch (IOException | SyntaxException ex) {
            throw new Error(ex);
        }

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        testSave();
        // testSaveReadSave();
    }

    /**
     * @return the leTerrain
     */
    public Terrain getLeTerrain() {
        return leTerrain;
    }

    /**
     * @return the typesBarre
     */
    public CatalogueBarres getTypesBarre() {
        return typesBarre;
    }

    /**
     * @return the lesNoeuds
     */
    public List<Noeud> getLesNoeuds() {
        return lesNoeuds;
    }

    /**
     * @return the lesBarres
     */
    public List<Barre> getLesBarres() {
        return lesBarres;
    }

    private Optional<SegmentTerrain> findSegmentTerrain(Point get) {
        //TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
