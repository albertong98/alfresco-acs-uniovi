package org.uniovi.dto;

public class Degree {
    public String id;
    public String name;
    public String center;
    public Integer years;

    public Degree(){}
    public Degree(String id, String name, Integer years, String center) {
        this.id = id;
        this.name = name;
        this.years = years;
        this.center = center;
    }
}
