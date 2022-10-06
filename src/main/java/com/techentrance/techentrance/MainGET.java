package com.techentrance.techentrance;

import java.util.*;
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import okhttp3.*;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONArray;
import com.careerjet.webservice.api.Client;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import javax.servlet.http.HttpServletRequest;

import java.net.URI;
import java.net.HttpURLConnection;



public class MainGET {

    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            String value = "{\"search_terms\": \"python programmer\",\"location\": \"30301\",\"page\": \"1\"}";
            RequestBody body = RequestBody.create(mediaType, value);
            Request request = new Request.Builder()
                    .url("https://linkedin-jobs-search.p.rapidapi.com/")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Key", "83a5b17e50msh2dadc0af40cb420p1c5e67jsncb38131953c6")
                    .addHeader("X-RapidAPI-Host", "linkedin-jobs-search.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            if(response.code() != 200)
            {
                System.out.println("bad request");
            }
            else{
                response.body().;
                String jsonData = response.body().string();
                jsonData = jsonData.substring(1, jsonData.length() - 1);
                JSONObject Jobject = new JSONObject(jsonData);
                //JSONArray Jarray = Jobject.getJSONArray("job_url");
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(jsonData);


                //for (int i = 0; i < Jarray.length(); i++) {
                //    JSONObject object = Jarray.getJSONObject(i);
                //}
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
/**HttpRequest request = HttpRequest.newBuilder()
 .uri(URI.create("https://linkedin-jobs-search.p.rapidapi.com/"))
 .header("content-type", "application/json")
 .header("X-RapidAPI-Key", "83a5b17e50msh2dadc0af40cb420p1c5e67jsncb38131953c6")
 .header("X-RapidAPI-Host", "linkedin-jobs-search.p.rapidapi.com")
 .method("POST", HttpRequest.BodyPublishers.ofString("{\n    \"search_terms\": \"python programmer\",\n    \"location\": \"30301\",\n    \"page\": \"1\"\n}"))
 .build();
 HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
 System.out.println(response.body());**/
/**
 try {
 //https://serpapi.com/search.json?engine=google&q=what%20is%20coronavirus&api_key=APIKEY
 //&q=what%20is%20coronavirus&api_key=APIKEY

 //&q=software%20engineer%20intern%20jobs&api_key=200c27df3c6f859cf22b37c1e53284a84a932095a5b4a22f74d5cb7b8225953d


 //URL url = new URL("https://serpapi.com/search?engine=google_jobs&q=software%20engineer%20intern%20jobs&api_key=200c27df3c6f859cf22b37c1e53284a84a932095a5b4a22f74d5cb7b8225953d");

 //
 //URL url = new URL("https://arbeitnow.com/api/job-board-api");

 //ADZUNA
 //URL url = new URL("http://api.adzuna.com/v1/api/jobs/gb/search/1?app_id=a403f850&app_key=f9c082ed9499b80d0c28041bc29a8f5e&results_per_page=20&what=software%20engineer%20intern%20jobs&content-type=application/json");

 URL url = new URL("https://api.adzuna.com/v1/api/jobs/us/search/1?app_id=a403f850&app_key=f9c082ed9499b80d0c28041bc29a8f5e&results_per_page=20&what=software%20engineer%20intern");
 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 conn.setRequestMethod("GET");
 conn.connect();

 //Getting the response code
 int responsecode = conn.getResponseCode();

 if (responsecode != 200) {
 throw new RuntimeException("HttpResponseCode: " + responsecode);
 }
 else {

 String inline = "";
 Scanner scanner = new Scanner(url.openStream());

 //Write all the JSON data into a string using a scanner
 while (scanner.hasNext()) {
 inline += scanner.nextLine();
 }

 //Close the scanner
 scanner.close();
 System.out.println(inline);
 /**
 //Using the JSON simple library parse the string into a json object
 JSONParser parse = new JSONParser();
 JSONObject data_obj = (JSONObject) parse.parse(inline);

 //Get the required object from the above created object
 JSONObject obj = (JSONObject) data_obj.get("Global");

 //Get the required data using its key
 System.out.println(obj.get("TotalRecovered"));

 JSONArray arr = (JSONArray) data_obj.get("Countries");

 for (int i = 0; i < arr.size(); i++) {

 JSONObject new_obj = (JSONObject) arr.get(i);
 System.out.println(new_obj);
 }
 }

 }
 catch (Exception e) {
 e.printStackTrace();
 }**/
/**
 Client c = new Client("en_US");

 Map<String, String> arg = new HashMap<String, String>();
 arg.put("keywords", "java");
 arg.put("location", "USA");

 arg.put("affid", "6d4fb47252e9b9f0801a14e7c0830775");

 arg.put("user_ip",    request.getRemoteAddr());
 arg.put("user_agent", request.getHeader("User-Agent"));
 arg.put("url",        request.getRequestURL().toString());

 JSONObject results = (JSONObject) c.search(arg);

 // A list of jobs is returned
 if (results.get("type").equals("JOBS")) {
 JSONArray jobs = (JSONArray) results.get("jobs");
 System.out.println("Number of results:" + results.get("hits"));
 int index = 0;
 while( index < jobs.size()) {
 JSONObject job = (JSONObject) jobs.get(index);
 System.out.println("URL         :" + job.get("url"));
 System.out.println("TITLE       :" + job.get("title"));
 System.out.println("COMPANY     :" + job.get("company"));
 System.out.println("SALARY      :" + job.get("salary"));
 System.out.println("DATE        :" + job.get("date"));
 System.out.println("DESCRIPTION :" + job.get("description"));
 System.out.println("SITE        :" + job.get("site"));
 System.out.println("LOCATIONS   :" + job.get("locations"));
 index++;
 }
 }

 // The location was amiguous. Suggestions are returned.
 // Add the location_id to the query to resolve the ambiguity.
 if (results.get("type").equals("LOCATIONS")) {
 System.out.println("Narrow down your location ");
 System.out.println("Please specify a location");
 JSONArray solvelocations = (JSONArray) results.get("solveLocations");
 int index = 0;
 while(index < solvelocations.size()) {
 JSONObject location = (JSONObject) solvelocations.get(index);
 System.out.println("NAME        :" + location.get("name"));
 System.out.println("LOCATION ID :" + location.get("location_id"));
 index++;
 }
 }

 // An error occured. An error message is returned.
 if (results.get("type").equals("ERROR")) {
 System.out.println("An error occurred whilst processing the search query");
 System.out.println(Error message    :" + results.get("ERROR"));
 }**/


