package lab;

public class Coordinate {
    int x;
    int y;

    public Coordinate() {
        x = 0;
        y = 0;
    }

    public Coordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setCoordinate(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public boolean equals(Object object) {

        //checks if object has a null reference
        if (object == null)
            return false;

        //checks if object equals to itself
        if (object == this)
            return true;

        //checks if object is an instance of Coordinate object
        if ((object instanceof Coordinate) == false) {
            return false;

        } else {

            //downcasts the object to Coordinate and compare its values
            Coordinate convertedObject = (Coordinate) object;

            /* compares this instance and downcasted object
             * if they have the same attribute values
             */
            if (this.x == convertedObject.x
                    && this.y == convertedObject.y) {
                        return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.y, this.x);
    }
}