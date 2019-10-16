package mobile.example.network.sample.openapi_with_file_sample;

import android.text.Html;
import android.text.Spanned;

public class NaverBookDto {

    private int _id;
    private String title;
    private String author;
    private String link;
    private String imageLink;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        Spanned spanned = Html.fromHtml(title);
        return spanned.toString();
//        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  _id + ": " + title + " (" + author + ')';
    }
}
