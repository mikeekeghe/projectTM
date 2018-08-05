package com.cchd.talk2me;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.cchd.talk2me.common.ActivityBase;

import java.util.ArrayList;
import java.util.List;

public class RobotFollowUp3Nav extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "ROBOT_FOLLOW_UP_3";
    Spinner sub_category_Spinner;
    private Bundle extras;
    private ImageButton submitButton;
    private TextView freeButton, highButton, thousandNutton,
            freeButtonDown, highButtonDown, thousandNuttonDown;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private String qString;
    private Toolbar mToolbar;
    private MediaPlayer mp;
    private Context context = this;
    private String strUserNameFrmExtra, billEveryExtra, amountExtra;
    private TextView billedEvery6monthsTop, billedEvery6monthsBottom, billedEvery1monthTop, billedEvery1monthBottom, billedEvery3monthsTop, billedEvery3monthsBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_follow_up3_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        billedEvery6monthsTop = findViewById(R.id.billedEvery6monthsTop);
        billedEvery6monthsBottom = findViewById(R.id.billedEvery6monthsBottom);

        billedEvery1monthTop = findViewById(R.id.billedEvery1monthTop);
        billedEvery1monthBottom = findViewById(R.id.billedEvery1monthBottom);
        billedEvery3monthsTop = findViewById(R.id.billedEvery3monthsTop);
        billedEvery3monthsBottom = findViewById(R.id.billedEvery3monthsBottom);
        setSupportActionBar(toolbar);
        billEveryExtra = "1_month";
        amountExtra = "1000";
        mp = MediaPlayer.create(context, R.raw.click_button);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        submitButton = findViewById(R.id.submitButton);

        freeButton = findViewById(R.id.btnFree);
        highButton = findViewById(R.id.btnHigh);
        thousandNutton = findViewById(R.id.btnThousand);
        freeButtonDown = findViewById(R.id.btnFreeDown);
        highButtonDown = findViewById(R.id.btnHighDown);
        thousandNuttonDown = findViewById(R.id.btnThousandDown);

        extras = getIntent().getExtras();
        if (extras != null) {
            strUserNameFrmExtra = extras.getString("USER_REPLY");
            Log.d(TAG, "my personal oncreate in NAV strUserNameFrmExtra is :" + strUserNameFrmExtra);
        }

        loadSelectMenus();
        addListenerOnReplyButton();

        addListenerOnfreeButton();
        addListenerOnhighButton();
        addListenerOnthousandNutton();
        addListenerOnfreeButtonDown();
        addListenerOnhighButtonDown();
        addListenerOnthousandNuttonDown();
        addListenerOn1monthBilledEveryButton();
        addListenerOn3monthsBilledEveryButton();
        addListenerOn6monthsBilledEveryButton();
    }

    private void addListenerOn6monthsBilledEveryButton() {
        billedEvery6monthsTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "6_month";
                amountExtra = "6000";
                billedEvery6monthsTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery6monthsBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery6monthsTop.setTextColor(Color.WHITE);
                billedEvery6monthsBottom.setTextColor(Color.WHITE);
                //others
                billedEvery1monthTop.setBackgroundColor(Color.WHITE);
                billedEvery1monthBottom.setBackgroundColor(Color.WHITE);
                billedEvery1monthTop.setTextColor(Color.BLACK);
                billedEvery1monthBottom.setTextColor(Color.BLACK);

                billedEvery3monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery3monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery3monthsTop.setTextColor(Color.BLACK);
                billedEvery3monthsBottom.setTextColor(Color.BLACK);
            }
        });
        billedEvery6monthsBottom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "6_month";
                amountExtra = "6000";
                billedEvery6monthsTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery6monthsBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery6monthsTop.setTextColor(Color.WHITE);
                billedEvery6monthsBottom.setTextColor(Color.WHITE);
                //others
                billedEvery1monthTop.setBackgroundColor(Color.WHITE);
                billedEvery1monthBottom.setBackgroundColor(Color.WHITE);
                billedEvery1monthTop.setTextColor(Color.BLACK);
                billedEvery1monthBottom.setTextColor(Color.BLACK);

                billedEvery3monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery3monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery3monthsTop.setTextColor(Color.BLACK);
                billedEvery3monthsBottom.setTextColor(Color.BLACK);
            }
        });
    }

    private void addListenerOn3monthsBilledEveryButton() {
        billedEvery3monthsTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "3_month";
                amountExtra = "3000";
                billedEvery3monthsTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery3monthsBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery3monthsTop.setTextColor(Color.WHITE);
                billedEvery3monthsBottom.setTextColor(Color.WHITE);
                //others
                billedEvery1monthTop.setBackgroundColor(Color.WHITE);
                billedEvery1monthBottom.setBackgroundColor(Color.WHITE);
                billedEvery1monthTop.setTextColor(Color.BLACK);
                billedEvery1monthBottom.setTextColor(Color.BLACK);

                billedEvery6monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery6monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery6monthsTop.setTextColor(Color.BLACK);
                billedEvery6monthsBottom.setTextColor(Color.BLACK);
            }
        });
        billedEvery3monthsBottom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "3_month";
                amountExtra = "3000";
                billedEvery3monthsTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery3monthsBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery3monthsTop.setTextColor(Color.WHITE);
                billedEvery3monthsBottom.setTextColor(Color.WHITE);
                //others
                billedEvery1monthTop.setBackgroundColor(Color.WHITE);
                billedEvery1monthBottom.setBackgroundColor(Color.WHITE);
                billedEvery1monthTop.setTextColor(Color.BLACK);
                billedEvery1monthBottom.setTextColor(Color.BLACK);

                billedEvery6monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery6monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery6monthsTop.setTextColor(Color.BLACK);
                billedEvery6monthsBottom.setTextColor(Color.BLACK);
            }
        });
    }

    private void addListenerOn1monthBilledEveryButton() {
        billedEvery1monthTop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "1_month";
                amountExtra = "1000";
                billedEvery1monthTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery1monthBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery1monthTop.setTextColor(Color.WHITE);
                billedEvery1monthBottom.setTextColor(Color.WHITE);
                //others
                billedEvery6monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery6monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery6monthsTop.setTextColor(Color.BLACK);
                billedEvery6monthsBottom.setTextColor(Color.BLACK);

                billedEvery3monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery3monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery3monthsTop.setTextColor(Color.BLACK);
                billedEvery3monthsBottom.setTextColor(Color.BLACK);
            }
        });
        billedEvery1monthBottom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                billEveryExtra = "1_month";
                amountExtra = "1000";
                billedEvery1monthTop.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery1monthBottom.setBackgroundColor(Color.parseColor("#5B84FA"));
                billedEvery1monthTop.setTextColor(Color.WHITE);
                billedEvery1monthBottom.setTextColor(Color.WHITE);
                //others
                billedEvery6monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery6monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery6monthsTop.setTextColor(Color.BLACK);
                billedEvery6monthsBottom.setTextColor(Color.BLACK);

                billedEvery3monthsTop.setBackgroundColor(Color.WHITE);
                billedEvery3monthsBottom.setBackgroundColor(Color.WHITE);
                billedEvery3monthsTop.setTextColor(Color.BLACK);
                billedEvery3monthsBottom.setTextColor(Color.BLACK);
            }
        });
    }
    private void addListenerOnthousandNutton() {
        thousandNutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3Nav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra);
                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnfreeButtonDown() {
        freeButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3FreeNav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra);
                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnhighButtonDown() {
        highButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3HighNav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra );

                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnthousandNuttonDown() {
        thousandNuttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3Nav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra);
                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnhighButton() {
        highButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3HighNav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra );

                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnfreeButton() {
        freeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, RobotFollowUp3FreeNav.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra );

                RobotFollowUp3Nav.this.startActivity(intent);
            }
        });
    }

    private void addListenerOnReplyButton() {

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }

                Intent intent = new Intent(RobotFollowUp3Nav.this, PaystackActivity.class);
                intent.putExtra("USER_NAME_EXTRA", strUserNameFrmExtra );
                intent.putExtra("BILL_EVERY_EXTRA", billEveryExtra);
                intent.putExtra("AMOUNT_EXTRA", amountExtra);

                Log.d(TAG, "after saving object");
                RobotFollowUp3Nav.this.startActivity(intent);

            }

        });

    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    private void loadSelectMenus() {

        sub_category_Spinner = findViewById(R.id.spinnerSubCategory);
        //sub_category_Spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> subCategoryList = new ArrayList<String>();
        subCategoryList.add("---SELECT ONE---");
        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        strUserNameFrmExtra = extras.getString("USER_NAME_EXTRA");
        Log.d(TAG, "my strUserNameFrmExtra is :" + strUserNameFrmExtra);
        qString = extras.getString("service_category");
        System.out.println("qString is :" + qString);
        if (qString == null) {
            return;
        }
        switch (qString) {
            case "TRAUMA":
                subCategoryList.add("Rape");
                subCategoryList.add("Abuse");
                subCategoryList.add("Trafficking");
                subCategoryList.add("Death");
                subCategoryList.add("Pain");
                break;

            case "ADDICTION":
                subCategoryList.add("Drug");
                subCategoryList.add("Alcohol");
                subCategoryList.add("Substance");
                subCategoryList.add("Sex");
                subCategoryList.add("Internet");

                break;

            case "EMOTIONAL":
                subCategoryList.add("Depression");
                subCategoryList.add("Anxiety");
                subCategoryList.add("Anger");
                subCategoryList.add("Mood Disorder");

                break;

            case "EDUCATION/CAREER":
                subCategoryList.add("Career");
                subCategoryList.add("Self Discovery");
                break;

            case "MARRIAGE/FAMILY":
                subCategoryList.add("Couple Issues");
                subCategoryList.add("Relationship");
                subCategoryList.add("Same Sex Attraction");
                subCategoryList.add("TIN/VAT");
                subCategoryList.add("Divorce Recovery");

                break;

        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subCategoryList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_category_Spinner.setAdapter(adapter2);


    }

    /*@Override
    public void onBackPressed() {
    }*/


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout){
            extras = null;
            prefSetDefaults();
            launchLoginActivity();
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void prefSetDefaults() {
        SP = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        SharedPreferences.Editor editor = SP.edit();
        editor.remove("logged_in_status");
        editor.remove("email");
        editor.remove("displayName");
        editor.apply();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.robot_follow_up3_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.menuLogout){
            extras = null;
            prefSetDefaults();
            launchLoginActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_rate) {
            // Handle the camera action
        } else if (id == R.id.nav_tell) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text));
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.how_share)));

        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
