package com.example.lida.myapplication.models;

/**
 * Created by SUDHEESH on 11/9/2017.
 */

public class mins {
    String name,id,department,constituency;

    public mins(String name, String id, String department, String constituency) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.constituency = constituency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
