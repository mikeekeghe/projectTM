package com.cchd.talk2me;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cchd.talk2me.app.App;
import com.cchd.talk2me.constants.Constants;
import com.cchd.talk2me.dialogs.GenderSelectDialog;
import com.cchd.talk2me.util.CustomRequest;

public class AccountSettingsFragment extends Fragment implements Constants {

    public static final int RESULT_OK = -1;

    private ProgressDialog pDialog;

    private String fullname, location, facebookPage, instagramPage, bio;

    private int sex, year, month, day;
    private int allowShowMyBirthday;

    EditText mFullname, mLocation, mFacebookPage, mInstagramPage, mBio;
    Button mBirth, mGender;

    CheckBox mAllowShowDatebirth;

    private Boolean loading = false;

    public AccountSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        setHasOptionsMenu(true);

        initpDialog();

        Intent i = getActivity().getIntent();
        fullname = i.getStringExtra("fullname");
        location = i.getStringExtra("location");
        facebookPage = i.getStringExtra("facebookPage");
        instagramPage = i.getStringExtra("instagramPage");
        bio = i.getStringExtra("bio");

        sex = i.getIntExtra("sex", 0);

        year = i.getIntExtra("year", 0);
        month = i.getIntExtra("month", 0);
        day = i.getIntExtra("day", 0);

        allowShowMyBirthday = i.getIntExtra("allowShowMyBirthday", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account_settings, container, false);

        if (loading) {

            showpDialog();
        }

        mFullname = (EditText) rootView.findViewById(R.id.fullname);
        mLocation = (EditText) rootView.findViewById(R.id.location);
        mFacebookPage = (EditText) rootView.findViewById(R.id.facebookPage);
        mInstagramPage = (EditText) rootView.findViewById(R.id.instagramPage);
        mBio = (EditText) rootView.findViewById(R.id.bio);

        mBirth = (Button) rootView.findViewById(R.id.selectBirth);
        mGender = (Button) rootView.findViewById(R.id.selectGender);

        mAllowShowDatebirth = (CheckBox) rootView.findViewById(R.id.allowShowDatebirth);

        mBirth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DatePickerDialog dpd = new DatePickerDialog(getActivity(), mDateSetListener, year, month, day);
                dpd.getDatePicker().setMaxDate(new Date().getTime());

                dpd.show();
            }
        });

        mGender.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                selectGender(sex);
            }
        });


        mFullname.setText(fullname);
        mLocation.setText(location);
        mFacebookPage.setText(facebookPage);
        mInstagramPage.setText(instagramPage);
        mBio.setText(bio);

        int mMonth1 = month + 1;

        mBirth.setText(getString(R.string.action_select_birth) + ": " + new StringBuilder().append(day).append("/").append(mMonth1).append("/").append(year));

        checkAllowShowBirthday(allowShowMyBirthday);

        // Inflate the layout for this fragment
        return rootView;
    }

    public void checkAllowShowBirthday(int value) {

        if (value == 1) {

            mAllowShowDatebirth.setChecked(true);
            allowShowMyBirthday = 1;

        } else {

            mAllowShowDatebirth.setChecked(false);
            allowShowMyBirthday = 0;
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {

            year = mYear;
            month = monthOfYear;
            day = dayOfMonth;

            int mMonth1 = month + 1;

            mBirth.setText(getString(R.string.action_select_birth) + ": " + new StringBuilder().append(day).append("/").append(mMonth1).append("/").append(year));

        }

    };

    public void selectGender(int position) {

        android.app.FragmentManager fm = getActivity().getFragmentManager();

        GenderSelectDialog alert = new GenderSelectDialog();

        Bundle b  = new Bundle();
        b.putInt("position", position);

        alert.setArguments(b);
        alert.show(fm, "alert_dialog_select_gender");
    }

    public void getGender(int mSex) {

        sex = mSex;

        if (mSex == 0) {

            mGender.setText(getString(R.string.label_sex_male));

        } else {

            mGender.setText(getString(R.string.label_sex_female));
        }
    }

    public void onDestroyView() {

        super.onDestroyView();

        hidepDialog();
    }

    protected void initpDialog() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(getString(R.string.msg_loading));
        pDialog.setCancelable(false);
    }

    protected void showpDialog() {

        if (!pDialog.isShowing()) pDialog.show();
    }

    protected void hidepDialog() {

        if (pDialog.isShowing()) pDialog.dismiss();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_save: {

                fullname = mFullname.getText().toString();
                location = mLocation.getText().toString();
                facebookPage = mFacebookPage.getText().toString();
                instagramPage = mInstagramPage.getText().toString();
                bio = mBio.getText().toString();

                if (mAllowShowDatebirth.isChecked()) {

                    allowShowMyBirthday = 1;

                } else {

                    allowShowMyBirthday = 0;
                }

                saveSettings();

                return true;
            }

            default: {

                break;
            }
        }

        return false;
    }

    public void saveSettings() {

        loading = true;

        showpDialog();

        CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_SAVE_SETTINGS, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (response.has("error")) {

                                if (!response.getBoolean("error")) {

                                    fullname = response.getString("fullname");
                                    location = response.getString("location");
                                    facebookPage = response.getString("fb_page");
                                    instagramPage = response.getString("instagram_page");
                                    bio = response.getString("status");

                                    Toast.makeText(getActivity(), getText(R.string.msg_settings_saved), Toast.LENGTH_SHORT).show();

                                    App.getInstance().setFullname(fullname);
                                    App.getInstance().saveData();

                                    Intent i = new Intent();
                                    i.putExtra("fullname", fullname);
                                    i.putExtra("location", location);
                                    i.putExtra("facebookPage", facebookPage);
                                    i.putExtra("instagramPage", instagramPage);
                                    i.putExtra("bio", bio);

                                    i.putExtra("sex", sex);

                                    i.putExtra("year", year);
                                    i.putExtra("month", month);
                                    i.putExtra("day", day);

                                    i.putExtra("allowShowMyBirthday", allowShowMyBirthday);

                                    getActivity().setResult(RESULT_OK, i);

                                    getActivity().finish();
                                }
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();

                        } finally {

                            loading = false;

                            hidepDialog();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading = false;

                hidepDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("accountId", Long.toString(App.getInstance().getId()));
                params.put("accessToken", App.getInstance().getAccessToken());
                params.put("fullname", fullname);
                params.put("location", location);
                params.put("facebookPage", facebookPage);
                params.put("instagramPage", instagramPage);
                params.put("bio", bio);
                params.put("sex", Integer.toString(sex));
                params.put("year", Integer.toString(year));
                params.put("month", Integer.toString(month));
                params.put("day", Integer.toString(day));

                params.put("allowShowMyBirthday", Integer.toString(allowShowMyBirthday));

                return params;
            }
        };

        App.getInstance().addToRequestQueue(jsonReq);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}