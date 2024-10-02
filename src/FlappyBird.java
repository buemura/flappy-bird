import javax.swing.*;
import java.awt.*;

public class FlappyBird extends JPanel {

    Image backgroudImage;
    Image birdImage;
    Image topPipeImage;
    Image bottomPipeImage;

    FlappyBird() {
        setPreferredSize(new Dimension(Board.width, Board.height));
//        setBackground(Color.blue);

        // Load images
        backgroudImage = new ImageIcon(getClass().getResource("./assets/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./assets/flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./assets/toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./assets/bottompipe.png")).getImage();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(backgroudImage, 0, 0, Board.width, Board.height, null);
    }
}
