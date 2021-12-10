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

import Entity.HoaDon;

public class HoaDonDAO {

//	static String url="http://localhost:5001";
	static String url=BenhNhanDAO.url;
	static String POST_HOA_DON=url+"/hoadon/insert";
	static String GET_ONE_HOA_DON=url+"/hoadon/getone";
	static String GET_ALL_HOA_DON=url+"/hoadon/getall";
	static String PUT_HOA_DON=url+"/hoadon/update";
	static String GET_ALL_HOA_DON_CHUA_THANH_TOAN=url+"/hoadon/getHoaDonChuaThanhToan";
	static String GET_ALL_HOA_DON_DA_THANH_TOAN=url+"/hoadon/getHoaDonDaThanhToan";
	/**
	 * @author Vien
	 * date : 15/5/2021
	 * @return Thêm hóa đơn vào cơ sở dữ liệu 
	 * @decripstion : Thêm hóa đơn bằng cái sử dụng RestFull API
	 * */
	//[START POST Request]
	public  int POSTHoaDon(HoaDon hoadon) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(hoadon);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_HOA_DON);
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
	//[End POST Request]
	
	/**
	 * @author Vien
	 * date: 15/5/2021
	 * @return list danh sách hóa đơn chưa thanh toán
	 * @decription: Lấy danh sách hóa đơn chưa thanh toán được gọi về từ RestFullAPI
	 * */
	//[START GetAll]
	public  List<HoaDon>  GetAllHoaDonChuaThanhToan(Long id) throws IOException {
		List<HoaDon>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_HOA_DON_CHUA_THANH_TOAN+"/"+id);
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
		        HoaDon[] hoadonList = gson.fromJson(object, HoaDon[].class);
		        	
		        for(int i=0;i<hoadonList.length;i++)
		        	getall.add(hoadonList[i]);
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
	//[END GetALL]
	
	/**
	 * @author Vien
	 * date: 15/5/2021
	 * @return list danh sách hóa đơn chưa thanh toán
	 * @decription: Lấy danh sách hóa đơn chưa thanh toán được gọi về từ RestFullAPI
	 * */
	//[START GetAll]
	public  List<HoaDon>  GetAllHoaDonDaThanhToan() throws IOException {
		List<HoaDon>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_HOA_DON_DA_THANH_TOAN);
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
		        HoaDon[] hoadonList = gson.fromJson(object, HoaDon[].class);
		        	
		        for(int i=0;i<hoadonList.length;i++)
		        	getall.add(hoadonList[i]);
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
	//[END GetALL]
	
	public HoaDon GetOneHoaDon(Long id) throws IOException {
		HoaDon hd=new HoaDon();
		URL urlForGetRequest = new URL(GET_ONE_HOA_DON+"/"+id);
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET"); 
		conection.setRequestProperty("Content-Type", "application/json");
		int responseCode = conection.getResponseCode();
		System.out.println(urlForGetRequest);

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
			hd = gson.fromJson(response, HoaDon.class);

			
		} else {
			System.out.println(responseCode);
		}

		return hd;
	}
	public  List<HoaDon>  GetAllHoaDon() throws IOException {
		List<HoaDon>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_HOA_DON);
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
		        HoaDon[] hoadonList = gson.fromJson(object, HoaDon[].class);
		        	
		        for(int i=0;i<hoadonList.length;i++)
		        	getall.add(hoadonList[i]);
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
	public  int PUTHoaDon(HoaDon hd) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String PUT_PARAMS = gson.toJson(hd);
	    System.out.println(PUT_PARAMS);
	    URL obj = new URL(PUT_HOA_DON+"/"+hd.getId());
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
}
