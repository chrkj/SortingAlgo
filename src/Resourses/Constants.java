package Resourses;

public class Constants {
    // Should be renamed if we are not only housing constants
    // Can be refactored into "settings" and "constants" or something
    public static final int INITIAL_HEIGHT = 800;
    public static final int INITIAL_WIDTH = 1000;
    public static int spacing = 4;
    public static int rectAmount = 5;
    public static int borderMargin = 50;
    public static int currentHeight = INITIAL_HEIGHT;
    public static int currentWidth = INITIAL_WIDTH;
    public static int graphLength = Constants.currentWidth - 2 * Constants.borderMargin - (Constants.rectAmount - 1) * Constants.spacing;
    public static int barLength = graphLength / Constants.rectAmount;
    public static int minBarHeight = 20;
    public static int maxBarHeight = 500;

    public static void update() {
        graphLength = Constants.currentWidth - 2 * Constants.borderMargin - (Constants.rectAmount - 1) * Constants.spacing;
        barLength = graphLength / Constants.rectAmount;
    }
}
