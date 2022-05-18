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
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author francois
 */
public class CatalogueBarres {

    private List<TypeBarre> typesBarre;

    /**
     *
     * @param typesBarre
     */
    public CatalogueBarres(List<TypeBarre> typesBarre) {
        this.typesBarre = typesBarre;
    }

    /**
     *
     * @param typesBarre
     */
    public CatalogueBarres(TypeBarre[] typesBarre) {
        this(new ArrayList<TypeBarre>(Arrays.asList(typesBarre)));
    }

    /**
     * todoDoc.
     */
    public CatalogueBarres() {
        this(new ArrayList<>());
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("CatalogueBarres{\n");
        for (TypeBarre tb : this.typesBarre) {
            res.append(StringUtil.specialIndent(tb.toString(), "  "));
            res.append("\n");
        }
        res.append("}\n");
        return res.toString();
    }

    /**
     *
     * @param tb
     */
    public void addTypeBarre(TypeBarre tb) {
        this.typesBarre.add(tb);
    }

    /**
     *
     * @param idType
     * @return
     */
    public TypeBarre trouveTypeBarre(int idType) {
        for (TypeBarre tb : this.typesBarre) {
            if (tb.getId() == idType) {
                return tb;
            }
        }
        throw new Error("typeBarre d'id " + idType + " n'existe pas dans catalogue " + this);
    }

    void save(Writer out) throws IOException {
        out.append("TYPESBARRE\n");
        for (TypeBarre tb : this.typesBarre) {
            tb.save(out);
        }
        out.append("FINTYPESBARRE\n");
    }

    /**
     *
     * @param in
     * @return
     * @throws IOException
     * @throws SyntaxException
     */
    public static CatalogueBarres read(LineReader in) throws IOException, SyntaxException {
        CatalogueBarres res = new CatalogueBarres();
        String line = in.nextLineNoComment();
        if (!line.equals("TYPESBARRE")) {
            in.throwSyntaxExceptionWithLineNumber("TYPESBARRE attendu");
        }
        while (!(line = in.nextLineNoComment()).equals("FINTYPESBARRE")) {
            String[] decomp = line.split(";");
            if (!decomp[0].equals("TypeBarre")) {
                in.throwSyntaxExceptionWithLineNumber("TypeBarre attendu");
            }
            if (decomp.length < 7) {
                in.throwSyntaxExceptionWithLineNumber("TypeBarre mal formée");
            }
            int id;
            double coutMetre, longMin, longMax, tractionMax, compressionMax;
            try {
                int i = 1;
                id = Integer.parseInt(decomp[i++]);
                coutMetre = Double.parseDouble(decomp[i++]);
                longMin = Double.parseDouble(decomp[i++]);
                longMax = Double.parseDouble(decomp[i++]);
                tractionMax = Double.parseDouble(decomp[i++]);
                compressionMax = Double.parseDouble(decomp[i++]);
                TypeBarre tb = new TypeBarre(id, coutMetre, longMin, longMax, compressionMax, compressionMax);
                res.addTypeBarre(tb);
            } catch (NumberFormatException ex) {
                in.throwSyntaxExceptionWithLineNumber("TypeBarre : id ou nombre invalide");
            }
        }
        return res;

    }

}
