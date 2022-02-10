/**
 * Colin Zhou
 * Fatma Serce
 * Assignment 3
 * 6/4/2021
 * This class reads a csv file of movies into a hashtable of red black trees and performs search and get operation.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import edu.princeton.cs.algs4.RedBlackBST;
import java.util.*;

class CompareIDMovie implements Comparator<Movie> { //helper class to sort movies by the id, ascending
	public int compare(Movie first, Movie second) {
		return Integer.parseInt(first.getID()) - Integer.parseInt(second.getID());
	}
}

public class MovieDatabaseMain {
	
	public static void main(String[]args) throws FileNotFoundException {
		Movie[] movieObjList = new Movie[5041]; //list of movies
		File file = new File("movies.csv");
		Scanner scanner = new Scanner(file);
		Scanner scanInput = new Scanner(System.in);
		List<List<String>> records = new ArrayList<>(); //used for storing the results of reading the csv file.

		HashSet<Movie> set1 = new HashSet<Movie>(); //hashsets to store the results of searching by each individual field
		HashSet<Movie> set2 = new HashSet<Movie>();
		HashSet<Movie> set3 = new HashSet<Movie>();
		HashSet<Movie> set4 = new HashSet<Movie>();
		HashSet<Movie> finalSet = null;

		scanner.nextLine(); //skip the first line

		while(scanner.hasNextLine()) { //call helper method to help read the csv file
			records.add(getMoviesByLine(scanner.nextLine()));
		}
		
		scanner.close();
		for (int i = 0; i < records.size(); i++) { //create movie objects and add to array
			List<String> record = records.get(i);
			Movie movie = new Movie(record.get(0), record.get(1), record.get(2), record.get(3), record.get(4), record.get(5), record.get(6), record.get(7), record.get(8), record.get(9), 
				record.get(10), record.get(11), record.get(12), record.get(13));
			movieObjList[i] = movie;
		}
		
		Hashtable<String, RedBlackBST> table = new Hashtable<String, RedBlackBST>(); //create hashtable of RB trees
		
		RedBlackBST<Integer, HashSet<Movie>> yearTree = new RedBlackBST<>();
		RedBlackBST<Double, HashSet<Movie>> scoreTree = new RedBlackBST<>();
		RedBlackBST<String, HashSet<Movie>> languageTree = new RedBlackBST<>();
		RedBlackBST<String, HashSet<Movie>> ratingTree = new RedBlackBST<>();
		
		insertYears(movieObjList, yearTree); //insert the movies into each redblack tree
		
		insertScore(movieObjList, scoreTree);
		
		insertLanguage(movieObjList, languageTree);
		
		insertRating(movieObjList, ratingTree);
		
		table.put("Year", yearTree); //put into hashtable
		table.put("Score", scoreTree);
		table.put("Language", languageTree);
		table.put("Rating", ratingTree);
		
		String result = "Results (Movies -> "; //build the result string based on user input

		try { //to catch any wrong inputs, such as negative numbers, etc....

			System.out.print("Enter Year: " ); //get year from user
			String input = scanInput.next();
	
			if (!input.equals("-")) {
				int enterYear = Integer.parseInt(input);
				set1 = yearTree.get(enterYear);
				finalSet = set1;
				result += " year:" + enterYear;
			}
	
			System.out.print("Enter Score: "); //get score from user
			String scoreString = scanInput.next();
	
			if (!scoreString.equals("-")) {
				double enterScore = Double.parseDouble(scoreString);
				result += " score:" + enterScore;
				set2 = scoreTree.get(enterScore);
				if (finalSet == null) { //determine whether to call retainAll. 
					finalSet = set2;
				} else {
					finalSet.retainAll(set2);
				}
	
			}
	
			System.out.print("Enter language: "); //get language from user
			String enterLang = scanInput.next();
	
			if (!enterLang.equals("-")) {
				set3 = languageTree.get(enterLang);
				result += " language:" + enterLang;
	
				if (finalSet == null) {
					finalSet = set3;
				} else {
					finalSet.retainAll(set3);
				}
			} 
	
			System.out.print("Enter Rating: "); //get rating from user
			String enterRating = scanInput.next();
	
			if (!enterRating.equals("-")) {
				set4 = ratingTree.get(enterRating);
				result += " year:" + enterRating;
				if (finalSet == null) {
					finalSet = set4;
				} else {
					finalSet.retainAll(set4);
				}
	
			} 
			scanInput.close();
		} catch(Exception e) { //catch for wrong input
			System.out.println("Wrong input, try it over again!");
			return;
		}

		if (finalSet == null) { //if all fields are blank, print that no data has been returned
			System.out.println("No data entered / Could not find matching Movies! Please try again.");
			return;
		}

		System.out.println(result); //print the final string
		System.out.println();
		
		List<Movie> list = new ArrayList<Movie>(finalSet); //create new list and read result hashset into it
		Collections.sort(list, new CompareIDMovie()); //sort ascending
		
		System.out.print('['); 

		for (int i = 0; i < list.size(); i++) { //helps print out the movie id's in array format
			Movie movie = list.get(i);
			if (i == list.size() - 1) {
				System.out.print(movie.getID());
				break;
			}
			System.out.print(movie.getID() + ", ");
		}

		System.out.println(']');

		Iterator<Movie> iterator = list.iterator(); //prints out the information related to movies
		while(iterator.hasNext()) {
			try {
				Movie currentMovie = iterator.next();
				System.out.println("id: " + currentMovie.getID());
				System.out.println("color: " + currentMovie.getColor());
				System.out.println("title: " + currentMovie.getTitle());
				System.out.println("duration: " + currentMovie.getDuration());
				System.out.println("director_name: " + currentMovie.getDirector_Name());
				System.out.println("act1: " + currentMovie.getAct1());
				System.out.println("act2: " + currentMovie.getAct2());
				System.out.println("act3: " + currentMovie.getAct3());
				System.out.println("movie_imdb_link: " + currentMovie.getImdb_Link());
				System.out.println("language: " + currentMovie.getLanguage());
				System.out.println("country: " + currentMovie.getCountry());
				System.out.println("content_rating: " + currentMovie.getContent_Rating());
				System.out.println("title_year: " + currentMovie.getTitle_Year());
				System.out.println("imdb_score: " + currentMovie.getImdb_Score());
				System.out.println("---------------------------------------------------------");
				System.out.println();
				System.out.println();
				System.out.println("---------------------------------------------------------");

			
			} catch (Exception e) {
				continue;
			}
		}
	}

	private static RedBlackBST<Double, HashSet<Movie>> insertScore(Movie[] movieList, RedBlackBST<Double, HashSet<Movie>> tree) { //inserts into score rb tree
		HashSet<Movie> set;

		for (int i = 0; i < movieList.length; i++) {
			try {
				double score = Double.parseDouble(movieList[i].getImdb_Score());

				if (tree.contains(score) == false) {
					set = new HashSet<Movie>();
					set.add(movieList[i]);
					tree.put(score, set);

				} else {
					tree.get(score).add(movieList[i]);
				}

			} catch (Exception e) {

			}
		}
		return tree;
	}

	private static RedBlackBST<Integer, HashSet<Movie>> insertYears(Movie[] movieList, RedBlackBST<Integer, HashSet<Movie>> tree) { //inserts into year rb tree
		HashSet<Movie> set;

		for (int i = 0; i < movieList.length; i++) {
			try {
				if (Integer.class.isInstance(Integer.parseInt(movieList[i].getTitle_Year())) == true) {
					int year = Integer.parseInt(movieList[i].getTitle_Year());
					if (tree.contains(year) == false) {
						set = new HashSet<Movie>();
						set.add(movieList[i]);
						tree.put(year, set);
		
					} else {
						tree.get(year).add(movieList[i]);
					}
				} 

			} catch(Exception e) { //to catch errors in the csv file, where the year could be one or two places off.
				try {
					if (Integer.class.isInstance((Integer.parseInt(movieList[i].getImdb_Score()))) == true) {
						int year = Integer.parseInt(movieList[i].getTitle_Year());
						if (tree.contains(year) == false) {
							set = new HashSet<Movie>();
							set.add(movieList[i]);
							tree.put(year, set);
			
						} else {
							tree.get(year).add(movieList[i]);
						}
		
					} 

				} catch(Exception ex) {
					try {
						if (Integer.class.isInstance(Integer.parseInt(movieList[i].getContent_Rating())) == true) {
							int year = Integer.parseInt(movieList[i].getContent_Rating());
							if (tree.contains(year) == false) {
								set = new HashSet<Movie>();
								set.add(movieList[i]);
								tree.put(year, set);
				
							} else {
								tree.get(year).add(movieList[i]);
							}
			
						}
	
					} catch(Exception exc) {
						
					}
				}
			}

		}

		return tree;
	}

	private static RedBlackBST<String, HashSet<Movie>> insertLanguage(Movie[] movieList, RedBlackBST<String, HashSet<Movie>> tree) { //insert into language rb tree
		HashSet<Movie> set;
		for (int i = 0; i < movieList.length; i++) {
			try {
				String language = movieList[i].getLanguage();
				if (tree.contains(language) == false) {
					set = new HashSet<Movie>();
					set.add(movieList[i]);
					tree.put(language, set);
				} else {
					tree.get(language).add(movieList[i]);
				}
	
			}	catch (Exception e) {
	
			}	
			
		}
		return tree;
	}

	private static RedBlackBST<String, HashSet<Movie>> insertRating(Movie[] movieList, RedBlackBST<String, HashSet<Movie>> tree) { //insert rating into rb tree.
		HashSet<Movie> set;
		for (int i = 0; i < movieList.length; i++) {
			try {
				String rating = movieList[i].getContent_Rating();
				if (tree.contains(rating) == false) {
					set = new HashSet<Movie>();
					set.add(movieList[i]);
					tree.put(rating, set);
				} else {
					tree.get(rating).add(movieList[i]);
				}

			} catch (Exception e) {

			}

		}
		return tree;

	}

	//helper method for reading into csv file
	
	private static List<String> getMoviesByLine(String movie) {
		Scanner scanner = new Scanner(movie);
		List<String> values = new ArrayList<String>();
		
		scanner.useDelimiter(",");
		while(scanner.hasNext()) {
			values.add(scanner.next());
		}
		scanner.close();
		return values;
		
	}
}


