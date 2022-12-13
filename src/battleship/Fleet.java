package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Fleet {

    int fleetSize;
    /*Ship aircraftCarrier = new Ship(5, "Aircraft Carrier");
    Ship battleship = new Ship(4, "Battleship");
    Ship submarine = new Ship(3, "Submarine");
    Ship cruiser = new Ship(3, "Cruiser");
    Ship destroyer = new Ship(2, "Destroyer");*/
    ArrayList<Ship> fleet = new ArrayList<>();

    public Fleet() {
        fleet.add(new Ship(5, "Aircraft Carrier"));
        fleet.add(new Ship(4, "Battleship"));
        fleet.add(new Ship(3, "Submarine"));
        fleet.add(new Ship(3, "Cruiser"));
        fleet.add(new Ship(2, "Destroyer"));
        fleetSize = fleet.size();
    }

    public void initiateFleet(Map map, Scanner sc) {
        for (Ship ship : fleet) {
            ship.initiateShip(map,sc);
        }
            //aircraftCarrier.initiateShip(map, sc);
            //battleship.initiateShip(map, sc);
            //submarine.initiateShip(map, sc);
            //cruiser.initiateShip(map, sc);
            //destroyer.initiateShip(map, sc);
            //destroyer.displayCoordinates();
    }
    public void checkShot(int[] coords){
        for (Ship ship : fleet) {
            if (ship.isShipHit(coords)) {
                if (ship.isShipSunk()) {
                    fleetSize -= 1;
                    //System.out.println(fleetSize);
                    if (fleetSize == 0) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship. Specify new target:");
                    }
                }
                else {
                    System.out.println("You hit a ship! Try again:");
                }
                break;
            }
        }
    }

    public boolean isFleetSunk() {
        if (fleetSize == 0) {
            //System.out.println("You won!");
            return true;
        }
        return false;
    }
}
