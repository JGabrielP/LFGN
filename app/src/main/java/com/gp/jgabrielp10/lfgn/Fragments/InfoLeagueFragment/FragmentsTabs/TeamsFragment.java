package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.jgabrielp10.lfgn.Models.Team;
import com.gp.jgabrielp10.lfgn.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {

    private List<Team> teamList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public TeamsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_teams, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_teams);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        teamList = new ArrayList<>();
        mAdapter = new TeamsAdapter(teamList);
        recyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider);
        if (drawable != null)
            dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(getArguments().getString("CollectionId")).document(getArguments().getString("DocumentId")).collection("teams").orderBy("Name").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                teamList.removeAll(teamList);
                for (QueryDocumentSnapshot doc : value)
                    teamList.add(doc.toObject(Team.class));
                mAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
}
