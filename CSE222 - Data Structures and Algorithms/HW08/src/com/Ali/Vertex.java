package com.Ali;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
    protected int ID;   //index
    protected String label;
    protected double weight;
    protected Map<String , String> properties = new HashMap<>();

    public Vertex(int ID ,String label , double weight){
        this.ID = ID;
        this.label = label;
        this.weight = weight;
    }

    public int getID() {
        return ID;
    }

    public double getWeight() {
        return weight;
    }

    public String getLabel() {
        return label;
    }

    public boolean addProperty(String key, String value){
        if(properties.get(key) == null) {
            properties.put(key, value);
            return true;
        }
        System.out.println("This property is already given!!");
        return false;
    }

}
