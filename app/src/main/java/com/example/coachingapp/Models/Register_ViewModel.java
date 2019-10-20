package com.example.coachingapp.Models;

public class Register_ViewModel {
    public  String name;
    public  String username;
    public  String  address;
    public  String  birthdate;
    public  String email;
    public  String password;
    public  String reEnterPaswword;


    public Register_ViewModel() {
    }


    public Register_ViewModel(String name, String username, String address, String birthdate, String email, String password, String reEnterPaswword) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.reEnterPaswword = reEnterPaswword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReEnterPaswword() {
        return reEnterPaswword;
    }

    public void setReEnterPaswword(String reEnterPaswword) {
        this.reEnterPaswword = reEnterPaswword;
    }
}
