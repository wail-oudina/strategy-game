package main.tools;

import java.util.ArrayList;
import java.util.List;

public class Tool {
    public ToolType type;
    int level;
    public static List<Tool> tools = new ArrayList<>();
    //constructor
    public Tool(ToolType type, int level){
        this.type = type;
        this.level = level;
        tools.add(this);
    }
    public static int getlowestlevel(ToolType type){
        int lowestlevel = 0;
        for (Tool tool : tools){
            if (tool.type == type){
                if (tool.level < lowestlevel){
                    lowestlevel = tool.level;
                }
            }
        }
        return lowestlevel;
    }
}
