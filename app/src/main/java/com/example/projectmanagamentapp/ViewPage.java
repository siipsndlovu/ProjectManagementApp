package com.example.projectmanagamentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPage extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Project> projects;
    ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_page);

        recyclerView = findViewById(R.id.projectList);
        projects = new ArrayList<>();

        extractProjects();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ViewAdapter(this, projects);
        recyclerView.setAdapter(adapter);

    }

    private void extractProjects() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "http://web.socem.plymouth.ac.uk/COMP2000/api/students", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Intent intent = getIntent();
                        int s = intent.getIntExtra("id2", 1);
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject projectObject = null;
                            try {
                                projectObject = response.getJSONObject(i);
                                Project project = new Project();
                                project.setProjectID(projectObject.getInt("projectID"));
                                project.setStudentID(projectObject.getInt("studentID"));
                                project.setTitle(projectObject.getString("title").toString());
                                project.setDescription(projectObject.getString("description").toString());
                                project.setYear(projectObject.getInt("year"));
                                project.setFirst_Name(projectObject.getString("first_Name").toString());
                                project.setSecond_Name(projectObject.getString("second_Name").toString());
                                project.setPhoto(projectObject.getString("photo").toString());
                                if (project.getStudentID() == s) {
                                    projects.add(project);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewPage.this, "I can't read!",
                        Toast.LENGTH_LONG).show();
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }
}


