package exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.Bus_dao;
import dao.Bus_dto;

public class busSelect {
	static String stt = ""; //startTime
	public String busSelect(String st, String ds) {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_Select(st, ds);
		
		int bus = 0;
		String[] bus_arr = new String[50];
		String[] stt_arr = new String[50];
		Arrays.fill(stt_arr, "");
		Arrays.fill(bus_arr, "");
		
		System.out.println("버스를 선택하여 주십시오. \n");
		System.out.println("No\t버스사\t출발지\t출발시간\t도착시간\t도착지\t우등/일반\t잔여석");
		for(int i=0; i<dtos.size(); i++) {
			dto = dtos.get(i);
			System.out.printf("[%d]\t%s\t%s\t%s\t%s\t%s\t%s\t%d\n",i+1,dto.getbCompany(), dto.getbStart(),dto.getbStartTime(),dto.getbDesTime(), dto.getbDes(), dto.getbGrade(), dto.getsCount());
			bus_arr[i] = dto.getbNo();
			stt_arr[i] = dto.getbStartTime();
			if(i == dtos.size()-1) {
				while(true) {
					System.out.print("\n\n버스(No) : ");
					bus = scn.nextInt();
					if(bus > dtos.size() | bus == 0) 
						System.out.print("다시 입력하여 주세요!");
					else 
						break;
				}
			}
		}System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		stt = stt_arr[bus-1];
		//bNo반환해줌
		return bus_arr[bus-1];
	}
	
	public String startT() {
		return stt;
	}
	
	
}
