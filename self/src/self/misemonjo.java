package self;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 공공데이터 포털의 OPEN API 예제
 * (레시피 재료정보 가져오는 예제)
 *
 */
public class misemonjo {
	private String apiKey = "%2FPd%2B2NDlBwjgevYkd6CvBO7RfbY6rwJVQfEngB%2BgOYyWavBCmm%2Bho2Yu%2B4hcCqorl6IYZemDOFL%2FP521BUe2SQ%3D%3D"; // 개인별 발급.
	private String YEAR = "2021";
	private String Rows = "5";
	
	// 지역 특정하는거 구현 못했음
	
	/**
	 * JSON
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException 
	 */
	private JSONObject getJSONObject() throws IOException, ParseException, org.json.simple.parser.ParseException {
		URL url = new URL("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?year="
				+ YEAR // 2021
				+ "&pageNo=1&"
				+ "&numOfRows=" + Rows //100
				+ "&returnType=json&serviceKey=" + apiKey);
		URLConnection urlConnection = url.openConnection();
		
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));

		return (JSONObject)obj;
	}

	/**
	 * 시작
	 * @throws IOException
	 * @throws ParseException
	 * @throws org.json.simple.parser.ParseException 
	 */
	public void start() throws IOException, ParseException, org.json.simple.parser.ParseException {

		JSONObject jsonfile = getJSONObject();

		JSONObject rootObj = (JSONObject) jsonfile.get("response");

		//-----------------------------------------------------------------------
		// 구해온 전체 재료수를 이용하여 다시 요청함.


		jsonfile = getJSONObject();

		rootObj = (JSONObject) jsonfile.get("response");

		JSONObject result = (JSONObject)rootObj.get("header");
		String code = (String)result.get("resultMsg");

		
		JSONObject getBody = (JSONObject)rootObj.get("body");
		if(code.equals("NORMAL_CODE")) { // 정상 상태이면...

			JSONArray list = (JSONArray)getBody.get("items");

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = list.iterator();

			while(it.hasNext()){

				JSONObject tempJson = it.next();

				System.out.println("순번 : " + tempJson.get("issueGbn"));
				System.out.println("지역명 : " + tempJson.get("districtName"));
				System.out.println("발령일 : " + tempJson.get("issueDate"));
				System.out.println("발령시간 : " + tempJson.get("issueTime"));
				System.out.println("발령농도 : " + tempJson.get("issueVal"));
				System.out.println("해제일 : " + tempJson.get("clearDate"));
				System.out.println("해제시간 : " + tempJson.get("clearTime"));
				System.out.println("해제농도 : " + tempJson.get("clearVal"));

				System.out.println("-------------------------");
			}
		}
	}

	public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
		new misemonjo().start();
	}
}
