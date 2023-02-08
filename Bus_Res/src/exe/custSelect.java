package exe;

import java.util.Scanner;

import dao.Bus_dao;
import dao.Bus_dto;

public class custSelect {
	
	public int adult;
	public int student;
	public int child;
	static int[] cust = {0, 0, 0};
	static String[] st_cust = {"어른", "청소년", "아동"};
	
	
	public int customer(String bNo) {
		Scanner scn = new Scanner(System.in);
		Scanner yn_scn = new Scanner(System.in);
		String yn = null;
		
		adult=0;
		student=0;
		child=0;
		
		while(true) {
			System.out.println("총 탑승인의 수를 입력하여 주십시오.\n");
			
			System.out.print("어른 : ");
			adult = scn.nextInt();
			cust[0] = adult;
			
			System.out.print("청소년 : ");
			student = scn.nextInt();
			cust[1] = student;
			
			System.out.print("아동 : ");
			child = scn.nextInt();
			cust[2] = child;
			System.out.println("+++++++++++++++++총 탑승인 수 확인+++++++++++++++++");
			System.out.printf("성인 : %d, 청소년 : %d, 아동 : %d",adult, student, child);
			System.out.print("\n\n좌석을 선택하시겠습니까(y/n) ?   >> ");
			yn = yn_scn.nextLine();
			
			if(yn.equals("y") || yn.equals("Y")) {
				break;
			}
		}
		int custTotal = adult + student + child;
		return custTotal;
	}
	
	public int custChange(int adt, int stu, int chd) {
		int custTotal = adt + stu + chd;
		cust[0] = adt;
		cust[1] = stu;
		cust[2] = chd;
		
		return custTotal;
	}
}
