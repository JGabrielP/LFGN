package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.jgabrielp10.lfgn.Models.Match;
import com.gp.jgabrielp10.lfgn.R;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchweekFragment extends Fragment {

    private List<Match> matchList;
    private List<String> matchNameList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore db;

    public MatchweekFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_matchweek, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_matchweek);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        matchList = new ArrayList<>();
        matchNameList = new ArrayList<>();
        mAdapter = new MatchweekAdapter(matchList, matchNameList);
        recyclerView.setAdapter(mAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection(getArguments().getString("CollectionId")).document(getArguments().getString("DocumentId")).collection("tournaments").orderBy("DateAdded", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable final QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent").equals("Cuartos de final") || queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent").equals("Semifinal") || queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent").equals("Final")) {
                    Log.d("ENTRYYYYY", "onEvent: HOLAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    db.collection(getArguments().getString("CollectionId")).document(getArguments().getString("DocumentId")).collection("liguillas").document(queryDocumentSnapshots.getDocuments().get(0).getString("Name")).collection(queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent")).addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                            matchList.removeAll(matchList);
                            matchNameList.removeAll(matchNameList);
                            for (QueryDocumentSnapshot doc : value) {
                                matchList.add(doc.toObject(Match.class));
                                matchNameList.add(queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent"));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    db.collection(getArguments().getString("CollectionId")).document(getArguments().getString("DocumentId")).collection("tournaments").document(queryDocumentSnapshots.getDocuments().get(0).getString("Name")).collection("Jornadas").document(queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent")).collection("Partidos").addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                            matchList.removeAll(matchList);
                            matchNameList.removeAll(matchNameList);
                            for (QueryDocumentSnapshot doc : value) {
                                matchList.add(doc.toObject(Match.class));
                                matchNameList.add(queryDocumentSnapshots.getDocuments().get(0).getString("MatchCurrent"));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        return v;
    }
}
