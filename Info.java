package lab;

public class Info {

    public Info() {}

    public String mapOptions(int option) {

        switch (option) {
            case 1:
                return getPurposeOfTheGame();
            case 2:
                return getLayoutOfTheCave();
            case 3:
                return getSenses();
            case 4:
                return getActions();
            case 5:
                return getEndGame();
            default:
                return null;
        }
    }

    public String getWelcomeMenu() {

        return String.format(
            "+========================================================+" +
            "\n|               Welcome to Wumpus World                  |" +
            "\n+========================================================+" +
            "\n| Option | Description                                   |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   1    | Purpose of the game                           |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   2    | Layout of the cave                            |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   3    | Senses                                        |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   4    | Actions                                       |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   5    | End game                                      |" +
            "\n+--------+-----------------------------------------------+" +
            "\n|   6    | Start the game                                |" +
            "\n+--------+-----------------------------------------------+"
        );
    }

    public String getPurposeOfTheGame() {

        return String.format(
            "+========================================================+" +
            "\n|                 Purpose of the game                    |" +
            "\n+========================================================+" +
            "\n| The archer:                                            |" +
            "\n|     - starts at (0,0) of the cave of cell,             |" +
            "\n|     - aiming to get the gold ($),                      |" +
            "\n|     - while avoiding the pits (P) and the wumpus (W).  |" +
            "\n+--------------------------------------------------------+"
        );
    }

    public String getLayoutOfTheCave() {

        return String.format(
            "+=======================================================================+" +
            "\n|                 Layout of the cave                                    |" +
            "\n+=======================================================================+" +
            "\n| The cave is a 4-by-4 grid.                                            |" +
            "\n+------------+----------------------------------------------------------+" +
            "\n| Component  | Appearence in the cave                                   |" +
            "\n+------------+----------------------------------------------------------+" +
            "\n| Gold ($)   | 1, randomly appears in cell other than (0,0)             |" +
            "\n+------------+----------------------------------------------------------+" +
            "\n| Wumpus (W) | 1, randomly appears in cell other than (0,0)             |" +
            "\n+------------+----------------------------------------------------------+" +
            "\n| Pits (P)   | multiple, each cell has a probability of 0.2 to be a pit |" +
            "\n+------------+----------------------------------------------------------+" +
            "\n| Gold, pit, and wumpus will not appear in the same cell,               |" +
            "\n| and they will not be in (0,0).                                        |" +
            "\n+-----------------------------------------------------------------------+"
        );
    }

    public String getSenses() {

        return String.format(
            "+==========================================================================+" +
            "\n|                                Senses                                    |" +
            "\n+==========================================================================+" +
            "\n|- Breeze (~) will be perceived when the archer is next to a pit (P).      |" +
            "\n|- Stench (*) will be perceived when the archer is next to the wumpus (W). |" +
            "\n+--------------------------------------------------------------------------+"
        );
    }

    public String getActions() {

        return String.format(
            "+==================================================================+" +
            "\n|                          Actions                                 |" +
            "\n+==================================================================+" +
            "\n| As the archer is the agent, only the archer can perform actions. |" +
            "\n+------------------------------------------------------------------+" +
            "\n| Action | Description               | Points                      |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   w    | Facing north (^)          | -1                          |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   d    | Facing east (>)           | -1                          |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   s    | Facing south (v)          | -1                          |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   a    | Facing west (<)           | -1                          |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   f    | Move forward to next cell | -1                          |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n|   g    | Shoot arrow               | -10                         |" +
            "\n+--------+---------------------------+-----------------------------+" +
            "\n| If the wumpus is in one of the roow that the arrow pass through, |" +
            "\n| the wumpus will die. The archer has only 1 arrow.                |" +
            "\n+------------------------------------------------------------------+"
        );
    }

    public String getEndGame() {

        return String.format(
            "+======================================================================================+" +
            "\n|                             End game                                                 |" +
            "\n+======================================================================================+" +
            "\n| The game is over when the archer dies, or found the gold.                            |" +
            "\n+--------------------------------------------------------------------------------------+" +
            "\n| Event                     | Points | Status                                          |" +
            "\n+---------------------------+--------+-------------------------------------------------+" +
            "\n| Fall into a pit           | -1000  | You lose, the archer is dead                    |" +
            "\n+---------------------------+--------+-------------------------------------------------+" +
            "\n| Being eaten by the wumpus | -1000  | You lose, the archer is dead                    |" +
            "\n+---------------------------+--------+-------------------------------------------------+" +
            "\n| Reach the gold            | +1000  | You win, the archer is alive and found the gold |" +
            "\n+---------------------------+--------+-------------------------------------------------+"
        );
    }
}