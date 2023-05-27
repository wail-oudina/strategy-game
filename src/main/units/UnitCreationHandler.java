package main.units;

import main.Player;
import main.Resource;
import main.map.GameMap;
import main.tools.Tool;
import main.tools.ToolType;

public class UnitCreationHandler {
    public Player player;
    public GameMap gm;
    public UnitCreationHandler(Player player,GameMap gm){
        this.player = player;
        this.gm = gm;
    }

    public void createUnit(GameMap gm){

        int x,y ;
        while(true) {

            //x = (int) (Math.random() * gm.width);
            //y = (int) (Math.random() * gm.height);
            x= 0;
            y= 0;

            System.out.println("assigned x: " + x + " assigned y: " + y);
            if ( gm.getTile(x, y) != 0 ){
                break;
            }

        }
        gm.setMap(x,y,'U');
        System.out.println("Created unit at: " + x + "," + y);

        if ( this.player.Inventory.get(Resource.FOOD) >= 1){

            Unit unit = new Unit(x,y,1,1,new Tool(ToolType.HOE),gm,this.player);
            this.player.addUnit(unit);
            unit.addToMap();
            this.player.Inventory.put(Resource.FOOD,this.player.Inventory.get(Resource.FOOD)-1);
        }
        else{
            System.out.println("Not enough food to create a unit");
        }

    }
    public void RunUnits(){
        for (Unit unit : this.player.Units) {
            System.out.println("Unit at: " + unit.x + "," + unit.y + "\n");
            int[] closestResource = new int[3];
            closestResource = unit.getClosestResource();
            unit.moveTo(closestResource[0],closestResource[1],closestResource[2]);
            if ( unit.x == closestResource[0] && unit.y == closestResource[1]){
                unit.harvestResource();
            }
        }

    }
}
