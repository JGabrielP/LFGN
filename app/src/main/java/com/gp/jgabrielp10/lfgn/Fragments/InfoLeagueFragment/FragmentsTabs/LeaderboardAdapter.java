package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.jgabrielp10.lfgn.Models.Leaderboard;
import com.gp.jgabrielp10.lfgn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.mViewHolder> {

    private List<Leaderboard> leaderboardList;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;

    public LeaderboardAdapter(List<Leaderboard> leaderboardList) {
        this.leaderboardList = leaderboardList;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v;
        LeaderboardAdapter.mViewHolder vh;
        if (viewType == TYPE_LIST) {
            v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_card, viewGroup, false);
            vh = new LeaderboardAdapter.mViewHolder(v, viewType);
            return vh;
        } else if (viewType == TYPE_HEAD) {
            v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_leaderboard, viewGroup, false);
            vh = new LeaderboardAdapter.mViewHolder(v, viewType);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.mViewHolder holder, int i) {
        if (holder.view_type == TYPE_LIST) {
            holder.info_pos.setText(String.valueOf(i));
            if (leaderboardList.get(i - 1).team.LogoUrl != "")
                Picasso.get().load(leaderboardList.get(i - 1).team.LogoUrl).placeholder(R.drawable.no_logo).into(holder.info_logo);
            else
                holder.info_logo.setImageResource(R.drawable.no_logo);
            holder.info_name.setText(leaderboardList.get(i - 1).team.Name);
            holder.info_pj.setText(String.valueOf(leaderboardList.get(i - 1).jj));
            holder.info_dg.setText(String.valueOf(leaderboardList.get(i - 1).dg));
            holder.info_pts.setText(String.valueOf(leaderboardList.get(i - 1).pts));
        } else if (holder.view_type == TYPE_HEAD) {
            holder.title_pos.setText("Pos");
            holder.title_nameteam.setText("Equipo");
            holder.title_pj.setText("PJ");
            holder.title_dg.setText("DG");
            holder.title_pts.setText("Pts");
        }
    }

    @Override
    public int getItemCount() {
        return (leaderboardList.size() + 1);
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView info_pos, info_name, info_pj, info_dg, info_pts, title_pos, title_logo, title_nameteam, title_pj, title_dg, title_pts;
        public ImageView info_logo;
        int view_type;

        public mViewHolder(View v, int viewType) {
            super(v);
            if (viewType == TYPE_LIST) {
                info_pos = (TextView) v.findViewById(R.id.txt_info_pos_leaderboard);
                info_logo = (ImageView) v.findViewById(R.id.img_info_logo_leaderboard);
                info_name = (TextView) v.findViewById(R.id.txt_name_team_leaderboard);
                info_pj = (TextView) v.findViewById(R.id.txt_info_pj_leaderboard);
                info_dg = (TextView) v.findViewById(R.id.txt_info_dg_leaderboard);
                info_pts = (TextView) v.findViewById(R.id.txt_info_pts_leaderboard);
                view_type = 1;
            } else if (viewType == TYPE_HEAD) {
                title_pos = (TextView) v.findViewById(R.id.txt_pos_leaderboard);
                title_logo = (TextView) v.findViewById(R.id.txt_logo_leaderboard);
                title_nameteam = (TextView) v.findViewById(R.id.txt_nameteam_leaderboard);
                title_pj = (TextView) v.findViewById(R.id.txt_pj_leaderboard);
                title_dg = (TextView) v.findViewById(R.id.txt_dg_leaderboard);
                title_pts = (TextView) v.findViewById(R.id.txt_pts_leaderboard);
                view_type = 0;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEAD;
        return TYPE_LIST;
    }
}