package com.swashtech.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class RestClient {

	public String callPOSTApi(String endpoint, String input) {
		String response = null;
		try {
			System.err.println("endpoint : " + endpoint);
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("st-transid", UUID.randomUUID().toString());
			
			System.err.println("st-transid : " + conn.getRequestProperty("st-transid"));
			System.err.println("input : " + input);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.err.println("conn.getResponseCode() : " + conn.getResponseCode());
			BufferedReader br;
			if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}

			String output;
			while ((output = br.readLine()) != null) {
				response = output;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		} catch (IOException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		}
		return response;
	}

	public String callPUTApi(String endpoint, String input) {
		String response = null;
		try {
			System.err.println("endpoint : " + endpoint);
			URL url = new URL(endpoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("st-transid", UUID.randomUUID().toString());
			
			System.err.println("st-transid : " + conn.getRequestProperty("st-transid"));
			System.err.println("input : " + input);
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			System.err.println("conn.getResponseCode() : " + conn.getResponseCode());
			BufferedReader br;
			if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}

			String output;
			while ((output = br.readLine()) != null) {
				response = output;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		} catch (IOException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		}
		return response;
	}

	public String callGETApi(String endpoint) {
		String response = null;
		try {

			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
				public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
					if (hostname.equals("localhost")) {
						return true;
					}
					return false;
				}
			});

			System.err.println("endpoint : " + endpoint);
			URL url = new URL(endpoint);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			conn.setSSLSocketFactory(sslsocketfactory);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("st-transid", UUID.randomUUID().toString());

			System.err.println("st-transid : " + conn.getRequestProperty("st-transid"));
			System.err.println("conn.getResponseCode() : " + conn.getResponseCode());
			BufferedReader br;
			if (conn.getResponseCode() == 200 || conn.getResponseCode() == 201) {
				br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			} else {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
			}

			String output;
			while ((output = br.readLine()) != null) {
				response = output;
			}
			conn.disconnect();

		} catch (MalformedURLException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		} catch (IOException e) {
			response = "{\r\n" + "    \"message\": \"Internal Server Error\",\r\n" + "    \"status\": \"Error\"\r\n"
					+ "}";
			e.printStackTrace();
		}
		return response;
	}

}
