package main.map;

import main.Resource;

import java.util.*;

public class GameMap {
    public int width;
    public int height;
    public int RESSOURCE_RATIO = 5;
    public int RESSOURCE_VALUES_LENGTH = Resource.values().length;
    public Map<Resource, Integer>  RESSOURCE_COUNT;
    public Character[][] map;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new Character[width][height];
        this.RESSOURCE_COUNT = new TreeMap<>();


    }


    public Character[][] getMap() {
        return this.map;
    }
    public void setMap(int x, int y, Character value) {
        this.map[x][y] = value;
    }
    public Character getTile(int x, int y) {
        return this.map[x][y];
    }
    public void printMap() {
        System.out.print("# ");
        for (int x = 0; x < this.width; x++) {
            System.out.print(x+" ");
        }
        System.out.println();
        for (int y = 0; y < this.height; y++) {
            System.out.print(y + " ");
            for (int x = 0; x < this.width; x++) {
                System.out.print(this.map[x][y]+" ");
            }
            System.out.println();
        }

    }
    public void generate(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++){
                this.map[x][y] = 0;
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++){
                if (Math.random() * 100 < RESSOURCE_RATIO*RESSOURCE_VALUES_LENGTH ){
                    Resource resource = Resource.values()[(int)(Math.random() * Resource.values().length)];
                    this.map[x][y] = resource.textmaplabel ;
                    this.RESSOURCE_COUNT.put(resource, this.RESSOURCE_COUNT.getOrDefault(resource, 0) + 1);
                }
            }
        }

    }
    public boolean ResourcesDepleted(){
        for (Resource resource : Resource.values()) {
            if (this.RESSOURCE_COUNT.get(resource) != 0){
                return false;
            }
        }
        return true;
    }
}
