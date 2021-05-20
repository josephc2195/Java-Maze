import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;

public class Maze {
    int width;
    int height;
    Cell[][] cells;

  public Maze() {
    width = 15;
    height = 15;
    cells = new Cell[width][height];
    initializeCells();
    generateMaze();
  }

  public Maze(int x, int y) {
    width = x;
    height = y;
    cells = new Cell[width][height];
    initializeCells();
    generateMaze();

  }

  public void printCells() {
    for(int i = 0; i < width; i++) {
      for(int j = 0; j < height; j++) {
        System.out.println(i + " " + j);
        cells[i][j].printCells();
        System.out.println("\n");

      }
    }
  }

  public void initializeCells() {
    for(int i=0; i < width; i++) {
      for(int j=0; j < height; j++) {
        cells[i][j] = new Cell();;
        cells[i][j].x = i;
        cells[i][j].y = j;
        if(i == 0) {
          cells[i][j].borders[0] = 1;
        }
        if(j == 0) {
          cells[i][j].borders[3] = 1;
        }
        if(i == width - 1 ) {
          cells[i][j].borders[2] = 1;
        }
        if(i == height - 1) {
          cells[i][j].borders[1] = 1;
        }
      }
    }
  }

  private void generateMaze() {
    Random rand = new Random();
    int x = rand.nextInt(width);
    int y = rand.nextInt(height);
    Stack<Cell> cellStack = new Stack<Cell>();
    int totalCells = width * height;
    int visitedCells = 1;
    Cell currentCell = cells[x][y];

    ArrayList<Vertex> neighborCellList = new ArrayList<Vertex>();

    Vertex tempV = new Vertex();

    while(visitedCells < totalCells) {
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

        cellStack.push(currentCell);

        currentCell = cells[tempV.x2][tempV.y2];

        x = currentCell.x;
        y = currentCell.y;

        visitedCells++;
      }
      else {
        currentCell = cellStack.pop();
        x = currentCell.x;
        y = currentCell.y;
      }
    }

    cells[0][0].walls[3] = 0;
    cells[width - 2][height - 2].walls[1] = 0;
  }

  public void init() {
    char wall_char = '#';
    char start_char = 's';
    char end_char = 'e';
    char open_char = ' ';
  }
}
