package com.forzafootball.assignment.teams;

import com.forzafootball.assignment.TasksCallback;
import com.forzafootball.assignment.teams.domain.GetTeams;
import com.forzafootball.assignment.teams.domain.Team;

import java.util.List;

/**
 * Presenter call for Teams activity. Notify all the UI changes to the teams
 * activity through this presenter
 *
 * Created by Manthena Murali on 2/11/2018.
 */
public class TeamsPresenter implements TeamsContract.Presenter, TasksCallback<List<Team>> {

    private TeamsContract.View mTeamsView;
    private GetTeams mGetTeams;

    TeamsPresenter(final TeamsContract.View teamsView) {
        mTeamsView = teamsView;
        mTeamsView.setPresenter(this);

        mGetTeams = new GetTeams(this);
    }

    @Override
    public void onCreate() {
        //activity onCreate
        mTeamsView.fetchTeams();
    }

    @Override
    public void onDestroy() {
        //activity onDestroy. clean anything required
    }

    @Override
    public void refreshTeams() {
        mGetTeams.getTeamsListFromServer();
    }

    @Override
    public void fetchTeams() {
        mTeamsView.showLoader();
        mGetTeams.getTeamsListFromServer();
    }

    @Override
    public void onComplete(List<Team> result) {
        mTeamsView.showTeams(result);
        mTeamsView.dismissLoader();
    }

    @Override
    public void onError(Throwable exception) {
        mTeamsView.dismissLoader();
        mTeamsView.displayAlert("Failed to fetch teams list. Please try again later.");
    }
}
