package dduwcom.mobile.a02_20170964_report01;

public class MovieDto {
    private long _id;
    private String title;
    private String actor;
    private String director;
    private String rated;
    private String time;

    public MovieDto(long _id, String title, String actor, String director, String rated, String time) {
        this._id = _id;
        this.title = title;
        this.actor = actor;
        this.director = director;
        this.rated = rated;
        this.time = time;
    }

    public long get_id() { return _id; }
    public void set_id(long _id) { this._id = _id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getActor() { return actor; }
    public void setActor(String actor) { this.actor = actor; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getRated() { return rated; }
    public void setRated(String rated) { this.rated = rated; }
}
