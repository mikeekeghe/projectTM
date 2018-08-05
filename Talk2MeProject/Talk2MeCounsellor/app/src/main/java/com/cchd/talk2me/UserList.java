package com.cchd.talk2me;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.cchd.talk2me.custom.CustomAdapter;
import com.cchd.talk2me.util.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * The Class UserList is the Activity class. It shows a list of all users of
 * this app. It also shows the Offline/Online status of users.
 */
public class UserList extends AppCompatActivity {
    private String globalSearchResult, myOwnDisplayName;
    Bundle extras;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences SP;
    String email = new String();
     ArrayList<String> display_name = new ArrayList<>();
     ArrayList<String> location = new ArrayList<>();
     ArrayList<String> user_id = new ArrayList<>();
     ArrayList<String> online_status = new ArrayList<>();
     ArrayList<String> my_own_display_name = new ArrayList<>();
    private static String TAG = "USER_LIST";

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        Log.d(TAG, "before makeTalk2meSearchQuery");

          makeTalk2meUsFetchQuery();

        Log.d(TAG, "after makeTalk2meSearchQuery");
    }

    private void makeTalk2meUsFetchQuery() {
        URL talk2meSearchUrl = NetworkUtils.buildUserListUrl();
        System.out.println("talk2meSearchUrl is: " + talk2meSearchUrl.toString());
        // COMPLETED (4) Create a new talk2meQueryTask and call its execute method, passing in the url to query
        new UserList.talk2meQueryTask().execute(talk2meSearchUrl);
    }

    public class talk2meQueryTask extends AsyncTask<URL, Void, String> {

        // COMPLETED (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String talk2meSearchResults = null;
            try {
                talk2meSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                System.out.println("talk2meSearchResults is : " + talk2meSearchResults.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return talk2meSearchResults;
        }

        // COMPLETED (3) Override onPostExecute to display the results
        @Override
        protected void onPostExecute(String talk2meSearchResults) {
            if (talk2meSearchResults != null && !talk2meSearchResults.equals("")) {
                System.out.println("talk2meSearchResults is :" + talk2meSearchResults);
                globalSearchResult = talk2meSearchResults;
                loadResultView();
            }
        }
    }

    private void loadResultView() {
        // get the reference of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.list);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {

            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(globalSearchResultMethod());
            // fetch JSONArray named users
            JSONArray usersArray = obj.getJSONArray("users");
            // implement for loop for getting users list data
            for (int i = 0; i < usersArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject srDetail = usersArray.getJSONObject(i);
                // fetch email and name and store it in arraylist

                display_name.add(srDetail.getString("display_name"));
                Log.d(TAG, "display_name is: " + display_name);
                location.add(srDetail.getString("location"));
                Log.d(TAG, "location is: " + location);
                user_id.add(srDetail.getString("id"));
                Log.d(TAG, "user_id is: " + user_id);
                String tempStr = (srDetail.getString("online"));
                Log.d(TAG, "online_status is: " + tempStr);
                if (tempStr.equals("true")) {
                    tempStr = "Counsellor";
                    online_status.add(tempStr);
                } else if (tempStr.equals("false")) {
                    tempStr = "Offline";
                    online_status.add(tempStr);
                }
            }
            extras = getIntent().getExtras();
            if (extras != null) {
                myOwnDisplayName = extras.getString("MY_USER_EMAIL");
//                myOwnDisplayName = extras.getString("MY_DISPLAY_NAME");
                Log.d(TAG, "my personal user DisplayName is :" + myOwnDisplayName);
                extras.putString("MY_DISPLAY_NAME", myOwnDisplayName);
            }
                my_own_display_name.add(myOwnDisplayName);
                Log.d(TAG, "my_own_display_name is: " + myOwnDisplayName);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(UserList.this, display_name,
                location,
                online_status,
                user_id,
                my_own_display_name
        );
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }

    private String globalSearchResultMethod() {
        return globalSearchResult;
    }



    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */

    public void onLogout(View view) {
        extras = null;
        prefSetDefaults();
        launchLoginActivity();
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
    }


}
