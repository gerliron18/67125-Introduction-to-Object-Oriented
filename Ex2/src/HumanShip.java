import oop.ex2.GameGUI;

import java.awt.*;

/**
 * Human class controlled by the user. The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, 'd' to fire a shot, 's' to turn on the shield, 'a' to teleport.
 *
 * @author Liron Gershuny
 */
public class HumanShip extends SpaceShip {

    /**
     * This function control of the game round flow of the human control ship.
     *
     * @param game the game object to which this ship belongs.
     */
    protected void specificAction(SpaceWars game) {
        boolean accel = game.getGUI().isUpPressed();
        int turn = INIT_TURN;

        if (game.getGUI().isTeleportPressed()) {
            teleport();
        }

        if (!(game.getGUI().isRightPressed() && game.getGUI().isLeftPressed())) {
            if (game.getGUI().isRightPressed()) {
                turn = TURN_RIGHT;
            } else if (game.getGUI().isLeftPressed()) {
                turn = TURN_LEFT;
            }
            shipPhysics.move(accel, turn);
        }

        if (game.getGUI().isShieldsPressed()) {
            shieldOn();
        }

        if (game.getGUI().isShotPressed()) {
            fire(game);
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
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        } else {
            return GameGUI.SPACESHIP_IMAGE;
        }
    }
}
