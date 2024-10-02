import java.awt.*;

public class Obstacle {
    int x;
    int y;
    int width;
    int height;
    Image img;
    boolean passed = false;

    Obstacle(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = image;
    }
}
