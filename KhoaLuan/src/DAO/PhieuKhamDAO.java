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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Entity.PhieuKhambenh;



public class PhieuKhamDAO {
//	static String POST_PHIEU_KHAM_BENH="http://13.212.45.136:5001/phieukham/insert";
//	static String PUT_PHIEU_KHAM_BENH="http://13.212.45.136:5001/phieukham/update";
//	static String GET_ONE_PHIEU_KHAM="http://13.212.45.136:5001/phieukham/getone";
	
	static String url="http://localhost:5001";
	static String GET_PHIEU_KHAM_BY_ID_BENH_NHAN=url+"/phieukham/getphieukhambybenhnhanid";
	static String POST_PHIEU_KHAM_BENH=url+"/phieukham/insert";
	static String PUT_PHIEU_KHAM_BENH=url+"/phieukham/update";
	static String GET_ONE_PHIEU_KHAM=url+"/phieukham/getone";
	
	/**
	 * @author Vien
	 * @return Thêm phiếu khám bệnh vào cơ sở dữ liệu 
	 * @decripstion : Thêm phiếu khám bệnh bằng cái sử dụng RestFull API
	 * */
	//[START POST Phiếu khám]
	public  int POSTPhieuKhamBenh(PhieuKhambenh phieukham) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(phieukham);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_PHIEU_KHAM_BENH);
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

	      
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return responseCode;
	}
	//[End POST Phiếu Khám]

	/**
	 * @author Vien
	 * @return Thêm phiếu khám bệnh vào cơ sở dữ liệu 
	 * @decripstion : Thêm phiếu khám bệnh bằng cách sử dụng RestFull API
	 * */
	//[START PUT Phiếu Khám]
	public  int PUTPhieuKhamBenh(PhieuKhambenh pk) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String PUT_PARAMS = gson.toJson(pk);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_PHIEU_KHAM_BENH+"/"+pk.getId());
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
	/**
	 * @author Vien
	 * @return một phiếu khám bệnh
	 * @decription: Lấy phiếu khám bệnh theo id được gọi về từ RestFullAPI
	 * */
	//[START lấy về 1 phiếu khám theo id]
	public PhieuKhambenh GetOnePhieuKham(Long id) throws IOException {
		PhieuKhambenh bn=new PhieuKhambenh();
		URL urlForGetRequest = new URL(GET_ONE_PHIEU_KHAM+"/"+id);
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
			
			Gson gson = new GsonBuilder()
        		    .setDateFormat("yyyy-MM-dd")
        		    .create();
			bn = gson.fromJson(response, PhieuKhambenh.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return bn;
	}
	//[END lấy về 1 phiếu khám theo id]
	
	/**
	 * @author Vien
	 * date: 21/4/2021
	 * @return list danh sách phiếu khám bệnh theo bệnh nhân id
	 * @decription: Lấy danh sách phiếu khám theo bệnh nhân id được gọi về từ RestFullAPI
	 * */
	//[START GetAll phiếu khám theo bệnh nhân id]
	public  List<PhieuKhambenh>GetAllPhieuKhamByBenhNhanIDANDDate(Long idbn) throws IOException {
		List<PhieuKhambenh>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_PHIEU_KHAM_BY_ID_BENH_NHAN+"/"+idbn);
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
	        if(responseCode==200)
	        {
	        	Gson gson = new GsonBuilder()
	        		    .setDateFormat("yyyy-MM-dd")
	        		    .create();
		        JsonParser parser = new JsonParser();
		        JsonArray object = (JsonArray) parser.parse(response);
		        PhieuKhambenh[] phieuKhambenhs = gson.fromJson(object, PhieuKhambenh[].class);
		        	
		        for(int i=0;i<phieuKhambenhs.length;i++)
		        	getall.add(phieuKhambenhs[i]);
	        }
	        else
	        {
	        	return null;
	        }
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
		return getall;

	}
	//[END GetALL phiếu khám theo bệnh nhân id]
	
	public  PhieuKhambenh POSTPhieuKhamBenhReturnPK(PhieuKhambenh phieukham) throws IOException {
		PhieuKhambenh bn= new PhieuKhambenh();
		String readLine = null;
		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(phieukham);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_PHIEU_KHAM_BENH);
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Content-Type", "application/json");


	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();


	    int responseCode = postConnection.getResponseCode();
	    

	    if (responseCode == HttpURLConnection.HTTP_OK) { 
	    	BufferedReader in = new BufferedReader(
					new InputStreamReader(postConnection.getInputStream()));
			String response = new String();
			while ((readLine = in .readLine()) != null) {
				response+=(readLine);
			} in .close();
			Gson gson1 = new GsonBuilder()
	    		    .setDateFormat("yyyy-MM-dd")
	    		    .create();
			bn = gson1.fromJson(response, PhieuKhambenh.class);

	        
	        System.out.println(response.toString());
	    } else {
	        System.out.println("POST NOT WORKED");
	    }
	    return bn;
	}
}
