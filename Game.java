import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame{
        int width = 10;
        int height = 10;
        int cellSize = 25;
        Maze mz = new Maze(width, height);
        String input;
        

        public Game() {
                inputUI();
                System.out.println("input: " + input);  
                // if(width == 0 && height == 0){
                //         width = 15;
                //         height = 15;
                //         mazeUI();
                // }
                // else {
                //         mazeUI();
                // }

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
                
                userSize.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                input = userSize.getText();
                        }

                });

                text.add(userSize);
                
                add(ask);
                add(text);
                add(submit);
                
                setSize(400, 150);
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
                DisplayMaze dm = new DisplayMaze(mz, cellSize);
                add(dm);
                setJMenuBar(mb);
                addKeyListener(dm);
                setContentPane(dm);
                dm.setFocusable(true);
                setLocationRelativeTo(null);
        }
        
        
}
