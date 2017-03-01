package com.example.nikola.toastcatcher.DataLayer;


public class LogItem {

    private String time;
    private String message;
    private String sourcePackage;

    public LogItem(String time, String message, String sourcePackage){
        this.time = time;
        this.message = message;
        this.sourcePackage = sourcePackage;
    }

    public String getTime(){
        return time;
    }
    public String getMessage(){
        return message;
    }
    public String getSourcePackage(){
        return sourcePackage;
    }
}
