package main.units;


import main.Player;
import main.Resource;
import main.map.GameMap;
import main.tools.Tool;


public class Unit {
    public int speed,food_cost;
    public int x,y;
    public boolean isExpert;
    public int xp;
    public GameMap gm;
    public Player player;
    public Tool tool;
    private int xpMultiplier;


    public Unit(int x, int y, int speed, int food_cost,Tool tool,GameMap gm,Player player) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.food_cost = food_cost;
        this.isExpert = false;
        this.tool = tool;
        this.xp = 0;
        this.gm = gm;
        this.player = player;
        this.xpMultiplier = 1;
    }



    public void addToMap(){
        if (this.tool != null) {
            this.gm.map[this.x][this.y] = this.tool.type.jobname.toUpperCase().charAt(0);
        }else {
            this.gm.map[this.x][this.y] = 'U';
        }

    }


    public int[] getClosestResource() {
        int[] closest = new int[3];
        int closestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < gm.width; i++) {
            for (int j = 0; j < gm.height; j++) {
                char tile = gm.getTile(i, j);

                if (tile != 'U' && tile != '0') {
                    Resource resource = Resource.getResourceByMapLabel(tile);

                    if (resource != null && tool.type.job.getRequiredResources().contains(resource)) {
                        int distance = Math.abs(x - i) + Math.abs(y - j);
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closest[0] = i;
                            closest[1] = j;
                            closest[2] = closestDistance;
                        }
                    }
                }
            }
        }
        System.out.println("distance: " + closest[2]);
        return closest;
    }
    public void incrementXp(){
        this.xp += this.xpMultiplier;
        if (this.xp >= 10){
            this.isExpert = true;
        }
    }

    public boolean canMove(){
        return this.player.Inventory.get(Resource.FOOD) >= this.food_cost;
    }
    public void decreaseFood(){
        this.player.Inventory.put(Resource.FOOD, this.player.Inventory.get(Resource.FOOD) - this.food_cost);
    }
    public void moveTo(int x, int y,int distance) {
        if (distance == 0) {
            System.out.println("Already at the resource");
            return;
        }
        if ( speed < distance) {
            System.out.println("Not enough speed to move that far");
            return;
        }

        for ( int i = 0; i < Math.min(this.speed,distance); i++) {
            if (this.canMove()) {
                if (this.x < x) {
                    this.x++;
                } else if (this.x > x) {
                    this.x--;
                } else if (this.y < y) {
                    this.y++;
                } else if (this.y > y) {
                    this.y--;
                }
                this.decreaseFood();
                System.out.println("harvested " + Resource.getResourcebyTile(this.x,this.y,this.gm));
            }else{
                System.out.println("Not enough food to move");
                break;
            }
        }
    }
    public void harvestResource(){
        this.incrementXp();
        Resource rs = Resource.getResourcebyTile(this.x,this.y,this.gm);
        this.player.Inventory.put(rs,this.player.Inventory.get(rs)+1);
        this.gm.map[this.x][this.y] = 0;
    }



}

