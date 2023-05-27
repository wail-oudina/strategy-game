package main.units;

import main.Resource;

import java.util.List;

public enum UnitJobs {
    BUTCHER(Resource.WOOD),
    FARMER(Resource.FOOD),
    MINER(Resource.STONE,Resource.GOLD);

    private final List<Resource> requiredResources;

    UnitJobs(Resource... requiredResources) {

        this.requiredResources = List.of(requiredResources);

    }
    public List<Resource> getRequiredResources() {
        return requiredResources;
    }

}

