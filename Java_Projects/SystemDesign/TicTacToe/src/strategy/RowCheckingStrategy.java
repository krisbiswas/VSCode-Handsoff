package strategy;

import data.CheckingResult;
import data.Symbol;

public class RowCheckingStrategy implements IWinCheckerStrategy {
    @Override
    public CheckingResult checkWinCondition(Symbol[][] board, Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return CheckingResult.WINNER;
            }
        }
        return CheckingResult.UNKNOWN;
    }    
}
