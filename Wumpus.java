package lab;

public class Wumpus extends Component {

    private boolean isDead;

    public Wumpus() {
        super("Wumpus", 'W', '*');
    }

    public boolean getStatus() {
        return this.isDead;
    }

    public void die() {
        this.isDead = true;
    }
}