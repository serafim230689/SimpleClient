package com.simpleclient.mvp.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.simpleclient.R;
import com.simpleclient.api.Person;
import com.simpleclient.mvp.details.PersonDetailFragment;
import com.simpleclient.mvp.persons.PersonsFragment;

public class HomeActivity extends AppCompatActivity implements IHomeView {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private PersonsFragment personsFragment;
    private IHomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new HomePresenter(this);
        presenter.attachView();

        initToolbar();
    }

    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_message);

        drawer = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    setActionBarTitle(personsFragment.getTitle(), personsFragment.getBackButtonEnabled());
                } else {
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START);
                    } else {
                        drawer.openDrawer(GravityCompat.START);
                    }
                }
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        View navHeader = navigationView.getHeaderView(0);
        TextView projectNameTitle = navHeader.findViewById(R.id.project_name_title);
        TextView projectVersion = navHeader.findViewById(R.id.project_version);
        projectNameTitle.setText(

                getString(R.string.app_name));
        projectVersion.setText(presenter.getVersionName());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            setActionBarTitle(personsFragment.getTitle(), personsFragment.getBackButtonEnabled());
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showDetailedView(Person person) {
        openDetailsFragment(person);
    }

    @Override
    public void showPersons() {
        openPersonsListFragment();
    }

    public void setActionBarTitle(String title, boolean enabledBackButton) {
        getSupportActionBar().setTitle(title);
        if (enabledBackButton) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            drawerToggle.setDrawerIndicatorEnabled(true);
            drawerToggle.syncState();
        }
    }

    private void openDetailsFragment(Person selectedPerson) {
        PersonDetailFragment selectedFragment = PersonDetailFragment.getInstance(selectedPerson);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(getSupportFragmentManager().findFragmentByTag("personsFragment"));
        ft.add(R.id.contentLayout, selectedFragment);
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    private void openPersonsListFragment() {
        personsFragment = new PersonsFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contentLayout, personsFragment, "personsFragment");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }
}
