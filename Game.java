import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game extends JFrame{
        int width = 20;
        int height = 20;
        int cellSize = 25;
        Maze mz = new Maze(width, height);

        public Game() {
                mazeUI();
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable(){
                        public void run() {
                                Game start = new Game();
                                start.setVisible(true);
                        }
                });
        }

        private void mazeUI() {
                setSize(width * cellSize + 250, height * cellSize + 100);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Chicas Apps: Maze Game");
                DisplayMaze dm = new DisplayMaze(mz, cellSize);
                add(dm);
                addKeyListener(dm);
                setContentPane(dm);
                dm.setFocusable(true);
                setLocationRelativeTo(null);
        }
        
}
