package com.example.parth.pman;

public class Task {
    String id;
    private String taskname;
    private String stats;
    private String colr;
    private String dte;
    private String prrity;
    private String descrptn;


    public Task() {

    }

    public Task(String id, String taskname, String stats, String dte, String descrptn, String prrity) {
        this.id = id;
        this.taskname = taskname;
        this.stats = stats;
        this.dte = dte;
        this.prrity = prrity;
        this.descrptn = descrptn;

    }

//    public Task(String id,String taskname){
//        this.id = id;
//        this.taskname = taskname;
//    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskname() {
        return this.taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getStats() {
        return this.stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }


    public String getDte() {
        return this.dte;
    }

    public void setDte(String dte) {
        this.dte = dte;
    }

    public String getPrrity() {
        return this.prrity;
    }

    public void setPrrity(String prrity) {
        this.prrity = prrity;
    }

    public String getDescrptn() {
        return this.descrptn;
    }

    public void setDescrptn(String descrptn) {
        this.descrptn = descrptn;
    }
}
