package com.cchd.talk2me;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.cchd.talk2me.app.App;
import com.cchd.talk2me.common.ActivityBase;
import com.cchd.talk2me.util.CustomRequest;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AppActivity extends ActivityBase {

    Button loginBtn, signupBtn;

    RelativeLayout loadingScreen;
    LinearLayout contentScreen;
    private MediaPlayer mp;
    private Bundle extras;
    private String isLogoutTrueValue="";
    private static final String TAG = "PP_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        FacebookSdk.sdkInitialize(getApplicationContext());

        contentScreen = (LinearLayout) findViewById(R.id.contentScreen);
        loadingScreen = (RelativeLayout) findViewById(R.id.loadingScreen);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        App.getInstance().setGcmToken(refreshedToken);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.click_button);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent i = new Intent(AppActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mp.isPlaying()) {
                        mp.stop();
                        mp.release();
                        mp = MediaPlayer.create(getApplicationContext(), R.raw.click_button);
                    }
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                Intent i = new Intent(AppActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        if (App.getInstance().isConnected() && App.getInstance().getId() != 0) {
            showLoadingScreen();

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_AUTHORIZE, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (App.getInstance().authorize(response)) {

                                if (App.getInstance().getState() == ACCOUNT_STATE_ENABLED) {

                                    App.getInstance().updateGeoLocation();

                                    Intent intent = new Intent(AppActivity.this, RobotWelcomActivityNav.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);

                                } else {

                                    showContentScreen();
                                    App.getInstance().logout();
                                }

                            } else {

                                showContentScreen();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    showContentScreen();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("clientId", CLIENT_ID);
                    params.put("accountId", Long.toString(App.getInstance().getId()));
                    params.put("accessToken", App.getInstance().getAccessToken());
                    params.put("gcm_regId", App.getInstance().getGcmToken());

                    return params;
                }
            };

            App.getInstance().addToRequestQueue(jsonReq);

        } else {

            showContentScreen();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void showContentScreen() {

        loadingScreen.setVisibility(View.GONE);

        contentScreen.setVisibility(View.VISIBLE);
    }

    public void showLoadingScreen() {

        contentScreen.setVisibility(View.GONE);

        loadingScreen.setVisibility(View.VISIBLE);
    }


}
