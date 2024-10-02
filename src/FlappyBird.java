import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlappyBird extends JPanel implements ActionListener {

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
    int velocityY = -6;

    Timer gameLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(Board.width, Board.height));

        // Load imagesß
        backgroudImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();

        // bird
        bird = new Bird(birdImage);

        // game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        System.out.println("draw");

        graphics.drawImage(backgroudImage, 0, 0, Board.width, Board.height, null); // Draw background
        graphics.drawImage(birdImage, bird.x, bird.y, bird.width, bird.height, null); // Draw player
    }

    public void move() {
        // bird
        bird.y += velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }
}
