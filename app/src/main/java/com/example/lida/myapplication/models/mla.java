package com.example.lida.myapplication.models;

/**
 * Created by SUDHEESH on 11/8/2017.
 */

public class mla {
    String name;
    String id;
    String constituency;

    public mla(String name, String id, String constituency) {
        this.name = name;
        this.id = id;
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

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}
