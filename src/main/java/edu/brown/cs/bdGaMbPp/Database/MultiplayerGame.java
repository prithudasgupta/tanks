package edu.brown.cs.bdGaMbPp.Database;

import edu.brown.cs.bdGaMbPp.Collect.Pair;

public class MultiplayerGame {
	
	private int id1;
	private int id2;
	private String username1;
	private String username2;
	private boolean winner;
	
	private int isOver;
	private int gameId;
	
	public MultiplayerGame(int theId1, int theId2, String theUsername2, boolean theWinner, int theGameId,
			int over) {
		id1 = theId1;
		id2 = theId2;
		username2 = theUsername2;
		winner = theWinner;
		gameId = theGameId;
		isOver = over;

	}

	@Override
	public String toString() {
		return "MultiplayerGame{" +
						"id1=" + id1 +
						", id2=" + id2 +
						", username1='" + username1 + '\'' +
						", username2='" + username2 + '\'' +
						", winner=" + winner +
						", isOver=" + isOver +
						", gameId=" + gameId +
						'}';
	}
}