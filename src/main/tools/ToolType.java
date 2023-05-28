package main.tools;

import main.Resource;
import main.tools.ToolType;

import java.util.List;

public enum ToolType {
    AXE("Butcher",Resource.WOOD),
    HOE("Farmer",Resource.FOOD),
    PICKAXE("Miner",Resource.STONE,Resource.GOLD);

    private final List<Resource> requiredResources;
    private final String jobname;
    ToolType(String jobname,Resource... requiredResources) {
        this.jobname = jobname;
        this.requiredResources = List.of(requiredResources);

    }
    public List<Resource> getRequiredResources() {
        return requiredResources;
    }
    public String getJobname() {
        return jobname;
    }


}

