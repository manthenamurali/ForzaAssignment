package com.forzafootball.assignment.teams.domain;

import com.forzafootball.assignment.TasksCallback;
import com.forzafootball.assignment.data.rest.ApiClient;
import com.forzafootball.assignment.data.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Get teams from server
 * Created by Manthena Murali on 2/11/2018.
 */
public class GetTeams {

    public static final String TEAMS_JSON_NAME     =   "teams.json";
    /**
     * callback for the presenter
     */
    private TasksCallback mCallback;

    public GetTeams(TasksCallback callback) {
        mCallback = callback;
    }

    /**
     * Get teams list from server
     */
    public void getTeamsListFromServer() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Team>> call = apiService.fetchTeams(TEAMS_JSON_NAME);
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                mCallback.onComplete(response.body());
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                mCallback.onError(t);
            }
        });
    }
}
