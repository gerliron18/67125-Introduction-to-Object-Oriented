/**
 * The Move class represents a move in the Nim game by a player. A move consists of the row
 * on which it is applied, the left bound (inclusive) of the sequence of sticks to mark,
 * and the right bound (inclusive) of the same sequence.
 */

public class Move {

    /**
     * The row on which the move is performed.
     */
    int SelectedRow;

    /**
     * The left bound of the sequence to mark.
     */
    int LeftBound;

    /**
     * The right bound of the sequence to mark.
     */
    int RightBound;

    /*----=  Constructors  =-----*/

    /**
     * Constructs a Move object with the given parameters
     *
     * @param inRow   The row on which the move is performed.
     * @param inLeft  The left bound of the sequence to mark.
     * @param inRight The right bound of the sequence to mark.
     */
    public Move(int inRow, int inLeft, int inRight) {
        SelectedRow = inRow;
        LeftBound = inLeft;
        RightBound = inRight;
    }

    /*----=  Instance Methods  =-----*/

    /**
     * A get function which returns the left bound of the stick sequence to mark.
     *
     * @return The left bound of the stick sequence to mark.
     */
    public int getLeftBound() {
        return LeftBound;
    }

    /**
     * A get function which returns the right bound of the stick sequence to mark.
     *
     * @return The right bound of the stick sequence to mark.
     */
    public int getRightBound() {
        return RightBound;
    }

    /**
     * A get function which returns the row on which the move is performed.
     *
     * @return The row on which the move is performed.
     */
    public int getRow() {
        return SelectedRow;
    }

    /**
     * A function that returns a string representation of single move in the Nim game.
     *
     * @return a string representation of the move. For example, if the row is 2,
     * the left bound of the sequence is 3 and the right bound is 5,
     * this function will return the string "2:3-5" (without any spaces).
     */
    public String toString() {
        return SelectedRow + ":" + LeftBound + "-" + RightBound;
    }
}