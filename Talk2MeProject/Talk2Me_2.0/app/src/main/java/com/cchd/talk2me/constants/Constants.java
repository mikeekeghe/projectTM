package com.cchd.talk2me.constants;

public interface Constants {

    public static final Boolean EMOJI_KEYBOARD = true; // false = Do not display your own Emoji keyboard | true = allow display your own Emoji keyboard

    public static final Boolean FACEBOOK_AUTHORIZATION = false; // false = Do not show buttons Login/Signup with Facebook | true = allow display buttons Login/Signup with Facebook

    public static final Boolean WEB_SITE_AVAILABLE = false; // false = Do not show menu items (Open in browser, Copy profile link) in profile page | true = show menu items (Open in browser, Copy profile link) in profile page

    public static final String CLIENT_ID = "1";  //Client ID | For identify the application | Example: 12567

    // Be careful! API_DOMAIN should contain the url and a slash "/" at the end! For example: http://google.com/

    public static final String API_DOMAIN = "http://cchdnigeria.org/android_api/";  //url address to which the application sends requests

    public static final String API_FILE_EXTENSION = ".inc.php";
    public static final String API_VERSION = "v1";

    public static final String METHOD_ACCOUNT_LOGIN = API_DOMAIN + "api/" + API_VERSION + "/method/account.signIn" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_SIGNUP = API_DOMAIN + "api/" + API_VERSION + "/method/account.signUp" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_AUTHORIZE = API_DOMAIN + "api/" + API_VERSION + "/method/account.authorize" + API_FILE_EXTENSION;

    public static final String METHOD_ACCOUNT_SET_GCM_TOKEN = API_DOMAIN + "api/" + API_VERSION + "/method/account.setGcmToken" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_LOGINBYFACEBOOK = API_DOMAIN + "api/" + API_VERSION + "/method/account.signInByFacebook" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_RECOVERY = API_DOMAIN + "api/" + API_VERSION + "/method/account.recovery" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_SETPASSWORD = API_DOMAIN + "api/" + API_VERSION + "/method/account.setPassword" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_DEACTIVATE = API_DOMAIN + "api/" + API_VERSION + "/method/account.deactivate" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_SAVE_SETTINGS = API_DOMAIN + "api/" + API_VERSION + "/method/account.saveSettings" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_CONNECT_TO_FACEBOOK = API_DOMAIN + "api/" + API_VERSION + "/method/account.connectToFacebook" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_DISCONNECT_FROM_FACEBOOK = API_DOMAIN + "api/" + API_VERSION + "/method/account.disconnectFromFacebook" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_LOGOUT = API_DOMAIN + "api/" + API_VERSION + "/method/account.logOut" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_SET_ALLOW_MESSAGES = API_DOMAIN + "api/" + API_VERSION + "/method/account.setAllowMessages" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_SET_GEO_LOCATION = API_DOMAIN + "api/" + API_VERSION + "/method/account.setGeoLocation" + API_FILE_EXTENSION;
    public static final String METHOD_ACCOUNT_UPLOADPHOTO = API_DOMAIN + "api/" + API_VERSION + "/method/account.uploadPhoto" + API_FILE_EXTENSION;

    public static final String METHOD_ACCOUNT_GET_SETTINGS = API_DOMAIN + "api/" + API_VERSION + "/method/account.getSettings" + API_FILE_EXTENSION;

    public static final String METHOD_SUPPORT_SEND_TICKET = API_DOMAIN + "api/" + API_VERSION + "/method/support.sendTicket" + API_FILE_EXTENSION;

    public static final String METHOD_SETTINGS_FOLLOWERS_GCM = API_DOMAIN + "api/" + API_VERSION + "/method/account.setAllowFollowersGCM" + API_FILE_EXTENSION;
    public static final String METHOD_SETTINGS_MESSAGES_GCM = API_DOMAIN + "api/" + API_VERSION + "/method/account.setAllowMessagesGCM" + API_FILE_EXTENSION;

