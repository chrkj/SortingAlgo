package Algorithms;

import Controls.ControlFrame;

public abstract class SortingAlgorithm {

    protected ControlFrame controlFrame;

    public SortingAlgorithm(ControlFrame controlFrame) {
        this.controlFrame = controlFrame;
    }
    public abstract void sort(); // Temp
    public abstract void step(); // Temp
}
