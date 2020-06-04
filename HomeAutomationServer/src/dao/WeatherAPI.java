package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.WeatherVO;

public class WeatherAPI implements Runnable{
	WeatherVO vo;
	
	public WeatherAPI (WeatherVO vo) {
		this.vo = vo;
	}
	
	@Override
	public void run() {
		System.out.println("JAVA SERVER - DAO getWeather 실행");

		String urlstr = "http://70.12.60.98:8090/homeAutomation/getWeather";

		try {
			URL url = new URL(urlstr);
			BufferedReader bf;
			String line;
			String result = "";

			// 날씨 정보를 받아온다.
			bf = new BufferedReader(new InputStreamReader(url.openStream()));

			// 버퍼에 있는 정보를 문자열로 변환 -> JSON 확인
			while ((line = bf.readLine()) != null) {
				result = result.concat(line);
				System.out.println(line);
			}

			// 문자열을 JSON으로 파싱
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(result);

			// 지역 저장
			String name = (String) jsonObj.get("name");
			vo.setName(name);
			System.out.println("Name : "+vo.getName());
			
			//최고온도
			String tempMax = (String) jsonObj.get("tempMax");
			vo.setName(tempMax);
			System.out.println("tempMax : "+vo.getTempMax());

			//습도
			String humidity = (String) jsonObj.get("humidity");
			vo.setName(humidity);
			System.out.println("humidity : "+vo.getHumidity());
			
			//현재온도
			String temp = (String) jsonObj.get("temp");
			vo.setName(temp);
			System.out.println("temp : "+vo.getTemp());
			
			//체감온도
			String feelsLike = (String) jsonObj.get("feelsLike");
			vo.setName(feelsLike);
			
			//날씨
			String weather = (String) jsonObj.get("weather");
			vo.setName(weather);
			
			//pm10
			String pm10Value = (String) jsonObj.get("pm10Value");
			vo.setName(pm10Value);
			
			//pm25
			String pm25Value = (String) jsonObj.get("pm25Value");
			vo.setName(pm25Value);
			
			
		
			

			bf.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
//		//doa 값 LIST에 저장
//		ArrayList<WeatherVO> result = new ArrayList<WeatherVO>();
//		result.add(vo);
//		return result;
	}

}