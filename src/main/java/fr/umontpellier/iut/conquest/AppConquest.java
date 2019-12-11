package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Human;
import fr.umontpellier.iut.conquest.strategies.Strategy;

import java.util.Scanner;

public class AppConquest {
    public static void main(String[] args) {
        // Saisie à partir de la console
        Game.initInput(System.in);
        Scanner scan = Game.getScan();

        // Choix du mode de jeu : PvP, PvE, EvE
        int mode = chooseMode(scan);

        // Définit les joueurs (noms et stratégies).
        String[] names = new String[2];
        Strategy[] strategies = new Strategy[2];

        if (mode == 0) {
            pvp(names, strategies, scan);
        }

        if (mode == 1) {
            pve(names, strategies, scan);
        }

        if (mode == 2) {
            eve(names, strategies, scan);
        }

        // Choix du mode hardcore.
        int hardcore = chooseHardcore(scan);

        // Choix de la taille du plateau.
        int size = chooseBoardSize(scan);

        // Création de la partie.
        Game game = new Game(size, strategies[0], names[0], strategies[1], names[1]);

        // Lancement de la partie.
        game.run(hardcore);
    }

    /**
     * Choisit le mode de jeu : PvP, PvE ou EvE.
     */
    private static int chooseMode(Scanner scan) {
        System.out.println("PvP (0) ou PvE (1) ? ou Battle of the Artificial Intelligence (2) ?");
        int mode = scan.nextInt();
        scan.nextLine();
        return mode;
    }

    /**
     * Définit les noms et les stratégies pour le mode PvP.
     */
    private static void pvp(String[] names, Strategy[] strategies, Scanner scan) {
        strategies[0] = new Human(scan);
        strategies[1] = new Human(scan);

        System.out.println("Joueur 1, entrez votre nom :");
        names[0] = scan.nextLine();
        System.out.println("Joueur 2, entrez votre nom :");
        names[1] = scan.nextLine();
    }

    /**
     * Définit les noms et les stratégies pour le mode PvE.
     */
    private static void pve(String[] names, Strategy[] strategies, Scanner scan) {
        System.out.println("Voulez-vous jouez en premier (1) ou en deuxième (2) ?");
        int human = scan.nextInt() - 1;
        scan.nextLine();

        System.out.println("Choisissez le niveau de l'intelligence artificielle (entre 0 et 4 où 0 = Naive et 4 = Imbattable) :");
        int AILevel = scan.nextInt();
        scan.nextLine();

        strategies[human] = new Human(scan);
        strategies[1 - human] = defineStrategies(AILevel);

        System.out.println("Entrez votre nom :");
        names[human] = scan.nextLine();
        names[1 - human] = "Bonaparte";
    }

    /**
     * Définit les noms et les stratégies pour le mode EvE.
     */
    private static void eve(String[] names, Strategy[] strategies, Scanner scan) {
        System.out.println("Choisissez le niveau de la première intelligence artificielle (entre 0 et 4 où 0 = Naive et 4 = Imbattable) :");
        int firstAILevel = scan.nextInt();
        scan.nextLine();
        System.out.println("Choisissez le niveau de la deuxième intelligence artificielle (entre 0 et 4 où 0 = Naive et 4 = Imbattable) :");
        int secondAILevel = scan.nextInt();
        scan.nextLine();

        strategies[0] = defineStrategies(firstAILevel);
        strategies[1] = defineStrategies(secondAILevel);

        names[0] = "Waterloo";
        names[1] = "Bonaparte";
    }

    /**
     * Retourne la stratégie correspondant au niveau de l'IA.
     */
    private static Strategy defineStrategies(int AILevel) {
        throw new RuntimeException("Not implemented");
    }

    private static int chooseHardcore(Scanner scan) {
        System.out.println("Hardcore (1) ou non (0) ?");
        int hardcore = scan.nextInt();
        scan.nextLine();
        return hardcore;
    }

    private static int chooseBoardSize(Scanner scan) {
        System.out.println("Entrez la taille (impaire et au moins 3) du plateau :");
        int size = scan.nextInt();
        scan.nextLine();
        while (size % 2 == 0 || size < 3) {
            System.out.println("Veuillez entrer un entier impair supérieur ou égal à 3.");
            size = scan.nextInt();
            scan.nextLine();
        }
        return size;
    }

}
