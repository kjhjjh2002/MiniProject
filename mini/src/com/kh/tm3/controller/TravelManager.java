package com.kh.tm3.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.kh.tm3.model.CityCourse;

public class TravelManager {
	
	 Scanner sc = new Scanner(System.in);
	    BufferedWriter bw = null;

	    String travelTitle;
	    public TravelManager() {

	    }

	    public void mainMenu() {

	        System.out.print("여행 제목 : ");
	        travelTitle = sc.nextLine();

	        String pathName = "src" + File.separator + travelTitle;
	        mkdTitle(pathName);

	        String fileName = "course.txt";
	        mkdCourse(pathName, fileName);

	    }

	    public void mkdTitle(String pathName) {
	        File file = new File(pathName);
	        file.mkdir();
	    }

	    public void mkdCourse(String pathName, String fileName) {
	        File file = new File(pathName, fileName);
	        try {
	            setTravel(file);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }


	    public void setTravel(File file) throws IOException {

	        System.out.println("1. 제주도");
	        System.out.println("2. 강릉");
	        System.out.println("3. 여수");
	        System.out.println("4. 부산");
	        System.out.println("5. 속초");
	        System.out.println("6. 대구");
	        System.out.println("7. 전주");
	        System.out.println("8. 여행지 추가");
	        int num = sc.nextInt();

	        if (num == 8) {
	            System.out.println("=================");
	            System.out.println("여행지 추가 : ");
	            String str = sc.nextLine();
	            String write = sc.nextLine();
	            System.out.println("코스 경로 추가 (-) 으로 구분 : ");
	            String add = sc.nextLine();


	            try {
	                bw = new BufferedWriter(new FileWriter(file, true));
	                bw.write(write+"-");
	                bw.write(add);

	            } catch (IOException e) {
	                e.printStackTrace();
	            } finally {
	                if (bw != null) {
	                    try {
	                        bw.close();
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }

	        } else {
	            CityCourse cc = new CityCourse();

	            switch (num) {
	                case 1:
	                    cc.Jeju(file);
	                    break;
	                case 2:
	                    cc.Gangneung(file);
	                    break;
	                case 3:
	                    cc.Yeosu(file);
	                    break;
	                case 4:
	                    cc.Busan(file);
	                    break;
	                case 5:
	                    cc.Sokcho(file);
	                    break;
	                case 6:
	                    cc.Daegu(file);
	                    break;
	                case 7:
	                    cc.Jeonju(file);
	                    break;
	            }
	        }
	    }
}


