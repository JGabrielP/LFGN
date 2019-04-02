package com.gp.jgabrielp10.lfgn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gp.jgabrielp10.lfgn.Fragments.InfoLeagueFragment.InformationLeagueFragment;
import com.gp.jgabrielp10.lfgn.Fragments.LeaguesFragment.LeaguesFragment;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        LeaguesFragment.OnFragmentInteractionListener,
        InformationLeagueFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseMessaging.getInstance().subscribeToTopic("all");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Fragment fragment = new LeaguesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment mFragment = null;
        boolean fragmentSelected = false;

        if (id == R.id.nav_home) {
            mFragment = new LeaguesFragment();
            fragmentSelected = true;
        } else if (id == R.id.nav_league1) {
            Bundle bundle = new Bundle();
            bundle.putString("LeagueName", "LIGA 2DA. FUERZA");
            bundle.putString("CollectionId", "ligafgn@gmail.com");
            bundle.putString("DocumentId", "awRtO6xTwJQTU99MIlg0sNqEy5K2");
            mFragment = new InformationLeagueFragment();
            fragmentSelected = true;
            mFragment.setArguments(bundle);
        }

        if (fragmentSelected)
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, mFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }
}
