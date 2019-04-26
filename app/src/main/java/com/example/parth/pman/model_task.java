package com.example.parth.pman;

public class model_task {
    static String name;

    public model_task(String name) {
        this.name = name;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
