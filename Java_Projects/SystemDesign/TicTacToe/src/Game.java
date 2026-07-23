import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import data.CheckingResult;
import data.GameResult;
import data.Symbol;
import strategy.IWinCheckerStrategy;

/// Represents Actual Game
/// manages state of current game
public class Game {
    Symbol[][] board;
    Symbol currentPlayer;
    List<IWinCheckerStrategy> winCheckers;

    Game(Symbol startsWith) {
        this.currentPlayer = startsWith;
        this.board = new Symbol[3][3];
        for(int i=0;i<3;i++){
            Arrays.fill(this.board[i], Symbol.EMPTY);
        }
        winCheckers = new ArrayList<>();
    }

    void setWinningStrategies(IWinCheckerStrategy strategy) {
        this.winCheckers.add(strategy);
    }

    private boolean isValidMove(int x, int y) {
        return !(x<0 || x>=3 || y<0 || y>=3) && (board[x][y] == Symbol.EMPTY);
    }

    GameResult makeMove(int x, int y, Symbol symbol) {
        if(!isValidMove(x, y)) {
            System.out.println("Cannot place at position = " + x + ", " + y);
            return GameResult.INPROGRESS;
        }
        if(symbol == Symbol.EMPTY){
            System.out.println("Move can't be empty");
            return GameResult.INPROGRESS;
        }
        if(symbol != currentPlayer){
            System.out.println("Cheating :: It's "+ currentPlayer +" player's move.");
            return GameResult.INPROGRESS;
        }
        // System.out.println(currentPlayer);
        board[x][y] = symbol;
        for(IWinCheckerStrategy strategy : winCheckers) {
            CheckingResult result = strategy.checkWinCondition(board, symbol);
            if(result == CheckingResult.WINNER) {
                return currentPlayer == Symbol.X ? GameResult.WINNER_X : GameResult.WINNER_O;
            } else if(result == CheckingResult.DRAW){
                return GameResult.DRAW;
            }
        }
        // at this point (symbol == currentPlayer)
        currentPlayer = symbol == Symbol.X ? Symbol.O : Symbol.X;
        return GameResult.INPROGRESS;
    }

    void printBoard() {
        for(int i=0;i<3;i++){
            // for(int j=0;j<3;j++){
            //     System.out.print();
            // }
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }
}
