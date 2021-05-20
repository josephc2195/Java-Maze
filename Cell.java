public class Cell {
        byte[] walls = {1,1,1,1};
        byte[] borders = {0,0,0,0};
        byte[] solution = {0,0,0,0};
        byte[] backtrack = {0,0,0,0};
        Integer x;
        Integer y;

        public void printCells() {
                System.out.println(" " + walls[0] + " ");
                System.out.println(walls[3] + " " + walls[1]);
                System.out.println(" " + walls[2] + " ");
        }

        public boolean checkWalls() {
                if(walls[0] == 1 && walls[1] == 1 && walls[2] == 1 && walls[3] == 1) {
                        return true;
                }
                else {
                        return false;
                }
        }
}
