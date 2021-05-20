import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

public class Game extends JFrame{
        int width = 10;
        int height = 10;
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
                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                mb.add(file);
                setSize(width * cellSize + 225, height * cellSize + 50);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Chicas Apps: Maze Game");
                DisplayMaze dm = new DisplayMaze(mz, cellSize);
                add(dm);
                setJMenuBar(mb);
                addKeyListener(dm);
                setContentPane(dm);
                dm.setFocusable(true);
                setLocationRelativeTo(null);
        }
        
}
