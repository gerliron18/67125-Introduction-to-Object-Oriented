import java.awt.Image;

import oop.ex2.*;

/**
 * This abstract class is a container for all spaceships. this class contains the API spaceships
 * need to implement for the SpaceWars game and implements some methods used repeatedly in spaceships.
 *
 * @author oop and Liron Gershuny
 */
public abstract class SpaceShip {

    /**
     * Initial class instances.
     */
    private static final int INIT_MAX_ENERGY = 210;
    private static final int MIN_ENERGY = 0;
    private static final int CURRENT_ENERGY = 190;
    private static final int MAX_HEALTH = 22;
    private static final int MIN_HEALTH = 0;
    private static final int INIT_ROUND_COUNT = 0;
    private static final int INIT_FIRE_COUNT = 0;
    private static final int HIT_NO_SHIELD_HEALTH = -1;
    private static final int HIT_NO_SHIELD_ENERGY = -10;
    private static final int COLLIDE_WITH_SHIELD = 18;
    private static final int CHARGE_ENERGY = 1;
    private static final int SHOT_WAITING_TIME = 7;
    private static final int HIT_NO_SHIELD = 0;
    private static final int SHOT_ENERGY_COST = -19;
    private static final int SHIELD_ENERGY_COST = -3;
    private static final int TELEPORT_ENERGY_COST = -140;
    public static final int INIT_TURN = 0;
    public static final int TURN_RIGHT = -1;
    public static final int TURN_LEFT = 1;


    /**
     * An object represents the position, direction and velocity of the ship.
     */
    public SpaceShipPhysics shipPhysics = new SpaceShipPhysics();

    /**
     * A maximal energy level.
     */
    int maxEnergyLevel;

    /**
     * A current energy level, which is between 0 and the maximal energy level.
     */
    int currentEnergyLevel;

    /**
     * A Health level between 0 and 22.
     */
    int healthLevel;

    /**
     * A counter of the game rounds.
     */
    int roundCount;

    /**
     * A counter of the last turn the ship fired.
     */
    int fireCount;

    /**
     * A flag for the activation of the ship shield.
     */
    boolean shieldStatus = false;


    /*----=  Constructors  =-----*/

    SpaceShip() {
        reset();
    }


    /*----=  Instance Methods  =-----*/


    /**
     * Manage the general actions need to take place at one round of the game.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shieldStatus = false;

        roundCount++;

        manageCurrentEnergy(CHARGE_ENERGY);

        specificAction(game);
    }


    /**
     * An abstract method that each of the ships classes will do their specific actions
     * of one round at the game.
     *
     * @param game the game object to which this ship belongs.
     */
    protected abstract void specificAction(SpaceWars game);


    /**
     * Menage changes at the health level of the spaceship.
     *
     * @param value - the value that we need to check changes at the health level with
     */
    private void manageHealth(int value) {
        if ((healthLevel + value) <= MIN_HEALTH) {
            healthLevel = MIN_HEALTH;
        } else if ((healthLevel + value) >= MAX_HEALTH) {
            healthLevel = MAX_HEALTH;
        } else {
            healthLevel = healthLevel + value;
        }
    }

    /**
     * Menage changes at the energy level of the spaceship.
     *
     * @param value - the value that we need to check changes at the energy level with
     */
    private void manageCurrentEnergy(int value) {
        if ((currentEnergyLevel + value) <= MIN_ENERGY) {
            currentEnergyLevel = MIN_ENERGY;
        } else if ((currentEnergyLevel + value) >= maxEnergyLevel) {
            currentEnergyLevel = maxEnergyLevel;
        } else {
            currentEnergyLevel = currentEnergyLevel + value;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs
     */
    public void collidedWithAnotherShip() {
        if (!shieldStatus) {
            manageHealth(HIT_NO_SHIELD_HEALTH);
            maxEnergyLevel -= HIT_NO_SHIELD_ENERGY;
            manageCurrentEnergy(HIT_NO_SHIELD);
        } else {
            maxEnergyLevel += COLLIDE_WITH_SHIELD;
            manageCurrentEnergy(COLLIDE_WITH_SHIELD);
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset() {
        maxEnergyLevel = INIT_MAX_ENERGY;
        currentEnergyLevel = CURRENT_ENERGY;
        healthLevel = MAX_HEALTH;
        roundCount = INIT_ROUND_COUNT;
        fireCount = INIT_FIRE_COUNT;
        shipPhysics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return healthLevel == MIN_HEALTH;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return shipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldStatus) {
            manageHealth(HIT_NO_SHIELD_HEALTH);
            maxEnergyLevel -= HIT_NO_SHIELD_ENERGY;
            manageCurrentEnergy(HIT_NO_SHIELD);
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (shieldStatus) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (((roundCount - fireCount) > SHOT_WAITING_TIME) && (currentEnergyLevel >= -SHOT_ENERGY_COST)) {
            game.addShot(shipPhysics);
            manageCurrentEnergy(SHOT_ENERGY_COST);
            fireCount = roundCount;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergyLevel >= -SHIELD_ENERGY_COST) {
            manageCurrentEnergy(SHOT_ENERGY_COST);
            shieldStatus = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergyLevel >= -TELEPORT_ENERGY_COST) {
            manageCurrentEnergy(TELEPORT_ENERGY_COST);
            shipPhysics = new SpaceShipPhysics();
        }
    }


}