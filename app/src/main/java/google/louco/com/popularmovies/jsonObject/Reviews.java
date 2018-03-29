package google.louco.com.popularmovies.jsonObject;

import com.google.gson.annotations.SerializedName;

/**
 *  Комантарий фильма
 */
public class Reviews {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String Content;

    @SerializedName("url")
    private String url;

    public Reviews(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        Content = content;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return Content;
    }

    public String getUrl() {
        return url;
    }
}
