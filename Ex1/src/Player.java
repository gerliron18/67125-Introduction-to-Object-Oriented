import java.util.Random;
import java.util.Scanner;

/**
 * The Player class represents a player in the Nim game, producing Moves as a response to a Board state.
 * Each player is initialized with a type, either human or one of several computer strategies,
 * which defines the move he produces when given a board in some state. The heuristic strategy of the
 * player is already implemented. You are required to implement the rest of the player types according
 * to the exercise description.
 *
 * @author OOP course staff and gerliron18
 */
public class Player {

    //Constants that represent the different players.
    /**
     * The constant integer representing the Random player type.
     */
    public static final int RANDOM = 1;
    /**
     * The constant integer representing the Heuristic player type.
     */
    public static final int HEURISTIC = 2;
    /**
     * The constant integer representing the Smart player type.
     */
    public static final int SMART = 3;
    /**
     * The constant integer representing the Human player type.
     */
    public static final int HUMAN = 4;

    private static final int BINARY_LENGTH = 4;  //Used by produceHeuristicMove() for binary
    // representation of board rows.
    /**
     * Type of the rivals at the Nim game - Random, Heuristic, Smart or Human.
     */
    private final int playerType;
    /**
     * Id of the players, can be 1 or 2.
     */
    private final int playerId;
    /**
     * receive inputs from user via Scanner object.
     */
    private Scanner scanner;


    /*----=  Constructors  =-----*/


    /**
     * Initializes a new player of the given type and the given id, and an initialized scanner.
     *
     * @param type         The type of the player to create.
     * @param id           The id of the player (either 1 or 2).
     * @param inputScanner The Scanner object through which to get user input
     *                     for the Human player type.
     */
    public Player(int type, int id, Scanner inputScanner) {
        // Check for legal player type (we will see better ways to do this in the future).
        if (type != RANDOM && type != HEURISTIC
                && type != SMART && type != HUMAN) {
            System.out.println("Received an unknown player type as a parameter"
                    + " in Player constructor. Terminating.");
            System.exit(-1);
        }
        playerType = type;
        playerId = id;
        scanner = inputScanner;
    }

    /**
     * @return an integer matching the player type.
     */
    public int getPlayerType() {
        return playerType;
    }

    /**
     * @return the players id number.
     */
    public int getPlayerId() {
        return playerId;
    }


    /**
     * @return a String matching the player type.
     */
    public String getTypeName() {
        switch (playerType) {

            case RANDOM:
                return "Random";

            case SMART:
                return "Smart";

            case HEURISTIC:
                return "Heuristic";

            case HUMAN:
                return "Human";
        }
        //Because we checked for legal player types in the
        //constructor, this line shouldn't be reachable.
        return "UnknownPlayerType";
    }


    /**
     * This method encapsulates all the reasoning of the player about the game. The player is given the
     * board object, and is required to return his next move on the board. The choice of the move depends
     * on the type of the player: a human player chooses his move manually; the random player should
     * return some random move; the Smart player can represent any reasonable strategy; the Heuristic
     * player uses a strong heuristic to choose a move.
     *
     * @param board - a Board object representing the current state of the game.
     * @return a Move object representing the move that the current player will
     * play according to his strategy.
     */
    public Move produceMove(Board board) {

        switch (playerType) {

            case RANDOM:
                return produceRandomMove(board);

            case SMART:
                return produceSmartMove(board);

            case HEURISTIC:
                return produceHeuristicMove(board);

            case HUMAN:
                return produceHumanMove(board);

            //Because we checked for legal player types in the
            //constructor, this line shouldn't be reachable.
            default:
                return null;
        }
    }


    /**
     * Make sure that a given move is a valid one according to the current state of the game board.
     *
     * @param move  - a Move object representing single move.
     * @param board - a Board object representing the current state of the game.
     * @return true if the given move is a valid one, false otherwise.
     */
    private boolean validateMove(Move move, Board board) {
        //use Move object getters to get the int representation of the given move parameter
        int moveRow = move.getRow();
        int moveLeftBound = move.getLeftBound();
        int moveRightBound = move.getRightBound();

        for (int i = moveLeftBound; i < moveRightBound + 1; i++) { //Iterate over the chosen sticks
            if (!board.isStickUnmarked(moveRow, i)) { //Check if the stick is marked
                return false;
            }
        }
        return true;
    }


