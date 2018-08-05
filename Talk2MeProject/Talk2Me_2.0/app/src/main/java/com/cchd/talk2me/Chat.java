package com.cchd.talk2me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cchd.talk2me.model.RobotMessage;
import com.cchd.talk2me.model.RobotMessageDBHelper;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;


/**
 * The Class Chat is the Activity class that holds main chat screen. It shows
 * all the conversation messages between two users and also allows the user to
 * send and receive messages.
 */

public class Chat extends AppCompatActivity {

    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1, reference2;
    String myDisplayName, dNameString, myDisplayemail;
    RobotMessageDBHelper.DatabaseHelper dbHandler;
    private RobotMessage myRobotMsg;

    private static final String TAG = "CHAT";
    Bundle extras;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private String strUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_chat);
        dbHandler = new RobotMessageDBHelper.DatabaseHelper(this, null, null, 1);


        layout =  findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);

        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);;
        myDisplayName = SP.getString("MY_DISPLAY_NAME","");
         myDisplayemail = SP.getString("email","");
        Log.d(TAG, "CHAT email from prefs is :" + myDisplayemail);
        Log.d(TAG, "CHAT myDisplayName from prefs is :" + myDisplayName);


        extras = getIntent().getExtras();
        if (extras != null) {
            strUserEmail = extras.getString("MY_USER_EMAIL");
            Log.d(TAG, "my personal user_Email_extra is :" + strUserEmail);
            String ExtramyDisplayName = extras.getString("MY_DISPLAY_NAME");
            Log.d(TAG, "MY_DISPLAY_NAME from extra is :" + ExtramyDisplayName);
            dNameString = extras.getString("DISPLAY_NAME");
            Log.d(TAG, "other person dNameString is :" + dNameString);
            myDisplayName = extras.getString("MY_DISPLAY_NAME");
            Log.d(TAG, "CHAT myDisplayName is :" + myDisplayName);
        }
        else{
            return;
        }

//        myRobotMsg = dbHandler.findRobotMessageByEmail(strUserEmail);

        Log.d(TAG, "myDisplayName at chat e is :" + myDisplayName);
        myDisplayName = "Emmanuel";
        Log.d(TAG, "myDisplayName at chat e is :" + myDisplayName);

        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://talk2me-d88ef.firebaseio.com/messages/" + myDisplayName + "_" + dNameString);
        reference2 = new Firebase("https://talk2me-d88ef.firebaseio.com/messages/" + dNameString + "_" + myDisplayName);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", myDisplayName);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("message").toString();
                String userName = map.get("user").toString();

                if(userName.equals(myDisplayName)){
                    addMessageBox("You:-\n" + message, 1);
                }
                else{
                    addMessageBox(dNameString + ":-\n" + message, 2);
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
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.bubble_out);
            textView.setPadding(10,10,10,10);
        }
        else{
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.bubble_in);
            textView.setPadding(10,10,10,10);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    @Override
    public void onBackPressed() {
    }

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
        Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
        finish();
    }

}

