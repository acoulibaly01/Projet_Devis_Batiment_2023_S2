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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author francois
 */
public class LineReader {
    
    private int numLigne;
    private String curLigne;
    private BufferedReader reader;

    public LineReader(Reader reader) {
        this.reader = new BufferedReader(reader);
        this.curLigne = "";
        this.numLigne = 0;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public static boolean isComment(String line) {
        return line.length() == 0 || (line.length() >= 2 && line.charAt(0) == '/' && line.charAt(1) == '/');
    }

    /**
     * Lit le flux (reader) jusqu'à trouver une ligne qui n'est pas un
     * commentaire. Met à jour le compteur de ligne.
     *
     * @param in
     * @return la première ligne qui n'est pas un commentaire.
     */
    public String nextLineNoComment() throws IOException, SyntaxException {
        String line;
        do {
            line = this.nextLine();
        } while (isComment(line));
        return line;
    }
    
    public void throwSyntaxExceptionWithLineNumber(String message) throws SyntaxException {
        throw new SyntaxException("ligne " + this.getNumLigne() + " : " + message +
                "\n ligne = \"" + this.curLigne + "\"");
    }

    /**
     * Lit une ligne sur le flux le flux (reader). Met à jour le compteur de
     * ligne.
     *
     * @param in
     * @return la première ligne qui n'est pas un commentaire.
     */
    public String nextLine() throws IOException, SyntaxException {
        this.numLigne++;
        this.curLigne = this.reader.readLine();
        if (this.curLigne == null) {
            this.curLigne = "";
            throw new SyntaxException("fin de stream non attendu après ligne" + this.curLigne);
        }
        return this.curLigne;
    }
    
    public void skipFinalCommentLines() throws IOException, SyntaxException {
        while((this.curLigne = this.reader.readLine()) != null) {
            this.numLigne ++;
            if (! isComment(this.curLigne)) {
                this.throwSyntaxExceptionWithLineNumber("ligne non commentaire interdite");
            }
        }
    }
    
}
