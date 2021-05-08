package Resourses;

import Algorithms.SortingAlgorithm;

import java.awt.*;

public class Settings {
    // Bars
    public static final int BAR_SPACING = 3;
    public static final int MIN_BAR_HEIGHT = 30;
    public static final int MAX_BAR_HEIGHT = 500;
    public static final int INITIAL_RECT_COUNT = 15;

    // Window
    public static final int INITIAL_WINDOW_HEIGHT = 800;
    public static final int INITIAL_WINDOW_WIDTH = 1000;

    // Speed
    public static final int SPEED_MAX = 1;
    public static final int SPEED_MIN = 2000;
    public static final int SPEED_INIT = 1000;

    // Array panel
    public static final Color ARRAY_PANEL_COLOR = Color.LIGHT_GRAY;

    // Settings panel
    public static final int SETTINGS_PANEL_WIDTH = 500;
    public static final int SETTINGS_PANEL_HEIGHT = 100;

    // Variables
    public static int speed = 500;
    public static int arrayAccesses = 0;
    public static int arrayComparisons = 0;
    public static int barCounter = INITIAL_RECT_COUNT;
    public static SortingAlgorithm selectedAlgorithm;

}
