package exe;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Bus_dao;
import dao.Bus_dto;

public class payment {
	static String[] tNo = new String[45];
	public void payInfo(String bNo, String st, String ds, String sChoose, String stt, int adult, int student, int child, int custTotal) {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_Select(bNo, st, ds, stt);
		
		String yn = "";
		String s = "";
		String[] seatNo = sChoose.split("/");
		int rlt1 = 0, rlt2 = 0, rlt3 = 0;
		int scount = 0;
		
		dto = dtos.get(0);
		
		for(int i=0; i<seatNo.length; i++) {
			tNo[i] = make_ticketNo(dto.getbNo(),dto.getbStartTime(),seatNo[i]);
		}			
		tCheck(sChoose, tNo[0], adult, student, child);

		if("/".equals(dto.getSeat()))
			s = "/"+sChoose;
		else
			s = dto.getSeat()+sChoose;
		
		scount = dto.getsCount()-custTotal;
		
		while(true) {
			System.out.print("결제하시겠습니까(y/n) ?   >>");
			yn = scn.nextLine();

			if(yn.equals("y") | yn.equals("Y")) {
				dto.setAdult(adult);
				dto.setStudent(student);
				dto.setChild(child);
				dto.setsCount(scount);
				
				for(int i=0; i<custTotal; i++) {
					dto.setticketNo(tNo[i]);
					if(adult!=0) {
						dto.setAdult(1);
						dto.setStudent(0);
						dto.setChild(0);
						adult--;
						rlt1 = dao.Cust_Insert(dto);
					}else if(student!=0){
						dto.setAdult(0);
						dto.setStudent(1);
						dto.setChild(0);
						student--;
						rlt1 = dao.Cust_Insert(dto);
					}else if(child!=0) {
						dto.setAdult(0);
						dto.setStudent(0);
						dto.setChild(1);
						child--;
						rlt1 = dao.Cust_Insert(dto);
					}
				}

				dto.setSeat(s);
				rlt2 = dao.Seat_Update(dto);
				rlt3 = dao.sCount_Update(dto);
				
				System.out.println("\n예매되었습니다!");
				break;
			}
		}
	}
	
	//티켓번호 생성기
	public String make_ticketNo(String bN, String stt, String seat1) {
		String ticketNo="";
		int n=0; 
		while(true) {
			for(int i=0;i<(int)(Math.random() * 10000) + 1;i++) {
				n = (int)(Math.random() * 10000) + 1;
			}
			if(n > 999) {
				String random = Integer.toString(n);
				stt = stt.replace(":", "");
				ticketNo = bN+stt+seat1+random;
				break;
			}
		}
		return ticketNo;
	}
	
	//예매 정보 print
	public void tCheck(String sChoose, String tNo1, int adult, int student, int child) {
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		
		
		String[] seatNo = sChoose.split("/");
		int ds = dtos.size();
		int pay = 0;
		int aPrice = 0, sPrice = 0, cPrice = 0;
		dtos = dao.ticket_Select1(tNo1);

		System.out.println("\n+++++++++++++++++++예매 정보+++++++++++++++++++\n");
		
		for(int i=0; i<dtos.size(); i++) {
			dto = dtos.get(i);
			if(i == 0) {
				System.out.printf("티켓 번호 : ");
				for(int j=0; j<seatNo.length; j++) {
					System.out.printf("%s\n         ", tNo[j]);
				}
				
				System.out.printf("\n\n출발지 : %s\t\t도착지 : %s\n",dto.getbStart(), dto.getbDes());
				System.out.printf("출발 시간 : \"%s\"\t도착 시간 : \"%s\"\n",dto.getbStartTime(), dto.getbDesTime());
				System.out.print("\n좌석 : ");
				for(int j = 0; j<seatNo.length; j++) {
					System.out.printf("%s번 ", seatNo[j]);
				}
				aPrice = dto.getaPrice();
				sPrice = dto.getsPrice();
				cPrice = dto.getcPrice();
			}
		}
		pay = adult * aPrice + student * sPrice + child * cPrice;
		System.out.printf("\n\n버스 티켓 금액 : %,d\n", pay);
		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public void tCheck(String tNo) {
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.ticket_Select2(tNo);
		dto = dtos.get(0);

		System.out.println("\n+++++++++++++++++++예매 정보+++++++++++++++++++\n");
		System.out.printf("티켓 번호 : ");
		System.out.printf("%s\n", tNo);
		System.out.printf("\n출발지 : %s\t\t도착지 : %s\n",dto.getbStart(), dto.getbDes());
		System.out.printf("출발 시간 : \"%s\"\t도착 시간 : \"%s\"\n",dto.getbStartTime(), dto.getbDesTime());
		System.out.print("\n좌석 : ");
		
		if(tNo.length() == 14)
			System.out.printf("%s번 ", tNo.substring(8,10));
		else
			System.out.printf("%s번 ", tNo.substring(8,9));
		
		int pay = dto.getAdult()*dto.getaPrice() + dto.getStudent()*dto.getsPrice() + dto.getChild()*dto.getcPrice();
		System.out.printf("\n\n버스 티켓 금액 : %,d", pay);
		
		System.out.println("\n\n++++++++++++++++++++++++++++++++++++++++++++");
	}
		
}