    /**
     * Find the maximum moves possible according to a given state of the game board.
     *
     * @param board - a Board object representing the current state of the game board.
     * @return integer representing the count of all possible moves on the current state of the board.
     */
    private int allMovesCalc(Board board) {
        int maxMoves = 0; //Initialize counter

        for (int i = 1; i < board.getNumberOfRows() + 1; i++) { //Iterate over the rows
            int rowLength = board.getRowLength(i);
            maxMoves += ((rowLength + 1) * (rowLength / 2.0)); //Add the current row sum of possible moves to
            // the counter using the formula to sum of arithmetic progression
        }
        return maxMoves;
    }


    /**
     * Find all possible moves according to the current state of the game board, arrange them in an array,
     * will turn invalid moves to null.
     *
     * @param board - a Board object representing the current state of the game.
     * @return - an array of valid moves at the current state of the game board.
     */
    private Move[] filterMoves(Board board) {
        Move[] legalMoveArray = new Move[allMovesCalc(board)]; //Initialize an array with the length of all
        // possible moves using above method

        int currentIndex = 0; //A flag to array index
        for (int row = 1; row < board.getNumberOfRows() + 1; row++) { //Iterate over the game rows
            for (int LeftBound = 1; LeftBound < board.getRowLength(row) + 1; LeftBound++) {
                //Iterate over the sticks from left to right
                for (int RightBound = LeftBound; RightBound < board.getRowLength(row) + 1; RightBound++) {
                    //Iterate over the sticks from right to left
                    Move newMove = new Move(row, LeftBound, RightBound);
                    //Initialize a move object with the founded move
                    if (validateMove(newMove, board)) { //Check if the move is valid using above method
                        legalMoveArray[currentIndex] = newMove;
                    }
                    currentIndex++;
                }
            }
        }
        return legalMoveArray;
    }


    /**
     * Produces a random move.
     *
     * @param board - a Board object representing the current state of the game board.
     * @return returns a Move object representing the selected random move.
     */
    private Move produceRandomMove(Board board) {
        Random randomObj = new Random(); //Initialize a random object

        Move[] legalMoveArray = filterMoves(board); //Get an array of valid moves using above method

        int randomInt = randomObj.nextInt(legalMoveArray.length);
        Move flagMove = legalMoveArray[randomInt]; //Save the selected move as a move object

        //Switch moves between index zero and random selected index so will be no need to iterate few times
        //on the array and it will be arrange so that all nulls will be at the end of it.
        legalMoveArray[randomInt] = legalMoveArray[0];
        legalMoveArray[0] = flagMove;

        int randomMove = 0;
        while (legalMoveArray[randomMove] == null) {
            randomMove++;
        }
        return legalMoveArray[randomMove];
    }


    /**
     * Produce some intelligent strategy to produce a move.
     *
     * @param board - a Board object representing the current state of the game.
     * @return returns a Move object representing an intelligent move.
     */
    private Move produceSmartMove(Board board) {
        Move chosenMove = null; //Initialize a move object

        Move[] legalMoveArray = filterMoves(board); //Get an array of possible moves using above method

        boolean oddStickCheck = (board.getNumberOfUnmarkedSticks() % 2 == 1);
        //Initialize a boolean that will say if the remaining sticks at the board are count to an odd number

        for (int moveCheck = 0; moveCheck < legalMoveArray.length; moveCheck++) {
            //Iterate over the valid moves array
            if (legalMoveArray[moveCheck] != null) {
                chosenMove = legalMoveArray[moveCheck];

                boolean oddMoveCheck = (chosenMove.getRightBound() - chosenMove.getLeftBound()) % 2 == 0;
                //Check if the chosen move sticks are count to an even number so the remaining sticks
                // after preforming this move will count to an odd number

                if ((oddStickCheck && !oddMoveCheck) || (!oddStickCheck && oddMoveCheck)) {
                    //Check if preforming the chosen move will create a winning situation or not, if yes,
                    //this move will be chosen as a good move to do
                    return chosenMove;
                }
            }
        }
        return chosenMove;
    }


    /**
     * Make a move of a human type player. assuming all pressed keys of the move are valid
     * like it says at the exercise guidelines.
     *
     * @param board - a Board object representing the current state of the game.
     * @return returns a Move object representing the move that the human player
     * want to make in this state of the game board.
     */
    private Move produceHumanMove(Board board) {
        //Initialize int parameters representing the move
        int selectedRow;
        int leftBound;
        int rightBound;

        while (true) { //Iterate till all needed parameters given by the player
            System.out.println("Press 1 to display the board. Press 2 to make a move:");
            int pressedKey = scanner.nextInt();

            if (pressedKey == 1) {
                System.out.println(board.toString());

            } else if (pressedKey == 2) {
                System.out.println("Enter the row number:");
                selectedRow = scanner.nextInt();

                System.out.println("Enter the index of the leftmost stick:");
                leftBound = scanner.nextInt();

                System.out.println("Enter the index of the rightmost stick:");
                rightBound = scanner.nextInt();

                break;

            } else { //Just if the first key pressed was niter 1 or 2
                System.out.println("Unsupported command");
            }
        }
        return new Move(selectedRow, leftBound, rightBound);
    }


