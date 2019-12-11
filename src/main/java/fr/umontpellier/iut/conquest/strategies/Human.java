package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

import java.util.Scanner;

/**
 * Modélise la stratégie humaine.
 */
public class Human implements Strategy {
    /**
     * Scanner de l'entrée.
     */
    private Scanner scan;

    /**
     * Constructeur.
     */
    public Human(Scanner scan) {
        this.scan = scan;
    }

    /**
     * Retourne un coup valide à partir de l'entrée utilisateur.
     */
    public Move getMove(Board board, Player player) {
        Move move;
        boolean moveIsInvalid;
        do {
            int r1, c1, r2, c2;
            System.out.println("Entrez  les coordonnées de départ :");
            r1 = scan.nextInt();
            c1 = scan.nextInt();
            System.out.println("Entrez  les coordonnées d'arrivée : ");
            r2 = scan.nextInt();
            c2 = scan.nextInt();
            move = new Move(r1, c1, r2, c2);
            moveIsInvalid = !board.isValid(move, player);
            if (moveIsInvalid) {
                System.out.println("Coup non valide !");
            }
        } while (moveIsInvalid);
        return move;
    }
}