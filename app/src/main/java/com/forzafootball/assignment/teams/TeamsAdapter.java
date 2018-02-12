package com.forzafootball.assignment.teams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.forzafootball.assignment.R;
import com.forzafootball.assignment.teams.domain.Team;

import java.util.Collections;
import java.util.List;

/**
 * Recycler view adapter to display the list if teams
 *
 * Created by Manthena Murali on 2/11/2018.
 */
public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsHolder> {

    /**
     * Teams list to display in the recycler view
     */
    private List<Team> mTeamsList = Collections.emptyList();

    public static class TeamsHolder extends RecyclerView.ViewHolder {

        private TextView mTeamName;
        private TextView mTeamCountry;

        public TeamsHolder(View itemView) {
            super(itemView);

            mTeamName = itemView.findViewById(R.id.team_name_textView);
            mTeamCountry = itemView.findViewById(R.id.team_country_textView);
        }
    }

    public void updateTeamsList(final List<Team> teamsList) {
        mTeamsList = teamsList;
        notifyDataSetChanged();
    }

    @Override
    public TeamsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team, parent, false);
        return new TeamsHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamsHolder holder, int position) {

        final Team teamToDisplay = mTeamsList.get(position);
        holder.mTeamName.setText(teamToDisplay.getName());
        holder.mTeamCountry.setText(teamToDisplay.getCountryName());
    }

    @Override
    public int getItemCount() {
        if(mTeamsList == null) return 0;
        return mTeamsList.size();
    }
}
