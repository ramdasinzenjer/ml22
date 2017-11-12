package com.example.lida.myapplication.models;

/**
 * Created by SUDHEESH on 11/11/2017.
 */

public class mp {
    String name,id,constituency;

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

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public mp(String name, String id, String constituency) {
        this.name = name;
        this.id = id;
        this.constituency = constituency;
    }
}
