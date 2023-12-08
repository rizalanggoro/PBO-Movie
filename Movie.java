public class Movie {
  private final String title;
  private final String synopsis;
  private final String posterPath;

  public Movie(
      String title,
      String synopsis,
      String posterPath
  ) {
    this.title = title;
    this.synopsis = synopsis;
    this.posterPath = posterPath;
  }

  public String getTitle() {
    return title;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public String getPosterPath() {
    return posterPath;
  }
}
