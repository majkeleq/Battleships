package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Ship {

    final private int lenght;
    private int hp;
    private int[][] shipCoordinates;
    private String name;

    //private String[] position;

    public Ship(int fields, String name) {
        this.lenght = fields;
        this.hp = fields;
        shipCoordinates = new int[fields][2];
        this.name = name;
    }
    public void initiateShip(Map map, Scanner sc) {

        System.out.printf("Enter the coordinates of the %s (%d cells)\n", name, lenght);
        boolean isInitiated = false;
        while(!isInitiated) {
            try {
                String line = sc.nextLine();
                int[] coordinates = calculatePlaceCoordinates(line);
                if (arePlaceCoordinateValid(coordinates, this.lenght)) {
                    if (map.checkNeighbourhood(coordinates)) {
                        setCoordinates(map, coordinates);
                        //map.placeShip(coordinates);
                        isInitiated = true;
                    } else {
                        System.out.println("Error! You placed it to close to another one. Try again");
                    }
                } else {
                    System.out.println("Error! Wrong coordinates! Try again:");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! " + e.getMessage() + " Try again:");
            }
        }
        map.displayMap();
    }
    /**
     * Metoda pobiera wprowadzoną przez użytkownika linię, rozbija je na substringi zawierające wartości kolumn i wierszy
     *
     * @param line
     * @return
     */
    private int[] calculatePlaceCoordinates(String line) {
        String[] userCoordinates = line.split(" ");
        int[] coordinates = new int[4];
        //System.out.printf("%s %s",userCoordinates[0], userCoordinates[1]);
        //rozdzielenie pojedynczych wspolrzednych na numery kolumn i wierszy
        coordinates[0] = userCoordinates[0].substring(0, 1).charAt(0) - 65;//row1
        coordinates[2] = userCoordinates[1].substring(0, 1).charAt(0) - 65;//row2
        coordinates[1] = Integer.parseInt(userCoordinates[0].substring(1)) - 1;//col1
        coordinates[3] = Integer.parseInt(userCoordinates[1].substring(1)) - 1;//col2

        return coordinates;
    }
    /**
     * Metoda zwraca prawdę jeśli koordynaty są poprawne
     * Poprawne koordynaty:
     * -wszystkie wartości pomiędzy 0, a 9
     * -kolumny mają tę samą wartość, a różnica pomiędzy wartościami wierszy wynosi length - 1
     * -wiersze mają tę samą wartość, a różnica pomiędzy wartościami kolumn wynosi length - 1
     *
     * @param coordinates
     * @param length
     * @return
     */
    private boolean arePlaceCoordinateValid(int[] coordinates, int length) {
        if (coordinates[0] < 0 || coordinates[2] < 0 || coordinates[1] < 0 || coordinates[3] < 0
                || coordinates[0] > 9 || coordinates[2] > 9 || coordinates[1] > 9 || coordinates[3] > 9) {
            return false;
        }
        /*
         * jeśli wiersze/ kolumny są te same a odstęp pomiędzy koordynatami wynoszą dlugosc to zwróć prawdę*/
       if (coordinates[0] == coordinates[2] && (Math.abs(coordinates[1] - coordinates[3]) == length - 1 || Math.abs(coordinates[1] - coordinates[3]) == length - 1)) {
            return true;
        } else if (coordinates[1] == coordinates[3] && (Math.abs(coordinates[0] - coordinates[2]) == length - 1 || Math.abs(coordinates[0] - coordinates[2]) == length - 1)) {
            return true;
        } else {
            return false;
        }
    }

    private void setCoordinates(Map map, int[] coordinates) {
        //jesli wiersze maja ten sam numer to wykonaj zmień pola poziomo
        //this.coordinates = new int[lenght][2];
        int j = 0;
        if (coordinates[0] == coordinates[2]) {
            for (int i = Math.min(coordinates[1], coordinates[3]); i <= Math.max(coordinates[1], coordinates[3]); i++) {
                map.setField(coordinates[0], i, 'O');
                this.shipCoordinates[j][0] = coordinates[0];
                this.shipCoordinates[j][1] = i;
                //this.coordinates.add({ coordinates[0], i});
                j += 1;
            }
        }
        if (coordinates[1] == coordinates[3]) {
            for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
                map.setField(i, coordinates[1], 'O');
                this.shipCoordinates[j][0] = i;
                this.shipCoordinates[j][1] = coordinates[1];
                j += 1;
            }
        }
    }

    public void displayCoordinates() {
        for (int i = 0; i < shipCoordinates.length; i++) {
            System.out.println(Arrays.toString(shipCoordinates[i]));
        }
    }

    public boolean isShipHit(int[] coords) {
        for (int i = 0; i < shipCoordinates.length; i++) {
            if (coords[0] == shipCoordinates[i][0] && coords[1] == shipCoordinates[i][1]) {
                hp -= 1;
                //System.out.println(hp);
                /*if (isShipSunk()) {
                    System.out.println("You sank a ship!");
                } else {
                    System.out.println("You hit a ship!");
                }*/
                return true;
            }
        }
        return false;
    }
    public boolean isShipSunk() {
        if (hp == 0) {
            return true;
        }
        return false;
    }
}
