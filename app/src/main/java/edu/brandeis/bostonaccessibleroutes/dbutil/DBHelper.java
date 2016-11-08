package edu.brandeis.bostonaccessibleroutes.dbutil;

/**
 * Created by Mojir on 11/3/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "APR.db";
    public static final String RT_CND_TABLE_NAME = "apr_routecondition";
    public static final String RT_CND_COLUMN_ID = "id";
    public static final String RT_CND_COLUMN_TIME_STAMP = "time_stamp";
    public static final String RT_CND_COLUMN_GPS_LAT = "gps_lat";
    public static final String RT_CND_COLUMN_GPS_LONG = "gps_long";
    public static final String RT_CND_COLUMN_RT_CND_ID = "rt_cnd_id";
    public static final String RT_CND_COLUMN_RT_CND_COMMENT = "rt_cnd_comment";
    public static final String RT_CND_COLUMN_RT_CND_VOICE_ADR = "rt_cnd_voice_adr";
    public static final String RT_CND_COLUMN_RT_CND_IMG_ADR = "rt_cnd_img_adr";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table apr_routecondition " +
                        "(id integer primary key, time_stamp date, gps_lat integer,gps_long integer, rt_cnd_id integer, rt_cnd_comment text, rt_cnd_voice_adr text, rt_cnd_img_adr text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS apr_routecondition");
        onCreate(db);
    }

    public boolean insertRouteCondition  (long gps_lat, long gps_long, int rt_cnd_id, String rt_cnd_comment,String rt_cnd_voice_adr,String rt_cnd_img_adr)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RT_CND_COLUMN_GPS_LAT, gps_lat);
        contentValues.put(RT_CND_COLUMN_GPS_LONG, gps_long);
        contentValues.put(RT_CND_COLUMN_RT_CND_ID, rt_cnd_id);
        contentValues.put(RT_CND_COLUMN_RT_CND_COMMENT, rt_cnd_comment);
        contentValues.put(RT_CND_COLUMN_RT_CND_VOICE_ADR, rt_cnd_voice_adr);
        contentValues.put(RT_CND_COLUMN_RT_CND_IMG_ADR, rt_cnd_img_adr);

        db.insert(RT_CND_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, RT_CND_TABLE_NAME);
        return numRows;
    }

    public boolean updateRouteCondition (Integer id,long gps_lat, long gps_long, int rt_cnd_id, String rt_cnd_comment,String rt_cnd_voice_adr,String rt_cnd_img_adr)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RT_CND_COLUMN_GPS_LAT, gps_lat);
        contentValues.put(RT_CND_COLUMN_GPS_LONG, gps_long);
        contentValues.put(RT_CND_COLUMN_RT_CND_ID, rt_cnd_id);
        contentValues.put(RT_CND_COLUMN_RT_CND_COMMENT, rt_cnd_comment);
        contentValues.put(RT_CND_COLUMN_RT_CND_VOICE_ADR, rt_cnd_voice_adr);
        contentValues.put(RT_CND_COLUMN_RT_CND_IMG_ADR, rt_cnd_img_adr);
        db.update(RT_CND_TABLE_NAME, contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteRouteCondition (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(RT_CND_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllRouteConditions()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+RT_CND_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(RT_CND_COLUMN_RT_CND_ID)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}