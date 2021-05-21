import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

public class DisplayMaze extends JPanel implements KeyListener {
        Maze mz;
        int xOffset = 10;
        int yOffset = 10;
        int cellSize = 25;
        int currentX, currentY, prevX, prevY;
        int moveCounter = 0;
        
        public DisplayMaze() {
                mz = new Maze();
                currentX = xOffset + cellSize / 2;
                currentY = yOffset + cellSize / 2;
                prevX = currentX;
                prevY = currentY;
                addKeyListener(this);   
        }

        public DisplayMaze(Maze mz) {
                this.mz = mz;
                currentX = xOffset + cellSize / 2;
                currentY = yOffset + cellSize / 2;
                prevX = currentX;
                prevY = currentY;
                addKeyListener(this);
        }

        public DisplayMaze(Maze mz, int cellSize) {
                this.mz = mz;
                this.cellSize = cellSize;
                currentX = xOffset + cellSize / 2;
                currentY = yOffset + cellSize / 2;
                prevX = currentX;
                prevY = currentY;
                addKeyListener(this);
        }

        public void keyPressed(KeyEvent e) {
                prevX = currentX;
                prevY = currentY;

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        currentY = currentY + cellSize;
                        if (currentY > getBounds().height) {
                                currentY = getBounds().height;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                        currentY = currentY - cellSize;
                        if (currentY < 0) {
                                currentY = 0;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        currentX = currentX - cellSize;
                        if (currentX <= 0) {
                                currentX = cellSize;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        currentX = currentX + cellSize;
                        if (currentX > getBounds().width - 225 + cellSize) {
                                currentX = getBounds().width - 225 + cellSize;
                        }
                }
                repaint();
        }

        private void doDrawing(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                Dimension size = getSize();
                g2d.setBackground(Color.DARK_GRAY);
                g2d.setColor(Color.CYAN);
                g2d.clearRect(0, 0, size.width, size.height);
                
                Path2D path = new Path2D.Double();

                int x, y;
                for(int i = 0; i < mz.width; i++) {
                        x = i * cellSize + xOffset;
                        for(int j = 0; j < mz.height; j++) {
                                y = j * cellSize + yOffset;
                                if(mz.cells[i][j].walls[0] == 1) {
                                        path.moveTo(x, y);
                                        path.lineTo(x + cellSize, y);
                                        g2d.drawLine(x, y, x + cellSize, y);
                                }

                                if(mz.cells[i][j].walls[1] == 1) {
                                        path.moveTo(x + cellSize, y);
                                        path.lineTo(x + cellSize, y + cellSize);
                                        g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                                }

                                if(mz.cells[i][j].walls[2] == 1) {
                                        path.moveTo(x, y + cellSize);
                                        path.lineTo(x + cellSize, y + cellSize);
                                        g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                                }

                                if(mz.cells[i][j].walls[3] == 1) {
                                        path.moveTo(x, y);
                                        path.lineTo(x, y + cellSize);
                                        g2d.drawLine(x, y, x, y + cellSize);
                                }
                        }
                }

                x = (prevX - xOffset - cellSize / 2) / cellSize;
                y = (prevY - yOffset - cellSize / 2) / cellSize;

                if (x >= 0 && x < mz.width && prevX > currentX && mz.cells[x][y].walls[3] == 1) {
                        currentX = prevX;
                        currentY = prevY;
                }
                
                else if (x >= 0 && x < mz.width && prevX < currentX && mz.cells[x][y].walls[1] == 1) {
                        currentX = prevX;
                        currentY = prevY;
                }
        
                else if (y >= 0 && y < mz.height && prevY > currentY && mz.cells[x][y].walls[0] == 1) {
                        currentX = prevX;
                        currentY = prevY;
                }

                else if (y >= 0 && y < mz.height && prevY < currentY && mz.cells[x][y].walls[2] == 1) {
                        currentX = prevX;
                        currentY = prevY;
                }

                if (currentX != prevX || currentY != prevY) {
                        moveCounter++;
                }

                g2d.setFont(g2d.getFont().deriveFont(15f));
                g2d.drawString("Use arrow keys to move", mz.width * cellSize + xOffset + 20, 40);
                g2d.drawString("Moves: " + moveCounter, mz.width * cellSize + xOffset + 20, 20);
                
                if (y==mz.width - 1 && x == mz.width - 1) {
                        System.out.println("Winner!");
                        g2d.drawString("Congratulations, You WON! ", mz.width * cellSize + xOffset + 20, 100);
                }

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(prevX - 2, prevY - 2, 4, 4);

                g.setColor(Color.WHITE);
                g.fillRect(currentX - 2, currentY - 2, 4, 4);
        }
       
        @Override
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
        }

        public void keyTyped(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }


}
