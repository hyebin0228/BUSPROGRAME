package exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.Bus_dao;
import dao.Bus_dto;

public class seatSelect {
	//ps : primeSeat, gs : genSeat
	static String[] ps = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28"};
	static String[] gs = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45"};
	
	public String seatGrade(String bNo, String start, String des, String stt) {
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_Select(bNo, start, des, stt);
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		for(int i=0; i<dtos.size(); i++) {
			dto = dtos.get(i);
			if("우등".equals(dto.getbGrade())) {
				System.out.println("<우등 버스입니다.>");
				primeSeat(bNo, start, des, stt);
			}
			else if("일반".equals(dto.getbGrade())) {
				System.out.println("<일반 버스입니다.>");
				genSeat(bNo, start, des, stt);
			}
		}
		return dto.getbGrade();
	}
	
	public void primeSeat(String bNo, String start, String des, String stt) {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_seatShow(bNo, start, des, stt);
		
		dto = dtos.get(0);
		String[] seatNo = dto.getSeat().split("/");

		//보유 좌석 입력
		for(int i=0; i<ps.length; i++) {
			for(int j=0; j<seatNo.length; j++) {
				if(ps[i].equals(seatNo[j])) {
					ps[i] = "✕✕";
				}
			}
		}
		
		for(int i=0;i<ps.length; i ++) {
			//줄바꿈
			if(i==26|i==27) {}
			else if(i%3==0)
				System.out.println();
			else if(i%3==2)
				System.out.print("    ");
			//좌석 print
			if(i<=8)
				if(ps[i].equals("✕✕"))
					System.out.printf("[%s]", ps[i]);
				else
					System.out.printf("[0%s]", ps[i]);
			else
				System.out.printf("[%s]", ps[i]);
		}
	}
	
	
	public void genSeat(String bNo, String start, String des, String stt) {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_seatShow(bNo, start, des, stt);
		dto = dtos.get(0);
		
		String[] seatNo = dto.getSeat().split("/");
		
		for(int i=0; i<gs.length; i++) {
			//보유 좌석 표시
			for(int j=0; j<seatNo.length; j++) {
				if(gs[i].equals(seatNo[j])) {
					gs[i] = "✕✕";
				}
			}
		}
		
		for(int i=0;i<gs.length; i ++) {
			//줄바꿈
			if(i==42|i==44) {}
			else if(i%4==0)
				System.out.println();
			else if(i%2==0)
				System.out.print("    ");
			//좌석 print
			if(i<=8)
				if(gs[i].equals("✕✕"))
					System.out.printf("[%s]", gs[i]);
				else
					System.out.printf("[0%s]", gs[i]);
			else
				System.out.printf("[%s]", gs[i]);
		}
	}
	
	
	public String seatChoose(String bGrade, int adult, int student, int child) {
		Scanner scn = new Scanner(System.in);
		custSelect cs = new custSelect();
		System.out.println("\n\n※한 좌석씩 입력하여 주세요※");
		String sChoose = "";
		int custTotal = adult + student + child;
		int ct = custTotal;
		
		if("우등".equals(bGrade)) {
			int n = 0;
			while(true) {
				if(cs.cust[n] != 0) {
					System.out.println("\n선택해야 할 좌석 수 : "+custTotal);
					System.out.printf("%s 좌석을 선택하여 주세요. >> ", cs.st_cust[n]);
					int num = scn.nextInt();
					cs.cust[n]--;
					
					String stNum = Integer.toString(num);
					if(ps[num-1].equals(stNum)) {
						System.out.printf("%d번 좌석을 선택하셨습니다.\n",num);
						sChoose += ps[num-1] + "/";
						custTotal--;
					}else if("✕✕".equals(ps[num-1])){
						System.out.println("\n선택할 수 없는 좌석입니다.\n다시 입력하여 주세요!");
						cs.cust[n]++;
					}  
				}else
					n++;
				if(custTotal == 0) break;
			}
		}else {
			int n = 0;
			while(true) {
				if(cs.cust[n] != 0) {
					System.out.println("\n선택해야 할 좌석 수 : "+custTotal);
					System.out.printf("%s 좌석을 선택하여 주세요. >> ", cs.st_cust[n]);
					int num = scn.nextInt();
					cs.cust[n]--;
					
					String stNum = Integer.toString(num);
					if(gs[num-1].equals(stNum)) {
						System.out.printf("%d번 좌석을 선택하셨습니다.\n",num);
						sChoose += gs[num-1] + "/";
						custTotal--;
					}else if("✕✕".equals(gs[num-1])){
						System.out.println("\n선택할 수 없는 좌석입니다.\n다시 입력하여 주세요!");
						cs.cust[n]++;
					}  
				}else
					n++;
				if(custTotal == 0) break;
			}
		}
		
		return sChoose;
	}

}
