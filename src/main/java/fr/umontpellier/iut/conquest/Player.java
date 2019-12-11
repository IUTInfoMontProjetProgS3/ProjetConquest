package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Strategy;

import java.util.Objects;

/**
 * Modélise un joueur de Conquest.
 */
public class Player {
    /**
     * La stratégie du joueur.
     */
    private Strategy strategy;
    /**
     * La partie en cours.
     */
    private Game game;
    /**
     * Le nom du joueur.
     */
    private String name;
    /**
     * La couleur du joueur.
     */
    private int color;

    /**
     * Constructeur.
     */
    public Player(Strategy strategy, Game game, String name, int color) {
        this.strategy = strategy;
        this.game = game;
        this.name = name;
        this.color = color;
    }

    /**
     * Getters.
     */
    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    /**
     * Utilise la stratégie du joueur pour retourner un coup valide.
     */
    public Move play() { // Flag
        System.out.println("Au tour de " + name + " :");
        return strategy.getMove(game.getBoard(), this);
    }

    /**
     * equals et hashCode.
     * Un joueur est identifié par sa couleur.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getColor() == player.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }

}
