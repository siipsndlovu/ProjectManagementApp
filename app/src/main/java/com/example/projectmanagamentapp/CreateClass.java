package com.example.projectmanagamentapp;

import com.google.gson.annotations.SerializedName;

public class CreateClass {

        @SerializedName("projectID")
        private int ProjectId;

        @SerializedName("studentID")
        private int StudentID;

        @SerializedName("title")
        private String Title;

        @SerializedName("description")
        private String Description;

        @SerializedName("year")
        private int Year;

        @SerializedName("first_Name")
        private String FirstName;

        @SerializedName("second_Name")
        private String SecondName;

        @SerializedName("photo")
        private String Photo;

        @SerializedName("response")
        private static String Response;


        public static String getResponse() {
            return Response;
        }
    }
