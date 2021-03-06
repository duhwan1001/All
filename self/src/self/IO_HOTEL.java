package self;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class IO_HOTEL {
	private Scanner sc;
	private Map<String, Rooms> hm;

	IO_HOTEL() {
		sc = new Scanner(System.in);
		hm = new HashMap<String, Rooms>();
	}

	// 호텔 운영을 관리하는 프로그램 작성.(Map이용)
	// - 키값은 방번호

	File file = new File("d:/D_Other/hotel.bin");

	private void start() throws FileNotFoundException, IOException, ClassNotFoundException {
		if (!file.exists()) { // 파일 생성(파일 없을시)
			file.createNewFile();
			System.out.println(file.getAbsolutePath() + "파일을 새로 만들었습니다");
		}
		
		try {
			// 파일 불러오기(읽기)
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("d:/D_Other/hotel.bin"))); // 보조 스트림안에 보조스트림이
																										// 들어가도 상관없음.
																										// 기반스트림은 필수임.
			Object obj = null;

			while ((obj = ois.readObject()) != null) { // 역직렬화
				// 마지막에 다다르면 EOF 예외 발생

				Rooms rminfile = (Rooms) obj;
				Rooms rm = new Rooms(rminfile.getRoomnumber(), rminfile.getName()); // room객체에 넣어주기
				hm.put(rminfile.getRoomnumber(), rm); // room객체를 map에 넣기

				// test
				System.out.println("==========  File -> Map  ===============");
				System.out.println("방번호 : " + rminfile.getRoomnumber()+"		이름 : " + rminfile.getName());
				
			}
			
			ois.close();
			
		} catch (EOFException eofe) {
			System.out.println("============출력 작업 끝.============="); // EOFE 예외발생
			// eofe.printStackTrace();

			// 더이상 읽어올 객체가 없으면 예외발생함
			
			System.out.println("******************");
			System.out.println("호텔 문을 열었습니다");
			System.out.println("******************\n\n");

			while (true) {
				System.out.println("******************");
				System.out.println("어떤 업무를 하시겠습니까?");
				System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
				System.out.println("******************");

				switch (Integer.valueOf(sc.nextLine())) {
					case 1:
						checkin();
						break;
					case 2:
						checkout();
						break;
					case 3:
						roomstatus();
						break;
					case 4:
						closehotel(); {
						break;
					}
				}
			}
		}
	}

	private void checkin() {
		System.out.println("[체크인]");
		System.out.println("방 번호를 입력해주세요");
		String roomnumber = sc.nextLine().toUpperCase();

		if (hm.get(roomnumber) != null) {
			System.out.println("사용중인 방입니다.");
			return;
		}

		System.out.println("성함을 말씀해주세요");
		String name = sc.nextLine();
		if (hm.get(roomnumber) == null) {
			hm.put(roomnumber, new Rooms(roomnumber, name));
			System.out.println("체크인 되었습니다.");
		}
	}

	private void checkout() {
		System.out.println("[체크아웃]");
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호를 입력해주세요");
		String rommnumber = sc.nextLine().toUpperCase();
		// hm.remove(sc.nextLine());
		// System.out.println("체크아웃 되었습니다.");

		if (hm.remove(rommnumber) == null) {
			System.out.println("입력하신 방은 체크인 되어있지 않습니다");
		} else {
			System.out.println(rommnumber + "호 : 체크아웃 완료");
		}

	}

	private void roomstatus() {

		Set<String> keyset = hm.keySet();
		if(keyset.size()==0) {
			System.out.println("빈방..");
		} else {
			for (String key : keyset) {
				System.out.println(hm.get(key));
			}
		}

	}

	private void closehotel() throws IOException, IOException {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");

		System.out.println("============ Map -> File ==============");
		
		ObjectOutputStream oos = 
				new ObjectOutputStream
					(new BufferedOutputStream
						(new FileOutputStream("d:/D_Other/hotel.bin")));

		Set<String> keyset = hm.keySet();
		Iterator<String> it = keyset.iterator();
		while(it.hasNext()) {
			String key = it.next();
			Rooms rm = new Rooms(key, hm.get(key).getName());
			System.out.println(rm);
			oos.writeObject(rm);
		}
//		for (String key : keyset) {
//			Rooms rm = new Rooms(hm.get(key).getRoomnumber(), hm.get(key).getName());
//			System.out.println(rm);
//			oos.writeObject(rm);
//		}
		System.out.println("==================================");
		oos.close();
		System.exit(0);
	}


	public static void main(String[] args) {
		try {
			new IO_HOTEL().start();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}

class Rooms implements Serializable{ // 직렬화 가능하게끔
	private String name;
	private String roomnumber;

	public Rooms(String roomnumber, String name) {
		super();
		this.name = name;
		this.roomnumber = roomnumber;
	}
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	private void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	@Override
	public String toString() {
		return "방번호 : " + roomnumber + "		투숙객 : " + name;
	}
	
	
	
}