    /*
     * Uses a winning heuristic for the Nim game to produce a move.
     */
    private Move produceHeuristicMove(Board board) {

        int numRows = board.getNumberOfRows();
        int[][] bins = new int[numRows][BINARY_LENGTH];
        int[] binarySum = new int[BINARY_LENGTH];
        int bitIndex, higherThenOne = 0, totalOnes = 0, lastRow = 0, lastLeft = 0, lastSize = 0,
                lastOneRow = 0, lastOneLeft = 0;

        for (bitIndex = 0; bitIndex < BINARY_LENGTH; bitIndex++) {
            binarySum[bitIndex] = 0;
        }

        for (int k = 0; k < numRows; k++) {

            int curRowLength = board.getRowLength(k + 1);
            int i = 0;
            int numOnes = 0;

            for (bitIndex = 0; bitIndex < BINARY_LENGTH; bitIndex++) {
                bins[k][bitIndex] = 0;
            }

            do {
                if (i < curRowLength && board.isStickUnmarked(k + 1, i + 1)) {
                    numOnes++;
                } else {

                    if (numOnes > 0) {

                        String curNum = Integer.toBinaryString(numOnes);
                        while (curNum.length() < BINARY_LENGTH) {
                            curNum = "0" + curNum;
                        }
                        for (bitIndex = 0; bitIndex < BINARY_LENGTH; bitIndex++) {
                            bins[k][bitIndex] += curNum.charAt(bitIndex) - '0'; //Convert from char to int
                        }

                        if (numOnes > 1) {
                            higherThenOne++;
                            lastRow = k + 1;
                            lastLeft = i - numOnes + 1;
                            lastSize = numOnes;
                        } else {
                            totalOnes++;
                        }
                        lastOneRow = k + 1;
                        lastOneLeft = i;

                        numOnes = 0;
                    }
                }
                i++;
            } while (i <= curRowLength);

            for (bitIndex = 0; bitIndex < BINARY_LENGTH; bitIndex++) {
                binarySum[bitIndex] = (binarySum[bitIndex] + bins[k][bitIndex]) % 2;
            }
        }


        //We only have single sticks
        if (higherThenOne == 0) {
            return new Move(lastOneRow, lastOneLeft, lastOneLeft);
        }

        //We are at a finishing state
        if (higherThenOne <= 1) {

            if (totalOnes == 0) {
                return new Move(lastRow, lastLeft, lastLeft + (lastSize - 1) - 1);
            } else {
                return new Move(lastRow, lastLeft, lastLeft + (lastSize - 1) - (1 - totalOnes % 2));
            }

        }

        for (bitIndex = 0; bitIndex < BINARY_LENGTH - 1; bitIndex++) {

            if (binarySum[bitIndex] > 0) {

                int finalSum = 0, eraseRow = 0, eraseSize = 0, numRemove = 0;
                for (int k = 0; k < numRows; k++) {

                    if (bins[k][bitIndex] > 0) {
                        eraseRow = k + 1;
                        eraseSize = (int) Math.pow(2, BINARY_LENGTH - bitIndex - 1);

                        for (int b2 = bitIndex + 1; b2 < BINARY_LENGTH; b2++) {

                            if (binarySum[b2] > 0) {

                                if (bins[k][b2] == 0) {
                                    finalSum = finalSum + (int) Math.pow(2, BINARY_LENGTH - b2 - 1);
                                } else {
                                    finalSum = finalSum - (int) Math.pow(2, BINARY_LENGTH - b2 - 1);
                                }

                            }

                        }
                        break;
                    }
                }

                numRemove = eraseSize - finalSum;

                //Now we find that part and remove from it the required piece
                int numOnes = 0, i = 0;
                while (numOnes < eraseSize) {

                    if (board.isStickUnmarked(eraseRow, i + 1)) {
                        numOnes++;
                    } else {
                        numOnes = 0;
                    }
                    i++;

                }
                return new Move(eraseRow, i - numOnes + 1, i - numOnes + numRemove);
            }
        }

        //If we reached here, and the board is not symmetric, then we only need to erase a single stick
        if (binarySum[BINARY_LENGTH - 1] > 0) {
            return new Move(lastOneRow, lastOneLeft, lastOneLeft);
        }

        //If we reached here, it means that the board is already symmetric, and then we simply mark
        // one stick from the last sequence we saw:
        return new Move(lastRow, lastLeft, lastLeft);
    }


}
