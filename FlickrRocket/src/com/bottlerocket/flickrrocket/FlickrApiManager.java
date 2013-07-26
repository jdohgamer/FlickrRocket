package com.bottlerocket.flickrrocket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.gson.Gson;

public class FlickrApiManager extends Activity {
	 
    
	private static String _key = "3e9e38b4e857154bfcc46ba7cfd77edd";
	private static String _secret = "1db7e057095d84a9";
	
	public static String getPhotoUrl(JsonResponse.photos.Photo pp){
		String url = "http://farm"+pp.getFarm()+".static.flickr.com/"+pp.getServer()+"/"+pp.getId()+"_"+pp.getSecret()+"_m.jpg";
		print("Created URL :: " + url);
		return url;
	}
	public static String getUrl(String sort, String tags, String tag_mode){//sort=random,tags=rocket,tag_mode=all
		return "http://api.flickr.com/services/rest/?format=json&sort="+sort+"&method=flickr.photos.search&tags="+tags+"&tag_mode="+tag_mode+"&api_key="+_key+"&nojsoncallback=1";
	}
	public static String getUrl(){
		return getUrl("random","rocket","all");
	}
	
	public void perfRequest(){
		perfRequest(getUrl());
	}
	
	/**
	 * Get Image from the Url and save to directory
	 * @param photoUrl
	 * @param idx
	 * @return byte[] (image)
	 */
	public  byte[] getPhoto(String photoUrl, int idx
			, Bitmap bmp){
		
		byte[] bytes = null;
		HttpURLConnection conn = null;
		URL url = null;
		
		try{
			//***********************************************
			url = new URL( photoUrl );//create url
			//Set up the initial connection
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);	//we are using conn to get input stream
			conn.setReadTimeout(10000);	//set timeout to 10 sec
	                    
			conn.connect();
			
			//get file input stream from server
			InputStream inputStream = conn.getInputStream();
			 
			// write the inputStream to a FileOutputStream					//TODO Remove saving of image file
			//OutputStream outputStream = new FileOutputStream(new File("testImage-" + idx + ".jpg"));
			
			
	 
			int read = 0;
			bytes = new byte[1024];		//TODO: may need to change size
			
			
// One way I tried to implement the imageView			
			setContentView(R.layout.main);
			bmp = BitmapFactory.decodeStream(inputStream);
			 ImageView imageView = (ImageView) findViewById(R.id.imageView1);
				imageView.setImageBitmap(bmp);
		
			
			
			//while ((read = inputStream.read(bytes)) != -1) {
			//	outputStream.write(bytes, 0, read);		//save file to directory
			
				
	       
	        //***********************************************
	        print("done");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.disconnect();
			}
		}
		return bytes;
	}
	
	/**
	 * Perform GET request to flickr api server for query information
	 * @param urlInput
	 * @return JsonResponse
	 */
	public  JsonResponse perfRequest(String urlInput){
		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		String line = null;
		URL url = null;
		JsonResponse jsonResponse = null;
		
		try{
			//***********************************************
			url = new URL( urlInput );
			
			print("URL ::: ");
			print(url);
			//Set up the initial connection
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setReadTimeout(10000);
	                    
			conn.connect();
			
			//read the result from the server
	        br  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        sb = new StringBuilder();
	        while ((line = br.readLine()) != null){
	        	print(line);
	        	sb.append(line);
	        }
	        //***********************************************
			
	        Gson gson = new Gson();
	        String s = sb.toString().trim().substring(0, sb.toString().length()).replace("jsonFlickrApi(", "");
	        jsonResponse = gson.fromJson(s, JsonResponse.class);

	        print("done");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(conn != null){
				conn.disconnect();
			}
		}
		return jsonResponse;
	}
	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		print("FlickrApiManager");
//		print("performing request...");
//		JsonResponse jResponse = perfRequest( getUrl("random","cars","all") );
//		
//		for ( int i = 0 ; i < 5; i++){
//			byte[] firstphoto = getPhoto( getPhotoUrl(jResponse.getPhotoAtIdx(i)), i );
//		}
//	}

	private static void print(Object out){
		if(out != null){
			System.out.println(out.toString());
		}
	}
}


