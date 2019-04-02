package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.jgabrielp10.lfgn.Models.Leadergoal;
import com.gp.jgabrielp10.lfgn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class LeadergoalAdapter extends RecyclerView.Adapter<LeadergoalAdapter.mViewHolder> {

    private List<Leadergoal> leaderGoalList;
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_LIST = 1;

    public LeadergoalAdapter(List<Leadergoal> leaderGoalList) {
        this.leaderGoalList = leaderGoalList;
    }

    @NonNull
    @Override
    public LeadergoalAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v;
        LeadergoalAdapter.mViewHolder vh;
        if (viewType == TYPE_LIST) {
            v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leadergoal_item, viewGroup, false);
            vh = new LeadergoalAdapter.mViewHolder(v, viewType);
            return vh;
        } else if (viewType == TYPE_HEAD) {
            v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header_leadergoal, viewGroup, false);
            vh = new LeadergoalAdapter.mViewHolder(v, viewType);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LeadergoalAdapter.mViewHolder holder, int i) {
        if (holder.view_type == TYPE_LIST) {
            holder.info_pos.setText(String.valueOf(i));

            if (leaderGoalList.get(i - 1).Player.Team.LogoUrl != "")
                Picasso.get().load(leaderGoalList.get(i - 1).Player.Team.LogoUrl).placeholder(R.drawable.no_logo).into(holder.info_logo);
            else
                holder.info_logo.setImageResource(R.drawable.no_logo);

            if (leaderGoalList.get(i - 1).Player.PhotoUrl != "")
                Picasso.get().load(leaderGoalList.get(i - 1).Player.PhotoUrl).placeholder(R.drawable.no_photo_player).into(holder.info_photo);
            else
                holder.info_photo.setImageResource(R.drawable.no_photo_player);
            if (leaderGoalList.get(i - 1).Player.LastName.length() > 0)
                holder.info_name.setText(leaderGoalList.get(i - 1).Player.Name + " " + leaderGoalList.get(i - 1).Player.FirstName + " " + leaderGoalList.get(i - 1).Player.LastName.substring(0, 1) + ".");
            else
                holder.info_name.setText(leaderGoalList.get(i - 1).Player.Name);
            holder.info_ngoals.setText(String.valueOf(leaderGoalList.get(i - 1).nGoals));
        } else if (holder.view_type == TYPE_HEAD) {
            holder.title_pos.setText("Pos");
            holder.title_nameplayer.setText("Jugador");
            holder.title_ngoals.setText("Goles");
        }
    }

    @Override
    public int getItemCount() {
        return (leaderGoalList.size() + 1);
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView info_pos, info_name, info_ngoals, title_pos, title_logo, title_photo, title_nameplayer, title_ngoals;
        public ImageView info_logo, info_photo;
        int view_type;

        public mViewHolder(View v, int viewType) {
            super(v);
            if (viewType == TYPE_LIST) {
                info_pos = (TextView) v.findViewById(R.id.txt_info_pos_leadergoal);
                info_logo = (ImageView) v.findViewById(R.id.img_info_logo_leadergoal);
                info_photo = (ImageView) v.findViewById(R.id.img_info_photo_leadergoal);
                info_name = (TextView) v.findViewById(R.id.txt_nameplayer_leadergoal);
                info_ngoals = (TextView) v.findViewById(R.id.txt_info_ngoals_leadergoal);
                view_type = 1;
            } else if (viewType == TYPE_HEAD) {
                title_pos = (TextView) v.findViewById(R.id.txt_pos_leadergoal);
                title_logo = (TextView) v.findViewById(R.id.txt_logo_leadergoal);
                title_photo = (TextView) v.findViewById(R.id.txt_photo_leadergoal);
                title_nameplayer = (TextView) v.findViewById(R.id.txt_name_player_leadergoal);
                title_ngoals = (TextView) v.findViewById(R.id.txt_ngoals_leadergoal);
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
