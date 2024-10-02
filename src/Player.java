import java.awt.*;

public class Player {
    int x;
    int y;
    int width;
    int height;
    Image img;

    Player(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = image;
    }
}
