/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.portfolio.loanaccount.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BuildOptions {
		 public   String checkReceipt( String receipt) throws MalformedURLException,IOException{
	     String postUrl        = "http://localhost:9090/city/api/receipt/check";
	     
 
	URL url = new URL(postUrl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");
	
	//System.out.println("receipt");
	
	//System.out.println(receipt);
           
            String input = "{\"receiptNumber\":"+receipt+"}";
           // System.out.println(input);
          
	OutputStream os = conn.getOutputStream();
	os.write(input.getBytes());
	os.flush();

	BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

	
String output =  br.readLine();



return  output;


  } 


	

	 public  void sendMessage(Long loanId,String message) throws MalformedURLException, IOException{
	     String postUrl        = "http://localhost:9090/city/api/sms/send";

 
try {
  JsonParser parser = new JsonParser();
    JsonElement jsonTree = parser.parse(getClient(loanId));
    JsonObject jsonObject = jsonTree.getAsJsonObject();
    JsonElement mobileNo = jsonObject.get("mobileNo");
        String test = mobileNo.getAsString();
        String mobile = "+"+test;
        //System.out.println(mobile);
        
          String[] phone = new String[] {"\""+mobile+"\""};

	URL url = new URL(postUrl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");
            //getClient(loanId);
           
            String input = "{\"phone\":"+Arrays.toString(phone)+",\"message\":\""+message+"\"}";
          //  System.out.println(Arrays.toString(phone));

	OutputStream os = conn.getOutputStream();
	os.write(input.getBytes());
	os.flush();

//	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//		throw new RuntimeException("Failed : HTTP error code : "
//			+ conn.getResponseCode());
//	}

	BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

	String output;
	System.out.println("Output from Server .... \n");
	while ((output = br.readLine()) != null) {
		System.out.println(output);
	}

	

  } catch (MalformedURLException e) {

	e.printStackTrace();

  } catch (IOException e) {

	e.printStackTrace();

 }
  }
public  String getDetails( JsonElement clientId) throws MalformedURLException, IOException{
	     String postUrl        = "http://localhost:9090/city/api/details/get";
   

	URL url = new URL(postUrl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setDoOutput(true);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Content-Type", "application/json");
           
            String input = "{\"clientId\":"+clientId+"}";
           // System.out.println(Arrays.toString(phone));

	OutputStream os = conn.getOutputStream();
	os.write(input.getBytes());
	os.flush();

//	if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//		throw new RuntimeException("Failed : HTTP error code : "
//			+ conn.getResponseCode());
//	}

	BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

	
	//System.out.println("Output from Server .... \n");
           // System.out.println(br.readLine());
//	while ((output = br.readLine()) != null) {
//		System.out.println(output);
//	}

	//conn.disconnect();
       return  br.readLine();
	
}

public String getClient( Long loanId) throws MalformedURLException, IOException{
    String postUrl        = "http://localhost:9090/city/api/client/get";


URL url = new URL(postUrl);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setDoOutput(true);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Type", "application/json");
      
       String input = "{\"id\":"+loanId+"}";
         System.out.println("json input");
           System.out.println(input);

      // System.out.println(Arrays.toString(phone));

OutputStream os = conn.getOutputStream();
os.write(input.getBytes());
os.flush();

//if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//	throw new RuntimeException("Failed : HTTP error code : "
//		+ conn.getResponseCode());
//}

BufferedReader br = new BufferedReader(new InputStreamReader(
		(conn.getInputStream())));


//System.out.println("Output from Server .... \n");
      // System.out.println(br.readLine());
//while ((output = br.readLine()) != null) {
//	System.out.println(output);
String output =  br.readLine();

//}

//conn.disconnect();
  return  output;

}

public  void sendText(String[] phone,String message) throws MalformedURLException, IOException{
    String postUrl        = "http://localhost:9090/city/api/sms/send";


try {
URL url = new URL(postUrl);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setDoOutput(true);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Type", "application/json");
       //getClient(loanId);
      
       String input = "{\"phone\":"+Arrays.toString(phone)+",\"message\":\""+message+"\"}";
     //  System.out.println(Arrays.toString(phone));

OutputStream os = conn.getOutputStream();
os.write(input.getBytes());
os.flush();

//if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//	throw new RuntimeException("Failed : HTTP error code : "
//		+ conn.getResponseCode());
//}

BufferedReader br = new BufferedReader(new InputStreamReader(
		(conn.getInputStream())));

String output;
System.out.println("Output from Server .... \n");
while ((output = br.readLine()) != null) {
	System.out.println(output);
}

conn.disconnect();

} catch (MalformedURLException e) {

e.printStackTrace();

} catch (IOException e) {

e.printStackTrace();

}
}

public String getPayment( Long loanId) throws MalformedURLException, IOException{
    String postUrl        = "http://localhost:9090/city/api/payment/get";


URL url = new URL(postUrl);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setDoOutput(true);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Type", "application/json");
      
       String input = "{\"loanId\":"+loanId+"}";
         System.out.println("json input");
           System.out.println(input);

      // System.out.println(Arrays.toString(phone));

OutputStream os = conn.getOutputStream();
os.write(input.getBytes());
os.flush();

//if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//	throw new RuntimeException("Failed : HTTP error code : "
//		+ conn.getResponseCode());
//}

BufferedReader br = new BufferedReader(new InputStreamReader(
		(conn.getInputStream())));


//System.out.println("Output from Server .... \n");
      // System.out.println(br.readLine());
//while ((output = br.readLine()) != null) {
//	System.out.println(output);
String output =  br.readLine();

//}

//conn.disconnect();
  return  output;

}



public String getPaymentUrl( Double amount,String PhoneNumber, String accountReference) throws MalformedURLException, IOException{
    String postUrl        = "https://localhost:8443/stkpush/example/mpesa.php";


URL url = new URL(postUrl);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setDoOutput(true);
conn.setRequestMethod("POST");
conn.setRequestProperty("Content-Type", "application/json");
      
String input = "{\"amount\":"+amount+",\"PhoneNumber\":\""+PhoneNumber+"\",\"accountReference\":\""+accountReference+"\"}";
    System.out.println("json input");
           System.out.println(input);

      // System.out.println(Arrays.toString(phone));

OutputStream os = conn.getOutputStream();
os.write(input.getBytes());
os.flush();

//if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//	throw new RuntimeException("Failed : HTTP error code : "
//		+ conn.getResponseCode());
//}

BufferedReader br = new BufferedReader(new InputStreamReader(
		(conn.getInputStream())));


//System.out.println("Output from Server .... \n");
      // System.out.println(br.readLine());
//while ((output = br.readLine()) != null) {
//	System.out.println(output);
String output =  br.readLine();

//}

//conn.disconnect();
  return  output;

}

	
	
}