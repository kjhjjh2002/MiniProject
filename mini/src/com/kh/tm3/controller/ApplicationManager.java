package com.kh.tm3.controller;

import java.util.ArrayList;

public class ApplicationManager {
    private static ApplicationManager applicationManager = null;

    private ApplicationManager(){}

    public static ApplicationManager getInstance(){
        if(applicationManager == null)
            applicationManager = new ApplicationManager();

        return applicationManager;
    }

    public ArrayList<Boolean> isChecked = new ArrayList<>();

    public ArrayList<String> travelCourse = new ArrayList<>();

    private String travelDestination;
    private String coursePath;
    private String isCheckedPath;

    public String getTravelDestination() {
        return travelDestination;
    }

    public void setTravelDestination(String travelDestination) {
        this.travelDestination = travelDestination;
    }

    public String getCoursePath() {
        return coursePath;
    }

    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
    }

    public String getIsCheckedPath() {
        return isCheckedPath;
    }

    public void setIsCheckedPath(String isCheckedPath) {
        this.isCheckedPath = isCheckedPath;
    }
}
