package battleship;

public class Player {
    String name;
    Map map = new Map();
    Map enemyMap = new Map();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
    }

    public Map getEnemyMap() {
        return enemyMap;
    }

    public Fleet getFleet() {
        return fleet;
    }

    Fleet fleet = new Fleet();

}
