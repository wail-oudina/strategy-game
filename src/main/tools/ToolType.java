package main.tools;

import main.units.UnitJobs;

public enum ToolType {
    AXE(UnitJobs.BUTCHER,"Butcher"),
    HOE(UnitJobs.FARMER,"Farmer"),
    PICKAXE(UnitJobs.MINER,"Miner");

    public final String jobname;
    public final UnitJobs job;

    ToolType(UnitJobs job,String jobname){
        this.jobname = jobname;
        this.job = job;
    }

}
