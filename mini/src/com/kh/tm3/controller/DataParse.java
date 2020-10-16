package com.kh.tm3.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataParse {

	public String readFile(String fileName){

        String fileText = null;

        String folderName = new TravelManager().travelTitle;
        File file = new File(System.getProperty("user.dir")+"/src/"+"Test1/"+fileName+".txt");
        ApplicationManager.getInstance().setCoursePath(System.getProperty("user.dir")+"/src/"+"Test1/course.txt");
        ApplicationManager.getInstance().setIsCheckedPath(System.getProperty("user.dir")+"/src/"+"Test1/check.txt");
        String temp;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            fileText = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileText;
      
    }




    public String[] parsingData(String data){
        //System.out.println(data);
        return data.split("-");
    }
}
