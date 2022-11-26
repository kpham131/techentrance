package com.techentrance.techentrance;

import java.util.*;
import java.lang.*;

import com.techentrance.techentrance.model.Job;
import com.techentrance.techentrance.model.Skill;
import okhttp3.*;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;


public class MainGET {

    public static List<Job> getJobsWithSkills(List<Skill> skills) {
        List<Job> jobs = new ArrayList<>();
        Set<String> jobSet = new HashSet<>();
        List<String> tokens = new ArrayList<String>();
        for(Skill skill : skills) {
            if (skill.getSkill().equals("C")){
                tokens.add(" C");
                tokens.add(" C ");
            }
            else {
                tokens.add(skill.getSkill());
            }

        }
        String patternString = ".*\\b(" + String.join("|", tokens) + ")\\b.*";
        Pattern pattern = Pattern.compile(patternString);

        try {
            int count = 1;
            while(count <= 15) {
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                String sampleUrl = "https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&location=USA&page="+count+"&descending=false";
//                Request request = new Request.Builder()
//                        .url("https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&location=USA&page=1&descending=false")  //https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&page=1&descending=false
//                        .method("GET", null)
//                        .build();
                Request request = new Request.Builder()
                        .url(sampleUrl)  //https://www.themuse.com/api/public/jobs?category=Software%20Engineering&level=Internship&page=1&descending=false
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

                    JSONObject new_obj = (JSONObject) arr.get(i);
                    String content = new_obj.get("contents").toString();
                    String name = new_obj.get("name").toString();
                    if(jobSet.contains(name)) {
                        continue;
                    }
                    else {
                        jobSet.add(name);
                    }
                    String type = new_obj.get("type").toString();
                    String publication_date = new_obj.get("publication_date").toString();
                    String short_name = new_obj.get("short_name").toString();
                    String model_type = new_obj.get("model_type").toString();
                    String id = new_obj.get("id").toString();

                    JSONArray location_object = new_obj.getJSONArray("locations");
                    List<String> loc = new ArrayList();
                    for (int j = 0; j < location_object.length(); j++) {
                        loc.add(location_object.getJSONObject(j).optString("name"));
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
                    Matcher matcher = pattern.matcher(content);
                    String locations = loc.toString().replace("[", "");
                    locations = locations.replace("]", "");
                    if (matcher.matches()) {
                        Job job = new Job(id, content, name, locations, job_idLink, company_name);
                        jobs.add(job);
                    }
                }
                count += 1;
            }
            return jobs;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Java"));
        skills.add(new Skill("C++"));
        getJobsWithSkills(skills);
    }
}

