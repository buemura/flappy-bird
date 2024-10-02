import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {
    Image backgroudImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    // Game Logic
    int velocityY = -6;
    Player bird;
    Timer gameLoop;

    Game() {
        setPreferredSize(new Dimension(Board.width, Board.height));

        // Load images
        backgroudImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();

        // player
        bird = new Player(Board.width / 8, Board.height / 2, 34, 24, birdImage);

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
