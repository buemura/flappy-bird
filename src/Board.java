import javax.swing.*;

public class Board extends JFrame {
    static int width = 360;
    static int height = 640;

    Board(String boardName) {
        super(boardName);
        super.setSize(Board.width, Board.height);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(JPanel panel) {
        super.add(panel);
        super.pack();
        super.setVisible(true);
    }
}
