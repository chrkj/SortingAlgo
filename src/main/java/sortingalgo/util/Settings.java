package sortingalgo.util;

import sortingalgo.algorithms.SortingAlgorithm;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Settings {
    // Bars
    public static final int BAR_SPACING = 3;
    public static final int MIN_BAR_HEIGHT = 30;
    public static final int MAX_BAR_HEIGHT = 500;
    public static final int INITIAL_BAR_COUNT = 15;

    // Window
    public static final int INITIAL_WINDOW_WIDTH = 1000;
    public static final int INITIAL_WINDOW_HEIGHT = 800;

    // Speed
    public static final int SPEED_MAX = 1;
    public static final int SPEED_MIN = 2000;
    public static final int INITIAL_SPEED = 1000;
    public static final int STEPPING_SPEED = 1;

    // Array panel
    public static final Color ARRAY_PANEL_COLOR = Color.LIGHT_GRAY;

    // Settings panel
    public static final int SETTINGS_PANEL_WIDTH = 500;
    public static final int SETTINGS_PANEL_HEIGHT = 100;
    public static final int SETTINGS_PANEL_TEXT_SIZE = 15;
    public static final Color SETTINGS_PANEL_COLOR = Color.GRAY;

    // Variables (TODO: Make variables atomic)
    public static Worker currentWorker;
    public static SortingAlgorithm selectedAlgorithm;

    public static AtomicInteger speed = new AtomicInteger(500);
    public static AtomicInteger arrayAccesses = new AtomicInteger(0);
    public static AtomicInteger arrayComparisons = new AtomicInteger(0);
    public static AtomicInteger barCounter = new AtomicInteger(INITIAL_BAR_COUNT);
    public static AtomicBoolean isRunning = new AtomicBoolean(false);
    public static AtomicBoolean isStepping = new AtomicBoolean(false);

}
