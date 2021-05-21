import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame{
        int width = 10;
        int height = 10;
        int cellSize = 25;
        String input;
        Boolean window = false;
        
        public Game() {
                inputUI();
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
                JLabel ask = new JLabel("Enter the size you want the maze to be.");
                
                setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
                
                userSize.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                while(input == null){
                                        input = userSize.getText();
                                }
                                String[] selection = input.split(" |\\,");
                                width = Integer.parseInt(selection[0]);
                                height = Integer.parseInt(selection[1]);
                                mazeUI();
                                
                        }
                });
                text.add(userSize);
                add(ask);
                add(text);
                
                setSize(300, 100);
                setTitle("Please enter a Size");

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
                Maze mz = new Maze(width, height);
                DisplayMaze dm = new DisplayMaze(mz, cellSize);
                add(dm);
                setJMenuBar(mb);
                dm.setFocusable(true);
                addKeyListener(dm);
                setContentPane(dm);
                setLocationRelativeTo(null);
        }
}
