package edu.brown.cs.bdGaMbPp.Database;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	private int id;
	private String username;
	private String password;
	
	private int numWins;
	private int bestSurvival;
	private List<Integer> friendId;
	
	public Profile(int newId, String newUsername, String newPassword, int newBestSurvival){
		id = newId;
		username = newUsername;
		password = newPassword;
		bestSurvival = newBestSurvival;
		friendId = new ArrayList<Integer>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getSurvival() {
		return bestSurvival;
	}
	
	public void setSurvival(int newSurvival) {
		bestSurvival = newSurvival;
	}
	

}
