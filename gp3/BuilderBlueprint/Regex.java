package gp3.BuilderBlueprint;

import java.util.*;
public class Regex {
private String pattern;

public Regex(){
    this.pattern = "";
}

public Regex(List<String> components) {
    this.pattern = "";
    if (components != null){
        for (int i = 0; i < components.size(); i++){
            pattern = pattern + components.get(i);  // Concatenation creates NEW String each time
        }
    }
}

public String getPattern() {
    return pattern;
}

    public void setPattern(){
        this.pattern = "";
    }
}