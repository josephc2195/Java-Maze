import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game extends JFrame{
        int width = 15;
        int height = 15;
        int cellSize = 20;
        Maze mz = new Maze(width, height);

        public Game() {
                initUI();
        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable(){
                        public void run() {
                                Game run = new Game();
                                run.setVisible(true);
                        }
                });
        }

        private void initUI() {
                setTitle("New Game");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(width * cellSize + 200, height * cellSize + 75);

                DisplayMaze dm = new DisplayMaze(mz, cellSize);
                add(dm);
                addKeyListener(dm);
                setContentPane(dm);
                dm.setFocusable(true);
                setLocationRelativeTo(null);
        }
        
}
