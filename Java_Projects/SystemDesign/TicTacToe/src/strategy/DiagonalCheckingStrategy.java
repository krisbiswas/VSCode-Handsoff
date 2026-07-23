package strategy;

import data.CheckingResult;
import data.Symbol;

public class DiagonalCheckingStrategy implements IWinCheckerStrategy {
    @Override
    public CheckingResult checkWinCondition(Symbol[][] board, Symbol symbol) {
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return CheckingResult.WINNER;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return CheckingResult.WINNER;
        }
        return CheckingResult.UNKNOWN;
    }
}
