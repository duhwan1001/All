package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSinpleReadTest {

	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fr = new FileReader("d:/D_Other/myJsonFile.txt"); // 파일 읽어옴
		
		JSONParser parser = new JSONParser(); //
		
		Object obj = parser.parse(fr); // json형태로 변환
		JSONObject jsonfile = (JSONObject) obj;
		
		String name = (String)jsonfile.get("name");
		String job = (String)jsonfile.get("job");
		long age = (long)jsonfile.get("age"); // Integer로 처리하면 오류남
		String addr = (String)jsonfile.get("addr");
		
		System.out.println("name : " + name);
		System.out.println("job : " + job);
		System.out.println("age : " + age);
		System.out.println("addr : " + addr);
		
		JSONArray list = (JSONArray) jsonfile.get("singerList");
		
		Iterator<JSONObject> it = list.iterator();
		
		JSONObject singer;
		while(it.hasNext()) {
			singer = it.next();
			System.out.printf(
					"이름 : %s, \t성별 : %s, \t나이: %d\n",
					singer.get("name"),
					singer.get("gender"),
					singer.get("age"));
		}
		
	}
}


















