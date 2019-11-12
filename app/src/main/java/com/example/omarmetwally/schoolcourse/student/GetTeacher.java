package com.example.omarmetwally.schoolcourse.student;


import java.util.ArrayList;

public  class GetTeacher {

    private  String tid,tFirstName,tLastName;

    ArrayList<String> subject,city,stageName;

    public ArrayList<String> getCity() {
        return city;
    }

    public void setCity(ArrayList<String> city) {
        this.city = city;
    }

    public ArrayList<String> getStageName() {
        return stageName;
    }

    public void setStageName(ArrayList<String> stageName) {
        this.stageName = stageName;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public ArrayList<String> getSubject() {
        return subject;
    }

    public void setSubject(ArrayList<String> subject) {
        this.subject = subject;
    }

    public void settFirstName(String tFirstName) {
        this.tFirstName = tFirstName;
    }

    public void settLastName(String tLastName) {
        this.tLastName = tLastName;
    }

    public String gettFirstName() {
        return tFirstName;
    }

    public String gettLastName() {
        return tLastName;
    }
}
