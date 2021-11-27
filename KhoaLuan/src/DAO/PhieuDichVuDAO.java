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

import Entity.DichVu;
import Entity.PhieuDichVu;

public class PhieuDichVuDAO {
//	static String POST_PHIEU_DICH_VU="http://13.212.45.136:5001/phieudichvu/insert";
//	static String GET_ONE_PHIEU_DICH_VU="http://13.212.45.136:5001/phieudichvu/getone";
	
	static String url="http://localhost:5001";
	static String POST_PHIEU_DICH_VU=url+"/phieudichvu/insert";
	static String GET_ONE_PHIEU_DICH_VU=url+"/phieudichvu/getone";
	static String GET_ALL_DICH_VU=url+"/dichvu/getall";
	static String GET_ONE_DICH_VU=url+"/dichvu/getone";
	static String GET_ALL_BY_PHIEU_KHAM=url+"/phieudichvu/getPhieuDichVuByPhieuKhamBenh";
	
	/**
	 * @author Vien
	 * date : 21/4/2021
	 * @return Thêm phiếu dịch vụ vào cơ sở dữ liệu 
	 * @decripstion : Thêm phiếu dịch vụ bằng cách sử dụng RestFull API
	 * */
	//[START POST Phiếu dịch vụ]
	public  int POSTPhieuDichVu(PhieuDichVu phieuDichVu) throws IOException {

		Gson gson = new GsonBuilder()
    		    .setDateFormat("yyyy-MM-dd")
    		    .create();
		String POST_PARAMS = gson.toJson(phieuDichVu);
	    System.out.println(POST_PARAMS);
	    URL obj = new URL(POST_PHIEU_DICH_VU);
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

	    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
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
	//[End POST Phiếu dịch vụ]

	
	/**
	 * @author Vien
	 * date: 21/4/2021
	 * @return một phiếu dịch vụ
	 * @decription: Lấy phiếu dịch vụ theo id được gọi về từ RestFullAPI
	 * */
	//[START lấy về 1 phiếu dịch vụ theo id]
	public PhieuDichVu GetOnePhieuDichVu(Long id) throws IOException {
		PhieuDichVu bn=new PhieuDichVu();
		URL urlForGetRequest = new URL(GET_ONE_PHIEU_DICH_VU+"/"+id);
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
			bn = gson.fromJson(response, PhieuDichVu.class);

			
		} else {
			System.out.println("GET NOT WORKED");
		}

		return bn;
	}
	//[END lấy về 1 phiếu dịch vụ theo id]
	
	public  List<DichVu>  GetAllDichVu() throws IOException {
		List<DichVu>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_DICH_VU);
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
		        DichVu[] dichvuList = gson.fromJson(object, DichVu[].class);
		        	
		        for(int i=0;i<dichvuList.length;i++)
		        	getall.add(dichvuList[i]);
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
	
	public DichVu GetOneDichVu(Long id) throws IOException {
		DichVu bn=new DichVu();
		URL urlForGetRequest = new URL(GET_ONE_DICH_VU+"/"+id);
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
			bn = gson.fromJson(response, DichVu.class);

			
		} else {
			System.out.println(responseCode);
		}

		return bn;
	}
	public  List<PhieuDichVu>  GetAllDichVuByPhieuKham(Long id) throws IOException {
		List<PhieuDichVu>getall=new ArrayList<>();
	    URL urlForGetRequest = new URL(GET_ALL_BY_PHIEU_KHAM+"/"+id);
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
		        PhieuDichVu[] dichvuList = gson.fromJson(object, PhieuDichVu[].class);
		        	
		        for(int i=0;i<dichvuList.length;i++)
		        	getall.add(dichvuList[i]);
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
}
