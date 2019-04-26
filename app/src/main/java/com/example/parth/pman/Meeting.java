package com.example.parth.pman;

public class Meeting {
    String id;
    private String mname;
    private String mds;

    private String mdte;



    public Meeting() {

    }

    public Meeting(String id, String mname, String mds, String mdte) {
        this.id = id;
        this.mname = mname;
        this.mds = mds;
        this.mdte = mdte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMds() {
        return mds;
    }

    public void setMds(String mds) {
        this.mds = mds;
    }

    public String getMdte() {
        return mdte;
    }

    public void setMdte(String mdte) {
        this.mdte = mdte;
    }
}
