package com.example.jerko.employeesinfo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Jerko on 10.4.2017..
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private String name;
    private String surname;
    private String email;
    private String photo;
    private String role;
    private String department;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setDepartment(String department){
        this.department = department;
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

    public String getDepartment(){
        return department;
    }

}
