package com.example.lida.myapplication.models;

/**
 * Created by SUDHEESH on 11/11/2017.
 */

public class mp {
    String name,ministralid,constituency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinistralid() {
        return ministralid;
    }

    public void setMinistralid(String ministralid) {
        this.ministralid = ministralid;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public mp(String name, String ministralid, String constituency) {
        this.name = name;
        this.ministralid = ministralid;
        this.constituency = constituency;
    }
}