    public static final String METHOD_PROFILE_GET = API_DOMAIN + "api/" + API_VERSION + "/method/profile.get" + API_FILE_EXTENSION;
    public static final String METHOD_PROFILE_FOLLOW = API_DOMAIN + "api/" + API_VERSION + "/method/profile.follow" + API_FILE_EXTENSION;
    public static final String METHOD_PROFILE_REPORT = API_DOMAIN + "api/" + API_VERSION + "/method/profile.report" + API_FILE_EXTENSION;
    public static final String METHOD_PROFILE_UPLOADPHOTO = API_DOMAIN + "api/" + API_VERSION + "/method/profile.uploadPhoto" + API_FILE_EXTENSION;
    public static final String METHOD_PROFILE_UPLOADCOVER = API_DOMAIN + "api/" + API_VERSION + "/method/profile.uploadCover" + API_FILE_EXTENSION;
    public static final String METHOD_PROFILE_PEOPLE_NEARBY_GET = API_DOMAIN + "api/" + API_VERSION + "/method/profile.getPeopleNearby" + API_FILE_EXTENSION;

    public static final String METHOD_BLACKLIST_GET = API_DOMAIN + "api/" + API_VERSION + "/method/blacklist.get" + API_FILE_EXTENSION;
    public static final String METHOD_BLACKLIST_ADD = API_DOMAIN + "api/" + API_VERSION + "/method/blacklist.add" + API_FILE_EXTENSION;
    public static final String METHOD_BLACKLIST_REMOVE = API_DOMAIN + "api/" + API_VERSION + "/method/blacklist.remove" + API_FILE_EXTENSION;

    public static final String METHOD_FRIENDS_GET = API_DOMAIN + "api/" + API_VERSION + "/method/friends.get" + API_FILE_EXTENSION;
    public static final String METHOD_FRIENDS_ACCEPT = API_DOMAIN + "api/" + API_VERSION + "/method/friends.acceptRequest" + API_FILE_EXTENSION;
    public static final String METHOD_FRIENDS_REJECT = API_DOMAIN + "api/" + API_VERSION + "/method/friends.rejectRequest" + API_FILE_EXTENSION;
    public static final String METHOD_FRIENDS_REMOVE = API_DOMAIN + "api/" + API_VERSION + "/method/friends.remove" + API_FILE_EXTENSION;

    public static final String METHOD_NOTIFICATIONS_GET = API_DOMAIN + "api/" + API_VERSION + "/method/notifications.get" + API_FILE_EXTENSION;


    public static final String METHOD_APP_CHECKUSERNAME = API_DOMAIN + "api/" + API_VERSION + "/method/app.checkUsername" + API_FILE_EXTENSION;
    public static final String METHOD_APP_TERMS = API_DOMAIN + "api/" + API_VERSION + "/method/app.terms" + API_FILE_EXTENSION;
    public static final String METHOD_APP_THANKS = API_DOMAIN + "api/" + API_VERSION + "/method/app.thanks" + API_FILE_EXTENSION;
    public static final String METHOD_APP_SEARCH = API_DOMAIN + "api/" + API_VERSION + "/method/app.search" + API_FILE_EXTENSION;

    public static final String METHOD_APP_SEARCH_PRELOAD = API_DOMAIN + "api/" + API_VERSION + "/method/app.searchPreload" + API_FILE_EXTENSION;

    public static final String METHOD_CHAT_GET = API_DOMAIN + "api/" + API_VERSION + "/method/chat.get" + API_FILE_EXTENSION;
    public static final String METHOD_CHAT_REMOVE = API_DOMAIN + "api/" + API_VERSION + "/method/chat.remove" + API_FILE_EXTENSION;
    public static final String METHOD_CHAT_GET_PREVIOUS = API_DOMAIN + "api/" + API_VERSION + "/method/chat.getPrevious" + API_FILE_EXTENSION;
    public static final String METHOD_DIALOGS_GET = API_DOMAIN + "api/" + API_VERSION + "/method/dialogs.get" + API_FILE_EXTENSION;
    public static final String METHOD_CHAT_UPDATE = API_DOMAIN + "api/" + API_VERSION + "/method/chat.update" + API_FILE_EXTENSION;

    public static final String METHOD_MSG_NEW = API_DOMAIN + "api/" + API_VERSION + "/method/msg.new" + API_FILE_EXTENSION;
    public static final String METHOD_MSG_UPLOAD_IMG = API_DOMAIN + "api/" + API_VERSION + "/method/msg.uploadImg" + API_FILE_EXTENSION;

    // added for version 1.3

