package lab;

public class Cave {
    private int rows;
    private int columns;
    private Cell[][] layout;

    // empty constructor
    public Cave() {
        this(4,4);
    }

    // full args constructor
    public Cave(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        layout = new Cell[this.rows][this.columns];
        createLayout();
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return this.rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return this.columns;
    }

    public void createLayout() {

        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {

                Coordinate actualCoordinate = new Coordinate(row, column);

                // gets the relative coordinate of the cell
                Coordinate relativeCoordinate = mapCoordinate(actualCoordinate);

                layout[row][column] = new Cell(relativeCoordinate);
            }
        }
    }

    public Cell[][] getLayout() {
        return this.layout;
    }

    // returns cell based on relative coordinate
    public Cell getCell(Coordinate relativeCoordinate) {

        // gets back the actual coordinate of the cell through mapping
        Coordinate actualCoordinate = mapCoordinate(relativeCoordinate);

        int row = actualCoordinate.getY();
        int column = actualCoordinate.getX();

        return layout[row][column];
    }

    /** maps actual coordinate to relative coordinate
        or relative coordinate to actual coordinate
        however, this is mainly used to map relative coordinate to actual coordinate
    */
    public Coordinate mapCoordinate(Coordinate coordinate) {

        int row = (this.rows - 1) - coordinate.getY();
        int column = coordinate.getX();

        return new Coordinate(row, column);
    }

    @Override
    public String toString() {
        
        String border = "";

        for (int row = 0; row < this.columns; row++) {
            border += "+";
            for (int column = 0; column < this.layout[0][0].getShape()[0]; column++) {
                border += "-";
            }
        }
        border += "+";

        String caveLayout = "";

        String sideBorder = "|";

        int rowNumber = this.rows - 1;
        for (int row = 0; row < this.rows; row++) {
            
            caveLayout += "     " + border + "\n     ";

            for (int column = 0; column < this.columns; column++) {
                caveLayout += sideBorder;
                for (char status: this.layout[row][column].getStatusBar()) {
                    caveLayout += status;
                }
            }

            caveLayout += sideBorder + "\n" + "   " + Integer.toString(rowNumber) + " ";

            for (int column = 0; column < this.columns; column++) {
                caveLayout += sideBorder;
                for (char position: this.layout[row][column].getPositionBar()) {
                    caveLayout += position;
                }
            }

            caveLayout += sideBorder + "\n";

            rowNumber -= 1;
        }

        caveLayout += "     " + border + "\n     ";

        int columnNumber = 0;
        for (int iteration = 0; iteration < this.columns; iteration++) {
            caveLayout +=  " ";
            for (int column = 0; column < 5; column++) {
                if (column == 2) {
                    caveLayout += Integer.toString(columnNumber);
                } else {
                    caveLayout += " ";
                }
            }
            columnNumber += 1;
        }

        return caveLayout;
    }
}