package lab;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Info info = new Info();

        boolean keepLooping = true;
        while (keepLooping == true) {

            System.out.println(info.getWelcomeMenu());

            System.out.print("\nEnter option: ");
            int option = input.nextInt();

            System.out.println();

            if (option == 6) {
                keepLooping = false;

            } else {
                String message = info.mapOptions(option);

                if (message != null) {
                    System.out.println(message + "\n");
                } else {
                    System.out.println("This is an invalid option");
                }
            }
        }

        // initialises cave
        Cave cave = new Cave(4,4);

        // creates components such as Wumpus, Gold and Pit(s)
        Utility.createWumpus(cave);
        Utility.createGold(cave);
        Utility.createPits(cave);

        // initialses player
        Player player = new Player();

        // initialises the position of the player at origin
        cave.getCell(player.getPosition()).setPositionBar(player.getDirection());

        /** keep playing game as long:
            player is still alive or,
            have not found gold or,
            player enters q (quit)
         */
        String message = "Wumpus World";
        while (player.stillAlive() == true && player.gotGold() == false && player.getAction() != 'q') {

            System.out.println(
                                "\n================================\n" +
                                " " + message +
                                "\n================================\n" +
                                cave.toString() +
                                "\n--------------------------------\n" +
                                "Points: " + player.getPoints() +
                                "\nNumber of arrows: " + player.getNumOfArrows()
            );

            player.enterAction();

            char action = player.getAction();

            // north
            if (action == 'w' || action == 'd' || action == 's' || action == 'a') {
                player.decreasePoints(1);
                player.setDirection(Utility.mapDirection(action));
                cave.getCell(player.getPosition()).setPositionBar(player.getDirection());

                message = "Changing direction";

            // moves forward
            } else if (action == 'f') {

                Coordinate previousCoordinate = player.getPosition();
                char direction = player.getDirection();

                Coordinate futureCoordinate = Utility.nextCoordinate(previousCoordinate, direction);

                // checks if new future coordinate is valid
                if (Utility.isOutOfRange(futureCoordinate, cave) != true) {

                    player.decreasePoints(1);

                    player.updatePosition(futureCoordinate);

                    Cell cell = cave.getCell(futureCoordinate);

                    // update cells in cave
                    cave.getCell(previousCoordinate).setPositionBar(' ');
                    cell.setPositionBar(player.getDirection());

                    Component component = cell.getComponent();

                    // checks if cell have Wumpus
                    if (component instanceof Wumpus) {

                        if (((Wumpus) component).getStatus() != true) {
                            cell.setStatusBar(cell.getComponent().getSymbol());
                            player.die();

                            message = "You have been killed by the Wumpus!";
                        } else {
                            message = "Moving forward";
                        }

                    // checks if cell have Pit
                    } else if (component instanceof Pit) {
                        cell.setStatusBar(cell.getComponent().getSymbol());
                        player.die();

                        message = "You fell into a Pit!";

                    // checks if cell have Gold
                    } else if (component instanceof Gold) {
                        cell.setStatusBar(cell.getComponent().getSymbol());
                        player.foundGold();

                        message = "You found Gold!";

                    } else {
                        message = "Moving forward";
                    }
                }

                //checks if there are traces left by Wumpus or Pit
                Utility.sense(player, cave);

            //shoots arrow
            } else if (action == 'g') {

                player.shootArrow();

                Coordinate playerCoordinate = player.getPosition();
                char direction = player.getDirection();

                Coordinate coordinate = Utility.arrowHitWumpus(playerCoordinate, direction, cave);

                if (coordinate != null) {

                    Cell cell = cave.getCell(coordinate);

                    Component component = cell.getComponent();

                    ((Wumpus) component).die();

                    cell.setStatusBar(cell.getComponent().getSymbol());

                    message = "Rawr!! You killed the Wumpus";

                } else {
                    message = "You missed your shot!!";
                }

            } else if (action == 'q') {
                message = "Exiting Wumpus World...";

            } else {
                message = "Invalid move!";
            }
        }

        Utility.revealAllComponents(cave);
        System.out.println(
                            "\n================================\n" +
                            " " + message +
                            "\n================================\n" +
                            cave.toString() +
                            "\n--------------------------------\n" +
                            "Points: " + player.getPoints() +
                            "\nNumber of arrows: " + player.getNumOfArrows()
        );
    }
}