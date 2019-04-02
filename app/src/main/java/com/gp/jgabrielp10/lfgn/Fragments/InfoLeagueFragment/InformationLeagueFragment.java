package com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs.LeaderboardFragment;
import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs.LeadergoalFragment;
import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs.MatchweekFragment;
import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.FragmentsTabs.TeamsFragment;
import com.gp.jgabrielp10.lfgn.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InformationLeagueFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InformationLeagueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationLeagueFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public InformationLeagueFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationLeagueFragment.
     */

    public static InformationLeagueFragment newInstance(String param1, String param2) {
        InformationLeagueFragment fragment = new InformationLeagueFragment();
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
        View v = inflater.inflate(R.layout.fragment_information_league, container, false);
        getActivity().setTitle(getArguments().getString("LeagueName"));
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabs = (TabLayout) v.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        TeamsFragment teamsFragment = new TeamsFragment();
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
        LeadergoalFragment leadergoalFragment = new LeadergoalFragment();
        MatchweekFragment matchweekFragment = new MatchweekFragment();
        teamsFragment.setArguments(getArguments());
        leaderboardFragment.setArguments(getArguments());
        leadergoalFragment.setArguments(getArguments());
        matchweekFragment.setArguments(getArguments());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(teamsFragment, "EQUIPOS");
        adapter.addFragment(matchweekFragment, "PARTIDOS");
        adapter.addFragment(leaderboardFragment, "TABLA");
        adapter.addFragment(leadergoalFragment, "GOLEADORES");
        viewPager.setAdapter(adapter);
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
