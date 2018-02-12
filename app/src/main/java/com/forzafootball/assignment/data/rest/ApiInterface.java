package com.forzafootball.assignment.data.rest;

import com.forzafootball.assignment.teams.domain.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * API calls
 * Created by Manthena Murali on 2/11/2018.
 */
public interface ApiInterface {

    @GET("/forza-assignment/android/{teamsJson}")
    Call<List<Team>> fetchTeams(@Path("teamsJson") String teamsJson);
}
