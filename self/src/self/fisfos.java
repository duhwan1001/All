package self;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class fisfos {
	
	private static Map<String, Hotel1> HotelServiceMap;
	
	public fisfos() {
		HotelServiceMap = new HashMap<>();
	}
	
	private void checkIn() {
		HotelServiceMap.put("방번호", new Hotel1("방번호", "김두환"));
	}
	
	
	public static void main(String[] args) throws IOException {
		
		new fisfos().checkIn();
		
		String testString = "제발들어가주세요";
		
		Set<String> keySet = HotelServiceMap.keySet();
		
		Iterator<String> it = keySet.iterator();
		
		int cnt = 0;
		while(it.hasNext()) {
			cnt++;
			String roomnum = it.next();
			Hotel1 h = HotelServiceMap.get(roomnum);
			System.out.println("방번호 : " + h.getRoomNum() + "\t투숙객 : " + h.getName());
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("D:/java/hotel.data");
			
			DataOutputStream dos = new DataOutputStream(fos);
			
			dos.writeUTF(testString);
			
			System.out.println("출력완료...");
			
			dos.close();
			
			FileInputStream fis =
					new FileInputStream("D:/java/hotel.data");
			DataInputStream dis = new DataInputStream(fis);
			
			System.out.println("문자열 자료 : " + dis.readUTF());
			
			dis.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Hotel1{
	private String roomNum;
	private String name;
	
	public Hotel1(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
