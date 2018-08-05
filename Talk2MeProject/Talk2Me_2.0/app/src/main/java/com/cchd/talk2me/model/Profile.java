package com.cchd.talk2me.model;

import android.app.Application;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import com.cchd.talk2me.constants.Constants;


public class Profile extends Application implements Constants, Parcelable {

    private long id;

    private int state, sex, year, month, day, verify, friendsCount, allowShowMyBirthday, allowMessages, lastAuthorize;

    private double distance = 0;

    private String username, fullname, lowPhotoUrl, bigPhotoUrl, normalPhotoUrl, normalCoverUrl, location, facebookPage, instagramPage, bio, lastAuthorizeDate, lastAuthorizeTimeAgo, createDate;

    private Boolean blocked = false;

    private Boolean inBlackList = false;

    private Boolean follower = false;

    private Boolean friend = false;

    private Boolean follow = false;

    private Boolean online = false;

    public Profile() {


    }

    public Profile(JSONObject jsonData) {

        try {

            if (!jsonData.getBoolean("error")) {

                this.setId(jsonData.getLong("id"));
                this.setState(jsonData.getInt("state"));
                this.setSex(jsonData.getInt("sex"));
                this.setYear(jsonData.getInt("year"));
                this.setMonth(jsonData.getInt("month"));
                this.setDay(jsonData.getInt("day"));
                this.setUsername(jsonData.getString("username"));
                this.setFullname(jsonData.getString("fullname"));
                this.setLocation(jsonData.getString("location"));
                this.setFacebookPage(jsonData.getString("fb_page"));
                this.setInstagramPage(jsonData.getString("instagram_page"));
                this.setBio(jsonData.getString("status"));
                this.setVerify(jsonData.getInt("verify"));

                this.setLowPhotoUrl(jsonData.getString("lowPhotoUrl"));
                this.setNormalPhotoUrl(jsonData.getString("normalPhotoUrl"));
                this.setBigPhotoUrl(jsonData.getString("bigPhotoUrl"));

                this.setNormalCoverUrl(jsonData.getString("normalCoverUrl"));

                this.setFriendsCount(jsonData.getInt("friendsCount"));

                this.setAllowMessages(jsonData.getInt("allowMessages"));
                this.setAllowShowMyBirthday(jsonData.getInt("allowShowMyBirthday"));

                this.setInBlackList(jsonData.getBoolean("inBlackList"));
                this.setFollower(jsonData.getBoolean("follower"));
                this.setFriend(jsonData.getBoolean("friend"));
                this.setFollow(jsonData.getBoolean("follow"));
                this.setOnline(jsonData.getBoolean("online"));
                this.setBlocked(jsonData.getBoolean("blocked"));

                this.setLastActive(jsonData.getInt("lastAuthorize"));
                this.setLastActiveDate(jsonData.getString("lastAuthorizeDate"));
                this.setLastActiveTimeAgo(jsonData.getString("lastAuthorizeTimeAgo"));

                this.setCreateDate(jsonData.getString("createDate"));

                if (jsonData.has("distance")) {

                    this.setDistance(jsonData.getDouble("distance"));
                }
            }

        } catch (Throwable t) {

            Log.e("Profile", "Could not parse malformed JSON: \"" + jsonData.toString() + "\"");

        } finally {

            Log.d("Profile", jsonData.toString());
        }
    }

    public void setId(long profile_id) {

        this.id = profile_id;
    }

    public long getId() {

        return this.id;
    }

    public void setState(int profileState) {

        this.state = profileState;
    }

    public int getState() {

        return this.state;
    }

    public void setSex(int sex) {

        this.sex = sex;
    }

    public int getSex() {

        return this.sex;
    }

    public void setYear(int year) {

        this.year = year;
    }

    public int getYear() {

        return this.year;
    }

    public void setMonth(int month) {

        this.month = month;
    }

    public int getMonth() {

        return this.month;
    }

    public void setDay(int day) {

        this.day = day;
    }

    public int getDay() {

        return this.day;
    }

    public void setVerify(int profileVerify) {

        this.verify = profileVerify;
    }

    public int getVerify() {

        return this.verify;
    }

    public Boolean isVerify() {

        if (this.verify > 0) {

            return true;
        }

        return false;
    }

    public void setUsername(String profile_username) {

        this.username = profile_username;
    }

    public String getUsername() {

        return this.username;
    }

    public void setFullname(String profile_fullname) {

        this.fullname = profile_fullname;
    }

    public String getFullname() {

        return this.fullname;
    }

    public void setLocation(String location) {

        this.location = location;
    }

    public String getLocation() {

        if (this.location == null) {

            this.location = "";
        }

        return this.location;
    }

    public void setFacebookPage(String facebookPage) {

        this.facebookPage = facebookPage;
    }

    public String getFacebookPage() {

        return this.facebookPage;
    }

    public void setInstagramPage(String instagramPage) {

        this.instagramPage = instagramPage;
    }

    public String getInstagramPage() {

        return this.instagramPage;
    }

    public void setBio(String bio) {

        this.bio = bio;
    }

    public String getBio() {

        return this.bio;
    }

    public void setLowPhotoUrl(String lowPhotoUrl) {

        this.lowPhotoUrl = lowPhotoUrl;
    }

    public String getLowPhotoUrl() {

        return this.lowPhotoUrl;
    }

