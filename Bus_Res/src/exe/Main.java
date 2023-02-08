package exe;

import java.util.ArrayList;
import java.util.Scanner;

import dao.*;

public class Main {

	static int menu() {
		Scanner scn = new Scanner(System.in);
		int input = 0;

		System.out.println("┌───────────────────────────────────────┐");
		System.out.println("│					│");
		System.out.println("│           🚌버스예매 프로그램🚌		│");
		System.out.println("│					│");
		System.out.println("│					│");
		System.out.println("│       [1] 버스 예매			│");
		System.out.println("│       [2] 버스 예매 조회			│");
		System.out.println("│       [3] 버스 예매 변경			│");
		System.out.println("│       [4] 버스 예매 취소			│");
		System.out.println("│       [5] 프로그램 종료			│");
		System.out.println("│					│");
		System.out.println("│					│");
		System.out.println("│					│");
		System.out.println("└───────────────────────────────────────┘\n");
		System.out.print("🚌원하시는 기능의 번호를 입력하여 주세요🚌\n  입력 >> ");
		input = scn.nextInt();
		scn.nextLine();
		
		System.out.println("\n============================================");
		return input;
	}
	
	public static void reservation() {
		StartDes sd = new StartDes();
		busSelect bs = new busSelect();
		custSelect cs = new custSelect();
		seatSelect ss = new seatSelect();
		payment pay = new payment();
		String start, des, bNo, stt, grade, sChoose;	//stt = startTime
		int custTotal = 0;

		System.out.println("================== 버스 예매 ==================");
		
		start = sd.Start();
		des = sd.Des();
		bNo = bs.busSelect(start, des);
		stt = bs.startT();
		custTotal = cs.customer(bNo);
		grade = ss.seatGrade(bNo,start, des, stt);
		sChoose = ss.seatChoose(grade, cs.adult, cs.student, cs.child);
		pay.payInfo(bNo, start, des, sChoose, stt, cs.adult, cs.student, cs.child, custTotal);
		
	}
	
	public static void check() {
		Scanner scn = new Scanner(System.in);
		payment pay = new payment();
		String yn = "";
		String ticketNo;
		while(true) {
			System.out.println("================== 예매 조회 ==================\n");
			System.out.print("티켓 번호 : ");
			ticketNo = scn.nextLine();
			
			if("".equals(ticketNo)) {
				System.out.println("null");
			}else
				pay.tCheck(ticketNo);
			System.out.print("티켓 조회를 마치겠습니까(y/n) ?   >>");
			yn = scn.nextLine();
			if("y".equals(yn) | "Y".equals(yn)) break;

		}
		
	}
	
	public static void change() {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		busSelect bs = new busSelect();
		custSelect cs = new custSelect();
		seatSelect ss = new seatSelect();
		payment pay = new payment();
		
		String ticketNo, bNo, stt, grade, sChoose;
		String yn = "", sget = "", sset = "";
		int custTotal;
		
		System.out.println("================== 예매 변경 ==================\n");
		System.out.print("티켓 번호 : ");
		ticketNo = scn.nextLine();
		dtos = dao.ticket_Select2(ticketNo);
		
		dto = dtos.get(0);
		
		if(ticketNo.length() == 14) 
			sget = "/"+ticketNo.substring(8, 10);
		else 
			sget = "/"+ticketNo.substring(8,9);
		sset = dto.getSeat().replaceFirst(sget, "");
		
		pay.tCheck(ticketNo);
		
		while(true) {
			System.out.print("예매 변경하시겠습니까(y/n) ?   >> ");
			yn = scn.nextLine();
			
			if(yn.equals("N") || yn.equals("n")) {
				break;
			} else if(yn.equals("Y") || yn.equals("y")) {
				dto.setsCount(dto.getsCount()+1);
				dto.setSeat(sset);
				
				dao.Seat_Update(dto);
				dao.sCount_Update(dto);
				dao.ticket_Update(dto);
				dao.ticket_Delete(ticketNo);
			}else {
				System.out.println("다시 입력하시오.");
				break;
			}

			System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++");
			System.out.printf("출발지 : %s\t\t도착지 : %s\n\n",dto.getbStart(), dto.getbDes());
			
			bNo = bs.busSelect(dto.getbStart(), dto.getbDes());
			stt = bs.startT();
			custTotal = cs.custChange(dto.getAdult(), dto.getStudent(), dto.getChild());
			grade = ss.seatGrade(bNo, dto.getbStart(), dto.getbDes(), stt);
			sChoose = ss.seatChoose(grade, dto.getAdult(), dto.getStudent(), dto.getChild());
			pay.payInfo(bNo,dto.getbStart(), dto.getbDes(), sChoose, stt,  dto.getAdult(), dto.getStudent(), dto.getChild(), custTotal);
			
			System.out.println("예매 변경이 완료되었습니다.");
			break;
		}
	}
	
	public static void cancel() {
		Scanner scn = new Scanner(System.in);
		Bus_dao dao = new Bus_dao();
		payment pay = new payment();
		
		String ticketNo = "";
		String yn;
		
		while(true) {
			System.out.println("================== 예매 취소 ==================\n");
			System.out.print("티켓 번호 : ");
			ticketNo = scn.nextLine();
			
			if("".equals(ticketNo)) {
				System.out.println("입력이 없습니다.");
			}else
				pay.tCheck(ticketNo);

			System.out.print("삭제하시겠습니까(y/n) ?   >> ");
			yn = scn.nextLine();
			
			if(yn.equals("Y") || yn.equals("y")) {
				dao.ticket_Delete(ticketNo);
				System.out.println("티켓이 취소되었습니다.");
			}
			
			System.out.print("\n\n예매 취소를 진행하시겠습니까(y/n) ?   >> ");
			yn = scn.nextLine();
			
			if(yn.equals("N") || yn.equals("n")) {
				break;
			}
		}
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int menu = 0;
		Exit : while(true) {
			menu = menu();
			
			switch(menu) {
			case 1 : 
				reservation();
				break;
			case 2 : 
				check();
				break;
			case 3 : 
				change();
				break;
			case 4 : 
				cancel();
				break;
			case 5 :
				System.out.print("프로그램을 종료하시겠습니까(y/n) ?   >>");
				String yn = scn.nextLine();
				if(yn.equals("Y") || yn.equals("y"))
					break Exit;
			default :
				System.out.println("해당 번호가 없습니다. 다시 입력하여 주세요.");
			}

		}
	}
}
