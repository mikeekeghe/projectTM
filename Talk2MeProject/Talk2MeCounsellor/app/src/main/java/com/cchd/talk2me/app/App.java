package com.cchd.talk2me.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.cchd.talk2me.R;
import com.cchd.talk2me.constants.Constants;
import com.cchd.talk2me.util.CustomRequest;
import com.cchd.talk2me.util.GPSTracker;
import com.cchd.talk2me.util.LruBitmapCache;

public class App extends Application implements Constants {

	public static final String TAG = App.class.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static App mInstance;

    private SharedPreferences sharedPref;

    private GPSTracker gps;

    private String username, fullname, accessToken, gcmToken = "", fb_id = "", photoUrl, coverUrl, area = "", country = "", city = "";
    private Double lat = 0.000000, lng = 0.000000;
    private long id;
    private int state, admob, verify, allowMessages, allowFollowersGCM, allowMessagesGCM, errorCode, currentChatId = 0, notificationsCount = 0, messagesCount = 0, newFriendsCount = 0;

	@Override
	public void onCreate() {

		super.onCreate();
        mInstance = this;

        sharedPref = this.getSharedPreferences(getString(R.string.settings_file), Context.MODE_PRIVATE);

        this.readData();

        getLocation();
	}

    public void getLocation() {

        if (App.getInstance().isConnected()) {

            gps = new GPSTracker(this);

            if (gps.canGetLocation()) {

                final double latitude = gps.getLatitude();
                final double longitude = gps.getLongitude();

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);

                try {

                    addresses = geocoder.getFromLocation(latitude, longitude, 1);

                    if (addresses.size() > 0) {

                        App.getInstance().setLat(latitude);
                        App.getInstance().setLng(longitude);

                        App.getInstance().setArea(addresses.get(0).getAdminArea());
                        App.getInstance().setCity(addresses.get(0).getLocality());
                        App.getInstance().setCountry(addresses.get(0).getCountryName());

//                        Toast.makeText(this, addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName(), Toast.LENGTH_LONG).show();
                    }

                } catch (IOException e) {

                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (App.getInstance().isConnected() && App.getInstance().getId() != 0 && App.getInstance().getLat() != 0.000000 && App.getInstance().getLng() != 0.000000) {

                    CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_SET_GEO_LOCATION, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

                                    try {

                                        if (!response.getBoolean("error")) {

//                                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {

                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            //Toast.makeText(getApplicationContext(), getString(R.string.msg_network_error), Toast.LENGTH_SHORT).show();
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("accountId", Long.toString(App.getInstance().getId()));
                            params.put("accessToken", App.getInstance().getAccessToken());
                            params.put("lat", Double.toString(latitude));
                            params.put("lng", Double.toString(longitude));

                            return params;
                        }
                    };

                    App.getInstance().addToRequestQueue(jsonReq);
                }
            }
        }
    }
    
    public boolean isConnected() {
    	
    	ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    	
    	NetworkInfo netInfo = cm.getActiveNetworkInfo();
    	
    	if (netInfo != null && netInfo.isConnectedOrConnecting()) {
    		
    		return true;
    	}
    	
    	return false;
    }

    public void logout() {

        if (App.getInstance().isConnected() && App.getInstance().getId() != 0) {

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_LOGOUT, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                if (!response.getBoolean("error")) {



                                }

                            } catch (JSONException e) {

                                e.printStackTrace();

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    App.getInstance().removeData();
                    App.getInstance().readData();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("clientId", CLIENT_ID);
                    params.put("accountId", Long.toString(App.getInstance().getId()));
                    params.put("accessToken", App.getInstance().getAccessToken());

                    return params;
                }
            };

