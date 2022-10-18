import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players,
 * consisting of a given number of rounds. It also keeps track of the number of victories of each player.
 */
public class Competition {

    /**
     * The Player objects representing the first player.
     */
    private Player FirstRival;

    /**
     * The Player objects representing the second player.
     */
    private Player SecondRival;

    /**
     * A flag indicating whether game play messages should be printed to the console.
     */
    private boolean FlagMessage;

    /**
     * Constants of players int representation.
     */
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    /**
     * The score of each player at the Nim game.
     */
    private int FirstRivalScore = 0;
    private int SecondRivalScore = 0;


    /*----=  Constructors  =-----*/

    /**
     * Receives two Player objects, representing the two competing opponents,
     * and a flag determining whether messages should be displayed.
     *
     * @param player1        - The Player objects representing the first player.
     * @param player2        - The Player objects representing the second player.
     * @param displayMessage - a flag indicating whether game play messages should be printed to the console.
     */
    public Competition(Player player1, Player player2, boolean displayMessage) {
        FirstRival = player1;
        SecondRival = player2;
        FlagMessage = displayMessage;
    }


    /*----=  Instance Methods  =-----*/


    /*
     * Returns the integer representing the type of player 1; returns -1 on bad
     * input.
     */
    private static int parsePlayer1Type(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception E) {
            return -1;
        }
    }

    /*
     * Returns the integer representing the type of player 2; returns -1 on bad
     * input.
     */
    private static int parsePlayer2Type(String[] args) {
        try {
            return Integer.parseInt(args[1]);
        } catch (Exception E) {
            return -1;
        }
    }

    /*
     * Returns the integer representing the type of player 2; returns -1 on bad
     * input.
     */
    private static int parseNumberOfGames(String[] args) {
        try {
            return Integer.parseInt(args[2]);
        } catch (Exception E) {
            return -1;
        }
    }


    /**
     * Returns the number of victories of a player. playerPosition should be 1 or 2,
     * corresponding to the first or the second player in the competition.
     *
     * @param playerPosition The current player to make a move in the game.
     * @return the number of victories of a player.
     */
    public int getPlayerScore(int playerPosition) {
        if (playerPosition == PLAYER_ONE) {
            return FirstRivalScore;
        } else if (playerPosition == PLAYER_TWO) {
            return SecondRivalScore;
        }
        return -1;
    }


    /**
     * menage a number of Nim game round according to a given parameter.
     *
     * @param numberOfRounds The number of game rounds to manage.
     */
    public void playMultipleRounds(int numberOfRounds) {
        System.out.println("Starting a Nim competition of " + numberOfRounds + " rounds between a " +
                FirstRival.getTypeName() + " player and a " + SecondRival.getTypeName() + " player.");

        for (int i = numberOfRounds; i > 0; i--) {
            playSingleRound();
        }

        System.out.println("The results are " + FirstRivalScore + ":" + SecondRivalScore);
    }


    /**
     * menage a single round of Nim game.
     */
    private void playSingleRound() {
        Board gameBoard = new Board(); //Initialize a board object
        Player currentPlayer = FirstRival; //Make a flag to the current player that will do switches

        if (FlagMessage) {
            System.out.println("Welcome to the sticks game!");
        }

        while (gameBoard.getNumberOfUnmarkedSticks() > 0) {
            if (FlagMessage) {
                System.out.println("Player " + currentPlayer.getPlayerId() + ", it is now your turn!");
            }
            while (true) { //Iterate till player done with his move
                Move singleMove = currentPlayer.produceMove(gameBoard);
                if (gameBoard.markStickSequence(singleMove) < 0 && FlagMessage) {
                    //Check if the move was invalid
                    System.out.println("Invalid move. Enter another:");
                } else {
                    if (FlagMessage) {
                        System.out.println("Player " + currentPlayer.getPlayerId() + " made the move: " +
                                singleMove.toString());
                    }
                    break;
                }
            }
            if (currentPlayer.getPlayerId() == PLAYER_ONE) { //Switch players
                currentPlayer = SecondRival;
            } else {
                currentPlayer = FirstRival;
            }
        }

        if (currentPlayer.getPlayerId() == PLAYER_ONE) { //Find for the winning rival and increase his score
            FirstRivalScore += 1;
        } else {
            SecondRivalScore += 1;
        }

        if (FlagMessage) {
            System.out.println("Player " + currentPlayer.getPlayerId() + " won!");
        }
    }


    /**
     * The method runs a Nim competition between two players according to the three user-specified arguments.
     * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
     * player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
     * (2) The type of the second player, which is a positive integer between 1 and 4.
     * (3) The number of rounds to be played in the competition.
     *
     * @param args an array of string representations of the three input arguments, as detailed above.
     */
    public static void main(String[] args) {

        int p1Type = parsePlayer1Type(args);
        int p2Type = parsePlayer2Type(args);
        int numGames = parseNumberOfGames(args);

        boolean DisplayMessage = false; //Initialize a flag to know if there is a human player,
        // false as default
        if ((p1Type == Player.HUMAN) || (p2Type == Player.HUMAN)) {
            DisplayMessage = true;
        }

        Scanner scanner = new Scanner(System.in); //Initialize scanner to get players tapping
        Player player1 = new Player(p1Type, PLAYER_ONE, scanner); //Initialize player one
        Player player2 = new Player(p2Type, PLAYER_TWO, scanner); //Initialize player two

        Competition competition = new Competition(player1, player2, DisplayMessage);
        //Start a new competition with new competition object

        competition.playMultipleRounds(numGames);

        scanner.close();
    }

}
