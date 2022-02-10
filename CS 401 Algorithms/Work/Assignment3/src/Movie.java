/**
 * This class defines the movie object, with getter methods for the private fields.
 */
public class Movie {
  private String id;
  private String color;
  private String title;
  private String duration;
  private String director_name;
  private String act1;
  private String act2;
  private String act3;
  private String movie_imdb_link;
  private String language;
  private String country;
  private String content_rating;
  private String title_year;
  private String imdb_score;

  public Movie(String id, String color, String title, String duration, String director_name, String act1, String act2, String act3, String movie_imdb_link, String language,
    String country, String content_rating, String title_year, String imdb_score) {

      this.id = id;
      this.color = color;
      this.title = title;
      this.duration = duration;
      this.director_name = director_name;
      this.act1 = act1;
      this.act2 = act2;
      this.act3 = act3;
      this.movie_imdb_link = movie_imdb_link;
      this.language = language;
      this.country = country;
      this.content_rating = content_rating;
      this.title_year = title_year;
      this.imdb_score = imdb_score;
    }

    public String getID() {
      return id;
    }    
    
    public String getColor() {
      return color;
    }

    public String getTitle() {
      return title;
    }

    public String getDuration() {
      return duration;
    }

    public String getDirector_Name() {
      return director_name;
    }

    public String getAct1() {
      return act1;
    }

    public String getAct2() {
      return act2;
    }

    public String getAct3() {
      return act3;
    }

    public String getImdb_Link() {
      return movie_imdb_link;
    }
    public String getLanguage() {
      return language;
    }

    public String getCountry() {
      return country;
    }

    public String getContent_Rating() {
      return content_rating;
    }

    public String getTitle_Year() {
      return title_year;
    }

    public String getImdb_Score() {
      return imdb_score;
    }


}
