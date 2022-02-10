/**
 * This is the main class for LastFMRecommender. 
 */

import java.io.File;
import java.io.FileNotFoundException;

public class main {
  public static void main(String[] args) throws FileNotFoundException {
    File file = new File("user_friends.dat");
    File file2 = new File("user_artists.dat");
    File file3 = new File("artists.dat");
    LastFMRecommender rec = new LastFMRecommender(file, file2, file3);
    rec.listFriends(3);
    rec.commonFriends(12, 4);
    rec.listTop10();
    rec.listArtists(2, 5);
    rec.setUpRec10(100); //have to call this to read all information for recommend10
    rec.recommend10(100);
  }
  
}
