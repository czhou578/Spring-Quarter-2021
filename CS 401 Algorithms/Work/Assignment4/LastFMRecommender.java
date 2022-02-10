/**
 * Colin Zhou
 * Fatma Serce
 * Assignment 4
 * Implements methods for the LastFMRecommender class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;
import edu.princeton.cs.algs4.*;

//for comparing entries using value (weight) in artistID, weight map
class CompareArtist implements Comparator<Map.Entry<Integer, Double>> {

  @Override
  public int compare(Entry<Integer, Double> o1, Entry<Integer, Double> o2) {

    return (o2.getValue().compareTo(o1.getValue()));
  }
}

public class LastFMRecommender {

  HashMap<Integer, ArrayList<Integer>> map; //maps user id to arraylist of friendId's
  HashMap<Integer, ArrayList<Integer>> artistListened1; //mapping user id to all the artist id's the user listens to
  HashMap<Integer, String> artistIDName; //matches artist id to name
  HashMap<Integer, Double> artistWeight; //stores artistID, weight pair
  int[] top10ID;
  int[] top10FriendRec;
  int maxGraphVertex;

  //constructor
  public LastFMRecommender(File file, File file2, File file3) throws FileNotFoundException {
    readFriends(file);
    readUserArtists(file2);
    readArtistList(file3);
    readArtistTop10(file2);
  }

  //returns an array that contains the id's of the top 10 artists
  public int[] sortDescendingValue(HashMap<Integer, Double> map) {
    List<Map.Entry<Integer, Double> > list = new LinkedList<Map.Entry<Integer, Double>>(map.entrySet());
    Collections.sort(list, new CompareArtist());
    top10ID = new int[10];
    
    int i = 0; //counter
    
    for (Map.Entry<Integer, Double> entry : list) {
      if (i == 10) break;
      top10ID[i] = entry.getKey();
      i++;
    }
    return top10ID;

  }

  //this method below reads the specified file and constructs a digraph using the friendID, artistID, and associated weight
  public HashMap<Integer, Double> readArtistTop10(File file3) throws FileNotFoundException {
    Scanner scanLength = new Scanner(file3);
    scanLength.next();
    int numLineData = 0;
    while (scanLength.hasNext()) {
      scanLength.nextLine();
      numLineData++;
    }
    
    maxGraphVertex = numLineData;
    scanLength.close();

    Scanner scanner = new Scanner(file3); //for reading content and adding to graph
    artistWeight = new HashMap<Integer, Double>();
    EdgeWeightedDigraph graph = new EdgeWeightedDigraph(numLineData);
    scanner.nextLine(); //skip first line
    while (scanner.hasNext()) {
      int friendID = scanner.nextInt();
      int artistID = scanner.nextInt();
      int artistWeight = scanner.nextInt();
      DirectedEdge edge = new DirectedEdge(friendID, artistID, artistWeight);
      graph.addEdge(edge);
    }

    Iterable<DirectedEdge> list = graph.edges();

    for (DirectedEdge e : list) { //compare with next
      if (artistWeight.containsKey(e.to())) { //if artist weight contains this artist
        double alreadyWeight = artistWeight.get(e.to());
        alreadyWeight += e.weight();
        artistWeight.put(e.to(), alreadyWeight);
        continue;
      } else {
        artistWeight.put(e.to(), e.weight());
      }
    }
    
    sortDescendingValue(artistWeight);
    
    return artistWeight;
  }
  
  //this method is for testing purposes with JUnit. 
  public int[] getTop10ID() {
	  return top10ID;
  }
  
  //prints out the top 10 artist names to the console
  public void listTop10() {
    String name;
    System.out.println("Top 10 most popular artists by listened users: ");
    for (Integer id : getTop10ID()) {
      name = artistIDName.get(id);
      System.out.println(name);
    }
  }

    //method below returns hashmap mapping artist id to the artist name
  private HashMap<Integer, String> readArtistList(File file3) throws FileNotFoundException {
    Scanner scanner = new Scanner(file3, "UTF-8");
    artistIDName = new HashMap<Integer, String>();
    scanner.nextLine();
    String[] splitString;
    String toBeAdded = "";

    while (scanner.hasNext()) {
      int first = scanner.nextInt();
      String str = scanner.nextLine();
      splitString = str.split("\\s+");
      for (String string : splitString) {
        if (!string.contains("http")) {
          toBeAdded += string;
          toBeAdded += " ";
        }
      }
      
      artistIDName.put(first, toBeAdded);
      toBeAdded = "";
      
    }
    
    scanner.close();
    return artistIDName;
  }
  

  // returns hashmap mapping user id to all the artist id's the user listens to
  private HashMap<Integer, ArrayList<Integer>> readUserArtists(File file2) throws FileNotFoundException {
    Scanner scanner = new Scanner(file2);
    scanner.nextLine(); //skip first line
    artistListened1 = new HashMap<Integer, ArrayList<Integer>>();
    ArrayList<Integer> artistID = new ArrayList<Integer>();
    int temp = -1; //for comparison
    while (scanner.hasNext()) {
      int first = scanner.nextInt();
      if (temp != first) {
        artistID = new ArrayList<Integer>();
        int second = scanner.nextInt(); //reads the artistID
        artistID.add(second);
        artistListened1.put(first, artistID);
        temp = first;
        scanner.skip(".*");
        continue;
      }

      int second = scanner.nextInt();
      artistID.add(second);
      artistListened1.put(first, artistID);
      temp = first;
      scanner.skip(".*");
    }
    scanner.close();
    return artistListened1;
  }

  //returns hashmap that maps id of user to arraylist containing id's of all friends
  private HashMap<Integer, ArrayList<Integer>> readFriends(File file) throws FileNotFoundException{
    Scanner scanner = new Scanner(file);
    scanner.nextLine(); //skip the line
    map = new HashMap<Integer, ArrayList<Integer>>();
    ArrayList<Integer> values = new ArrayList<Integer>();
    int temp = -1;
    while (scanner.hasNext()) {
      int first = scanner.nextInt();
      if (temp != first) { //make new arraylist if userid changes
        values = new ArrayList<Integer>();
        int second = scanner.nextInt();
        values.add(second);
        map.put(first, values);
        temp = first; //have to compare
        continue;
      }

      int second = scanner.nextInt();
      values.add(second);
      map.put(first, values);
      temp = first; //have to compare
    }
    scanner.close();
    return map;
  }

  //this method is for testing purposes with JUnit. 
  public ArrayList<Integer> getListFriends(int user) {
	if (user < 2) {
		  System.out.println("Invalid userID. Try again.");
		  throw new IllegalArgumentException();
	}
	  
    ArrayList<Integer> getFriends = map.get(user);
    
    return getFriends;
  }

  //prints all friends of the user into the console
  public void listFriends(int user) {
	if (user < 2) {
		  System.out.println("Invalid userID. Try again.");
		  throw new IllegalArgumentException();
	}

    if (getListFriends(user).size() == 0) {
      System.out.println("No found friends for this user.");
    }

    System.out.println("Friends of User " + user + " are: ");
    System.out.println(getListFriends(user));
  }
  
  //returns hashset with the common friends of user1 and user2
  public HashSet<Integer> getCommonFriends(int user1, int user2) {
    if (user1 < 2 || user2 < 2) {
      System.out.println("Invalid userID. Try again.");
      throw new IllegalArgumentException();
    }
 
    HashSet<Integer> user1Set = new HashSet<Integer>(map.get(user1));
    HashSet<Integer> user2Set = new HashSet<Integer>(map.get(user2));
    user1Set.retainAll(user2Set);
    return user1Set;
  }

  //prints all common friends between two users
  public void commonFriends(int user1, int user2) {
    if (user1 < 2 || user2 < 2) {
      System.out.println("Invalid userID. Try again.");
      throw new IllegalArgumentException();
    }
    
    System.out.println("Common friends of " + user1 + " and " + user2 + " are: ");

    if (getCommonFriends(user1, user2).size() == 0) {
      System.out.println("none");
      return;
    }

    System.out.println(getCommonFriends(user1, user2).toString());
  }
  
  //for testing purposes, returns the hashet containing the id's
  public HashSet<Integer> getListArtists(int user1, int user2) {
		if (user1 < 2 || user2 < 2) {
			System.out.println("Invalid userID. Try again.");
			throw new IllegalArgumentException();
		}

	  HashSet<Integer> user1Set = new HashSet<Integer>(artistListened1.get(user1));
	  HashSet<Integer> user2Set = new HashSet<Integer>(artistListened1.get(user2));
	  user1Set.retainAll(user2Set);
	  return user1Set;
  }

  //lists artists listened to by both users
  public void listArtists(int user1, int user2) {
    if (user1 < 2 || user2 < 2) {
      System.out.println("Invalid userID. Try again.");
      throw new IllegalArgumentException();
    }

    if (getListArtists(user1, user2).size() == 0) {
      System.out.println("No common artists listened.");
    }
	
    System.out.println("Artists listened to by both users: ");
    for (int artistID : getListArtists(user1, user2)) {
      System.out.println(artistIDName.get(artistID));
    }
  }
  
  //reads from file and constructs the digraph with userID and artistID as vertices and using weights.
  public int[] setUpRec10(int user) throws FileNotFoundException {
	  
    if (user < 2) {
      System.out.println("Invalid userID. Try again.");
      throw new IllegalArgumentException();
    }
    
    ArrayList<Integer> friendIDList = map.get(user); //get list of friends
    HashSet<Integer> userIDList = new HashSet<Integer>(artistListened1.get(user)); //get all artists listened by user
    HashMap<Integer, HashSet<Integer>> userMapArtist = new HashMap<Integer, HashSet<Integer>>(); //map friend to artist id listened
    HashSet<Integer> friendArtistList;
    int totalVertices = 0;
    
    for (Integer friend : friendIDList) {
      friendArtistList = new HashSet<Integer>(artistListened1.get(friend)); //find all friend's artists
      friendArtistList.removeAll(userIDList); //remove duplicates
      userMapArtist.put(friend, friendArtistList); //put into usermap
      
    } 

    totalVertices += maxGraphVertex;
    EdgeWeightedDigraph graph = new EdgeWeightedDigraph(totalVertices);
    userMapArtist.keySet();
    File file = new File("user_artists.dat");
    Scanner scanner = new Scanner(file);
    scanner.nextLine();
    while (scanner.hasNext()) {
      int userID = scanner.nextInt();
      int artistID = scanner.nextInt();
      
      if (userMapArtist.keySet().contains(userID) && userMapArtist.get(userID).contains(artistID)) { //remove one by one and insert into graph
        int weight = scanner.nextInt();
        DirectedEdge edge = new DirectedEdge(userID, artistID, weight);
        graph.addEdge(edge);
        continue;
      } else {
        scanner.skip(".*");
        continue;
      }
    }

    Iterable<DirectedEdge> listEdges = graph.edges();
    HashMap<Integer, Double> recommendList = new HashMap<Integer, Double>();
    for (DirectedEdge e : listEdges) { //adds to hashmap for sorting
      if (recommendList.containsKey(e.to())) {
        double alreadyWeight = recommendList.get(e.to());
        alreadyWeight += e.weight();
        recommendList.put(e.to(), alreadyWeight);
        continue; 
      } else {
        recommendList.put(e.to(), e.weight());
      }
    }

    top10FriendRec = sortDescendingValue(recommendList);
    return top10FriendRec;
  }
  
  //prints out the top 10 recommended artist based on friends to the console
  public void recommend10(int user) throws FileNotFoundException {
	if (user < 2) {
      System.out.println("Invalid userID. Try again.");
      throw new IllegalArgumentException();
	}
	
    String name = "";
    System.out.println("Top 10 Recommended Artists through your friends: " + "(user id: " + user + ")");
    for (Integer id : top10FriendRec) {
      name = artistIDName.get(id);
      System.out.println(name);
    }  
  }
}
