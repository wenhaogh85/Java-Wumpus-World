package lab;

import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;

public class Utility {

    // creates wumpus
    public static void createWumpus(Cave cave) {
        createComponent(new Wumpus(), cave);
    }

    // creates gold
    public static void createGold(Cave cave) {
        createComponent(new Gold(), cave);
    }

    // creates pit
    public static void createPit(Cave cave) {
        createComponent(new Pit(), cave);
    }

    // creates multiple pit
    public static void createPits(Cave cave) {

        // initialises random variable
        Random random = new Random();

        int minNumOfPits = 1;
        int maxNumOfPits = 3;

        // sets the number of pits in a cave
        int totalNumOfPits = minNumOfPits + random.nextInt((maxNumOfPits - minNumOfPits) + 1);

        // creates pits
        int numOfPits = 1;
        while (numOfPits <= totalNumOfPits) {

            // sets probability
            double probability = Math.round(random.nextDouble() * 10.0) / 10.0;

            double probabilityIsPit = 0.2;

            if (probability <= probabilityIsPit) {
                createPit(cave);
                numOfPits++;
            }
        }
    }

    /** creates a component and
        assigns a coordinate to it
        distribute traces (stench or breeze) to neigbouring cells
     */
    public static void createComponent(Component component, Cave cave) {

        Coordinate relativeCoordinate = null;
        Cell cell = null;

        boolean keepFindingEmptyCell = true;
        while (keepFindingEmptyCell == true) {

            // sets new relative coordinate for component
            relativeCoordinate = setRelativeCoordinate(cave);

            // checks if the component coordinate is valid or not
            if (isValidRelativeCoordinate(relativeCoordinate) != true) {

                cell = cave.getCell(relativeCoordinate);

                // checks if cell already have a component
                if (cell.getComponent() == null) {

                    cell.setComponent(component);
                    keepFindingEmptyCell = false;
                }
            }
        }
    }

    // sets relative coordinate
    public static Coordinate setRelativeCoordinate(Cave cave) {

        // initialises random variable
        Random random = new Random();

        int minRow = 0;
        int maxRow = cave.getRows() - 1;

        // sets the wumpus's row range between 0 to maximum cave rows
        int row = minRow + random.nextInt((maxRow - minRow) + 1);

        int minColumn = 0;
        int maxColumn = cave.getColumns() - 1;

        // sets the wumpus's column range between 0 to maximum cave columns
        int column = minColumn + random.nextInt((maxColumn - minColumn) + 1);

        return new Coordinate(row, column);
    }

    // checks if the coordinate is out of range
    public static boolean isOutOfRange(Coordinate coordinate, Cave cave) {

        // gets the row and column values of a coordinate
        int row = coordinate.getY();
        int column = coordinate.getX();

        if (row > cave.getRows() - 1 || row < 0) {
            return true;
        }

        if (column > cave.getColumns() - 1 || column < 0) {
            return true;
        }

        return false;
    }

    // checks if relative coordinate is valid
    public static boolean isValidRelativeCoordinate(Coordinate relativeCoordinate) {

        // relative coordinate at origin
        if (relativeCoordinate.getY() == 0 && relativeCoordinate.getX() == 0) {
            return true;
        }

        // relative coordinate at origin's neighbour
        if (relativeCoordinate.getY() == 1 && relativeCoordinate.getX() == 0) {
            return true;
        }

        // relative coordinate at origin's neighbour
        if (relativeCoordinate.getY() == 0 && relativeCoordinate.getX() == 1) {
            return true;
        }

        return false;
    }

    public static Coordinate nextCoordinate(Coordinate relativeCoordinate, char direction) {

        int row = relativeCoordinate.getY();
        int column = relativeCoordinate.getX();

        // initialises new coordinate
        Coordinate coordinate = null;

        switch (direction) {
            
            // north
            case 'n':
                coordinate = new Coordinate(row + 1, column);
                break;
            
            // east
            case 'e':
                coordinate = new Coordinate(row, column + 1);
                break;

            // south
            case 's':
                coordinate = new Coordinate(row - 1, column);
                break;

            // west
            case 'w':
                coordinate = new Coordinate(row, column - 1);
                break;

            // not sure what to put, so put back the original relative coordinate
            default:
                coordinate = relativeCoordinate;
                break;
        }

        return coordinate;
    }

