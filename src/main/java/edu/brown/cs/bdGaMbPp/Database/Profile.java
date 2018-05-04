package edu.brown.cs.bdGaMbPp.Database;

import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	private int id;
	private String username;
	private String password;
	
	private int numWins;
	private int bestSurvival;
	private int campaign;
	private int time; 
	private int kills;
	private List<Integer> friendId;
	
	public Profile(int newId, String newUsername, String newPassword, int newBestSurvival, int campaignBest, int timePlayed, int numKills){
		id = newId;
		username = newUsername;
		password = newPassword;
		bestSurvival = newBestSurvival;
		campaign = campaignBest;
		friendId = new ArrayList<Integer>();
		time = timePlayed;
		kills = numKills;
	}
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getSurvival() {
		return bestSurvival;
	}
	
	public void setSurvival(int newSurvival) {
		bestSurvival = newSurvival;
	}
	
	public int getCampaign() {
		return campaign;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getKills() {
		return kills;
	}

}
