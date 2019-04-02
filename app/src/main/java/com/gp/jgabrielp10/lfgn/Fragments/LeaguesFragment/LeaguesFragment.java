package com.gp.jgabrielp10.lfgn.Fragments.LeaguesFragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.InformationLeagueFragment;
import com.gp.jgabrielp10.lfgn.Models.League;
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
 * Activities that contain this fragment must implement the
 * {@link LeaguesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeaguesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeaguesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<League> leaguesList;

    public LeaguesFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeaguesFragment.
     */
    public static LeaguesFragment newInstance(String param1, String param2) {
        LeaguesFragment fragment = new LeaguesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("LFGN");
        View v = inflater.inflate(R.layout.fragment_leagues, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        leaguesList = new ArrayList<>();

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_leagues);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider);
        if (drawable != null)
            dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mAdapter = new AdapterLeagues(leaguesList, new AdapterLeagues.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Bundle bundle = new Bundle();
                InformationLeagueFragment mFragment = new InformationLeagueFragment();
                if (item.equals("Liga 2da. Fuerza")) {
                    bundle.putString("LeagueName", "LIGA 2DA. FUERZA");
                    bundle.putString("CollectionId", "ligafgn@gmail.com");
                    bundle.putString("DocumentId", "awRtO6xTwJQTU99MIlg0sNqEy5K2");
                }
                mFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left).replace(R.id.content_main, mFragment).commit();
            }
        });
        recyclerView.setAdapter(mAdapter);

        String leagueCollection = "";
        leaguesList.removeAll(leaguesList);
        for (int i = 0; i < 1; i++) {
            switch (i) {
                case 0:
                    leagueCollection = "ligafgn@gmail.com";
                    break;
            }
            db.collection(leagueCollection).addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                    for (QueryDocumentSnapshot doc : value)
                        leaguesList.add(doc.toObject(League.class));
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
