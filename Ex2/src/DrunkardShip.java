import java.util.Random;

/**
 * Drunkard ship class that implements a space ship that his pilot drunk too much
 * and lost control on the ship, all of his view spinning and from time to time
 * he will press the fire and teleport buttons.
 *
 * @author Liron Gershuny
 */
public class DrunkardShip extends SpaceShip {

    /**
     * Initial class instances.
     */
    private static final int DECISION = 90;

    /**
     * This function control of the game round flow of the drunkard ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        Random random = new Random();

        int turn = TURN_RIGHT;
        boolean accel = random.nextBoolean();
        if (roundCount % DECISION == INIT_TURN) {
            turn = TURN_LEFT;
            accel = false;
            fire(game);
        }
        shipPhysics.move(accel, turn);

        boolean choice = random.nextBoolean();
        if (choice) {
            teleport();
        }
    }
}
