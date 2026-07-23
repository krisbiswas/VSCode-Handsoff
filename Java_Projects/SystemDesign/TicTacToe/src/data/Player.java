package data;

public class Player {
    Symbol symbol;
    String name;

    public Player(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name == null ? "No name for player " + symbol : name;
    }
}
