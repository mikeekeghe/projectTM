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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cchd.talk2me.common.ActivityBase;
import com.cchd.talk2me.model.RobotMessage;
import com.cchd.talk2me.model.RobotMessageDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RobotFolowUp2Nav extends ActivityBase
        implements NavigationView.OnNavigationItemSelectedListener {
    RobotMessageDBHelper.DatabaseHelper dbHandler;
    private static final String TAG = "ROBOT_FOLLOW_UP_2";
    Spinner category_Spinner;
    Spinner sub_category_Spinner;
    private Bundle extras;
    private TextView tvObDate1, tvObMessage, tvWelcome2, replyText2,tvObWelcome3;
    private ImageButton submitButton;
    private Context context = this;
    private String strDateInFormat, strUserReply1, strObReply1,strRepDate,strCatName;
    private TextView replyText1, tvObWelcome2;
    private EditText replyText3;
    private String strUserReply, strObDate, strUserEmail,obioma_reply2, strUserReply2,strObDate2, strObDate3;
    private RobotMessage myRobotMsg;
    private Spinner category_Spinner_var;
    private Spinner subCategory_Spinner_var;
    private Spinner paymentMethod_Spinner_var;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private Toolbar mToolbar;
    private MediaPlayer mp;
    private LinearLayout myLayout;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robot_folow_up2_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mp = MediaPlayer.create(context, R.raw.click_button);
    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        replyText1 = findViewById(R.id.userReply1);
//        tvRepDate1 = findViewById(R.id.tvDate_you);
        replyText2 = findViewById(R.id.userReplySecond);
        replyText3 = findViewById(R.id.messageArea);
//        tvRepDate2 = findViewById(R.id.tvDate_you2);
//        tvRepDate3 = findViewById(R.id.tvDate_you3);
        tvObDate1 = findViewById(R.id.tvDate);
        tvObMessage = findViewById(R.id.tvWelcomeMessage);
//        tvObDate2 = findViewById(R.id.tvDate2);
        tvObWelcome2 = findViewById(R.id.tvWelcomeMessage2);
        tvObWelcome3 = findViewById(R.id.obiomaReply2);
        submitButton = findViewById(R.id.submitButton);

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
            Log.d(TAG, "my personal Ob Datel_extra is :" + strObDate);
            strUserReply = extras.getString("USER_REPLY1");
            Log.d(TAG, "my strUserReply1 is :" + strUserReply);
            Log.d(TAG, "my personal Ob Datel_extra is :" + strObDate);
            strUserReply1 = extras.getString("USER_REPLY1");

            strUserReply2 = extras.getString("USER_REPLY2");
            Log.d(TAG, "my strUserReply2 is :" + strUserReply2);
            strObReply1 = extras.getString("OBIOMA_REPLY1");
            Log.d(TAG, "my strUserReply2 is :" + strUserReply2);
            strObDate2 = extras.getString("OBIOMA_DATE2");
            Log.d(TAG, "my strUserReply2 is :" + strObDate2);
            strObDate3 = extras.getString("OBIOMA_DATE3");
            Log.d(TAG, "my strUserReply2 is :" + strObDate3);
            strRepDate = extras.getString("USER_REPDATE2");
            Log.d(TAG, "my strUserReply2 is :" + strRepDate);
        }
        if (extras == null) {
            Log.d(TAG, "extra is NULLLLLLL" );
        }

        replyText1.setText(strUserReply);
        //tvRepDate1.setText(myRobotMsg.getuReplyDate1());
        tvObDate1.setText(strObDate);
//        tvRepDate1.setText(strDateInFormat);
        obioma_reply2 = getString(R.string.obioma_reply_2);

        tvObWelcome2.setText(strObReply1);
//        tvObDate2.setText(strObDate2);
//        tvRepDate2.setText(strObDate3);
//        tvRepDate3.setText(strRepDate);
        replyText1.setText(strUserReply1);
        if (strUserReply2.length() >30){
            replyText2.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }
        replyText2.setText(strUserReply2);
        tvObWelcome3.setText(obioma_reply2);
        myLayout = findViewById(R.id.layout4);

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

        loadSelectMenus();
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
                category_Spinner = findViewById(R.id.spinnerCategory);
                strCatName = String.valueOf(category_Spinner.getSelectedItem());
                if (strCatName.trim().equalsIgnoreCase("---SELECT ONE---")) {
                    Toast.makeText(getApplicationContext(), "Select A Category.", Toast.LENGTH_SHORT).show();
                    return;
                }

        /*        Log.d(TAG, "my RobotMsgis :" + myRobotMsg);

                Log.d(TAG, "getuReplyMsg1 is :" + myRobotMsg.getuReplyMsg1());
                Log.d(TAG, "getuReplyDate1 is :" + myRobotMsg.getuReplyDate1());
                tvRepDate1.setText(myRobotMsg.getuReplyDate1());
*/
                tvObDate1.setText(strObDate);
//                tvRepDate1.setText(strDateInFormat);


                Intent intent = new Intent(RobotFolowUp2Nav.this, RobotFollowUp3Nav.class);
                intent.putExtra("USER_REPLY", strUserReply);
                intent.putExtra("USER_NAME_EXTRA", strUserReply);
                intent.putExtra("service_category", strCatName);

                Log.d(TAG, "my reply us Text1 is :" + replyText1);


        /*        RobotMessage myRobotMesage = new RobotMessage(strUserEmail,tvObMessage.getText().toString(),
                        tvObDate1.getText().toString(),replyText1.getText().toString(),strDateInFormat,"",
                        obioma_reply1,"","","","","",
                        "","", "");
                //dbHandler.updateRobotMessage(myRobotMesage);*/

                Log.d(TAG, "after saving object");
                RobotFolowUp2Nav.this.startActivity(intent);

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
        category_Spinner = findViewById(R.id.spinnerCategory);
        //category_Spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categoryList = new ArrayList<String>();
        categoryList.add("---SELECT ONE---");
        categoryList.add("TRAUMA");
        categoryList.add("ADDICTION");
        categoryList.add("EMOTIONAL");
        categoryList.add("EDUCATION/CAREER");
        categoryList.add("MARRIAGE/FAMILY");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_Spinner.setAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.robot_folow_up2_nav, menu);
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
