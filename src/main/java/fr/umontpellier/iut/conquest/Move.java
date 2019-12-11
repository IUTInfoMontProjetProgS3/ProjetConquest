package fr.umontpellier.iut.conquest;

import java.util.Objects;

/**
 * Modélise un coup.
 */
public class Move {
    /**
     * Ligne de départ.
     */
    private final int row1;
    /**
     * Colonne de départ.
     */
    private final int column1;
    /**
     * Ligne d'arrivée.
     */
    private final int row2;
    /**
     * Colonne d'arrivée.
     */
    private final int column2;

    /**
     * Constructeur.
     */
    public Move(int row1, int column1, int row2, int column2) {
        this.row1 = row1;
        this.column1 = column1;
        this.row2 = row2;
        this.column2 = column2;
    }

    /**
     * Getters.
     */

    int getRow1() {
        return row1;
    }

    int getColumn1() {
        return column1;
    }

    int getRow2() {
        return row2;
    }

    int getColumn2() {
        return column2;
    }

    /**
     * equals and hashCode.
     * Un coup est identifié par ses attributs.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row1, column1, row2, column2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move m = (Move) o;
        return ((this.row1 == m.row1) && (this.column1 == m.column1) && (this.row2 == m.row2) && (this.column2 == m.column2));
    }

}