package battleship;

import java.util.Scanner;

public class Map {

    private char[][] map = new char[10][10];

    public Map() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = '~';
            }
        }
    }

    public void displayMap() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char) (i + 65));
            for (char field : map[i]) {
                System.out.print(" " + field);
            }
            System.out.println();
        }
    }

    public char getField(int i, int j) {
        return map[i][j];
    }

    /**
     * Ustawia pole na mapie o parametrach [i][j] na znak 'c'
     * @param i
     * @param j
     * @param c
     */
    public void setField(int i, int j, char c) {
        map[i][j] = c;
    }

    /**
     * Funkcja pobiera skrajne koordynaty statku i sprzawdza czy w ich obrębie są same "~" - tzn nie ma innych statków
     * 1 pętla for: wylicza wiersz -1 od skrajnego (nie mniejszy niz 0) aż do wiersze +1 od skrajnego (nie wieksza niż 9)
     * 2 petla for: wylicza kolumne -1 od skrejnej (nie mniejsza niż 0) aż do kolumny +1 od skrajnej (nie wieksza niż 9)
     * @param coordinates
     * @return
     */
    public boolean checkNeighbourhood(int[] coordinates) {
            for (int i = Math.min(coordinates[0], coordinates[2]) > 0 ? Math.min(coordinates[0], coordinates[2]) - 1 : Math.min(coordinates[0], coordinates[2]);
                 i <= (Math.max(coordinates[0], coordinates[2]) < 9 ? Math.max(coordinates[0], coordinates[2]) + 1 : Math.max(coordinates[0], coordinates[2]));
                 i++) {
                for (int j = Math.min(coordinates[1], coordinates[3]) > 0 ? Math.min(coordinates[1], coordinates[3]) - 1 : Math.min(coordinates[1], coordinates[3]);
                     j <= (Math.max(coordinates[1], coordinates[3]) < 9 ? Math.max(coordinates[1], coordinates[3]) + 1 : Math.max(coordinates[1], coordinates[3]));
                     j++) {
                    if (getField(i, j) != '~') {
                        return false;
                    }
                }
            }
        return true;
    }

    /**
     * Pobiera skrajne koordynaty statku i wypełnia mapę między tymi koordynayami "0"
     * @param coordinates
     */
  /*  public void placeShip(int[] coordinates) {
        //jesli wiersze maja ten sam numer to wykonaj zmień pola poziomo
        if (coordinates[0] == coordinates[2]) {
            for (int i = Math.min(coordinates[1], coordinates[3]); i <= Math.max(coordinates[1], coordinates[3]); i++) {
                setField(coordinates[0], i, 'O');
            }
        }
        if (coordinates[1] == coordinates[3]) {
            for (int i = Math.min(coordinates[0], coordinates[2]); i <= Math.max(coordinates[0], coordinates[2]); i++) {
                setField(i, coordinates[1], 'O');
            }
        }
    }
*/
}
