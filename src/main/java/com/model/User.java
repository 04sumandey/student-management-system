package com.model;

public class User {
    int Id;
    String Name;
    String PhNo;
    String Email;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhNo() {
        return PhNo;
    }

    public void setPhNo(String phNo) {
        PhNo = phNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public User(){}
    public User(int id, String name, String email, String phNo) {
        Id=id;
        Name=name;
        PhNo=phNo;
        Email=email;
    }
}


