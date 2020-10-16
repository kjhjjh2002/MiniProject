package com.kh.tm3.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CityCourse {
	Scanner sc = new Scanner(System.in);
	BufferedWriter bw = null;

	public CityCourse() {

	}

	public void Jeju(File file) {

		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("제주도 중문·서귀포-윈드 1947-뽀로로 앤 타요 테마파크-카멜리아 힐-피규어 뮤지엄-소인국 테마파크₩\n"
					+ "제주도 조천·구좌-김녕요트투어-선녀와나무꾼-에코랜드-제주레일바이크₩\n" + "제주도 애월·한림-도치돌 알파카 목장-한림 공원-유리의 성-마법의 숲");
			bw.newLine();

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

	}

	public void Gangneung(File file) {
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("강릉-드라이브 명소 헌화로-정동진 레일 바이크-오죽헌 숲길-주문진 해수욕장 해변-테라로사 커피 공장");

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

	}

	public void Busan(File file) {
		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("부산-임랑 해수욕장-아홉산숲-오랑대 공원-해동 용궁사-국제 시장-광안대교");

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

	}

	public void Yeosu(File file) {

		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("여수-향일암-자산 공원 해상 케이블카-오동도-해변 산책로-소호동동다리-갯벌 노을 마을");

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

	}

	public void Sokcho(File file) {

		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("속초-등대 해수욕장-영금정-설악산 권금정-동명항");

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

	}

	public void Daegu(File file) {

		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("대구-대구사격장-달성 공원-대구예술 발전소-이월드-비슬산");

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

	}

	public void Jeonju(File file) {

		try {
			bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("전주-전주 한옥마을-객리단길-남부 시장-덕진 공원-전동 성당-영화의 거리");

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

	}

}
