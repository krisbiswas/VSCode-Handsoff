package strategy;

import data.CheckingResult;
import data.Symbol;

public class DrawCheckingStrategy implements IWinCheckerStrategy {
    @Override
    public CheckingResult checkWinCondition(Symbol[][] board, Symbol symbol) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[i][j] == Symbol.EMPTY) {
                    return CheckingResult.UNKNOWN;
                }
            }
        }
        return CheckingResult.DRAW;
    }
}
