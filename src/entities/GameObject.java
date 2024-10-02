package entities;

import java.awt.*;

public class GameObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image img;

    public GameObject(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = image;
    }
}
