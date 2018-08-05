package com.cchd.talk2me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.cchd.talk2me.common.ActivityBase;

public class PaystackActivity extends ActivityBase {
    private WebView wv1;
    private TextView tv;
    private Bundle extras;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    private Toolbar mToolbar;
    private String strPaystackAmountFrmExtra, strPystackMonthFrmExtra;
    private static final String TAG = "PAYSTACK";


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paystack);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        wv1= findViewById(R.id.webView);
        tv = findViewById(R.id.tvGoBack);

        extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        strPaystackAmountFrmExtra = extras.getString("AMOUNT_EXTRA");
        strPystackMonthFrmExtra = extras.getString("BILL_EVERY_EXTRA");
        Log.d(TAG, "my strPaystackAmountFrmExtra is :" + strPaystackAmountFrmExtra);
        Log.d(TAG, "my strPystackMonthFrmExtra is :" + strPystackMonthFrmExtra);

        if (strPaystackAmountFrmExtra == null || strPystackMonthFrmExtra == null) {
            return;
        }
        if (strPaystackAmountFrmExtra.equals("1000") && strPystackMonthFrmExtra.equals("1_month")) {
            wv1.loadUrl("https://paystack.com/pay/dg1txn1jfd");
        }
        if (strPaystackAmountFrmExtra.equals("3000") && strPystackMonthFrmExtra.equals("3_month")) {
            wv1.loadUrl("https://paystack.com/pay/yrzm1pd9qk");
        }
        if (strPaystackAmountFrmExtra.equals("6000") && strPystackMonthFrmExtra.equals("6_month")) {
            wv1.loadUrl("https://paystack.com/pay/1bko8qayed");
        }
        if (strPaystackAmountFrmExtra.equals("1500") && strPystackMonthFrmExtra.equals("1_month")) {
            wv1.loadUrl("https://paystack.com/pay/bdnlx040nr");
        }
        if (strPaystackAmountFrmExtra.equals("4500") && strPystackMonthFrmExtra.equals("3_month")) {
            wv1.loadUrl("https://paystack.com/pay/02taaheo53");
        }
        if (strPaystackAmountFrmExtra.equals("9000") && strPystackMonthFrmExtra.equals("6_month")) {
            wv1.loadUrl("https://paystack.com/pay/3ju677cjs8");
        }


        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PaystackActivity.this, RobotFollowUp3Nav.class);
                PaystackActivity.this.startActivity(intent);
            }
        });

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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}