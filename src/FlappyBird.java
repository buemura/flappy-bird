import javax.swing.*;
import java.awt.*;

public class FlappyBird extends JPanel {

    Image backgroudImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    // Bird
    int birdX = Board.width / 8;
    int birdY = Board.height / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image image) {
            this.img = image;
        }
    }

    // Game Logic
    Bird bird;

    FlappyBird() {
        setPreferredSize(new Dimension(Board.width, Board.height));
//        setBackground(Color.blue);

        // Load imagesß
        backgroudImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();

        bird = new Bird(birdImage);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(backgroudImage, 0, 0, Board.width, Board.height, null);
    }
}
