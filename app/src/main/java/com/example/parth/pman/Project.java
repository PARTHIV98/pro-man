package com.example.parth.pman;

public class Project {


    String id;
    private String pname;

    public Project() {

    }

    public Project(String id, String pname) {
        this.id = id;
        this.pname = pname;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return pname;
    }

    public void setProjectname(String pname) {
        this.pname = pname;
    }


           }


