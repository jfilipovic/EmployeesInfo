package com.example.jerko.employeesinfo.models;

/**
 * Created by Jerko on 10.4.2017..
 */

public class Employee {
    private String name;
    private String surname;
    private String email;
    private String photo;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getRole() {
        return role;
    }

}
