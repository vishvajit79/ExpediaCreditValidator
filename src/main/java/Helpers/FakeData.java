package main.java.Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FakeData {
	private final String USER_AGENT = "Mozilla/5.0";	
	
	/**
	 * 
	 * @param n
	 * @return String[] of cardnumbers
	 * @throws Exception
	 */
	public String[] sendGet(int n) throws Exception {

		String[] cardnumbers = {};
		String url = "https://node-data-generator.herokuapp.com/api/creditCard?n=" + n;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode + "\n\n");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			cardnumbers = inputLine.replaceAll("[\\[\\{\\}\\]]", "").split(",");
			response.append(inputLine);
		}
		in.close();
		return cardnumbers;

	}
}