    public static final String METHOD_GET_STICKERS = API_DOMAIN + "api/" + API_VERSION + "/method/stickers.get" + API_FILE_EXTENSION;

    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE_PHOTO = 1;                  //WRITE_EXTERNAL_STORAGE
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE_COVER = 2;                  //WRITE_EXTERNAL_STORAGE
    public static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 3;                               //ACCESS_COARSE_LOCATION
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 4;                        //WRITE_EXTERNAL_STORAGE

    public static final String APP_TEMP_FOLDER = "chat"; //directory for temporary storage of images from the camera

    public static final int LIST_ITEMS = 20;

    public static final int ENABLED = 1;
    public static final int DISABLED = 0;

    public static final int GCM_ENABLED = 1;
    public static final int GCM_DISABLED = 0;

    public static final int ADMOB_ENABLED = 1;
    public static final int ADMOB_DISABLED = 0;

    public static final int COMMENTS_ENABLED = 1;
    public static final int COMMENTS_DISABLED = 0;

    public static final int MESSAGES_ENABLED = 1;
    public static final int MESSAGES_DISABLED = 0;

    public static final int ERROR_SUCCESS = 0;

    public static final int SEX_UNKNOWN = 0;
    public static final int SEX_MALE = 1;
    public static final int SEX_FEMALE = 2;

    public static final int NOTIFY_TYPE_LIKE = 0;
    public static final int NOTIFY_TYPE_FOLLOWER = 1;
    public static final int NOTIFY_TYPE_MESSAGE = 2;
    public static final int NOTIFY_TYPE_COMMENT = 3;
    public static final int NOTIFY_TYPE_COMMENT_REPLY = 4;
    public static final int NOTIFY_TYPE_FRIEND_REQUEST_ACCEPTED = 5;
    public static final int NOTIFY_TYPE_GIFT = 6;

    public static final int GCM_NOTIFY_CONFIG = 0;
    public static final int GCM_NOTIFY_SYSTEM = 1;
    public static final int GCM_NOTIFY_CUSTOM = 2;
    public static final int GCM_NOTIFY_LIKE = 3;
    public static final int GCM_NOTIFY_ANSWER = 4;
    public static final int GCM_NOTIFY_QUESTION = 5;
    public static final int GCM_NOTIFY_COMMENT = 6;
    public static final int GCM_NOTIFY_FOLLOWER = 7;
    public static final int GCM_NOTIFY_PERSONAL = 8;
    public static final int GCM_NOTIFY_MESSAGE = 9;
    public static final int GCM_NOTIFY_COMMENT_REPLY = 10;
    public static final int GCM_FRIEND_REQUEST_INBOX = 11;
    public static final int GCM_FRIEND_REQUEST_ACCEPTED = 12;
    public static final int GCM_NOTIFY_GIFT = 14;
    public static final int GCM_NOTIFY_SEEN = 15;
    public static final int GCM_NOTIFY_TYPING = 16;
    public static final int GCM_NOTIFY_URL = 17;


    public static final int ERROR_LOGIN_TAKEN = 300;
    public static final int ERROR_EMAIL_TAKEN = 301;
    public static final int ERROR_FACEBOOK_ID_TAKEN = 302;

    public static final int ACCOUNT_STATE_ENABLED = 0;
    public static final int ACCOUNT_STATE_DISABLED = 1;
    public static final int ACCOUNT_STATE_BLOCKED = 2;
    public static final int ACCOUNT_STATE_DEACTIVATED = 3;

    public static final int ACCOUNT_TYPE_USER = 0;
    public static final int ACCOUNT_TYPE_GROUP = 1;

    public static final int ERROR_UNKNOWN = 100;
    public static final int ERROR_ACCESS_TOKEN = 101;

    public static final int ERROR_ACCOUNT_ID = 400;

    public static final int UPLOAD_TYPE_PHOTO = 0;
    public static final int UPLOAD_TYPE_COVER = 1;

    public static final int VIEW_CHAT = 4;
    public static final int SELECT_CHAT_IMG = 6;
    public static final int CREATE_CHAT_IMG = 7;

    public static final int SELECT_PHOTO_IMG = 20;
    public static final int CREATE_PHOTO_IMG = 21;

    public static final String TAG = "TAG";
}