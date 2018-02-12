package com.forzafootball.assignment.teams;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.forzafootball.assignment.BaseActivity;
import com.forzafootball.assignment.R;
import com.forzafootball.assignment.Utils;
import com.forzafootball.assignment.teams.domain.Team;

import java.util.List;

/**
 * Activity to show the list of teams fetched from server.
 * Created by Manthena Murali on 2/11/2018.
 */
public class TeamsActivity
        extends BaseActivity
        implements TeamsContract.View, SwipeRefreshLayout.OnRefreshListener {

    private TeamsContract.Presenter mPresenter;
    private TeamsAdapter mTeamsAdapter;
    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        new TeamsPresenter(this);
        populateUI();

        mPresenter.onCreate();
    }

    /**
     * Populate the activity UI
     */
    private void populateUI() {

        // create teams adapter
        mTeamsAdapter = new TeamsAdapter();

        // set adapter to recycler view
        RecyclerView teamsRecyclerView = findViewById(R.id.teams_recycler_view);
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamsRecyclerView.setAdapter(mTeamsAdapter);

        // pull-to-refresh layout
        mSwipeRefreshLayout = findViewById(R.id.teams_swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void fetchTeams() {
        if( Utils.isNetworkAvailable(this) ) {
            mPresenter.fetchTeams();
        } else {
            displayAlert(getString(R.string.no_network_msg));
        }
    }

    @Override
    public void setPresenter(TeamsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoader() {

        if(mProgressDialog == null) {
           mProgressDialog = ProgressDialog.show(this, null, getString(R.string.fetching_teams_message));
        }
        mProgressDialog.show();
    }

    @Override
    public void dismissLoader() {

        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if(mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showTeams(List<Team> teamsList) {
        mTeamsAdapter.updateTeamsList(teamsList);
    }

    @Override
    public void displayAlert(String alertMessage) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(alertMessage);
        alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.create().show();
    }

    @Override
    public void onRefresh() {
        mPresenter.refreshTeams();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
