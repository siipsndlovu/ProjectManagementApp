package com.example.projectmanagamentapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CreateInterface {

    @FormUrlEncoded
    @POST("api/students/")
    Call<CreateClass> StudentPost(@Field("projectID") int ProjectID,
                                  @Field("studentID") int StudentID,
                                  @Field("title") String Title,
                                  @Field("description") String Description,
                                  @Field("year") int Year,
                                  String firstName, @Field("first_Name") String FirstName,
                                  @Field("second_Name") String SecondName
    );



}
