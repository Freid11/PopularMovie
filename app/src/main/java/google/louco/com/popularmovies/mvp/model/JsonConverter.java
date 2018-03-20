package google.louco.com.popularmovies.mvp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
    private static Gson gson = null;

    public static Gson getGson() {
        if(gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gson = gsonBuilder.create();
        }
        return gson;
    }
}
