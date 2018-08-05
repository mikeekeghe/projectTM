package com.cchd.talk2me;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LinkActivity extends AppCompatActivity {

    private static final String TAG = "LINK_ACTIVITY";
    private Bundle extras;
    private String myDisplayName;
    private TextView my_name;
    private EditText chat_partner;
    private MediaPlayer mp;
    private Button continueBtn;
    private String strChatPartner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        my_name = findViewById(R.id.my_name);
        chat_partner = findViewById(R.id.chat_partner);
        continueBtn = findViewById(R.id.continueBtn);
        extras = getIntent().getExtras();
        if (extras != null) {
            myDisplayName = extras.getString("DISPLAY_NAME");
            Log.d(TAG, "my myDisplayName from Extra is :" + myDisplayName);
        } else {
            return;
        }
        Log.d(TAG, "myDisplayName at chat e is :" + myDisplayName);
        my_name.setText(myDisplayName);

        addListenerOnContinueButton();
    }

    private void addListenerOnContinueButton() {
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(LinkActivity.this, R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                strChatPartner = chat_partner.getText().toString();
                Intent intent = new Intent(LinkActivity.this, MainChatActivity.class);
                intent.putExtra("USER_NAME_EXTRA", myDisplayName);
                intent.putExtra("CHAT_PARTNER_NAME_EXTRA", strChatPartner);
                LinkActivity.this.startActivity(intent);
            }
        });
    }
}