    public void setBigPhotoUrl(String bigPhotoUrl) {

        this.bigPhotoUrl = bigPhotoUrl;
    }

    public String getBigPhotoUrl() {

        return this.bigPhotoUrl;
    }

    public void setNormalPhotoUrl(String normalPhotoUrl) {

        this.normalPhotoUrl = normalPhotoUrl;
    }

    public String getNormalPhotoUrl() {

        return this.normalPhotoUrl;
    }

    public void setNormalCoverUrl(String normalCoverUrl) {

        this.normalCoverUrl = normalCoverUrl;
    }

    public String getNormalCoverUrl() {

        return this.normalCoverUrl;
    }

    public void setFriendsCount(int friendsCount) {

        this.friendsCount = friendsCount;
    }

    public int getFriendsCount() {

        return this.friendsCount;
    }

    public void setAllowMessages(int allowMessages) {

        this.allowMessages = allowMessages;
    }

    public int getAllowMessages() {

        return this.allowMessages;
    }

    public void setAllowShowMyBirthday(int allowShowMyBirthday) {

        this.allowShowMyBirthday = allowShowMyBirthday;
    }

    public int getAllowShowMyBirthday() {

        return this.allowShowMyBirthday;
    }

    public void setLastActive(int lastAuthorize) {

        this.lastAuthorize = lastAuthorize;
    }

    public int getLastActive() {

        return this.lastAuthorize;
    }

    public void setDistance(double distance) {

        this.distance = distance;
    }

    public double getDistance() {

        return this.distance;
    }

    public void setLastActiveDate(String lastAuthorizeDate) {

        this.lastAuthorizeDate = lastAuthorizeDate;
    }

    public String getLastActiveDate() {

        return this.lastAuthorizeDate;
    }

    public void setCreateDate(String createDate) {

        this.createDate = createDate;
    }

    public String getCreateDate() {

        return this.createDate;
    }

    public String getBirthDate() {

        int tMonth = this.month + 1;

        return this.day + "/" + tMonth + "/" + this.year;
    }

    public void setLastActiveTimeAgo(String lastAuthorizeTimeAgo) {

        this.lastAuthorizeTimeAgo = lastAuthorizeTimeAgo;
    }

    public String getLastActiveTimeAgo() {

        return this.lastAuthorizeTimeAgo;
    }

    public void setBlocked(Boolean blocked) {

        this.blocked = blocked;
    }

    public Boolean isBlocked() {

        return this.blocked;
    }

    public void setFollower(Boolean follower) {

        this.follower = follower;
    }

    public Boolean isFollower() {

        return this.follower;
    }

    public void setFriend(Boolean friend) {

        this.friend = friend;
    }

    public Boolean isFriend() {

        return this.friend;
    }

    public void setFollow(Boolean follow) {

        this.follow = follow;
    }

    public Boolean isFollow() {

        return this.follow;
    }

    public void setOnline(Boolean online) {

        this.online = online;
    }

    public Boolean isOnline() {

        return this.online;
    }

    public void setInBlackList(Boolean inBlackList) {

        this.inBlackList = inBlackList;
    }

    public Boolean isInBlackList() {

        return this.inBlackList;
    }

    public String getProfileLink() {

        return API_DOMAIN + "profile.php/?id=" + Long.toString(this.getId());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.state);
        dest.writeInt(this.sex);
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
        dest.writeInt(this.verify);
        dest.writeInt(this.friendsCount);
        dest.writeInt(this.allowShowMyBirthday);
        dest.writeInt(this.allowMessages);
        dest.writeInt(this.lastAuthorize);
        dest.writeDouble(this.distance);
        dest.writeString(this.username);
        dest.writeString(this.fullname);
        dest.writeString(this.lowPhotoUrl);
        dest.writeString(this.bigPhotoUrl);
        dest.writeString(this.normalPhotoUrl);
        dest.writeString(this.normalCoverUrl);
        dest.writeString(this.location);
        dest.writeString(this.facebookPage);
        dest.writeString(this.instagramPage);
        dest.writeString(this.bio);
        dest.writeString(this.lastAuthorizeDate);
        dest.writeString(this.lastAuthorizeTimeAgo);
        dest.writeString(this.createDate);
        dest.writeValue(this.blocked);
        dest.writeValue(this.inBlackList);
        dest.writeValue(this.follower);
        dest.writeValue(this.friend);
        dest.writeValue(this.follow);
        dest.writeValue(this.online);
    }

    protected Profile(Parcel in) {
        this.id = in.readLong();
        this.state = in.readInt();
        this.sex = in.readInt();
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.verify = in.readInt();
        this.friendsCount = in.readInt();
        this.allowShowMyBirthday = in.readInt();
        this.allowMessages = in.readInt();
        this.lastAuthorize = in.readInt();
        this.distance = in.readDouble();
        this.username = in.readString();
        this.fullname = in.readString();
        this.lowPhotoUrl = in.readString();
        this.bigPhotoUrl = in.readString();
        this.normalPhotoUrl = in.readString();
        this.normalCoverUrl = in.readString();
        this.location = in.readString();
        this.facebookPage = in.readString();
        this.instagramPage = in.readString();
        this.bio = in.readString();
        this.lastAuthorizeDate = in.readString();
        this.lastAuthorizeTimeAgo = in.readString();
        this.createDate = in.readString();
        this.blocked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.inBlackList = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.follower = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.friend = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.follow = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.online = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
