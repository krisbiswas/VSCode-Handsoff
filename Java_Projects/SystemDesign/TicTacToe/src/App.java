import java.util.Map;

import data.Symbol;

public class App {
    public static void main(String[] args) throws Exception {
        GameSystem gameSystem = GameSystem.getInstance();
        gameSystem.start(Map.entry(Symbol.X, "Player X"), Map.entry(Symbol.O, "Player O"));
    }
}
