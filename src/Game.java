import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;

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
    boolean gameOver = false;

    Player bird;
    ArrayList<Obstacle> pipes;
    double score = 0;

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

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.PLAIN, 32));

        if (gameOver) {
            graphics.drawString("Game Over: " + String.valueOf((int) score), 10 , 50);
        } else {
            graphics.drawString(String.valueOf((int) score), Board.width / 2, 50);
        }
    }

    public void placeObstacles() {
        int randomPipeY = (int) (pipeY - ((double) pipeHeight /4) - Math.random()*((double) pipeHeight /2));
        int openingSpace = Board.height / 4;

        Obstacle topPipe = new Obstacle(pipeX, pipeY, pipeWidth, pipeHeight, topPipeImage);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Obstacle bottomPipe = new Obstacle(pipeX, pipeY, pipeWidth, pipeHeight, bottomPipeImage);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
    }

    public void move() {
        // bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        // obstacles
        pipes.forEach((pipe) -> {
            pipe.x += velocityX;

            if (!pipe.passed && bird.x > pipe.x + pipe.width) {
                pipe.passed = true;
                score += 0.5;
            }

            if (collision(bird, pipe)) gameOver = true;
        });

        if (bird.y > Board.height) gameOver = true;
    }

    public boolean collision(Player a, Obstacle b) {
        return a.x < b.x + b.width &&   //a's top left corner doesn't reach b's top right corner
                a.x + a.width > b.x &&   //a's top right corner passes b's top left corner
                a.y < b.y + b.height &&  //a's top left corner doesn't reach b's bottom left corner
                a.y + a.height > b.y;    //a's bottom left corner passes b's top left corner
    }

    private void setInitialState() {
        bird.y = playerY;
        velocityY = 0;
        pipes.clear();
        score = 0;
        gameOver = false;
        gameLoop.start();
        placeObstaclesTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if (gameOver) {
            placeObstaclesTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
            if (gameOver) this.setInitialState();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
