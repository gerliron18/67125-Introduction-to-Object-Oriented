import oop.ex2.SpaceShipPhysics;

/**
 * This special spaceship class implements a space ship that is invincible - The pilot of this spaceship
 * hack to his spaceship computer and removed all the blocking from doing teleporting and fire so his
 * energy level has no matter. The only vulnerability is that he lost the control of his shield so
 * it unactivated during the game. The pilot will bash after the nearest ship like the basher ship
 * and will always fire at it.
 *
 * @author Liron Gershuny
 */
public class SpecialShip extends SpaceShip {

    /**
     * Initial class instances.
     */
    private static final double MIN_DISTANCE = 0.25;
    private static final double MIN_ANGLE = 0.23;

    /**
     * This function control of the game round flow of the special ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        int runnerAngle = (int) Math.signum(shipPhysics.angleTo(closestShip.shipPhysics));
        shieldStatus = false;

        if ((shipPhysics.distanceFrom(closestShip.shipPhysics) < MIN_DISTANCE) &&
                (shipPhysics.angleTo(closestShip.shipPhysics) < MIN_ANGLE)) {
            shipPhysics = new SpaceShipPhysics();
        }

        game.addShot(shipPhysics);
        shipPhysics.move(true, runnerAngle);
    }
}