            App.getInstance().addToRequestQueue(jsonReq);

        }

        App.getInstance().removeData();
        App.getInstance().readData();
    }

    public void reload() {

        if (App.getInstance().isConnected() && App.getInstance().getId() != 0) {

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_AUTHORIZE, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            if (!App.getInstance().authorize(response)) {

                                Toast.makeText(getApplicationContext(), getString(R.string.msg_network_error), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(), getString(R.string.msg_network_error), Toast.LENGTH_SHORT).show();
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
        }
    }

    public void getSettings() {

        if (App.getInstance().isConnected() && App.getInstance().getId() != 0) {

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_GET_SETTINGS, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                if (!response.getBoolean("error")) {

                                    if (response.has("messagesCount")) {

                                        App.getInstance().setMessagesCount(response.getInt("messagesCount"));
                                    }
                                }

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(), getString(R.string.msg_network_error), Toast.LENGTH_SHORT).show();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("clientId", CLIENT_ID);
                    params.put("accountId", Long.toString(App.getInstance().getId()));
                    params.put("accessToken", App.getInstance().getAccessToken());

                    return params;
                }
            };

            App.getInstance().addToRequestQueue(jsonReq);
        }
    }

    public void updateGeoLocation() {

        // Now it is empty
        // In this application, there is no a web version and this code has been deleted
    }

    public Boolean authorize(JSONObject authObj) {

        try {

            if (authObj.has("error_code")) {

                this.setErrorCode(authObj.getInt("error_code"));
            }

            if (!authObj.has("error")) {

                return false;
            }

            if (authObj.getBoolean("error")) {

                return false;
            }

            if (!authObj.has("account")) {

                return false;
            }

            JSONArray accountArray = authObj.getJSONArray("account");

            if (accountArray.length() > 0) {

                JSONObject accountObj = (JSONObject) accountArray.get(0);

                this.setUsername(accountObj.getString("username"));
                this.setFullname(accountObj.getString("fullname"));
                this.setState(accountObj.getInt("state"));
                //this.setAdmob(accountObj.getInt("admob"));
                this.setVerify(accountObj.getInt("verify"));
                this.setFacebookId(accountObj.getString("fb_id"));
                this.setAllowMessages(accountObj.getInt("allowMessages"));
                this.setAllowFollowersGCM(accountObj.getInt("allowFollowersGCM"));
                this.setAllowMessagesGCM(accountObj.getInt("allowMessagesGCM"));

                this.setPhotoUrl(accountObj.getString("lowPhotoUrl"));
                this.setCoverUrl(accountObj.getString("coverUrl"));

                this.setNotificationsCount(accountObj.getInt("notificationsCount"));
                this.setMessagesCount(accountObj.getInt("messagesCount"));
                this.setNewFriendsCount(accountObj.getInt("newFriendsCount"));

                if (App.getInstance().getLat() == 0.000000 && App.getInstance().getLng() == 0.000000) {

                    this.setLat(accountObj.getDouble("lat"));
                    this.setLng(accountObj.getDouble("lng"));
                }

                Log.d("Account", accountObj.toString());
            }

            this.setId(authObj.getLong("accountId"));
            this.setAccessToken(authObj.getString("accessToken"));

            this.saveData();

            this.getSettings();

            if (getGcmToken().length() != 0) {

                setGcmToken(getGcmToken());
            }

            return true;

        } catch (JSONException e) {

            e.printStackTrace();
            return false;
        }
    }

    public long getId() {

        return this.id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public void setGcmToken(final String gcmToken) {

        if (this.getId() != 0 && this.getAccessToken().length() != 0) {

            CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_SET_GCM_TOKEN, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {

                                if (!response.getBoolean("error")) {

//                                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {

                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

//                    hidepDialog();
                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("accountId", Long.toString(App.getInstance().getId()));
                    params.put("accessToken", App.getInstance().getAccessToken());

                    params.put("gcm_regId", gcmToken);

                    return params;
                }
            };

            int socketTimeout = 0;//0 seconds - change to what you want
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

            jsonReq.setRetryPolicy(policy);

            App.getInstance().addToRequestQueue(jsonReq);
        }

        this.gcmToken = gcmToken;
    }

    public String getGcmToken() {

        return this.gcmToken;
    }

    public void setFacebookId(String fb_id) {

        this.fb_id = fb_id;
    }

    public String getFacebookId() {

        return this.fb_id;
    }

    public void setState(int state) {

        this.state = state;
    }

    public int getState() {

        return this.state;
    }

    public void setNotificationsCount(int notificationsCount) {

        this.notificationsCount = notificationsCount;
    }

    public int getNotificationsCount() {

        return this.notificationsCount;
    }

    public void setMessagesCount(int messagesCount) {

        this.messagesCount = messagesCount;
    }

    public int getMessagesCount() {

        return this.messagesCount;
    }

    public void setNewFriendsCount(int newFriendsCount) {

        this.newFriendsCount = newFriendsCount;
    }

    public int getNewFriendsCount() {

        return this.newFriendsCount;
    }

    public void setAllowMessagesGCM(int allowMessagesGCM) {

        this.allowMessagesGCM = allowMessagesGCM;
    }

    public int getAllowMessagesGCM() {

        return this.allowMessagesGCM;
    }

    public void setAllowFollowersGCM(int allowFollowersGCM) {

        this.allowFollowersGCM = allowFollowersGCM;
    }

    public int getAllowFollowersGCM() {

        return this.allowFollowersGCM;
    }

    public void setAllowMessages(int allowMessages) {

        this.allowMessages = allowMessages;
    }

    public int getAllowMessages() {

        return this.allowMessages;
    }

    public void setAdmob(int admob) {

        this.admob = admob;
    }

    public int getAdmob() {

        return this.admob;
    }

    public void setCurrentChatId(int currentChatId) {

        this.currentChatId = currentChatId;
    }

    public int getCurrentChatId() {

        return this.currentChatId;
    }

    public void setErrorCode(int errorCode) {

        this.errorCode = errorCode;
    }

    public int getErrorCode() {

        return this.errorCode;
    }

    public String getUsername() {

        if (this.username == null) {

            this.username = "";
        }

        return this.username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getAccessToken() {

        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {

        this.accessToken = accessToken;
    }

    public void setFullname(String fullname) {

        this.fullname = fullname;
    }

    public String getFullname() {

        if (this.fullname == null) {

            this.fullname = "";
        }

        return this.fullname;
    }

    public void setVerify(int verify) {

        this.verify = verify;
    }

    public int getVerify() {

        return this.verify;
    }

    public void setPhotoUrl(String photoUrl) {

        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {

        if (this.photoUrl == null) {

            this.photoUrl = "";
        }

        return this.photoUrl;
    }

    public void setCoverUrl(String coverUrl) {

        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() {

        if (coverUrl == null) {

            this.coverUrl = "";
        }

        return this.coverUrl;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getCountry() {

        if (this.country == null) {

            this.setCountry("");
        }

        return this.country;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public String getCity() {

        if (this.city == null) {

            this.setCity("");
        }

        return this.city;
    }

    public void setArea(String area) {

        this.area = area;
    }

    public String getArea() {

        if (this.area == null) {

            this.setArea("");
        }

        return this.area;
    }

    public void setLat(Double lat) {

        if (this.lat == null) {

            this.lat = 0.000000;
        }

        this.lat = lat;
    }

    public Double getLat() {

        if (this.lat == null) {

            this.lat = 0.000000;
        }

        return this.lat;
    }

    public void setLng(Double lng) {

        if (this.lng == null) {

            this.lng = 0.000000;
        }

        this.lng = lng;
    }

    public Double getLng() {

        return this.lng;
    }

    public void readData() {

        this.setId(sharedPref.getLong(getString(R.string.settings_account_id), 0));
        this.setUsername(sharedPref.getString(getString(R.string.settings_account_username), ""));
        this.setAccessToken(sharedPref.getString(getString(R.string.settings_account_access_token), ""));
        this.setAllowMessagesGCM(sharedPref.getInt(getString(R.string.settings_account_allow_messages_gcm), 1));

        this.setAllowFollowersGCM(sharedPref.getInt(getString(R.string.settings_account_allow_followers_gcm), 1));

        this.setVerify(sharedPref.getInt(getString(R.string.settings_account_verify), 0));
        this.setFullname(sharedPref.getString(getString(R.string.settings_account_fullname), ""));
        this.setPhotoUrl(sharedPref.getString(getString(R.string.settings_account_photo_url), ""));
        this.setCoverUrl(sharedPref.getString(getString(R.string.settings_account_cover_url), ""));
    }

    public void saveData() {

        sharedPref.edit().putLong(getString(R.string.settings_account_id), this.getId()).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_username), this.getUsername()).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_access_token), this.getAccessToken()).apply();
        sharedPref.edit().putInt(getString(R.string.settings_account_allow_messages_gcm), this.getAllowMessagesGCM()).apply();

        sharedPref.edit().putInt(getString(R.string.settings_account_allow_followers_gcm), this.getAllowFollowersGCM()).apply();

        sharedPref.edit().putInt(getString(R.string.settings_account_verify), this.getVerify()).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_fullname), this.getFullname()).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_photo_url), this.getPhotoUrl()).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_cover_url), this.getCoverUrl()).apply();
    }

    public void removeData() {

        sharedPref.edit().putLong(getString(R.string.settings_account_id), 0).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_username), "").apply();
        sharedPref.edit().putString(getString(R.string.settings_account_access_token), "").apply();
        sharedPref.edit().putInt(getString(R.string.settings_account_allow_messages_gcm), 1).apply();

        sharedPref.edit().putInt(getString(R.string.settings_account_allow_followers_gcm), 1).apply();

        sharedPref.edit().putInt(getString(R.string.settings_account_verify), 0).apply();
        sharedPref.edit().putString(getString(R.string.settings_account_fullname), "").apply();
        sharedPref.edit().putString(getString(R.string.settings_account_photo_url), "").apply();
        sharedPref.edit().putString(getString(R.string.settings_account_cover_url), "").apply();
    }

    public static synchronized App getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {

		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public ImageLoader getImageLoader() {
		getRequestQueue();
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader(this.mRequestQueue,
					new LruBitmapCache());
		}
		return this.mImageLoader;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}