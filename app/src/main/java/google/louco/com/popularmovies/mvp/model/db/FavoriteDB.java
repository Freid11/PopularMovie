package google.louco.com.popularmovies.mvp.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import google.louco.com.popularmovies.jsonObject.Movie;

public class FavoriteDB  extends SQLiteOpenHelper{

    private static final String TYPE_STRING = " TEXT";
    private static final String TYPE_INT = " INTEGER";
    private static final int VERSION_DB = 1;
    private static final String SEPARATOR = " ,";
    public static final String NAME_TABLE = "favorite_movie";

    private static final String SQL_CREATE_TABLE = "Create table " + NAME_TABLE
            +" ( ID_movie" + TYPE_INT+" PRIMARY KEY,"
            + Movie.ID_MOVIE + TYPE_INT + SEPARATOR
            + Movie.TITLE + TYPE_STRING + SEPARATOR
            + Movie.OVERVIEW + TYPE_STRING + SEPARATOR
            + Movie.IMAGE_URL + TYPE_STRING + SEPARATOR
            + Movie.RELEASE_DATE + TYPE_STRING + SEPARATOR
            + Movie.VOTE_AVERAGE + TYPE_STRING +" )";

    private static final String SQL_DELETE = "DROP TABLE IF EXISTS " + NAME_TABLE;

    public FavoriteDB(Context context) {
        super(context, NAME_TABLE, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
