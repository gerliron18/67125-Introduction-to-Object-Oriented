/**
 * This runner spaceship class implements a space ship that attempts to run away from the fight.
 * It will constantly accelerate and turn away from the ship that is closest.
 * The runner has radar - It will try to get the nearest ship cannon angle from himself.
 * if that angle is smaller than 0.23 radians (in any direction) and the distance from the
 * nearest ship is smaller than 0.25 units, the runner will try to teleport.
 *
 * @author Liron Gershuny
 */
public class RunnerShip extends SpaceShip {

    /**
     * Initial class instances.
     */
    private static final double MIN_DISTANCE = 0.25;
    private static final double MIN_ANGLE = 0.23;

    /**
     * This function control of the game round flow of the runner ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        int runnerAngle = (int) Math.signum(shipPhysics.angleTo(closestShip.shipPhysics));

        if ((shipPhysics.distanceFrom(closestShip.shipPhysics) < MIN_DISTANCE) &&
                (shipPhysics.angleTo(closestShip.shipPhysics) < MIN_ANGLE)) {
            teleport();
        }

        shipPhysics.move(true, -runnerAngle);
    }
}