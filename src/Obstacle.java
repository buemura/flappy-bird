import java.awt.*;

public class Obstacle extends GameObject {
    boolean passed = false;

    Obstacle(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
}
