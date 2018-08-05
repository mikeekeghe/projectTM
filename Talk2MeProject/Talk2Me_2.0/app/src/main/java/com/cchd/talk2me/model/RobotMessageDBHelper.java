package com.cchd.talk2me.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Eche Michael on 27/1/2018.
 */
public class RobotMessageDBHelper {

    // RobotMessages Table Column names
    public static final String KEY_ID = "_id";
    public static final String KEY_O_WELCOME_MESSAGE = "oWelcomeMessage";
    public static final String KEY_O_WELCOME_DATE = "oWelcomeDate";
    public static final String KEY_U_REPLY_MSG_1 = "uReplyMsg1";
    public static final String KEY_U_REPLY_DATE_1 = "uReplyDate1";
    public static final String KEY_U_REPLY_MSG_2 = "uReplyMsg2";
    public static final String KEY_U_REPLY_DATE_2 = "uReplyDate2";
    public static final String KEY_U_REPLY_MSG_3 = "uReplyMsg3";
    public static final String KEY_U_REPLY_DATE_3 = "uReplyDate3";
    public static final String KEY_O_REPLY_FOLLOWUP_1 = "oReplyFollowUp1";
    public static final String KEY_O_REPLY_FOLLOWUP_DATE_1 = "oReplyFollowUpDate1";
    public static final String KEY_O_REPLY_FOLLOWUP_2 = "oReplyFollowUp2";
    public static final String KEY_O_REPLY_FOLLOWUP_DATE_2 = "oReplyFollowUpDate2";
    public static final String KEY_O_REPLY_FOLLOWUP_3 = "oReplyFollowUp3";
    public static final String KEY_O_REPLY_FOLLOWUP_DATE_3 = "oReplyFollowUpDate3";
    public static final String KEY_USER_EMAIL = "user_email";

   // public static final String KEY_DOCTOR_TREATING= "doctor_treating";
    // public static final String KEY_NOTES = "notes";

    private static final String TAG = RobotMessageDBHelper.class.getSimpleName();
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    // Database Name
    private static final String DATABASE_NAME = "myRobotMessageDB.db";
    //  table name
    private static final String TABLE_ROBOT_MESSAGES = "myRobotMessagesTbl";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_ROBOT_MESSAGE_TABLE = "CREATE TABLE " + TABLE_ROBOT_MESSAGES + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_O_WELCOME_MESSAGE + " TEXT,"
            + KEY_U_REPLY_DATE_3 + " TEXT,"
            + KEY_O_WELCOME_DATE + " TEXT,"
            + KEY_U_REPLY_MSG_1 + " TEXT,"
            + KEY_U_REPLY_DATE_1 + " TEXT,"
            + KEY_U_REPLY_MSG_2 + " TEXT,"
            + KEY_U_REPLY_DATE_2 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_DATE_3 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_1 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_DATE_1 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_2 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_DATE_2 + " TEXT,"
            + KEY_O_REPLY_FOLLOWUP_3 + " TEXT,"
            + KEY_USER_EMAIL + " TEXT,"
            + KEY_U_REPLY_MSG_3 + " TEXT" + ")";

    private final Context mContext;

        public static class DatabaseHelper extends SQLiteOpenHelper {
            public DatabaseHelper(Context context, String name,
                                  SQLiteDatabase.CursorFactory factory, int version) {
                super(context, DATABASE_NAME, factory, DATABASE_VERSION);
            }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_ROBOT_MESSAGE_TABLE);
            Log.d(TAG, "OnCreate() Database");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROBOT_MESSAGES);
            Log.d(TAG, "OnUpgrade() Database");
        // Creating tables again
            onCreate(db);
        } // Adding new myRobotMessage
        public void addRobotMessage(RobotMessage myRobotMessage) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_O_WELCOME_MESSAGE, myRobotMessage.getoWelcomeMessage()); // RobotMessage itself
            values.put(KEY_U_REPLY_DATE_3, myRobotMessage.getuReplyDate3()); //
            values.put(KEY_O_WELCOME_DATE, myRobotMessage.getoWelcomeDate()); //
            values.put(KEY_U_REPLY_MSG_1, myRobotMessage.getuReplyMsg1()); //
            values.put(KEY_U_REPLY_DATE_1, myRobotMessage.getuReplyDate1()); //
            values.put(KEY_U_REPLY_MSG_2, myRobotMessage.getuReplyMsg2()); //
            values.put(KEY_U_REPLY_DATE_2, myRobotMessage.getuReplyDate2()); //
            values.put(KEY_O_REPLY_FOLLOWUP_DATE_3, myRobotMessage.getoReplyFollowUpDate3()); //
            values.put(KEY_O_REPLY_FOLLOWUP_1, myRobotMessage.getoReplyFollowUp1()); //
            values.put(KEY_O_REPLY_FOLLOWUP_DATE_1, myRobotMessage.getoReplyFollowUpDate1()); //
            values.put(KEY_O_REPLY_FOLLOWUP_2, myRobotMessage.getoReplyFollowUp2()); //
            values.put(KEY_O_REPLY_FOLLOWUP_DATE_2, myRobotMessage.getoReplyFollowUpDate2()); //
            values.put(KEY_O_REPLY_FOLLOWUP_3, myRobotMessage.getoReplyFollowUp3());
            values.put(KEY_USER_EMAIL, myRobotMessage.getUser_email());

