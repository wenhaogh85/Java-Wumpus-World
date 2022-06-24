package lab;

import java.util.Scanner;

public class Player {
    private int points = 0;
    private boolean isAlive = true;
    private boolean haveGold = false;
    private char action;
    private char direction;
    private Coordinate position;
    private int numOfArrows = 1;

    public Player() {
        this.direction = 'e';
        this.position = new Coordinate(0,0);
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }

    public void updatePosition(Coordinate position) {
        this.position = position;
    }

    public Coordinate getPosition() {
        return this.position;
    }

    public int getPoints() {
        return this.points;
    }

    public boolean stillAlive() {
        return this.isAlive;
    }

    public boolean gotGold() {
        return this.haveGold;
    }

    public void foundGold() {
        this.points += 1000;
        this.haveGold = true;
    }

    public void enterAction() {

        // initialses scanner to accept user input
        Scanner input = new Scanner(System.in);

        // gets the player's input
        System.out.print("Enter action: ");
        char action = input.next(".").charAt(0);
        this.action = Character.toLowerCase(action);
    }

    public char getAction() {
        return this.action;
    }

    public void shootArrow() {
        this.points -= 10;
        this.numOfArrows -= 1;
    }

    public int getNumOfArrows() {
        return this.numOfArrows;
    }

    public void die() {
        this.points -= 1000;
        this.isAlive = false;
    }

    public void decreasePoints(int points) {
        this.points = this.points - points;
    }

    public void increasePoints(int points) {
        this.points = this.points + points;
    }

    public void reset() {
        this.points = 0;
        this.isAlive = true;
        this.haveGold = false;
    }

    @Override
    public String toString() {
        return String.format("Points: %d\nisAlive: %b", this.points, this.isAlive);
    }
}