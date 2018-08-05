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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.MODE_PRIVATE;

public class RobotWelcomActivityNav extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener {
    RobotMessageDBHelper.DatabaseHelper dbHandler;

    private static final String TAG = "ROBOT_WELCOME";
    Date date;
    String strDateInFormat;
    String dateTime;
    private TextView txtEdittextDate, obiomaDate;
    private ImageButton replyButton;
    private Context context = this;
    private EditText replyText;
    private TextView obiomaText;
    private Bundle extras;
    private String myOwnDisplayName, user_Email_extra, strReplyText;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private Toolbar mToolbar;
    private MediaPlayer mp;
    private LinearLayout myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_welcom_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mp = MediaPlayer.create(context, R.raw.click_button);
 /*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        extras = getIntent().getExtras();
        if (extras != null) {
            myOwnDisplayName = extras.getString("MY_DISPLAY_NAME");
            user_Email_extra = extras.getString("MY_USER_EMAIL");
            Log.d(TAG, "my personal user DisplayName is :" + myOwnDisplayName);
            Log.d(TAG, "my personal  user_Email_extra is :" + user_Email_extra);
        }

        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
        cal.add(Calendar.DATE, 0);
        strDateInFormat = dateFormat.format(cal.getTime());
        txtEdittextDate = findViewById(R.id.tvDate);
        myLayout = findViewById(R.id.layout1);
        txtEdittextDate.setText(strDateInFormat);
        addListenerOnReplyButton();

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




    private void addListenerOnReplyButton() {
        replyButton = findViewById(R.id.sendButton);
        replyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                replyText = findViewById(R.id.messageArea);
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(context, R.raw.click_button);
                    } mp.start();
                } catch(Exception e) { e.printStackTrace(); }
                strReplyText = replyText.getText().toString();
                if ((replyText.length() == 0) || (replyText .equals("name") )
                        || (replyText .equals("Name")) || (replyText .equals("NAME") )) {
                    showMessage("no data", "invalid name");
                    return;
                }

                strReplyText = strReplyText.replace(" is ","");
                strReplyText = strReplyText.replace(" name ","");
                strReplyText = strReplyText.replace("name","");
                strReplyText = strReplyText.replace("my ","");
                strReplyText = strReplyText.replace("My ","");
                strReplyText = strReplyText.replace(" my ","");
                strReplyText = strReplyText.replace("name ","");
                strReplyText = strReplyText.replace("Name ","");
                replyText.setText(strReplyText);
                saveRoboMessageData();
                Intent intent = new Intent(RobotWelcomActivityNav.this, RobotFollowUpNav.class);
                intent.putExtra("OBIOMA_DATE", strDateInFormat);
                intent.putExtra("USER_REPLY", strReplyText);
                intent.putExtra("MY_USER_EMAIL", user_Email_extra);
                intent.putExtra("MY_DISPLAY_NAME", myOwnDisplayName);

                RobotWelcomActivityNav.this.startActivity(intent);
            }

        });
    }

    private void saveRoboMessageData() {
        try {
            replyText = findViewById(R.id.messageArea);
            obiomaText = findViewById(R.id.textView);
            obiomaDate = findViewById(R.id.tvDate);
            Log.d(TAG, "my strReply is :" + strReplyText);
            Log.d(TAG, "my user_Email_extra is :" + user_Email_extra);
            Log.d(TAG, "my obiomaText is :" + obiomaText.getText().toString());
            Log.d(TAG, "my obiomaDate is :" + obiomaDate.getText().toString());
            Log.d(TAG, "my strDateInFormat is :" + strDateInFormat);

            RobotMessage myRobotMesage = new RobotMessage(user_Email_extra,obiomaText.getText().toString(),
                    obiomaDate.getText().toString(),strReplyText,strDateInFormat,"",
                    "","","","","","",
                    "","", "");

            dbHandler.saveRobotMessage(myRobotMesage);
            Log.d(TAG, "after saving object");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



/*    @Override
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
//            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.robot_welcom_activity_nav, menu);
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
