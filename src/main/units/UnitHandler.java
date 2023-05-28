package main.units;

import main.Player;
import main.map.GameMap;
import main.tools.Tool;


public class UnitHandler {
    public Player player;
    public GameMap gm;
    public UnitHandler(Player player, GameMap gm){
        this.player = player;
        this.gm = gm;
    }

    public void createUnit(GameMap gm,Tool tool){

        int x,y ;
        while(true) {
            x = (int) (Math.random() * gm.width);
            y = (int) (Math.random() * gm.height);
            if ( gm.getTile(x, y) == 0 ){
                break;
            }
        }
        Unit unit = new Unit(x,y,3,1,tool,gm,this.player);
        this.player.addUnit(unit);

        System.out.println("Created unit at: " + x + "," + y);

    }
    public void RunUnits(){
        for (Unit unit : this.player.Units) {
            unit.printInfo();
            int[] closestResource = new int[3];
            closestResource = unit.getClosestResource();
            unit.moveTo(closestResource[0],closestResource[1],closestResource[2]);
            if ( unit.x == closestResource[0] && unit.y == closestResource[1]){
                unit.harvestResource();
            }
        }

    }
}
