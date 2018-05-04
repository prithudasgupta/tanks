package edu.brown.cs.bdGaMbPp.Database;

public class Friend {
	
	private int user;
	private int friend;
	private int status;
	
	public Friend(int userId, int friendId, int statusId) {
		user = userId;
		friend = friendId;
		status = statusId;
	}
	
	public int getUser() {
		return user;
	}
	
	public int getFriend() {
		return friend;
	}
	
	public int getStatus() {
		return status;
	}

}
