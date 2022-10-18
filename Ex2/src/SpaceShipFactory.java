/**
 * This class get the chosen ships as arguments and create an array of the ships objects so the
 * SpaceWars.java that control the game flow could work with it.
 *
 * @author Liron Gershuny
 */

public class SpaceShipFactory {

    /**
     * Initial class instances.
     */
    private static final String HUMAN = "h";
    private static final String RUNNER = "r";
    private static final String BASHER = "b";
    private static final String AGGRESSIVE = "a";
    private static final String DRUNKARD = "d";
    private static final String SPECIAL = "s";

    /**
     * Creates the spaceships in the game according to the passed array of spaceships names (h,r,f,s,...).
     *
     * @param args the command line arguments of SpaceWars (e.g. spaceships={"h","r","f"}).
     * @return the array of the user chosen spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShipArray = new SpaceShip[args.length];

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(HUMAN)) {
                spaceShipArray[i] = new HumanShip();
            }
            if (args[i].equals(RUNNER)) {
                spaceShipArray[i] = new RunnerShip();
            }
            if (args[i].equals(BASHER)) {
                spaceShipArray[i] = new BasherShip();
            }
            if (args[i].equals(AGGRESSIVE)) {
                spaceShipArray[i] = new AggressiveShip();
            }
            if (args[i].equals(DRUNKARD)) {
                spaceShipArray[i] = new DrunkardShip();
            }
            if (args[i].equals(SPECIAL)) {
                spaceShipArray[i] = new SpecialShip();
            }
        }
        return spaceShipArray;
    }
}