// Inserting Row
            db.insert(TABLE_ROBOT_MESSAGES, null, values);
            db.close(); // Closing database connection
        }

//        // Getting one myRobotMessage
//        public RobotMessage getRobotMessage(int id) {
//            SQLiteDatabase db = this.getReadableDatabase();
//
//            Cursor cursor = db.query(TABLE_ROBOT_MESSAGES, new String[]{
//                            KEY_O_WELCOME_MESSAGE, KEY_U_REPLY_DATE_3, KEY_O_WELCOME_DATE, KEY_U_REPLY_MSG_1, KEY_U_REPLY_DATE_1, KEY_U_REPLY_MSG_2, KEY_U_REPLY_DATE_2, KEY_U_REPLY_MSG_3}, KEY_ID + "=?",
//                    new String[]{String.valueOf(id)}, null, null, null, null);
//            if (cursor != null)
//                cursor.moveToFirst();
//
//            assert cursor != null;
//            RobotMessage myRobotMessage = new RobotMessage(cursor.getString(0),
//                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
//                    cursor.getString(4), cursor.getString(5), cursor.getString(6),
//                    cursor.getString(7));
//            // return myRobotMessage
//            return myRobotMessage;
//        }

        //Getting Cursor to read data from table
        public Cursor readData() {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] allColumns = new String[] { RobotMessageDBHelper.KEY_O_WELCOME_MESSAGE,
                    RobotMessageDBHelper.KEY_U_REPLY_MSG_1 };
            Cursor c = db.query(RobotMessageDBHelper.TABLE_ROBOT_MESSAGES, allColumns, null,
                    null, null, null, null);
            if (c != null) {
                c.moveToFirst();
            }
            return c;
        }

        /**
         * Gets all myRobotMessages in the Database and returns a cursor of them.
         * If there are no items in the database then the cursor returns null
         *
         * @return A Cursor of all myRobotMessages or null
         */
        public Cursor getAllRobotMessageCursor() {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_ROBOT_MESSAGES, new String[] {KEY_ID, KEY_O_WELCOME_MESSAGE,KEY_U_REPLY_DATE_2,
                    KEY_U_REPLY_MSG_1, KEY_U_REPLY_DATE_1,KEY_U_REPLY_MSG_2, KEY_U_REPLY_DATE_3, KEY_O_WELCOME_DATE,KEY_O_REPLY_FOLLOWUP_DATE_3,
                    KEY_O_REPLY_FOLLOWUP_1, KEY_O_REPLY_FOLLOWUP_DATE_1,KEY_O_REPLY_FOLLOWUP_2, KEY_O_REPLY_FOLLOWUP_DATE_2, KEY_O_REPLY_FOLLOWUP_3,KEY_USER_EMAIL},
                    null, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                return cursor;
            }
            else
            {
                return null;
            }
        }

            public void saveRobotMessage(RobotMessage myRobotMessage) {
                ContentValues values = new ContentValues();
                SQLiteDatabase db = this.getWritableDatabase();
                values.put(KEY_USER_EMAIL, myRobotMessage.getUser_email()); //
                values.put(KEY_O_WELCOME_MESSAGE, myRobotMessage.getoWelcomeMessage()); // RobotMessage itself
                values.put(KEY_U_REPLY_DATE_3, myRobotMessage.getuReplyDate3()); //
                values.put(KEY_O_WELCOME_DATE, myRobotMessage.getoWelcomeDate()); //
                values.put(KEY_U_REPLY_MSG_1, myRobotMessage.getuReplyMsg1()); //
                values.put(KEY_U_REPLY_DATE_1, myRobotMessage.getuReplyDate1()); //
                values.put(KEY_U_REPLY_MSG_2, myRobotMessage.getuReplyMsg2()); //
                values.put(KEY_U_REPLY_DATE_2, myRobotMessage.getuReplyDate2()); //
                values.put(KEY_O_REPLY_FOLLOWUP_DATE_3, myRobotMessage.getoReplyFollowUpDate3()); //
                values.put(KEY_O_REPLY_FOLLOWUP_1, myRobotMessage.getoReplyFollowUp1()); //
                values.put(KEY_O_REPLY_FOLLOWUP_DATE_1, myRobotMessage.getoReplyFollowUpDate1()); //
                values.put(KEY_O_REPLY_FOLLOWUP_2, myRobotMessage.getoReplyFollowUp2()); //
                values.put(KEY_O_REPLY_FOLLOWUP_DATE_2, myRobotMessage.getoReplyFollowUpDate2()); //
                values.put(KEY_O_REPLY_FOLLOWUP_3, myRobotMessage.getoReplyFollowUp3()); //

// Inserting Row
                db.insert(TABLE_ROBOT_MESSAGES, null, values);
                db.close();
            }


            /**
             * Finds a myRobotMessage in the database and returns it to the caller. If this function does not find
             * a myRobotMessage, then the returned myRobotMessage is null
             * @param myRobotMessagename Name of the myRobotMessage to find
             * @return A valid myRobotMessage or a null myRobotMessage
             */
            public RobotMessage findRobotMessage(String myRobotMessagename) {
                Cursor cursor;
                String query = "Select * FROM " + TABLE_ROBOT_MESSAGES + " WHERE " + KEY_O_WELCOME_MESSAGE +
                        " = \"" + myRobotMessagename + "\"";
                SQLiteDatabase db = this.getWritableDatabase();
                cursor = db.rawQuery(query, null);
                RobotMessage p = new RobotMessage();

                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                   // p.setRmsgId(Integer.parseInt(cursor.getString(0)));
                    p.setoWelcomeMessage(cursor.getString(2));
                    p.setuReplyMsg2(cursor.getString(4));
                    p.setoWelcomeDate(cursor.getString(4));
                    p.setoReplyFollowUp3(cursor.getString(5));
                    p.setuReplyDate2(cursor.getString(6));
                    p.setoReplyFollowUpDate2(cursor.getString(7));

                    cursor.close();
                } else {
                    p = null;
                }
                db.close();
                return p;
            }

            public RobotMessage findRobotMessageByEmail(String myRobotMessageEmail) {
                Cursor cursor;
                String query = "Select * FROM " + TABLE_ROBOT_MESSAGES + " WHERE " + KEY_USER_EMAIL +
                        " = \"" + myRobotMessageEmail + "\"";
                SQLiteDatabase db = this.getWritableDatabase();
                cursor = db.rawQuery(query, null);
                RobotMessage p = new RobotMessage();

                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                   // p.setRmsgId(Integer.parseInt(cursor.getString(0)));
                    p.setoWelcomeMessage(cursor.getString(2));
                    p.setuReplyMsg2(cursor.getString(4));
                    p.setoWelcomeDate(cursor.getString(4));
                    p.setoReplyFollowUp3(cursor.getString(5));
                    p.setuReplyDate2(cursor.getString(6));
                    p.setoReplyFollowUpDate2(cursor.getString(7));

                    cursor.close();
                } else {
                    p = null;
                }
                db.close();
                return p;
            }

            
            /**
             * This function delete's a myRobotMessage in TABLE_myRobotMessageS based on the ID of the myRobotMessage retrieved
             * by it's myRobotMessage name.
             * @return True if deleted false otherwise.
             */
            public boolean specialdeleteRobotMessage() {
                boolean result = false;
                String q = "SELECT * FROM " + TABLE_ROBOT_MESSAGES + " WHERE " + KEY_ID
                        + " =106";
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(q, null);
                RobotMessage p = new RobotMessage();
                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                    p.setRmsgId((cursor.getString(0)));
                    db.delete(TABLE_ROBOT_MESSAGES, KEY_ID + " = ?",
                            new String[] { String.valueOf(p.getRmsgId())});
                    cursor.close();
                    result = true;
                }
                db.close();
                return result;
            }


            public boolean deleteRobotMessage(String myRobotMessagename) {
                boolean result = false;
                String q = "SELECT * FROM " + TABLE_ROBOT_MESSAGES + " WHERE " + KEY_O_WELCOME_MESSAGE
                        + " = \"" + myRobotMessagename + "\"";
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(q, null);
                RobotMessage p = new RobotMessage();
                if (cursor.moveToFirst()) {
                    cursor.moveToFirst();
                    p.setRmsgId(cursor.getString(0));
                    db.delete(TABLE_ROBOT_MESSAGES, KEY_ID + " = ?",
                            new String[] { String.valueOf(p.getRmsgId())});
                    cursor.close();
                    result = true;
                }
                db.close();
                return result;
            }

            /**
             * Updates the myRobotMessage passed to this function.
             * @param p The myRobotMessage to update
             * @return True if updated, false otherwise.
             */
            public boolean updateRobotMessage(RobotMessage p) {
                Log.d(TAG, "p.getUser_email() is :" + p.getUser_email());

                boolean result = false;
                String q = "SELECT * FROM " + TABLE_ROBOT_MESSAGES + " WHERE " + KEY_USER_EMAIL + " = " + p.getUser_email();
                SQLiteDatabase db = this.getWritableDatabase();
                Cursor c = db.rawQuery(q, null);
                if (c.moveToFirst())
                {
                    Log.d(TAG, "p.getuReplyMsg1() is :" + p.getuReplyMsg1());

                    String q2 = "UPDATE " + TABLE_ROBOT_MESSAGES + " SET " + KEY_U_REPLY_MSG_1 + " = \""
                            + p.getuReplyMsg1() + "\", " + KEY_U_REPLY_DATE_1 + " = " + p.getuReplyDate1()
                            + " WHERE " + KEY_USER_EMAIL + " = " + p.getUser_email();
                    db.execSQL(q2);
                    result = true;
                }
                db.close();
                return result;
            }
            
        // Getting All RobotMessages as a list
        public List<RobotMessage> getAllRobotMessages() {
            List<RobotMessage> myRobotMessageList = new ArrayList<>();
// Select All Query
            String selectQuery = "SELECT * FROM " + TABLE_ROBOT_MESSAGES;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to l
// ist
            if (cursor.moveToFirst()) do {
                RobotMessage drobomsg = new RobotMessage();
                // drobomsg.setRmsgId(cursor.getInt(0));
                drobomsg.setoWelcomeMessage(cursor.getString(1));
                drobomsg.setoWelcomeDate(cursor.getString(4));
                drobomsg.setuReplyMsg2(cursor.getString(3));
                drobomsg.setuReplyDate1(cursor.getString(5));
                drobomsg.setoReplyFollowUp3(cursor.getString(6));
                drobomsg.setuReplyDate2(cursor.getString(7));
                drobomsg.setoReplyFollowUpDate2(cursor.getString(8));


// Adding drobomsg to list
                myRobotMessageList.add(drobomsg);
            } while (cursor.moveToNext());

// return myRobotMessage list
            return myRobotMessageList;
        }

        // Getting myRobotMessages Count
        public int getRobotMessagesCount() {
            String countQuery = "SELECT * FROM " + TABLE_ROBOT_MESSAGES;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();

// return count
            return cursor.getCount();
        }

        // Updating a myRobotMessage
        public int updateRobotMessageWithoutCursor(RobotMessage myRobotMessage) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_O_WELCOME_MESSAGE, myRobotMessage.getoWelcomeMessage());
            values.put(KEY_U_REPLY_DATE_3, myRobotMessage.getuReplyDate3());
            values.put(KEY_O_WELCOME_DATE, myRobotMessage.getoWelcomeDate());
            values.put(KEY_U_REPLY_MSG_1, myRobotMessage.getuReplyMsg1());
            values.put(KEY_U_REPLY_DATE_1, myRobotMessage.getuReplyDate1());
            values.put(KEY_U_REPLY_MSG_2, myRobotMessage.getuReplyMsg2());
            values.put(KEY_U_REPLY_DATE_2, myRobotMessage.getuReplyDate2());

// updating row
            //return db.update(TABLE_ROBOT_MESSAGES, values, KEY_ID + " = ?",
            //         new String[]{String.valueOf(myRobotMessage.getRmsgId())});
            return 1;
        }
        // Deleting a myRobotMessage
 /*   public void deleteRobotMessage(RobotMessage myRobotMessage) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROBOT_MESSAGES, KEY_ID + " = ?",
               // new String[]{String.valueOf(myRobotMessage.getRmsgId())});
       // db.close();

    }*/

    }

public RobotMessageDBHelper(Context context) {
    mContext = context;
}

//public RobotMessageDBHelper Open() throws SQLiteException{
//        mDbHelper = new DatabaseHelper(mContext);
//        mDb=mDbHelper.getReadableDatabase();
//        return this;
//    }

}
