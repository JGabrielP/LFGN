package com.gp.jgabrielp10.lfgn.Fragments.LeaguesFragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.jgabrielp10.lfgn.Models.League;
import com.gp.jgabrielp10.lfgn.R;
import com.squareup.picasso.Picasso;

import java.util.List;

class AdapterLeagues extends RecyclerView.Adapter<AdapterLeagues.mViewHolder> {
    private List<League> leagueList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView logo;

        public mViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.name_league);
            logo = (ImageView) v.findViewById(R.id.img_logo_league);
        }

        public void bind(final League item, final OnItemClickListener listener) {
            textView.setText(item.Name);
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lfgn-web.appspot.com/o/ligafgn%40gmail.com%2FuserImage%2FawRtO6xTwJQTU99MIlg0sNqEy5K2?alt=media&token=a21f7faa-f469-496b-9830-5043eadda928").placeholder(R.drawable.no_logo).into(logo);
            //logo.setImageResource(R.drawable.no_logo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item.Name);
                }
            });
        }
    }

    public AdapterLeagues(List<League> leaguesList, OnItemClickListener listener) {
        this.leagueList = leaguesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterLeagues.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.league_card, viewGroup, false);
        mViewHolder vh = new mViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        holder.bind(leagueList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return leagueList.size();
    }
}
