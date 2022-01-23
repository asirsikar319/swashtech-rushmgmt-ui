package com.swashtech.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient {

	public String callRestApi(String endpoint, String type, String input) {
		String response = null;
		try {
			System.err.println("endpoint : " + endpoint);
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(type);
			conn.setRequestProperty("Content-Type", "application/json");
			
			System.err.println("input : "+input);
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			System.err.println("conn.getResponseCode() : "+conn.getResponseCode());
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				response = output;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
