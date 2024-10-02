package entities;

import java.awt.*;

public class Obstacle extends GameObject {
    public boolean passed = false;

    public Obstacle(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
}
