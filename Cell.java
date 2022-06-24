package lab;

public class Cell {
    private Coordinate relativeCoordinate;
    private Component component;
    private final char[] statusBar = new char[5];
    private final char[] positionBar = new char[5];
    private int[] shape = {statusBar.length, positionBar.length};

    public Cell(int row, int column) {
        setRelativeCoordinate(row, column);
    }

    public Cell(Coordinate relativeCoordinate) {
        this.relativeCoordinate = relativeCoordinate;
    }

    // setter for relative coordinate
    public void setRelativeCoordinate(int column, int row) {
        this.relativeCoordinate = new Coordinate(column, row);
    }

    // getter for relative coordinate
    public Coordinate getRelativeCoordinate() {
        return this.relativeCoordinate;
    }

    // setter for component
    public void setComponent(Component component) {
        this.component = component;
    }

    // getter for component
    public Component getComponent() {
        return this.component;
    }

    // setter for status bar
    public void setStatusBar(char symbol) {

        // checks for duplicate symbol
        boolean isDuplicate = false;
        for (int index = 0; index < shape[0]; index++) {
            if (this.statusBar[index] == symbol) {
                isDuplicate = true;
                break;
            }
        }

        // updates status bar if there are no duplicate symbols
        if (isDuplicate == false) {
            for (int index = 0; index < shape[0]; index++) {
                if (this.statusBar[index] == 0) {
                    this.statusBar[index] = symbol;
                    break;
                }
            }
        }

    }

    // getter for status bar
    public char[] getStatusBar() {
        return this.statusBar;
    }

    // setter for position bar
    public void setPositionBar(char position) {

        switch(position) {
            case 'n':
                this.positionBar[2] = '^';
                break;
            case 'e':
                this.positionBar[2] = '>';
                break;
            case 's':
                this.positionBar[2] = 'v';
                break;
            case 'w':
                this.positionBar[2] = '<';
                break;
            case ' ':
                this.positionBar[2] = ' ';
                break;
            default:
                System.out.println("Wrong position");
                break;
        }
    }

    // getter for position bar
    public char[] getPositionBar() {
        return this.positionBar;
    }

    // getter for shape of cell
    public int[] getShape() {
        return this.shape;
    }

    @Override
    public String toString() {

        String border = "+-----+";
        String sideBorder = "|";

        String statusBar = sideBorder + new String(this.statusBar) + sideBorder;
        String positionBar = sideBorder + new String(this.positionBar) + sideBorder;

        return border + "\n" + statusBar + "\n" + positionBar + "\n" + border;
    }
}