package data;

public enum Symbol {
    EMPTY(' '),
    X('X'),
    O('O');

    private final char symbol;

    Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(getSymbol());
    }
}
