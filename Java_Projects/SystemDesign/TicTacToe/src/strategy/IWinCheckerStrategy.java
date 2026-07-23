package strategy;

import data.CheckingResult;
import data.Symbol;

public interface IWinCheckerStrategy {
    CheckingResult checkWinCondition(Symbol[][] board, Symbol symbol);
}
