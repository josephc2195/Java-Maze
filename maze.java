import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Maze {

  public static void main(String args[]) {
    new Maze();
  }

  public void Maze() {

  }

  public void init() {
    char wall_char = '#';
    char start_char = 's';
    char end_char = 'e';
    char open_char = ' ';
  }

  private void buildNew(int width, int height) {
    for(int i = 0; i < height; i++) {
      for(int x = 0; x < width; x++) {
        Point p = (i, x);
        if(p.x == 0 || p.x == 1 || p.y == 0 || p.y == 1) {
          
        }
      }
    }
  }




}
