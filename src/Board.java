import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][]cells;

    Board(int boardSize,int numberOfSnakes,int numberOfLadders){
        initializeCells(boardSize);
        addSnakeLadder(cells,numberOfSnakes,numberOfLadders);
    }

    private void initializeCells(int boardSize){
        cells=new Cell[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellObj=new Cell();
                cells[i][j]=cellObj;
            }

        }

    }

    private void addSnakeLadder(Cell[][]cells,int numberOfSnakes,int numberOfLadders){

        while(numberOfSnakes>0){

            int SnakeHead= ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length)-1);
            int SnakeTail=ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length)-1);
            if(SnakeTail>=SnakeHead)continue;
            Jump snakeObj=new Jump();
            snakeObj.start=SnakeHead;
            snakeObj.end=SnakeTail;
            Cell cell=getCell(SnakeHead);
            cell.jump=snakeObj;
            numberOfSnakes--;
        }

        while(numberOfLadders>0){

            int ladderStart= ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length)-1);
            int ladderEnd=ThreadLocalRandom.current().nextInt(1, cells.length*(cells.length)-1);
            if(ladderEnd<=ladderStart)continue;
            Jump ladderObj=new Jump();
            ladderObj.start=ladderStart;
            ladderObj.end=ladderEnd;
            Cell cell=getCell(ladderStart);
            cell.jump=ladderObj;
            numberOfLadders--;
        }



    }

    Cell getCell(int playerPosition){
        int boardRow=playerPosition/ cells.length;
        int boardColumn=playerPosition%cells.length;
        return cells[boardRow][boardColumn];
    }

}
