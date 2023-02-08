package dao;

public class Bus_dto {
	private String bCompany;
	private String bNo;
	private String bStart;
	private String bStartTime;
	private String bDes;
	private String bDesTime;
	private String bGrade;		
	private String bDate;
	private int sCount;			//잔여석
	
	private String ticketNo;	//예매번호 = 버스번호+출발시간+랜덤
	private int adult;
	private int student;
	private int child;
	
	private String Seat;
	private int aPrice;
	private int sPrice;
	private int cPrice;
	
	private String pos;			//출발&도착 position, bGrade
	
	public Bus_dto() {}			//생성자
	
	public Bus_dto(String pos) {
		this.pos = pos;
	}
	
	public Bus_dto(String bStart, String bDes) {
		this.bStart = bStart;
		this.bDes = bDes;
	}
	
	public Bus_dto(String bNo, String bCompany, String bStart, String bStartTime , String bDesTime , String bDes, String bGrade, int sCount) {
		this.bNo = bNo;
		this.bCompany = bCompany;
		this.bStart = bStart;
		this.bStartTime = bStartTime;
		this.bDesTime = bDesTime;
		this.bDes = bDes;
		this.bGrade = bGrade;
		this.sCount = sCount;
	}

	public Bus_dto(String bNo, String bStart, String bDes, String bstartTime, String bdesTime, String bGrade, int sCount, String Seat, int aPrice, int sPrice, int cPrice) {
		this.bNo = bNo;
		this.bStart = bStart;
		this.bDes = bDes;
		this.bStartTime = bstartTime;
		this.bDesTime = bdesTime;
		this.bGrade = bGrade;
		this.sCount = sCount;
		this.Seat = Seat;
		this.aPrice = aPrice;
		this.sPrice = sPrice;
		this.cPrice = cPrice;
	}
	
	public Bus_dto(String bNo, String bStart, String bDes, String bstartTime, String bdesTime, String Seat, int aPrice, int sPrice, int cPrice) {
		this.bNo = bNo;
		this.bStart = bStart;
		this.bDes = bDes;
		this.bStartTime = bstartTime;
		this.bDesTime = bdesTime;
		this.Seat = Seat;
		this.aPrice = aPrice;
		this.sPrice = sPrice;
		this.cPrice = cPrice;
	}

	public Bus_dto(String bNo, String bStart, String Seat) {
		this.bNo = bNo;
		this.bStart = bStart;
		this.Seat = Seat;
	}

	public Bus_dto(String bNo, String bStart, String bDes, String bstartTime, String bdesTime, String bGrade,int sCount, String Seat, int aPrice, int sPrice, int cPrice, int adult, int student, int child) {
		this.bNo = bNo;
		this.bStart = bStart;
		this.bDes = bDes;
		this.bStartTime = bstartTime;
		this.bDesTime = bdesTime;
		this.bGrade = bGrade;
		this.sCount = sCount;
		this.Seat = Seat;
		this.aPrice = aPrice;
		this.sPrice = sPrice;
		this.cPrice = cPrice;
		this.adult = adult;
		this.student = student;
		this.child = child;
	}

	public String getPos() {
		return pos;
	}
	public String getbNo() {
		return bNo;
	}
	
	public void setbNo(String bNo) {
		this.bNo = bNo;
	}
	
	public String getbCompany() {
		return bCompany;
	}
	
	public String getbStart() {
		return bStart;
	}

	public String getbStartTime() {
		return bStartTime;
	}

	public String getbDes() {
		return bDes;
	}
	
	public String getbDesTime() {
		return bDesTime;
	}
	
	public int getsCount() {
		return sCount;
	}
	
	public void setsCount(int sCount) {
		this.sCount = sCount;
	}
	
	public String getbGrade() {
		return bGrade;
	}
	
	public int getAdult() {
		return adult;
	}

	public void setAdult(int adult) {
		this.adult = adult;
	}
	
	public int getaPrice() {
		return aPrice;
	}
	
	public int getStudent() {
		return student;
	}

	public void setStudent(int student) {
		this.student = student;
	}
	
	public int getsPrice() {
		return sPrice;
	}
	
	public int getChild() {
		return child;
	}
	
	public void setChild(int child) {
		this.child = child;
	}
	
	public int getcPrice() {
		return cPrice;
	}

	public String getticketNo() {
		// TODO Auto-generated method stub
		return ticketNo;
	}
	
	public void setticketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public void setSeat(String Seat) {
		this.Seat = Seat;
	}
	public String getSeat() {
		return Seat;
	}

}
