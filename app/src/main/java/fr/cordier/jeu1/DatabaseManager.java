package fr.cordier.jeu1;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Game.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseManager( Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create table Score ("
                       + " id integer primary key autoincrement,"
                       + " name text not null,"
                       + " score int not null,"
                       + " when_ integer not null);";
        db.execSQL(strSql);
        Log.i( "DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String strSql = "drop table Score";
        db.execSQL(strSql);
        this.onCreate(db);
        Log.i ("DATABASE","onUpgrade invoked");
    }

    public void insertScore(String name,int score){
        name = name.replace("'","''");
        String strSql="insert into Score (name, score, when_) values ('"
                     + name + "', "+score+", "+new Date().getTime()+")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE","insertScore invoked");
    }

    public List<ScoreData> top(){
        List<ScoreData> scores = new ArrayList<>();
        String strsql="Select * from Score order by score desc";
        Cursor cursor= this.getReadableDatabase().rawQuery(strsql,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ScoreData score=new ScoreData(cursor.getInt(0), cursor.getString(1),cursor.getInt(2), new Date(cursor.getInt(3)));
            scores.add(score);
            cursor.moveToNext();
        }
        cursor.close();
        return scores;
    }

    public ScoreData score(){
        String strsql="Select * FROM Score;";
        Cursor cursor= this.getReadableDatabase().rawQuery(strsql,null);
        cursor.moveToFirst();
        ScoreData score = new ScoreData(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), new Date(cursor.getInt(3)));
        int idMax=0;
        while(!cursor.isAfterLast()) {
            if (cursor.getInt(0) > idMax) {
                score.setName("Joueur");
                score.setScore(cursor.getInt(2));
                score.setWhen(new Date(cursor.getInt(3)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return score;

    }


}
