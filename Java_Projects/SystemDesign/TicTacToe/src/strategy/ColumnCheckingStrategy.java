package strategy;

import data.CheckingResult;
import data.Symbol;

public class ColumnCheckingStrategy implements IWinCheckerStrategy {
    @Override
    public CheckingResult checkWinCondition(Symbol[][] board, Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return CheckingResult.WINNER;
            }
        }
        return CheckingResult.UNKNOWN;
    }
}
