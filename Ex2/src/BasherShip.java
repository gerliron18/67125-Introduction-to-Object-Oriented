/**
 * This basher spaceship class implements a space ship that attempts to collide with other ships.
 * It will always accelerate, and turn towards the closest ship. If it gets within a distance
 * of 0.19 units from another ship, it will turn on its shield.
 *
 * @author Liron Gershuny
 */
public class BasherShip extends SpaceShip {

    /**
     * Initial class instances.
     */
    private static final double SHIELD_DISTANCE = 0.19;

    /**
     * This function control of the game round flow of the basher ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        int runnerAngle = (int) Math.signum(shipPhysics.angleTo(closestShip.shipPhysics));

        if (shipPhysics.distanceFrom(closestShip.shipPhysics) <= SHIELD_DISTANCE) {
            shieldOn();
        }

        shipPhysics.move(true, runnerAngle);
    }
}
