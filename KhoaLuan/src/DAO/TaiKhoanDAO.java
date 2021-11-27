package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Entity.Role;
import Entity.TaiKhoan;


public class TaiKhoanDAO {

//	public  String GET_ALL_TAI_KHOAN="http://13.212.45.136:5001/taikhoan/getall";
//	public  String GET_ONE_TAI_KHOAN="http://13.212.45.136:5001/taikhoan/getone";
//	public  String POST_TAI_KHOAN="http://13.212.45.136:5001/taikhoan/insert"; 
//	public  String DELETE_TAI_KHOAN="http://13.212.45.136:5001/taikhoan/delete";
//	public  String PUT_TAI_KHOAN="http://13.212.45.136:5001/taikhoan/update";
//	public  String GET_ALL_ROLE="http://13.212.45.136:5001/role/getall";
	static String url="http://localhost:5001";
	public  String GET_ALL_TAI_KHOAN=url+"/taikhoan/getall";
	public  String GET_ONE_TAI_KHOAN=url+"/taikhoan/getone";
	public  String POST_TAI_KHOAN=url+"/taikhoan/insert"; 
	public  String DELETE_TAI_KHOAN=url+"/taikhoan/delete";
	public  String PUT_TAI_KHOAN=url+"/taikhoan/update";
	public  String GET_ALL_ROLE=url+"/role/getall";

	public List<TaiKhoan>GetAllTaiKhoan() throws IOException {
		List<TaiKhoan>getall=new ArrayList<>();
		URL urlForGetRequest = new URL(GET_ALL_TAI_KHOAN);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			System.out.println("JSON String Result " + response);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray object = (JsonArray) parser.parse(response);// response will be the json String
			TaiKhoan[] taiKhoanList = gson.fromJson(object, TaiKhoan[].class);

			for(int i=0;i<taiKhoanList.length;i++)
				getall.add(taiKhoanList[i]);
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return getall;
	}
	public TaiKhoan GetOneTaiKhoan(String id) throws IOException {
		TaiKhoan taiKhoan=new TaiKhoan();
		URL urlForGetRequest = new URL(GET_ONE_TAI_KHOAN+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); 
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			Gson gson = new Gson();
			taiKhoan = gson.fromJson(response, TaiKhoan.class);

			
		} else {
			System.out.println(urlForGetRequest);
		}

		return taiKhoan;
	}
	public  int POSTTaiKhoan(TaiKhoan tk) throws IOException {
		Gson gson = new Gson();
		String POST_PARAMS = gson.toJson(tk);
	    
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_TAI_KHOAN);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type", "application/json");


	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { 
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	        // print result
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return responseCode;
	}
	@SuppressWarnings("unused")
	public int DeleteTaiKhoan(String id) throws IOException {
		TaiKhoan taiKhoan=new TaiKhoan();
		URL urlForGetRequest = new URL(DELETE_TAI_KHOAN+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); 
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return responseCode;
	}
	public  int PUTTaiKhoan(TaiKhoan tk) throws IOException {

		Gson gson = new Gson();
		String PUT_PARAMS = gson.toJson(tk);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_TAI_KHOAN+"/"+tk.getUsername());
	    HttpURLConnection putConnection = (HttpURLConnection) obj.openConnection();
	    putConnection.setRequestMethod("PUT");
	    putConnection.setRequestProperty("Content-Type", "application/json");


	    putConnection.setDoOutput(true);
	    OutputStream os = putConnection.getOutputStream();
	    os.write(PUT_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = putConnection.getResponseCode();
	    

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { 
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            putConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();

	       
	        System.out.println(response.toString());
	    } else {
	        System.out.println("PUT NOT WORKED");
	    }
	    return responseCode;
	}
	public boolean UpdateTK(TaiKhoan tk) {
		int ketqua=0;
		try {
			 ketqua=PUTTaiKhoan(tk);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ketqua==200)
			return true;
		else 
			return false;
	}
	public List<Role>GetAllRole() throws IOException {
		List<Role>getall=new ArrayList<>();
		URL urlForGetRequest = new URL(GET_ALL_ROLE);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); 
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();


		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			
			System.out.println("JSON String Result " + response);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray object = (JsonArray) parser.parse(response);
			Role[] roleList = gson.fromJson(object, Role[].class);

			for(int i=0;i<roleList.length;i++)
				getall.add(roleList[i]);
			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return getall;
	}
}
