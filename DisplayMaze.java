import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

public class DisplayMaze extends JPanel implements Printable, ActionListener, KeyListener {
        Maze mz;
        int xOffset = 10;
        int yOffset = 10;
        int cellSize = 20;

        Integer moveCounter = 0;

        int pointX, pointY, oldX, oldY;
        boolean erase;

        public DisplayMaze() {
                mz = new Maze();
                pointX = xOffset + cellSize / 2;
                pointY = yOffset + cellSize / 2;
                oldX = pointX;
                oldY = pointY;
                addKeyListener(this);               
        }

        public DisplayMaze(Maze mz2) {
                mz = mz2;
                pointX = xOffset + cellSize / 2;
                pointY = yOffset + cellSize / 2;
                oldX = pointX;
                oldY = pointY;
                addKeyListener(this);
        }

        DisplayMaze(Maze mz2, int cellSize2) {
                mz = mz2;
                cellSize = cellSize2;
                pointX = xOffset + cellSize / 2;
                pointY = yOffset + cellSize / 2;
                oldX = pointX;
                oldY = pointY;
                addKeyListener(this);
        }

        public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub                
        }

        public void keyPressed(KeyEvent e) {
                oldX = pointX;
                oldY = pointY;

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        pointY = pointY + cellSize;
                        if (pointY > getBounds().height) {
                                pointY = getBounds().height;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                        pointY = pointY - cellSize;
                        if (pointY < 0) {
                                pointY = 0;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        pointX = pointX - cellSize;
                        if (pointX < 0) {
                                pointX = 0;
                        }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        pointX = pointX + cellSize;
                        if (pointX > getBounds().width) {
                                pointX = getBounds().width;
                        }
                }

                repaint();
                
        }

        public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                
        }

        public void actionPerformed(ActionEvent e) {
                myPrint();                
        }

        public void myPrint() {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(this);
                job.setJobName("Maze");
                boolean log = job.printDialog();
                if(log) {
                        try {
                                job.print();
                        } catch (PrinterException ex) {
                                System.out.println("Failure");
                        }
                }
        }

        public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if(pageIndex > 0) {
                        return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

                doDrawing(g2d);

                return PAGE_EXISTS;
        }

        private void doDrawing(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.CYAN);

                Dimension size = getSize();
                Insets insets = getInsets();

                int w = size.width - insets.left - insets.right;
                int h = size.height - insets.top - insets.bottom;

                g2d.setBackground(Color.DARK_GRAY);
                g2d.clearRect(0, 0, w, h);

        
                Path2D mazeShape = new Path2D.Double();

                int x, y;
                for(Integer i = 0; i < mz.width; i++) {
                        x = i * cellSize + xOffset;
                        for(Integer j = 0; j < mz.height; j++) {
                                y = j * cellSize + yOffset;

                                if(mz.cells[i][j].walls[0] == 1) {
                                        mazeShape.moveTo(x, y);
                                        mazeShape.lineTo(x + cellSize, y);
                                        g2d.drawLine(x, y, x + cellSize, y);
                                }

                                if(mz.cells[i][j].walls[1] == 1) {
                                        mazeShape.moveTo(x + cellSize, y);
                                        mazeShape.lineTo(x + cellSize, y + cellSize);
                                        g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                                }

                                if(mz.cells[i][j].walls[2] == 1) {
                                        mazeShape.moveTo(x, y + cellSize);
                                        mazeShape.lineTo(x + cellSize, y + cellSize);
                                        g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                                }

                                if(mz.cells[i][j].walls[3] == 1) {
                                        mazeShape.moveTo(x, y);
                                        mazeShape.lineTo(x, y + cellSize);
                                        g2d.drawLine(x, y, x, y + cellSize);
                                }
                        }
                }

                x = (oldX - xOffset - cellSize / 2) / cellSize;
                y = (oldY - yOffset - cellSize / 2) / cellSize;

                if (x >= 0 && x < mz.width && oldX > pointX && mz.cells[x][y].walls[3] == 1) {
                        pointX = oldX;
                        pointY = oldY;
                }
                else if (x >= 0 && x < mz.width && oldX < pointX && mz.cells[x][y].walls[1] == 1) {
                        pointX = oldX;
                        pointY = oldY;
                }

                else if (y >= 0 && y < mz.height && oldY > pointY && mz.cells[x][y].walls[0] == 1) {
                        pointX = oldX;
                        pointY = oldY;
                }

                else if (y >= 0 && y < mz.height && oldY < pointY && mz.cells[x][y].walls[2] == 1) {
                        pointX = oldX;
                        pointY = oldY;
                }

                if (pointX != oldX || pointY != oldY) {
                        moveCounter++;
                }

                g2d.drawString("Use arrow keys to move", mz.width * cellSize + xOffset + 20, 40);
                g2d.drawString("Moves: " + moveCounter.toString(), mz.width * cellSize + xOffset + 20, 20);
                
                if (y==mz.width - 1 && x == mz.width - 1) {
                        System.out.println("Winner!");
                        g2d.drawString("Congratulations, You WON! ", mz.width * cellSize + xOffset + 20, 100);
                }

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(oldX - 2, oldY - 2, 4, 4);

                g.setColor(Color.WHITE);
                g.fillRect(pointX - 2, pointY - 2, 4, 4);

        }
       
        @Override
        public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
        }


}
