package fr.umontpellier.iut.conquest;

/**
 * Modélise un pion.
 */
public class Pawn {
    /**
     * Le joueur auquel le pion appartient.
     */
    private Player player;

    /**
     * Constructeur.
     */
    public Pawn(Player player) {
        this.player = player;
    }

    /**
     * Getter and setter.
     */

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
