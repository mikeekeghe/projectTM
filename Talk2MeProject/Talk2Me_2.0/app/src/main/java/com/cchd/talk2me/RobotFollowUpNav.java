package com.cchd.talk2me;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cchd.talk2me.common.ActivityBase;
import com.cchd.talk2me.model.RobotMessage;
import com.cchd.talk2me.model.RobotMessageDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RobotFollowUpNav extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener {
    RobotMessageDBHelper.DatabaseHelper dbHandler;
    private static final String TAG = "ROBOT_FOLLOW_UP";
    private Bundle extras;
    private TextView tvObDate1, tvObMessage, tvWelcome2;
    private ImageButton replyButton;
    private Context context = this;
    private String strDateInFormat;
    private TextView replyText1;
    private EditText replyText2;
    private String strUserReply, strObDate, strUserEmail,obioma_reply1;
    private RobotMessage myRobotMsg;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private Toolbar mToolbar;
    private MediaPlayer mp;
    private LinearLayout myLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_follow_up_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mp = MediaPlayer.create(context, R.raw.click_button);
   /*     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dbHandler = new RobotMessageDBHelper.DatabaseHelper(this, null, null, 1);
        replyText1 = findViewById(R.id.userReply1);
//        tvRepDate1 = findViewById(R.id.tvDate_you);
        replyText2 = findViewById(R.id.messageArea);
//        tvRepDate1 = findViewById(R.id.tvDate_you);
        tvObDate1 = findViewById(R.id.tvDate);
        tvObMessage = findViewById(R.id.tvWelcomeMessage);
        tvWelcome2 = findViewById(R.id.tvWelcomeMessage2);
//        tvObDate2 = findViewById(R.id.tvDate2);

        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        cal.add(Calendar.DATE, 0);
        strDateInFormat = dateFormat.format(cal.getTime());
        Log.d(TAG, "strDateInFormat is :" + strDateInFormat);

        extras = getIntent().getExtras();
        if (extras != null) {
            strUserEmail = extras.getString("MY_USER_EMAIL");
            Log.d(TAG, "my personal user_Email_extra is :" + strUserEmail);
            strObDate = extras.getString("OBIOMA_DATE");
            Log.d(TAG, "my personal user_Email_extra is :" + strObDate);
            strUserReply = extras.getString("USER_REPLY");
            Log.d(TAG, "my strUserReply is :" + strUserReply);
        }
        if (extras == null) {
            Log.d(TAG, "extra is NULLLLLLL" );
        }


        replyText1.setText(strUserReply);
        tvObDate1.setText(strObDate);
//        tvRepDate1.setText(strDateInFormat);
        obioma_reply1 = "Hi "+ strUserReply+", it’s nice to meet you. Can you " +
                "explain a bit about what brings you here  and what you’re hoping " +
                "to get out of therapy support?";

        tvWelcome2.setText(obioma_reply1);
//        tvObDate2.setText(strDateInFormat);

        addListenerOnReplyButton();
        myLayout = findViewById(R.id.layout5);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(5000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myLayout.setVisibility(View.VISIBLE);
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        };
        thread.start();
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    private void addListenerOnReplyButton() {
        replyButton = findViewById(R.id.sendButton);
        replyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                replyText1 = findViewById(R.id.userReply1);
                replyText2 = findViewById(R.id.messageArea);
//                tvRepDate1 = findViewById(R.id.tvDate_you);
                tvObDate1 = findViewById(R.id.tvDate);
//                tvObDate2 = findViewById(R.id.tvDate2);

                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }
                replyText2.getText().toString();
                if (replyText2.length() == 0 ) {
                    showMessage("no data", "invalid data");
                    return;
                }
                Calendar cal = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
                cal.add(Calendar.DATE, 0);
                strDateInFormat = dateFormat.format(cal.getTime());
                Log.d(TAG, "strDateInFormat is :" + strDateInFormat);

                extras = getIntent().getExtras();
                if (extras != null) {
                    strUserEmail = extras.getString("MY_USER_EMAIL");
                    Log.d(TAG, "ser_Email fromextra is :" + strUserEmail);
                }
                if (extras == null) {
                    Log.d(TAG, "extra is NULLLLLLL" );
                }


                tvObDate1.setText(strObDate);
//                tvRepDate1.setText(strDateInFormat);


                Intent intent = new Intent(RobotFollowUpNav.this, RobotFolowUp2Nav.class);
                intent.putExtra("OBIOMA_DATE", strDateInFormat);
                intent.putExtra("OBIOMA_REPLY1", obioma_reply1);
                intent.putExtra("USER_REPLY1", strUserReply);
                intent.putExtra("USER_REPLY2", replyText2.getText().toString());
                intent.putExtra("OBIOMA_DATE2", tvObDate1.getText().toString());
//                intent.putExtra("OBIOMA_DATE3", tvObDate2.getText().toString());
//                intent.putExtra("USER_REPDATE2", tvRepDate1.getText().toString());

                Log.d(TAG, "my reply us Text1 is :" + replyText1);
                Log.d(TAG, "mail_extra is :" + strUserEmail);
                Log.d(TAG, "my obiomaText is :" + tvObMessage.getText().toString());
                Log.d(TAG, "my obiomaDate is :" + tvObDate1.getText().toString());
                Log.d(TAG, "my strDateInFormat is :" + strDateInFormat);
                Log.d(TAG, "my obioma_reply1 is :" + obioma_reply1);

                RobotMessage myRobotMesage = new RobotMessage(strUserEmail,tvObMessage.getText().toString(),
                        tvObDate1.getText().toString(),replyText1.getText().toString(),strDateInFormat,"",
                        obioma_reply1,"","","","","",
                        "","", "");
                //dbHandler.updateRobotMessage(myRobotMesage);

                Log.d(TAG, "after saving object");
                RobotFollowUpNav.this.startActivity(intent);

            }

        });
    }


/*

    @Override
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
    }
*/

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
//            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.robot_follow_up_nav, menu);
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
            startActivity(Intent.createChooser(sharingIntent, "How do you want to share?"));

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
