import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;

public class Maze {
    int width;
    int height;
    Cell[][] cells;

  public Maze() {
    width = 20;
    height = 20;
    cells = new Cell[width][height];
    rollOut();
    createMaze();
  }

  public Maze(int width, int height) {
    this.width = width;
    this.height = height;
    cells = new Cell[this.width][this.height];
    rollOut();
    createMaze();

  }

  public void printCells() {
    for(int i = 0; i < width; i++) {
      for(int x = 0; x < height; x++) {
        System.out.println(i + " " + x);
        cells[i][x].printCells();
        System.out.println("\n");
      }
    }
  }

  public void rollOut() {
    for(int i=0; i < width; i++) {
      for(int c=0; c < height; c++) {
        cells[i][c] = new Cell();;
        cells[i][c].x = i;
        cells[i][c].y = c;
        if(i == 0) {
          cells[i][c].borders[0] = 1;
        }
        if(c == 0) {
          cells[i][c].borders[3] = 1;
        }
        if(i == width - 1 ) {
          cells[i][c].borders[2] = 1;
        }
        if(i == height - 1) {
          cells[i][c].borders[1] = 1;
        }
      }
    }
  }

  private void createMaze() {
    Random rand = new Random();
    int x = rand.nextInt(width);
    int y = rand.nextInt(height);
    Stack<Cell> cellStack = new Stack<Cell>();
    int totalCells = width * height;
    int cellsVisited = 1;
    Cell inCell = cells[x][y];

    ArrayList<Vertex> neighborCellList = new ArrayList<Vertex>();

    Vertex tempV = new Vertex();

    while(cellsVisited < totalCells) {
      neighborCellList.clear();

      tempV = new Vertex();
      if(y-1 >= 0 && cells[x][y-1].checkWalls() == true) {
        tempV.x1 = x;
        tempV.y1 = y;
        tempV.x2 = x;
        tempV.y2 = y-1;
        tempV.wall1 = 0;
        tempV.wall2 = 2;
        neighborCellList.add(tempV);
      }

      tempV = new Vertex();
      if(y+1 < height && cells[x][y+1].checkWalls() == true) {
        tempV.x1 = x;
        tempV.y1 = y;
        tempV.x2 = x;
        tempV.y2 = y+1;
        tempV.wall1 = 2;
        tempV.wall2 = 0;
        neighborCellList.add(tempV);
      }

      tempV = new Vertex();
      if(x-1 >= 0 && cells[x-1][y].checkWalls() == true) {
        tempV.x1 = x;
        tempV.y1 = y;
        tempV.x2 = x-1;
        tempV.y2 = y;
        tempV.wall1 = 3;
        tempV.wall2 = 1;
        neighborCellList.add(tempV);
      }

      tempV = new Vertex();
      if(x+1 < width && cells[x+1][y].checkWalls() == true) {
        tempV.x1 = x;
        tempV.y1 = y;
        tempV.x2 = x+1;
        tempV.y2 = y;
        tempV.wall1 = 1;
        tempV.wall2 = 3;
        neighborCellList.add(tempV);
      }

      if(neighborCellList.size() >=1) {
        int r1 = rand.nextInt(neighborCellList.size());
        tempV = neighborCellList.get(r1);
        cells[tempV.x1][tempV.y1].walls[tempV.wall1] = 0;
        cells[tempV.x2][tempV.y2].walls[tempV.wall2] = 0;

        cellStack.push(inCell);

        inCell = cells[tempV.x2][tempV.y2];

        x = inCell.x;
        y = inCell.y;

        cellsVisited++;
      }
      else {
        inCell = cellStack.pop();
        x = inCell.x;
        y = inCell.y;
      }
    }

    cells[0][0].walls[3] = 0;
    cells[width - 1][height - 1].walls[1] = 0;
  }
}
