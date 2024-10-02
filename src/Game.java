import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    Image backgroudImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    int playerX = Board.width / 8;
    int playerY = Board.height / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeX = Board.width;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // Game Logic
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    Timer gameLoop;
    Timer placeObstaclesTimer;

    Player bird;
    ArrayList<Obstacle> pipes;
    Random random = new Random();

    Game() {
        setPreferredSize(new Dimension(Board.width, Board.height));
        setFocusable(true);
        addKeyListener(this);

        // Load images
        backgroudImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/flappybirdbg.png"))).getImage();
        birdImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/flappybird.png"))).getImage();
        topPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/toppipe.png"))).getImage();
        bottomPipeImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./assets/bottompipe.png"))).getImage();

        // player
        bird = new Player(playerX, playerY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Obstacle>();

        // Place obstacles timer
        placeObstaclesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeObstacles();
            }
        });
        placeObstaclesTimer.start();

        // game timer
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(backgroudImage, 0, 0, Board.width, Board.height, null); // Draw background
        graphics.drawImage(birdImage, bird.x, bird.y, bird.width, bird.height, null); // Draw player

        pipes.forEach((pipe) -> graphics.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null));
    }

    public void placeObstacles() {
        int randomPipeY = (int) (pipeY - ((double) pipeHeight /4) - Math.random()*((double) pipeHeight /2));
        Obstacle topPipe = new Obstacle(pipeX, pipeY, pipeWidth, pipeHeight, topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);
    }

    public void move() {
        // bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        // obstacles
        pipes.forEach((pipe) -> pipe.x += velocityX);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
