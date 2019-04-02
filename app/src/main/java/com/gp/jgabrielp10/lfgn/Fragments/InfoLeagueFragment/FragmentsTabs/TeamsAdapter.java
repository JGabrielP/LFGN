package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.jgabrielp10.lfgn.Models.Team;
import com.gp.jgabrielp10.lfgn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.mViewHolder> {

    private List<Team> teamList;

    public TeamsAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    @NonNull
    @Override
    public TeamsAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teams_card, viewGroup, false);
        mViewHolder vh = new mViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int i) {
        holder.textView.setText(teamList.get(i).Name);
        if (teamList.get(i).LogoUrl != "") {
            Picasso.get().load(teamList.get(i).LogoUrl).placeholder(R.drawable.no_logo).into(holder.logo);
        } else {
            holder.logo.setImageResource(R.drawable.no_logo);
        }
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView logo;

        public mViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.name_team);
            logo = (ImageView) v.findViewById(R.id.img_logo_team);
        }
    }
}
