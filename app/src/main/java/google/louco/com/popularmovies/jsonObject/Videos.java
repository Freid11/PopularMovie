package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

/**
 * Параметры трэйлера фильма
 */
public class Videos {

    @SerializedName("id")
    private String id;
    @SerializedName("iso_3166_1")
    private String iso_3166_1;
    @SerializedName("iso_639_1")
    private String iso_639_1;
    @SerializedName("key")
    private String key;
    @SerializedName("name")
    private String name;
    @SerializedName("site")
    private String site;
    @SerializedName("size")
    private int size;
    @SerializedName("type")
    private String type;

    public Videos(String id, String iso_3166_1, String iso_639_1, String key, String name, String site, int size, String type) {
        this.id = id;
        this.iso_3166_1 = iso_3166_1;
        this.iso_639_1 = iso_639_1;
        this.key = key;
        this.name = name;
        this.site = site;
        this.size = size;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public String getIso_639_1() {
        return iso_639_1;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
