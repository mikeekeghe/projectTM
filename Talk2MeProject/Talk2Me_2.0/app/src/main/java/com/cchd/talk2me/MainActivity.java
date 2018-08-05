package com.cchd.talk2me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.cchd.talk2me.app.App;
import com.cchd.talk2me.common.ActivityBase;
import com.cchd.talk2me.dialogs.FriendRequestActionDialog;
import com.cchd.talk2me.dialogs.ImageChooseDialog;
import com.cchd.talk2me.dialogs.PeopleNearbySettingsDialog;
import com.cchd.talk2me.dialogs.ProfileBlockDialog;
import com.cchd.talk2me.dialogs.ProfileReportDialog;
import com.cchd.talk2me.dialogs.SearchSettingsDialog;

public class MainActivity extends ActivityBase implements FragmentDrawer.FragmentDrawerListener, ImageChooseDialog.AlertPositiveListener, ProfileReportDialog.AlertPositiveListener, ProfileBlockDialog.AlertPositiveListener, FriendRequestActionDialog.AlertPositiveListener, SearchSettingsDialog.AlertPositiveListener, PeopleNearbySettingsDialog.AlertPositiveListener {

    Toolbar mToolbar;

    private FragmentDrawer drawerFragment;

    // used to store app title
    private CharSequence mTitle;

    LinearLayout mContainerAdmob;

    Fragment fragment;
    Boolean action = false;
    int page = 0;

    private Boolean restore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

            //Restore the fragment's instance
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "currentFragment");

            restore = savedInstanceState.getBoolean("restore");
            mTitle = savedInstanceState.getString("mTitle");

        } else {

            fragment = new Fragment();

            restore = false;
            mTitle = getString(R.string.app_name);
        }

        if (fragment != null) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(mTitle);

        drawerFragment = (FragmentDrawer) getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        mContainerAdmob = (LinearLayout) findViewById(R.id.container_admob);

        if (App.getInstance().getAdmob() == ADMOB_ENABLED) {

            mContainerAdmob.setVisibility(View.VISIBLE);

            AdView mAdView = (AdView) findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        if (!restore) {

            displayView(3);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putBoolean("restore", true);
        outState.putString("mTitle", getSupportActionBar().getTitle().toString());
        getSupportFragmentManager().putFragment(outState, "currentFragment", fragment);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onChangeDistance(int position) {

        PeopleNearbyFragment p = (PeopleNearbyFragment) fragment;
        p.onChangeDistance(position);
    }

    @Override
    public void onCloseSettingsDialog(int searchGender, int searchOnline) {

        SearchFragment p = (SearchFragment) fragment;
        p.onCloseSettingsDialog(searchGender, searchOnline);
    }

    @Override
    public void onAcceptRequest(int position) {

        NotificationsFragment p = (NotificationsFragment) fragment;
        p.onAcceptRequest(position);
    }

    @Override
    public void onRejectRequest(int position) {

        NotificationsFragment p = (NotificationsFragment) fragment;
        p.onRejectRequest(position);
    }

    @Override
    public void onImageFromGallery() {

        ProfileFragment p = (ProfileFragment) fragment;
        p.imageFromGallery();
    }

    @Override
    public void onImageFromCamera() {

        ProfileFragment p = (ProfileFragment) fragment;
        p.imageFromCamera();
    }

    @Override
    public void onProfileReport(int position) {

        ProfileFragment p = (ProfileFragment) fragment;
        p.onProfileReport(position);
    }

    @Override
    public void onProfileBlock() {

        ProfileFragment p = (ProfileFragment) fragment;
        p.onProfileBlock();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {

        displayView(position);
    }

    private void displayView(int position) {

        action = false;

        switch (position) {

            case 0: {

                break;
            }

            case 1: {

                page = 1;

                fragment = new ProfileFragment();
                getSupportActionBar().setTitle(R.string.page_1);

                action = true;

                break;
            }

            case 2: {

                page = 2;

                fragment = new FriendsFragment();
                getSupportActionBar().setTitle(R.string.page_2);
                action = true;

                action = true;

                break;
            }

            case 3: {

                page = 3;

                fragment = new DialogsFragment();
                getSupportActionBar().setTitle(R.string.page_3);
                action = true;

                break;
            }

            case 4: {

                page = 4;

                fragment = new NotificationsFragment();
                getSupportActionBar().setTitle(R.string.page_4);

                action = true;

                break;
            }

            case 5: {

                page = 5;

                fragment = new PeopleNearbyFragment();
                getSupportActionBar().setTitle(R.string.page_5);

                action = true;

                break;
            }

            case 6: {

                page = 6;

                fragment = new SearchFragment();
                getSupportActionBar().setTitle("");

                action = true;

                break;
            }

            default: {

                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(i);
            }
        }

        if (action) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_body, fragment)
                    .commit();
        }
    }

    public void hideAds() {

        if (App.getInstance().getAdmob() == ADMOB_DISABLED) {

            mContainerAdmob.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home: {

                return true;
            }

            default: {

                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onBackPressed() {

        if (drawerFragment.isDrawerOpen()) {

            drawerFragment.closeDrawer();

        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void setTitle(CharSequence title) {

        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
}
