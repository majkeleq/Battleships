package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class Fleet {

    int fleetSize;
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
        map.displayMap();
        for (Ship ship : fleet) {
            ship.initiateShip(map,sc);
        }
    }
    public void checkShot(int[] coords){
        for (Ship ship : fleet) {
            if (ship.isShipHit(coords)) {
                if (ship.isShipSunk()) {
                    fleetSize -= 1;
                    if (fleetSize == 0) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                    } else {
                        System.out.println("You sank a ship.");
                    }
                }
                else {
                    System.out.println("You hit a ship!");
                }
                break;
            }
        }
    }
    public boolean isFleetSunk() {
        if (fleetSize == 0) {
            return true;
        }
        return false;
    }
}
