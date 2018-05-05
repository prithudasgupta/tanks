package edu.brown.cs.bdGaMbPp.Database;

public class Friend {
	
	private int user;
	private int friend;
	private int status;
	private String friendName;
	
	public Friend(int userId, int friendId, int statusId, String friendname) {
		this.user = userId;
		this.friend = friendId;
		this.status = statusId;
		this.friendName = friendname;
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

	public String getFriendName(){ return friendName; }
}
