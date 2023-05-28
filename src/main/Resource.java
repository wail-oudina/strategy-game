package main;

import main.map.GameMap;

public enum Resource {
    GOLD("Gold",'G',5,10,10),
    FOOD("Food",'F',5,10,10),
    WOOD("Wood",'W',5,10,10),
    STONE("Stone",'S',5,10,10);
    public final String label;
    public final Character textmaplabel;
    public final int unitcost,unitfactorycost,toolfactorycost;
    Resource(String label, Character maplabel,int unitcost,int unitfactorycost,int toolfactorycost){
        this.label = label;
        this.textmaplabel = maplabel;
        this.unitcost = unitcost;
        this.unitfactorycost = unitfactorycost;
        this.toolfactorycost = toolfactorycost;

    }

    public static Resource getResourceByMapLabel(Character maplabel){
        for (Resource resource : Resource.values()) {
            if (resource.textmaplabel == maplabel) {
                return resource;
            }
        }
        return null;
    }
    public static Resource getResourcebyTile(int x,int y,GameMap gm){
        return getResourceByMapLabel(gm.getTile(x,y));
    }


}
