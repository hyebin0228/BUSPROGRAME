package exe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.Bus_dao;
import dao.Bus_dto;

public class StartDes {

	//출발지 입력
	public String Start() {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		dtos = dao.Bus_startList();
		int st = 0;

		String[] st_arr = new String[20];
		Arrays.fill(st_arr, "");
		
		System.out.println("출발지를 선택하여 주십시오. \n");
		
		for(int i=0; i<dtos.size(); i++) {
			dto = dtos.get(i);
			System.out.print("["+(i+1)+"]" + dto.getPos()+"\t");
			st_arr[i] = dto.getPos();
			if(i%3==2) System.out.println();
		}
		while(true) {
			System.out.print("\n\n출발지(숫자) : ");
			st = scn.nextInt();
			if(st > dtos.size() | st == 0) {
				System.out.print("다시 입력하여 주세요!");
			}else
				break;
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		return st_arr[st-1];
	}

	//도착지 입력
	public String Des() {
		Scanner scn = new Scanner(System.in);
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		Bus_dao dao = new Bus_dao();
		Bus_dto dto = new Bus_dto();
		int ds = 0;
		
		String[] ds_arr = new String[20];
		Arrays.fill(ds_arr, "");
		
		dtos = dao.Bus_desList();
		System.out.println("도착지를 선택하여 주십시오. \n");
		
		for(int i=0; i<dtos.size(); i++) {
			dto = dtos.get(i);
			System.out.print("["+(i+1)+"]" + dto.getPos()+"\t");
			ds_arr[i] = dto.getPos();
			if(i%3==2) System.out.println();
		}
		while(true) {
			System.out.print("\n\n도착지(숫자) : ");
			ds = scn.nextInt();
			if(ds > dtos.size() | ds == 0) {
				System.out.print("다시 입력하여 주세요!");
			}else
				break;
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
		return ds_arr[ds-1];

	}
}