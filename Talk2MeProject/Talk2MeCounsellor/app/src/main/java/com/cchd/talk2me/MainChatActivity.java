package com.cchd.talk2me;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.cchd.talk2me.model.RobotMessage;
import com.cchd.talk2me.model.RobotMessageDBHelper;
import com.cchd.talk2me.util.NetworkUtils;

public class MainChatActivity extends AppCompatActivity {

    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    private String myDisplayName, dNameString, myDisplayemail, chatPartnerName;
    RobotMessageDBHelper.DatabaseHelper dbHandler;
    private RobotMessage myRobotMsg;

    private static final String TAG = "CHAT";
    Bundle extras;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private String strUserEmail;
    private MediaPlayer mp;
    private Context context = this;
    private String globalSearchResult;
    private String chat_partner_name;
    Random rn = new Random();
    private String m_Text = "";
    private TextView whoWith;
    private TextView tvWhoWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);


       // makeTalk2meUsFetchQuery();
        mp = MediaPlayer.create(this, R.raw.click_button);

        dbHandler = new RobotMessageDBHelper.DatabaseHelper(this, null, null, 1);


        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);
        tvWhoWith = findViewById(R.id.tvWhoWith);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
        ;
        myDisplayName = SP.getString("MY_DISPLAY_NAME", "");
        myDisplayemail = SP.getString("email", "");
        Log.d(TAG, "CHAT email from prefs is :" + myDisplayemail);
        Log.d(TAG, "CHAT myDisplayName from prefs is :" + myDisplayName);

        extras = getIntent().getExtras();
        if (extras != null) {
            myDisplayName = extras.getString("USER_NAME_EXTRA");
            Log.d(TAG, "my myDisplayName from Extra is :" + myDisplayName);
            chatPartnerName = extras.getString("CHAT_PARTNER_NAME_EXTRA");
            Log.d(TAG, "my chatPartnerName from Extra is :" + chatPartnerName);
        } else {
            return;
        }
        tvWhoWith.setText(new StringBuilder().append(getString(R.string.chating_with)).append(getString(R.string.space)).append(chatPartnerName).toString());
        Log.d(TAG, "myDisplayName at chat e is :" + myDisplayName);
        Firebase.setAndroidContext(MainChatActivity.this);
        reference1 = new Firebase("https://talk2me-bc5f9.firebaseio.com/messages/" + myDisplayName + "_" + chatPartnerName);
        reference2 = new Firebase("https://talk2me-bc5f9.firebaseio.com/messages/" + chatPartnerName + "_" + myDisplayName);

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {

                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if (userName.equals(myDisplayName)) {
                    addMessageBox(message, 1);
                } else {
                    addMessageBox(message, 2);
                }
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String messageText = messageArea.getText().toString();

                if (!messageText.equals("")) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", myDisplayName);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });

    }


    private String globalSearchResultMethod() {
        return globalSearchResult;
    }

    public void addMessageBox(String message, int type) {
        TextView textView = new TextView(MainChatActivity.this);
        textView.setText(message);
        //textView.setTextColor(Color.WHITE);
        textView.setPadding(20, 10, 10, 10);



        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if (type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_out2);
            textView.setPadding(20, 10, 10, 10);
            lp2.setMargins(10, 10, 10, 10);
            textView.setTextColor(Color.WHITE);

        } else {
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_in);
            textView.setPadding(20, 10, 10, 10);
            lp2.setMargins(10, 10, 10, 10);
            textView.setTextColor(Color.BLACK);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }


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
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(this, R.raw.click_button);
            }
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {

            super.onBackPressed();

    }



}
