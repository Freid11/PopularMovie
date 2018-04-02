package google.louco.com.popularmovies.mvp.model.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import google.louco.com.popularmovies.jsonObject.Movie;

public class ContentFavorite extends ContentProvider {

    private FavoriteDB favorite;

    public static final String TABLE_NAME = "favorite";

    public ContentFavorite() {
    }


    @Override
    public boolean onCreate() {
        favorite = new FavoriteDB(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, String s, String[] strings1, String s1) {
        SQLiteDatabase database = favorite.getReadableDatabase();
        Cursor cursor = null;

        cursor = database.query(FavoriteDB.NAME_TABLE
                , strings
                , s
                , strings1
                , null,
                null,
                s1);
        Log.d("Louco", "good");

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase database = favorite.getWritableDatabase();

        long id = database.insert(FavoriteDB.NAME_TABLE, null, contentValues);
        if (id == -1) {
            Log.e("Louco", "Failed to insert row for " + uri);
            return null;
        } else {
            return ContentUris.withAppendedId(uri, id);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        SQLiteDatabase database = favorite.getWritableDatabase();
        return database.delete(FavoriteDB.NAME_TABLE, s, strings);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
