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


public class MainGET {

    private static List<ApiObject> apiObjectsList = new ArrayList<>();
    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url("https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&location=Fairfax%2C%20VA&page=1&descending=false")  //https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&page=1&descending=false
                    .method("GET", null)
                    .build();
            Response response = client.newCall(request).execute();
            if(response.code() != 200)
            {
                System.out.println("bad request");
                System.exit(0);
            }
            String jsonData = response.body().string();
            //jsonData = jsonData.substring(1, jsonData.length() - 1);
            JSONObject Jobject = new JSONObject(jsonData);
            JSONArray arr = Jobject.getJSONArray("results");
            for (int i = 0; i < arr.length(); i++) {
                /**
                    JSONObject new_obj = (JSONObject) arr.get(i);
                    JSONObject object = (JSONObject) new_obj.get("refs");
                    System.out.println(object.get("landing_page"));
                    System.out.println(new_obj.get("contents"));
                     **/
                JSONObject new_obj = (JSONObject) arr.get(i);
                String content = new_obj.get("contents").toString();
                String name = new_obj.get("name").toString();
                String type = new_obj.get("type").toString();
                String publication_date = new_obj.get("publication_date").toString();
                String short_name = new_obj.get("short_name").toString();
                String model_type = new_obj.get("model_type").toString();
                String id = new_obj.get("id").toString();

                JSONArray location_object = new_obj.getJSONArray("locations");
                List<String> loc = new ArrayList();
                for (int j = 0; j < location_object.length(); j++) {
                    loc.add(location_object.get(j).toString());
                }
                JSONArray categories_object = new_obj.getJSONArray("categories");
                List<String> cat = new ArrayList();
                for (int j = 0; j < categories_object.length(); j++) {
                    cat.add(categories_object.get(j).toString());
                }
                JSONArray levels_object = new_obj.getJSONArray("levels");
                List<String> lev = new ArrayList();
                for (int j = 0; j < levels_object.length(); j++) {
                    lev.add(levels_object.get(j).toString());
                }
                JSONArray tags_object = new_obj.getJSONArray("tags");
                List<String> tag = new ArrayList();
                for (int j = 0; j < tags_object.length(); j++) {
                    tag.add(tags_object.get(j).toString());
                }
                JSONObject refs_object = (JSONObject) new_obj.get("refs");
                String job_idLink = refs_object.get("landing_page").toString();
                JSONObject company_object = (JSONObject) new_obj.get("company");
                String company_name = company_object.get("name").toString();

                ApiObject apiObject;
                apiObject = new ApiObject(content, name, type, publication_date, short_name, model_type, id, loc, cat, lev, tag, job_idLink, company_name);
                apiObjectsList.add(apiObject);
            }
            System.out.println("help");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}

