package GUI;

import Resourses.Constants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class ResizeListener extends ComponentAdapter {
    public void componentResized(ComponentEvent e) {
        System.out.println("resize occurred");
        Constants.currentWidth = e.getComponent().getWidth();
        Constants.graphLength = Constants.currentWidth - 2 * Constants.borderMargin - (Constants.rectAmount - 1) * Constants.spacing;
        Constants.barLength = Constants.graphLength / Constants.rectAmount;
    }
}