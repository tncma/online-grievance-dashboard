package com.cma.hackathon.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;


public final class CommonUtil {
	
	private static final ApplicationLogger log = new ApplicationLogger(CommonUtil.class.getName());
	
	private static final HashMap<String, String> uniqueKey = new HashMap<String, String>();
	
	
	
	public static String inputStreamToString(InputStream is) {
		 
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        try {
            // Read response until the end
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
        	log.debug(e.getMessage(), e.fillInStackTrace());
        }

        // Return full string
        return total.toString();
    }
	
	/**
	 * Gets the address details in a JSON object
	 * @param street the customer street
	 * @param taluk the customer taluk
	 * @param city the customer city
	 * @param state the customer state info
	 * @return the JSON object of address details
	 * @throws JSONException thrown when there is an exception
	 */
	public static JSONObject getJsonObject(String taluk, String city, String state,String village,String country) throws TafeException{
		
		/*if(street==null){
			street = "";
    	}*/
		if(village == null){
			village = "";
		}
		if(city==null){
    		city = "";
    	}
    	if(taluk==null){
    		taluk = "";
    	}
    	if(state==null){
    		state = "";
    	}
    	if(country==null){
    		country = "";
    	}
		
		JSONObject jsonAddress = new JSONObject();
    	try {
			//jsonAddress.put("street", street);
			jsonAddress.put("taluk", taluk);
			jsonAddress.put("city", city);
			jsonAddress.put("state", state);
			jsonAddress.put("village", village);
			jsonAddress.put("country", country);
    	} catch (JSONException e) {
    		log.debug(e.getMessage(), e.fillInStackTrace());
		}
		return jsonAddress;
	}
	

	public static boolean getUniqueKey(String tokenId) throws TafeException{
		return uniqueKey.containsValue(tokenId);
		
	}
	

	public static HashMap<String, String> setUniqueKey(String key,String value) throws TafeException{
		
		//System.out.println("KEY===="+key);
		if(uniqueKey.containsKey(key)){
			uniqueKey.remove(key);
		}
		uniqueKey.put(key, value);
		return uniqueKey;
	}
	
	public static String getImagePath(){
		Properties properties = new Properties();
		InputStream prop = ImageManipulation.class.getClassLoader().getResourceAsStream("project.properties");
		try {
			properties.load(prop);
		} catch (IOException e) {
			
		}finally{
			try {
				prop.close();
				properties.clear();
			} catch (IOException e) {
			}
		}
	    String path = properties.getProperty("imagefilepath");
		return path;
	}
	
}