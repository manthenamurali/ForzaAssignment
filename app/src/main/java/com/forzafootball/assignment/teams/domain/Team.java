package com.forzafootball.assignment.teams.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manthena Murali on 2/11/2018.
 */

public class Team {

    @SerializedName("name")
    private String name;
    @SerializedName("national")
    private boolean national;
    @SerializedName("country_name")
    private String countryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNational() {
        return national;
    }

    public void setNational(boolean national) {
        this.national = national;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
