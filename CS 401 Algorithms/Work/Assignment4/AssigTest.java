/**
 * This is the test class for LastFMRecommender. 
 */

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssigTest {
	private LastFMRecommender recommend;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException {
	    File file = new File("user_friends.dat");
	    File file2 = new File("user_artists.dat");
	    File file3 = new File("artists.dat");

	    recommend = new LastFMRecommender(file, file2, file3);
	}

	@Test
	void listFriendsTest1() { //tests to see whether correct list of friends is generated
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(78);
		expected.add(255);
		expected.add(460);
		expected.add(837);
		expected.add(1740);
		expected.add(1801);
		expected.add(1975);
		assertEquals(expected, recommend.getListFriends(3));
	}
	
	@Test
	void listFriendsTest2() {
		assertThrows(IllegalArgumentException.class, () -> recommend.getListFriends(-3));
	}
	
	@Test
	void commonFriendsTest1() { //compares whether returned friend is correct
		HashSet<Integer> expected = new HashSet<Integer>();
		expected.add(593);
		assertEquals(expected, recommend.getCommonFriends(12, 17));
	}
	
	@Test
	void commonFriendsTest2() {
		assertThrows(IllegalArgumentException.class, () -> recommend.getCommonFriends(-1, 17));
	}
	
	@Test
	void listFriendArtist() { //compares whether returned artist is correct
		HashSet<Integer> expected = new HashSet<Integer>();
		expected.add(53);
		assertEquals(expected, recommend.getListArtists(2, 5));

	}
	
	@Test
	void listFriendArtist2() {
		assertThrows(IllegalArgumentException.class, () -> recommend.getListArtists(-3, -5));
	}

	@Test
	void top10AllArtistsTest() throws FileNotFoundException { //compares whether the artist ID is correct
	    int[] test = recommend.getTop10ID();
		int[] top10Expected = new int[10];
		top10Expected[0] = 289;
		top10Expected[1] = 72;
		top10Expected[2] = 89;
		top10Expected[3] = 292;
		top10Expected[4] = 498;
		top10Expected[5] = 67;
		top10Expected[6] = 288;
		top10Expected[7] = 701;
		top10Expected[8] = 227;
		top10Expected[9] = 300;

		Assert.assertArrayEquals(top10Expected, test);
	}
	
	@Test
	void recommendFriendException() {
		assertThrows(IllegalArgumentException.class, () -> recommend.recommend10(-3));
	}
	
	@Test
	void recommendFriend10() throws FileNotFoundException { //compare whether the recommendFriend10 id's is correct
	    int[] test = recommend.setUpRec10(100);
		int[] top10Expected = new int[10];
		top10Expected[0] = 1814;
		top10Expected[1] = 3444;
		top10Expected[2] = 867;
		top10Expected[3] = 631;
		top10Expected[4] = 9469;
		top10Expected[5] = 985;
		top10Expected[6] = 4156;
		top10Expected[7] = 4155;
		top10Expected[8] = 1534;
		top10Expected[9] = 875;
		Assert.assertArrayEquals(top10Expected, test);
	}
}