package battleship;

import java.util.Scanner;

public class Gameplay {

    Player playerOne = new Player("Player 1");
    Player playerTwo = new Player("Player 2");
    public void battle() {

        try (Scanner sc = new Scanner(System.in)) {
            playerOne.getFleet().initiateFleet(playerOne.getMap(), sc);
            passMove(sc);
            System.out.println("Player 2, place your ships to the game field");
            playerTwo.getFleet().initiateFleet(playerTwo.getMap(), sc);
            String winner;
            while(true) {
                passMove(sc);
                gameStarts(playerOne.name);
                takeShot(playerOne, playerTwo, sc);
                if(playerTwo.getFleet().isFleetSunk()) {
                    winner = playerOne.getName();
                    break;
                }
                passMove(sc);
                gameStarts(playerTwo.name);
                takeShot(playerTwo, playerOne, sc);
                if(playerOne.getFleet().isFleetSunk()) {
                    winner = playerTwo.getName();
                    break;
                }
            }
            //System.out.println("Congratulation " + winner + "! You won!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private void passMove(Scanner sc) {
        System.out.println("Press Enter and pass the move to another player");
        sc.nextLine();
        clearScreen();
    }
    private void gameStarts(String name) {

        System.out.println(name + ", it's your turn:");
        System.out.println();
    }
    public void takeShot(Player player, Player enemy, Scanner sc) {

        //while (!enemy.getFleet().isFleetSunk()) {
            //while (!isShotFired) {
        boolean isShotMade = false;
        while (!isShotMade) {
            try {
                showMaps(player.getMap(), player.getEnemyMap());
                int[] shotCoord = calculateShotField(sc.nextLine());
                isShotMade = areShotCoordsValid(shotCoord);
                if (isShotMade) {
                    isShipHit(shotCoord, enemy.getMap(), player.getEnemyMap(), enemy.getFleet());
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again!");
                }
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        }
        //}
        //}

    }

    private void showMaps(Map map, Map enemyMap) {
        enemyMap.displayMap();
        System.out.println("---------------------");
        map.displayMap();
        System.out.println();
    }
    private int[] calculateShotField(String line) {
        int[] coords = {line.substring(0, 1).charAt(0) - 65, Integer.parseInt(line.substring(1)) - 1};
        return coords;

    }

    private boolean areShotCoordsValid(int[] shotCoord) {
        if (shotCoord[0] < 10 && shotCoord[0] >= 0 || shotCoord[1] < 10 && shotCoord[1] >= 0) {
            return true;
        }
        return false;
    }

    private void isShipHit(int[] shotCoord, Map map, Map fogOfWar, Fleet fleet)     {
        if (map.getField(shotCoord[0], shotCoord[1]) == 'O') {
            map.setField(shotCoord[0], shotCoord[1], 'X');
            fogOfWar.setField(shotCoord[0], shotCoord[1], 'X');
            //fogOfWar.displayMap();
            //System.out.println("You hit a ship!");
            fleet.checkShot(shotCoord);
            //map.displayMap();
        } else if (map.getField(shotCoord[0], shotCoord[1]) == '~') {
            map.setField(shotCoord[0], shotCoord[1], 'M');
            fogOfWar.setField(shotCoord[0], shotCoord[1], 'M');
            //fogOfWar.displayMap();
            System.out.println("You missed!");
            //map.displayMap();
        } else if (map.getField(shotCoord[0], shotCoord[1]) == 'M') {
            //fogOfWar.displayMap();
            System.out.println("You missed!");
        } else if (map.getField(shotCoord[0], shotCoord[1]) == 'X') {
            //fogOfWar.displayMap();
            System.out.println("You hit a ship!");
        }
    }
}
