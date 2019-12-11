package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

/**
 * Modélise une stratégie.
 */
public interface Strategy {
    /**
     * Prend un plateau et un joueur et retourne un coup valide pour ce joueur.
     */
    Move getMove(Board board, Player player);
}