package lab;

public class Component {

    private String name;
    private char symbol;
    private char trace;

    public Component(String name, char symbol, char trace) {
        this.name = name;
        this.symbol = symbol;
        this.trace = trace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setTrace(char trace) {
        this.trace = trace;
    }

    public char getTrace() {
        return this.trace;
    }

    @Override
    public String toString() {
        return String.format("Component: %s\nTrace: %c", this.name, this.trace);
    }
}