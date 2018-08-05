/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cchd.talk2me.util;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String BASE_INSERT_USER_URL =  "https://cchdnigeria.org/android_api/v1/add_user.php";
    final static String BASE_SELECT_USER_URL =  "https://cchdnigeria.org/android_api/v1/select_user.php";
    final static String BASE_USER_LIST_URL =  "https://cchdnigeria.org/android_api/v1/userlist.php";
    final static String BASE_USER_LIST_ONE_URL =  "https://cchdnigeria.org/android_api/v1/userlist_one.php";

    final static String BASE_INSERT_MSG_URL =  "https://cchdnigeria.org/android_api/v1/add_msg.php";
    final static String BASE_SELECT_MSG_URL =  "https://cchdnigeria.org/android_api/v1/select_msg.php";

    final static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_DISPLAY_NAME =  "display_name";
    final static String PARAM_EMAIL =  "email";
    final static String PARAM_ONLINE =  "online";
    final static String PARAM_PHONE =  "phone";
    final static String PARAM_PASSWORD =  "password";
    final static String PARAM_ROOM =  "room";
    final static String PARAM_TOPIC =  "topic";

    final static String sortBy = "stars";
    private static final String PARAM_SORT = "random";

    // Message Constants
    // used Write a message to the database
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddmmss");
    private static final String COLUMN_TEXT = "message";
    private static final String COLUMN_SENDER = "sender";
    final static String PARAM_MESSAGE =  "message";
    final static String PARAM_SENDER_ID =  "sender_id";
    final static String PARAM_MDATE =  "mdate";
    final static String PARAM_SENDER_NAME = "sender_name";



    /**
     * Builds the URL used to query GitHub.
     *
     * @params Keyword that will be queried for.
     */
    public static URL buildInsertUserUrl(String display_nameValue, String emailValue, Boolean onlineValue,
                                         String phoneValue, String passwordValue, String roomValue, String topicValue) {
        Uri builtUri = Uri.parse(BASE_INSERT_USER_URL).buildUpon()
                .appendQueryParameter(PARAM_DISPLAY_NAME, display_nameValue)
                .appendQueryParameter(PARAM_EMAIL, emailValue)
                .appendQueryParameter(PARAM_ONLINE, String.valueOf(onlineValue))
                .appendQueryParameter(PARAM_PHONE, phoneValue)
                .appendQueryParameter(PARAM_PASSWORD, passwordValue)
                .appendQueryParameter(PARAM_ROOM, roomValue)
                .appendQueryParameter(PARAM_TOPIC, topicValue)

//                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static URL buildSelectUserUrl(String emailValue, String passwordValue) {
        Uri builtUri = Uri.parse(BASE_SELECT_USER_URL).buildUpon()
                .appendQueryParameter(PARAM_EMAIL, emailValue)
                .appendQueryParameter(PARAM_PASSWORD, passwordValue)

//                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static URL buildUserListUrl() {
        Uri builtUri = Uri.parse(BASE_USER_LIST_URL).buildUpon()

//                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildUserList_one_Url(String randomNumb) {
        Uri builtUri = Uri.parse(BASE_USER_LIST_ONE_URL).buildUpon()

                .appendQueryParameter(PARAM_SORT, randomNumb)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildInsertMessageUrl(String display_nameValue, Message message,
                                            String senderId, String roomValue, String topicValue) {
        Date date = message.getDate();
        String date_string = sDateFormat.format(date);
        HashMap<String, String> msg = new HashMap<>();
        msg.put(COLUMN_TEXT, message.getText());
        msg.put(COLUMN_SENDER, display_nameValue);
        Uri builtUri = Uri.parse(BASE_INSERT_MSG_URL).buildUpon()
                .appendQueryParameter(PARAM_SENDER_NAME, display_nameValue)
                .appendQueryParameter(PARAM_ROOM, roomValue)
                .appendQueryParameter(PARAM_MESSAGE, message.getText())
                .appendQueryParameter(PARAM_MDATE, date_string)
                .appendQueryParameter(PARAM_SENDER_ID, senderId)
                .appendQueryParameter(PARAM_TOPIC, topicValue)

                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

}