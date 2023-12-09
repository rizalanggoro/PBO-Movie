package domain.models;

public class Movie {
  private final String title;
  private final String synopsis;
  private final String posterPath;
  private final int price;

  public Movie(
      String title,
      String synopsis,
      String posterPath,
      int price
  ) {
    this.title = title;
    this.synopsis = synopsis;
    this.posterPath = posterPath;
    this.price = price;
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
  
  public int getPrice() {
    return price;
  }
}
