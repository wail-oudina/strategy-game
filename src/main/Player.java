package main;


import main.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Player {

    public Map<Resource,Integer> Inventory;
    public List<Unit> Units;
    public Player(){
        this.Inventory = new TreeMap<>();
        this.Units = new ArrayList<>();
        for (Resource resource : Resource.values()) {
            this.Inventory.put(resource,1500);
        }


    }
    public void addUnit(Unit unit){
        this.Units.add(unit);
    }
    public void printstats(){
        System.out.println("Inventory:");
        for (Resource resource : Resource.values()) {
            System.out.print(resource.name() + ": " + this.Inventory.get(resource)+", ");
        }

        System.out.println("\nUnits:");
        if(this.Units.size() == 0){
            System.out.println("No units");
            return;
        }
        for (Unit unit : this.Units) {
            System.out.println("Unit "+ unit.getJob()  +" at: " + unit.x + "," + unit.y + "\n");
        }
    }
}
