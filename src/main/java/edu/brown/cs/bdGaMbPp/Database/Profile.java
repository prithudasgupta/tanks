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
	
	public Profile(int newId, String newUsername, String newPassword){
		id = newId;
		username = newUsername;
		password = newPassword;
		
		numWins = 0;
		bestSurvival = 1;
		friendId = new ArrayList<Integer>();
	}
	
	public boolean signIn(String testPassword) {
		return testPassword.equals(password);
	}
	

}
