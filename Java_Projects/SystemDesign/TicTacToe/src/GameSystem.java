import java.util.Map;

import data.GameResult;
import data.Player;
import data.Symbol;
import strategy.ColumnCheckingStrategy;
import strategy.DiagonalCheckingStrategy;
import strategy.DrawCheckingStrategy;
import strategy.RowCheckingStrategy;

/// Manage each game record
/// Scoreboard update
/// DB updates
public class GameSystem {
    private static GameSystem instance;
    // Scoreboard

    private GameSystem(){}

    public static GameSystem getInstance() {
        if(instance == null){
            instance = new GameSystem();
        }
        return instance;
    }
    
    void start(Map.Entry<Symbol, String> p1, Map.Entry<Symbol, String> p2) {
        // if(p2.getKey() == Symbol.X) {
        //     Map.Entry<Symbol, String> temp = p1;
        //     p1 = p2;
        //     p2 = temp;
        // }
        Player p1Player = new Player(p1.getKey(), p1.getValue());
        Player p2Player = new Player(p2.getKey(), p2.getValue());
        String scoreBoardHash = p1Player.getName()+"-"+p2Player.getName();

        Game game = new Game(p1Player.getSymbol());
        game.setWinningStrategies(new RowCheckingStrategy());
        game.setWinningStrategies(new ColumnCheckingStrategy());
        game.setWinningStrategies(new DiagonalCheckingStrategy());
        game.setWinningStrategies(new DrawCheckingStrategy());
        // play moves
        GameResult result;
        int i = 0;
        int j = 0;
        Symbol dummySymbol = Symbol.X;
        do {
            // TODO : Take user input
            result = game.makeMove(i, j, dummySymbol);
            game.printBoard();
            dummySymbol = dummySymbol == Symbol.X ? Symbol.O : Symbol.X;
            i = j + 1 == 3 ? i+1 : i;
            j = (j + 1) % 3;
        } while (result == GameResult.INPROGRESS);

        // scoreBoard.put(scoreBoardHash, xPlayer.getName());

        String format = "Player (%s): %s is the winner";
        String output;
        switch (result) {
            case GameResult.WINNER_X:
                output = String.format(format, "X", p1Player.getName());
                break;
            case GameResult.WINNER_O:
                output = String.format(format, "O", p2Player.getName());
                break;
            case GameResult.DRAW:
                output = String.format("The game was a draw between %s and %s", p1Player.getName(), p2Player.getName());
                break;
            case GameResult.INTERRUPTED:
                output = "The game was a INTERRUPTED!";
                break;
            default:
                output = "";
                break;
        }
        
        System.out.println(output);
    }
}
