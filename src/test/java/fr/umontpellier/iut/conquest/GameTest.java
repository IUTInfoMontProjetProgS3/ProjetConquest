package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Human;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    void set_input(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Game.initInput(inputStream);
    }

    @Disabled
    @Test
    void if_player2_has_no_pawn_left_then_the_game_should_be_finished_and_player1_should_win() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|X _ X
         * 1|_ X _
         * 2|X _ X
         */

        // Set System.in to input
        set_input(input);

        // Create game and players
        Game game = new Game(3, new Human(Game.getScan()), null, null, null);
        Player player1 = game.getPlayers()[0];

        // Play in pvp hardcore mode
        game.run(1);

        // The game is finished and the winner is player1
        assertTrue(game.isFinished());
        assertEquals(player1, game.getWinner());
    }

    @Disabled
    @Test
    void if_the_board_is_filled_and_player2_has_more_pawns_then_the_game_should_be_finished_and_player2_should_win() {
        // Create predefined game
        Pawn[][] field = new Pawn[3][3];
        Board board = new Board(field);
        Game game = new Game(board, null, null, null, null);
        Player player1 = game.getPlayers()[0];
        Player player2 = game.getPlayers()[1];
        field[0][0] = new Pawn(player1);
        field[0][1] = new Pawn(player1);
        field[0][2] = new Pawn(player1);
        field[1][0] = new Pawn(player1);
        field[1][1] = new Pawn(player2);
        field[1][2] = new Pawn(player2);
        field[2][0] = new Pawn(player2);
        field[2][1] = new Pawn(player2);
        field[2][2] = new Pawn(player2);
        /*
         * __0_1_2
         * 0|X X X
         * 1|X O O
         * 2|O O O
         */

        // The game is finished and the winner is player2
        assertTrue(game.isFinished());
        assertEquals(player2, game.getWinner());
    }

    @Disabled
    @Test
    void test_undo_one_move() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "0 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Undo player1 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (0,1)
        input = input + "0 0 ";
        input = input + "0 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Valid player1 first move
        input = input + "0 ";

        // Set player2 first move to (2,0) -> (1,2)
        input = input + "2 0 ";
        input = input + "1 2 ";
        /*
         * __0_1_2
         * 0|X O O
         * 1|_ _ O
         * 2|_ _ O
         */

        // Undo player2 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Not undoing player1 first move
        input = input + "0 ";

        // Set player2 first move to (2,0) -> (1,0)
        input = input + "2 0 ";
        input = input + "1 2 ";
        /*
         * __0_1_2
         * 0|X O O
         * 1|_ _ O
         * 2|_ _ O
         */

        // Valid player2 first move
        input = input + "0 ";

        // Set player1 second move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ X X
         * 2|_ _ X
         */

        // Valid player1 second move
        input = input + "0 ";

        // Set System.in to input
        set_input(input);

        // Create game
        Game game = new Game(3, new Human(Game.getScan()), null, new Human(Game.getScan()), null);

        // Play in pvp non-hardcore mode
        game.run(0);

        // Test if the board state is correct
        Pawn[][] field = game.getBoard().getField();
        // Top left pawn should belong to player1
        assertEquals(1, field[0][0].getPlayer().getColor());
        // Top center pawn should belong to player1
        assertEquals(1, field[0][1].getPlayer().getColor());
        // Top right pawn should belong to player1
        assertEquals(1, field[0][2].getPlayer().getColor());
        // Middle left pawn should not exist
        assertNull(field[1][0]);
        // Middle center pawn should belong to player1
        assertEquals(1, field[1][1].getPlayer().getColor());
        // Middle right pawn should belong to player1
        assertEquals(1, field[1][2].getPlayer().getColor());
        // Bottom left pawn should not exist
        assertNull(field[2][0]);
        // Bottom center pawn should not exist
        assertNull(field[2][1]);
        // Bottom right pawn should belong to player1
        assertEquals(1, field[2][2].getPlayer().getColor());

    }

}