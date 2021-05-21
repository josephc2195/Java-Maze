import javax.swing.*;
import java.awt.*;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.JMenuItem;
// import javax.swing.JPanel;
// import javax.swing.JTextField;
// import javax.swing.SwingUtilities;

public class Game extends JFrame{
        int width = 10;
        int height = 10;
        int cellSize = 25;
        Maze mz = new Maze(width, height);
        String input;

        public Game() {
                inputUI();
                //mazeUI();

        }

        public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable(){
                        public void run() {
                                Game start = new Game();
                                start.setVisible(true);
                        }
                });
        }

        private void inputUI() {
                JPanel text = new JPanel();
                JTextField userSize = new JTextField(10);
                JButton submit = new JButton("Submit");
                JLabel ask = new JLabel("Please enter the size you want the maze to be.");
                setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
                text.add(userSize);
                add(ask);
                add(text);
                add(submit);
                setSize(400, 150);
                setTitle("Please enter a Size");
                input = userSize.getText();     
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                setLocationRelativeTo(null);          
        }

        private void mazeUI() {
                JMenuBar mb = new JMenuBar();
                JMenu file = new JMenu("File");
                JMenuItem nw = new JMenuItem("New");
                file.add(nw);
                mb.add(file);
                setSize(width * cellSize + 225, height * cellSize + 100);
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
