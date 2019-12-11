package fr.umontpellier.iut.conquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b;
    private Player player1 = new Player(null, null, null, 1);
    private Player player2 = new Player(null, null, null, 2);

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    @BeforeEach
    void create_board() {
        b = new Board(5);
        b.initField(player1, player2);
    }

    @Disabled
    @Test
    void at_the_start_top_left_pawn_should_belong_to_player1() {
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void at_the_start_bottom_right_pawn_should_belong_to_player1() {
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void at_the_start_top_right_pawn_should_belong_to_player2() {
        assertEquals(2, b.getField()[0][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void at_the_start_bottom_right_pawn_should_belong_to_player2() {
        assertEquals(2, b.getField()[b.getSize() - 1][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void at_the_start_every_pawn_must_be_free_except_for_corner_pawns() {
        Pawn[][] tab = b.getField();
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                if (!(i == 0 && (j == 0 || j == b.getSize() - 1)) && !(i == b.getSize() - 1 && (j == 0 || j == b.getSize() - 1))) {
                    assertNull(tab[i][j]);
                }
            }
        }
    }

    @Disabled
    @Test
    void a_move_that_starts_from_a_negative_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(-1, 0, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_starts_from_a_negative_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, -1, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_starts_from_too_large_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(b.getSize(), 0, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_starts_from_too_large_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, b.getSize(), 0, 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_ends_in_a_negative_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, -1, 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_ends_in_a_negative_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, 0, -1), player1));
    }

    @Disabled
    @Test
    void a_move_that_ends_in_a_too_large_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, b.getSize(), 1), player1));
    }

    @Disabled
    @Test
    void a_move_that_ends_in_a_too_large_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, 0, b.getSize()), player1));
    }

    @Disabled
    @Test
    void player1_should_not_be_able_to_move_a_pawn_that_does_not_exist() {
        assertFalse(b.isValid(new Move(0, 1, 0, 2), player1));
    }

    @Disabled
    @Test
    void player2_should_not_be_able_to_move_a_pawn_that_does_not_exist() {
        assertFalse(b.isValid(new Move(0, 1, 0, 2), player2));
    }

    @Disabled
    @Test
    void player2_should_not_be_able_to_move_a_pawn_from_player1() {
        assertFalse(b.isValid(new Move(0, 0, 0, 1), player2));
    }

    @Disabled
    @Test
    void player1_should_not_be_able_to_move_a_pawn_from_player2() {
        assertFalse(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_to_a_free_cell() {
        assertTrue(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_not_be_able_to_move_to_an_occupied_cell() {
        b.movePawn(new Move(0, 0, 0, 1));
        assertFalse(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_right() {
        assertTrue(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 2), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_down() {
        assertTrue(b.isValid(new Move(0, 0, 1, 0), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_up_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_up_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 2, 1), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_down_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 1, b.getSize() - 2), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_one_cell_down_right() {
        assertTrue(b.isValid(new Move(0, 0, 1, 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_right_should_clone_itself() {
        b.movePawn(new Move(0, 0, 0, 1));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_left_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 1, b.getSize() - 2));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_up_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 1));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_down_should_clone_itself() {
        b.movePawn(new Move(0, 0, 1, 0));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_up_right_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 2, 1));
        assertEquals(2, b.getField()[b.getSize() - 1][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_up_left_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 2));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_down_right_should_clone_itself() {
        b.movePawn(new Move(0, 0, 1, 1));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_that_moved_one_cell_down_left_should_clone_itself() {
        b.movePawn(new Move(0, b.getSize() - 1, 1, b.getSize() - 2));
        assertEquals(2, b.getField()[0][b.getSize() - 1].getPlayer().getColor());
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_right() {
        assertTrue(b.isValid(new Move(0, 0, 0, 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 1, b.getSize() - 3), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_down() {
        assertTrue(b.isValid(new Move(0, 0, 2, 0), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 3), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 3, 2), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_right() {
        assertTrue(b.isValid(new Move(0, 0, 2, 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 2, b.getSize() - 3), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_then_one_cell_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_then_one_cell_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 3, 1), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_then_one_cell_right() {
        assertTrue(b.isValid(new Move(0, 0, 2, 1), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_then_one_cell_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 2, b.getSize() - 2), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_right_then_one_cell_down() {
        assertTrue(b.isValid(new Move(0, 0, 1, 2), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_right_then_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 2, 2), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_left_then_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 3), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_be_able_to_move_two_cells_left_then_one_cell_down() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 1, b.getSize() - 3), player2));
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_should_be_free() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertNull(b.getField()[0][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 0, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 0));
        assertNull(b.getField()[0][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 1));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_left_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 3));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_right_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 3, 2));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 2, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_right_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 2));
        assertNull(b.getField()[0][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_then_one_cell_down_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 1, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_then_one_cell_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 3));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_then_one_cell_down_should_be_free() {
        b.movePawn(new Move(0, 0, 1, 2));
        assertNull(b.getField()[0][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_then_one_cell_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 2, 2));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_then_one_cell_left_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 2));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_then_one_cell_right_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 3, 1));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_then_one_cell_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 2, b.getSize() - 2));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Disabled
    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_then_one_cell_right_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 1));
        assertNull(b.getField()[0][0]);
    }

    @Disabled
    @Test
    void a_pawn_should_not_be_able_to_move_too_far_right() {
        assertFalse(b.isValid(new Move(0, 0, 0, 3), player1));
    }

    @Disabled
    @Test
    void a_pawn_should_not_be_able_to_move_too_far_left() {
        assertFalse(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 4), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_not_be_able_to_move_too_far_up() {
        assertFalse(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 4, b.getSize() - 1), player2));
    }

    @Disabled
    @Test
    void a_pawn_should_not_be_able_to_move_too_far_down() {
        assertFalse(b.isValid(new Move(0, 0, 3, 0), player1));
    }

    @Disabled
    @Test
    void after_a_move_an_adjacent_opposing_pawn_should_change_color() {
        b.movePawn(new Move(0, 0, 0, 2));
        b.movePawn(new Move(0, b.getSize() - 1, 0, b.getSize() - 2));
        assertEquals(2, b.getField()[0][2].getPlayer().getColor());
    }

    @Disabled
    @Test
    void after_a_move_a_non_adjacent_opposing_pawn_should_not_change_color() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertEquals(2, b.getField()[0][4].getPlayer().getColor());
    }

    @Disabled
    @Test
    void after_a_move_an_adjacent_free_cell_should_stay_free() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertNull(b.getField()[0][1]);
    }

    @Disabled
    @Test
    void from_starting_position_on_a_board_of_size_3_player1_should_be_able_to_move_top_left_pawn_and_bottom_right_to_every_free_cell() {
        Board board = new Board(3);
        board.initField(player1, player2);
        List<Move> validMoves = board.getValidMoves(player1);
        System.out.println(validMoves.size());
        assertTrue(validMoves.contains(new Move(0, 0, 0, 1)));
        assertTrue(validMoves.contains(new Move(0, 0, 1, 0)));
        assertTrue(validMoves.contains(new Move(0, 0, 1, 1)));
        assertTrue(validMoves.contains(new Move(0, 0, 2, 1)));
        assertTrue(validMoves.contains(new Move(0, 0, 1, 2)));
        assertTrue(validMoves.contains(new Move(2, 2, 0, 1)));
        assertTrue(validMoves.contains(new Move(2, 2, 1, 0)));
        assertTrue(validMoves.contains(new Move(2, 2, 1, 1)));
        assertTrue(validMoves.contains(new Move(2, 2, 2, 1)));
        assertTrue(validMoves.contains(new Move(2, 2, 1, 2)));
    }

    @Disabled
    @Test
    void from_starting_position_after_player1_does_one_distance_1_move_and_one_distance_2_move_and_taking_an_opponent_pawn_player1_should_have_four_pawns() {
        b.movePawn(new Move(0, 0, 0, 1));
        b.movePawn(new Move(0, 1, 0, 3));
        assertEquals(4, b.getNbPawns(player1));
    }
}