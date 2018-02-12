package com.forzafootball.assignment.teams;

import com.forzafootball.assignment.teams.domain.Team;

import java.util.List;

/**
 * Contract between teams activity and teams presenter
 * Created by Manthena Murali on 2/11/2018.
 */
public class TeamsContract {

    interface View {

        void setPresenter(TeamsContract.Presenter presenter);

        void showLoader();

        void dismissLoader();

        void fetchTeams();

        void showTeams(List<Team> teamsList);

        void displayAlert(String alertMessage);
    }

    interface Presenter {

        void onCreate();

        void fetchTeams();

        void refreshTeams();

        void onDestroy();
    }
}
