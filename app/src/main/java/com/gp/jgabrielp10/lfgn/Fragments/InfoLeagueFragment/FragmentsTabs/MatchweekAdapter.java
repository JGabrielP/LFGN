package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gp.jgabrielp10.lfgn.Models.Match;
import com.gp.jgabrielp10.lfgn.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

class MatchweekAdapter extends RecyclerView.Adapter<MatchweekAdapter.mViewHolder> {

    private List<Match> matchList;
    private List<String> matchName;

    public MatchweekAdapter(List<Match> matchList, List<String> matchName) {
        this.matchList = matchList;
        this.matchName = matchName;
    }

    @NonNull
    @Override
    public MatchweekAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.match_card, viewGroup, false);
        mViewHolder vh = new mViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int i) {
        holder.txt_name_match.setText(matchName.get(i));
        if (matchList.get(i).Local.Id.equals("Descansa") || matchList.get(i).Visit.Id.equals("Descansa")) {
            holder.txt_date_match.setText("Descansa");
            holder.txt_final_match.setText("");
            holder.txt_goals_local.setText("");
            holder.txt_goals_visit.setText("");
            holder.divider.setText("");
            holder.txt_visit_name.setText("");
            holder.txt_field_match.setText("");
            holder.img_logo_visit.setImageResource(android.R.color.transparent);
            if (matchList.get(i).Local.Id.equals("Descansa")) {
                holder.txt_local_name.setText(matchList.get(i).Visit.Name);
                if (matchList.get(i).Visit.LogoUrl.equals(""))
                    holder.img_logo_local.setImageResource(R.drawable.no_logo);
                else
                    Picasso.get().load(matchList.get(i).Visit.LogoUrl).placeholder(R.drawable.no_logo).into(holder.img_logo_local);

            } else {
                holder.txt_local_name.setText(matchList.get(i).Local.Name);
                if (matchList.get(i).Local.LogoUrl.equals(""))
                    holder.img_logo_local.setImageResource(R.drawable.no_logo);
                else
                    Picasso.get().load(matchList.get(i).Local.LogoUrl).placeholder(R.drawable.no_logo).into(holder.img_logo_local);
            }
        } else {
            holder.divider.setText(":");
            holder.txt_goals_local.setText("-");
            holder.txt_goals_visit.setText("-");
            holder.txt_local_name.setText(matchList.get(i).Local.Name);
            holder.txt_visit_name.setText(matchList.get(i).Visit.Name);

            if (matchList.get(i).Local.LogoUrl == "")
                holder.img_logo_local.setImageResource(R.drawable.no_logo);
            else
                Picasso.get().load(matchList.get(i).Local.LogoUrl).placeholder(R.drawable.no_logo).into(holder.img_logo_local);

            if (matchList.get(i).Visit.LogoUrl == "")
                holder.img_logo_visit.setImageResource(R.drawable.no_logo);
            else
                Picasso.get().load(matchList.get(i).Visit.LogoUrl).placeholder(R.drawable.no_logo).into(holder.img_logo_visit);

            if (matchList.get(i).Finished) {
                holder.txt_final_match.setText("Final");
                holder.txt_goals_local.setText(String.valueOf(matchList.get(i).GoalsLocal));
                holder.txt_goals_visit.setText(String.valueOf(matchList.get(i).GoalsVisit));
            } else
                holder.txt_final_match.setText("");

            if (matchList.get(i).Date == null)
                holder.txt_date_match.setText("Hora: Sin definir");
            else
                holder.txt_date_match.setText(new SimpleDateFormat("E d '-' MMM '-' yyyy ',' hh:mm aa", new Locale("ES", "MX")).format(matchList.get(i).Date.toDate()));

            if (matchList.get(i).Field == null)
                holder.txt_field_match.setText("Campo: Sin definir");
            else
                holder.txt_field_match.setText(matchList.get(i).Field.Name);
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_local_name, txt_visit_name, txt_date_match, txt_goals_local, txt_goals_visit, divider, txt_field_match, txt_name_match, txt_final_match;
        public ImageView img_logo_local, img_logo_visit;

        public mViewHolder(View v) {
            super(v);
            txt_local_name = (TextView) v.findViewById(R.id.name_team_local_match);
            txt_visit_name = (TextView) v.findViewById(R.id.name_team_visit_match);
            img_logo_local = (ImageView) v.findViewById(R.id.logo_team_local_match);
            img_logo_visit = (ImageView) v.findViewById(R.id.logo_team_visit_match);
            txt_date_match = (TextView) v.findViewById(R.id.txt_date_match);
            txt_goals_local = (TextView) v.findViewById(R.id.goals_team_local_match);
            txt_goals_visit = (TextView) v.findViewById(R.id.goals_team_visit_match);
            divider = (TextView) v.findViewById(R.id.divider_match);
            txt_field_match = (TextView) v.findViewById(R.id.txt_field_match);
            txt_name_match = (TextView) v.findViewById(R.id.txt_name_matchweek);
            txt_final_match = (TextView) v.findViewById(R.id.txt_final_matchweek);
        }
    }
}
