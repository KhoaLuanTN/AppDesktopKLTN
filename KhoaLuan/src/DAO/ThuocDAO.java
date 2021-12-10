package DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Entity.Thuoc;

public class ThuocDAO {
	
//	static String url="http://localhost:5001";
	static String url=BenhNhanDAO.url;
	
	static String GET_ONE_THUOC=url+"/thuoc/getone";
	static String GET_ALL_THUOC=url+"/thuoc/getall";

	/**
	 * @author Vien
	 * date: 13/5/2021
	 * @return list danh sách thuốc
	 * @decription: Lấy danh sách thuốc được gọi về từ RestFullAPI
	 * */
	//[START GetAll]
	public  List<Thuoc>  GetAllThuoc() throws IOException {
		List<Thuoc>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_THUOC);
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
		        Thuoc[] thuocList = gson.fromJson(object, Thuoc[].class);
		        	
		        for(int i=0;i<thuocList.length;i++)
		        	getall.add(thuocList[i]);
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
	
	public Thuoc GetOneThuoc(Long  id) throws IOException {
		
			Thuoc thuoc=new Thuoc();
			URL urlForGetRequest = new URL(GET_ONE_THUOC+"/"+id);
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
				
				Gson gson = new Gson();
				thuoc = gson.fromJson(response, Thuoc.class);

				
			} else {
				System.out.println("GET NOT WORKED");
			}

			return thuoc;
		
	}
}
