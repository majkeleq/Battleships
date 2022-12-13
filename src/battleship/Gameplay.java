package battleship;

import java.util.Scanner;

public class Gameplay {

    public void battle() {
        Map map = new Map();
        Map fogOfWar = new Map();
        map.displayMap();
        Fleet fleet = new Fleet();
        try (Scanner sc = new Scanner(System.in)) {
            fleet.initiateFleet(map, sc);
            takeShot(map, fogOfWar, sc, fleet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void takeShot(Map map, Map fogOfWar, Scanner sc, Fleet fleet) {
        System.out.println("...");
        System.out.println("The game starts!");
        System.out.println();
        fogOfWar.displayMap();
        System.out.println();

        System.out.println("Take a shoot!");
        System.out.println();
        while (!fleet.isFleetSunk()) {
            //while (!isShotFired) {
            try {
                int[] shotCoord = calculateShotField(sc.nextLine());
                if (areShotCoordsValid(shotCoord)) {
                    isShipHit(shotCoord, map, fogOfWar, fleet);
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again!");
                }
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
            }
        //}
        }

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

    private void isShipHit(int[] shotCoord, Map map, Map fogOfWar, Fleet fleet) {
        if (map.getField(shotCoord[0], shotCoord[1]) == 'O') {
            map.setField(shotCoord[0], shotCoord[1], 'X');
            fogOfWar.setField(shotCoord[0], shotCoord[1], 'X');
            fogOfWar.displayMap();
            //System.out.println("You hit a ship!");
            fleet.checkShot(shotCoord);
            //map.displayMap();
        } else if (map.getField(shotCoord[0], shotCoord[1]) == '~') {
            map.setField(shotCoord[0], shotCoord[1], 'M');
            fogOfWar.setField(shotCoord[0], shotCoord[1], 'M');
            fogOfWar.displayMap();
            System.out.println("You missed!");
            //map.displayMap();
        } else if (map.getField(shotCoord[0], shotCoord[1]) == 'M') {
            fogOfWar.displayMap();
            System.out.println("You missed!");
        } else if (map.getField(shotCoord[0], shotCoord[1]) == 'X') {
            fogOfWar.displayMap();
            System.out.println("You hit a ship!");
        }
    }
}
