package com.example.projectmanagamentapp;

public class Project {
    private int projectID;
    private int studentID;
    private String title;
    private String description;
    private int  year;
    private String first_Name;
    private String second_Name;
    private String photo;

    public Project(){

    }
    public Project(int projectID, int studentID, String title, String description, int year, String first_Name, String second_Name, String photo){
        this.projectID = projectID;
        this.studentID = studentID;
        this.title = title;
        this.description = description;
        this.year = year;
        this.first_Name = first_Name;
        this.second_Name = second_Name;
        this.photo = photo;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return "Description: " + description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFirst_Name() {
        return "Fisrt Name: " + first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getSecond_Name() {
        return "Second Name: " + second_Name;
    }

    public void setSecond_Name(String second_Name) {
        this.second_Name = second_Name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
