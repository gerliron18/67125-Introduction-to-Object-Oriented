/**
 * This aggressive spaceship class implements a space ship that pursues other ships and tries to fire at them.
 * It will always accelerate, and turn towards the nearest ship. If its angle from
 * the nearest ship is 0.21 radians or less (in any direction) then it will open fire.
 *
 * @author Liron Gershuny
 */
public class AggressiveShip extends SpaceShip {

    /**
     * Initial class instances.
     */
    private static final double FIRE_DISTANCE = 0.21;

    /**
     * This function control of the game round flow of the aggressive ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        int runnerAngle = (int) Math.signum(shipPhysics.angleTo(closestShip.shipPhysics));

        if (runnerAngle <= FIRE_DISTANCE) {
            fire(game);
        }

        shipPhysics.move(true, runnerAngle);
    }
}