    // gets traces from nearby component such as Wumpus or Pit
    public static void sense(Player player, Cave cave) {

        // gets neighbouring coordinates from player current position
        Coordinate[] neighbouringCoordinates = getNeigbouringCoordinates(player.getPosition());

        // checks if there are traces left by Wumpus or Pit
        for (Coordinate neighbouringCoordinate: neighbouringCoordinates) {

            // checks if coordinate is out of range
            if (isOutOfRange(neighbouringCoordinate, cave) != true) {

                Component component = cave.getCell(neighbouringCoordinate).getComponent();

                // checks if cell contains any component such as Wumpus or Pit
                if (component != null) {
                    if (component instanceof Wumpus || component instanceof Pit) {
                        cave.getCell(player.getPosition()).setStatusBar(component.getTrace());
                    }
                }
            }
        }
    }

    /** argument can be relative or actual coordinate,
        but it is mainly used for relative coordinate
     */
    public static Coordinate[] getNeigbouringCoordinates(Coordinate coordinate) {

        // gets the row and column values of a coordinate
        int row = coordinate.getY();
        int column = coordinate.getX();

        // one coordinate have 4 neighbouring cells
        Coordinate[] neighbouringCoordinates = new Coordinate[4];

        // top neighbour
        neighbouringCoordinates[0] = new Coordinate(row + 1, column);

        // bottom neighbour
        neighbouringCoordinates[1] = new Coordinate(row -1, column);

        // left neigbour
        neighbouringCoordinates[2] = new Coordinate(row, column - 1);

        // right neigbour
        neighbouringCoordinates[3] = new Coordinate(row, column + 1);

        return neighbouringCoordinates;
    }

    public static char mapDirection(char direction) {

        // north
        if (direction == 'w') {
            return 'n';

        // east
        } else if (direction == 'd') {
            return 'e';

        // south
        } else if (direction == 's') {
            return 's';

        // west
        } else if (direction == 'a') {
            return 'w';

        // if something very wrong happen, return back the original direction
        } else {
            return direction;
        }
    }

    public static Coordinate arrowHitWumpus(Coordinate coordinate, char direction, Cave cave) {

        // gets all cells that the player is facing
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

        // north (row number increases, column number constant)
        if (direction == 'n') {
            int startRow = coordinate.getY() + 1;
            for (int row = startRow; row < cave.getRows(); row++) {
                coordinates.add(new Coordinate(row, coordinate.getX()));
            }

        // east (column number constant, column number increases)
        } else if (direction == 'e') {
            int startColumn = coordinate.getX() + 1;
            for (int column = startColumn; column < cave.getColumns(); column++) {
                coordinates.add(new Coordinate(coordinate.getY(), column));
            }

        // south (row number decreases, column number constant)
        } else if (direction == 's') {
            int startRow = coordinate.getY() - 1;
            for (int row = startRow; row >= 0; row--) {
                coordinates.add(new Coordinate(row, coordinate.getX()));
            }

        // west (column number constant, column number decreases)
        } else if (direction == 'w') {
            int startColumn = coordinate.getX() - 1;
            for (int column = startColumn; column >= 0; column--) {
                coordinates.add(new Coordinate(coordinate.getY(), column));
            }
        }

        // checks if Wumpus is in line with the arrow's direction
        if (coordinates.size() != 0) {
            for (Coordinate newCoordinate: coordinates) {
                Component component = cave.getCell(newCoordinate).getComponent();
                if (component instanceof Wumpus) {
                    return newCoordinate;
                }
            }
        }

        return null;
    }

    // reveal all components when gave is over
    public static void revealAllComponents(Cave cave) {

        for (Cell[] cells: cave.getLayout()) {

            for (Cell cell: cells) {

                Component component = cell.getComponent();

                if (component != null) {

                    // updates cave with current position of the component
                    cell.setStatusBar(cell.getComponent().getSymbol());

                    if (component instanceof Wumpus || component instanceof Pit) {

                        Coordinate relativeCoordinate = cell.getRelativeCoordinate();

                        // gets neighbouring coordinates from player current position
                        Coordinate[] neighbouringCoordinates = getNeigbouringCoordinates(relativeCoordinate);

                        // checks if there are traces left by Wumpus or Pit
                        for (Coordinate neighbouringCoordinate: neighbouringCoordinates) {

                            // checks if coordinate is out of range
                            if (isOutOfRange(neighbouringCoordinate, cave) != true) {
                                cave.getCell(neighbouringCoordinate).setStatusBar(component.getTrace());
                            }
                        }
                    }

                }
            }
        }
    }
}