package edu.brown.cs.bdGaMbPp.Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.Location;
import edu.brown.cs.bdGaMbPp.Tank.DrunkWalkTank;
import edu.brown.cs.bdGaMbPp.Tank.HomingTank;
import edu.brown.cs.bdGaMbPp.Tank.PathTank;
import edu.brown.cs.bdGaMbPp.Tank.StationaryEnemyTank;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;


public final class Querier {
	
	private static Querier instance = new Querier();
	private Connection conn;
	
	private Querier()  {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:data/tanks.sqlite3");
			Statement stat = conn.createStatement();
	        stat.executeUpdate("PRAGMA foreign_keys = ON;");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int getNumMaps() {
		int num = 0;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT representation FROM maps;");
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	          num++;
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return num;
	}
	
	private static int getNumTanks() {
		int num = 0;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM tanks;");
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	          num++;
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return num;
	}
	
	private static int getNumGames() {
		int num = 0;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM game;");
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	          num++;
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return num;
	}
	
	private static int getNumProfiles() {
		int num = 0;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM profiles;");
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	          num++;
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return num;
	}
	

	public static int addMap(String representation, int user) {
		int id = -1;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO maps VALUES (?, ?, ?);");
			id = getNumMaps();
			prep.setString(1, Integer.toString(id));
			prep.setString(2, representation);
			prep.setString(3, Integer.toString(user));
			
			prep.addBatch();
			prep.executeBatch();
	        prep.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	public static int addGameToDatabase(int mapId, List<Tank> enemies) {
		
		int id = getNumGames();
		int tankId = getNumTanks();
		
		try {
			for(int i = 0; i < enemies.size(); i++) {
				addTankToDatabase(enemies.get(i), Integer.toString(tankId), id);
				tankId++;
			}
			
			
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO game VALUES (?, ?);");
			prep.setString(1, Integer.toString(id));
			prep.setString(2, Integer.toString(mapId));
			
			prep.addBatch();
			prep.executeBatch();
			prep.close();
		}
		catch (Exception e){
			
		}
		return id;
	}
	
	private static void addTankToDatabase(Tank tank, String id, int gameId) {

    System.out.println(tank.toString());
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO tanks VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, id);
				prep.setString(2, tank.getType());
				prep.setString(3, Integer.toString((int)tank.getCoord().getCoordinate(0)));
				prep.setString(4, Integer.toString((int)tank.getCoord().getCoordinate(1)));
				prep.setString(5, Integer.toString(gameId));
				if (tank.getType().equals("p")) {
					//fix
					prep.setString(6, Integer.toString((int)(tank.getEndCoord().getCoordinate(0))));
					prep.setString(7, Integer.toString((int)(tank.getEndCoord().getCoordinate(1))));
				}
				else {
					prep.setString(6, "-1");
					prep.setString(7, "-1");
				}
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

	public static String getMapById(int id)  {
		String representation = "";
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT representation FROM maps WHERE id = ?;");
			prep.setString(1, Integer.toString(id));
	        ResultSet rs = prep.executeQuery();
	        if (rs.next()) {
	          representation = rs.getString(1);
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	        return representation;
	}
	
	private static List<Tank> getTanksById(int id){
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM tanks WHERE game = ?;");
			prep.setString(1, Integer.toString(id));
	        ResultSet rs = prep.executeQuery();
	        List<Tank> tankList = new ArrayList<Tank>();
	        while (rs.next()) {
	          String type = rs.getString(2);
	          String startRow = rs.getString(3);
	          String startCol = rs.getString(4);
	          
	          if (type.equals("u")) {
	        	  	tankList.add(new UserTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow))));
	          }
	          else if (type.equals("s")) {
	        	  	tankList.add(new StationaryEnemyTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow))));
	          }
	          else if (type.equals("d")) {
	        	  	tankList.add(new DrunkWalkTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow))));
	          }else if (type.equals("p")) {
		        	  String endRow = rs.getString(6);
		        	  String endCol = rs.getString(7);
	        	  		tankList.add(new PathTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow)),  
	        	  			new Coordinate(Integer.parseInt(endCol), Integer.parseInt(endRow))));
	          }	else if (type.equals("h")) {
	        	  	tankList.add(new HomingTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow))));
          	}
	        }
	        prep.close();
	        rs.close();
	        return tankList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return null;
	}
	
	private static Tank parseTankList(List<Tank> tankList) {
		for (int i = 0; i < tankList.size(); i++) {
			if (tankList.get(i) instanceof UserTank) {
				Tank user = tankList.get(i);
				tankList.remove(i);
				return user;
			}
		}
		return null;
	}

	public static List<Pair<String, Integer>> getGameLeaderboard(int id) {
		String map = "";
		List<Pair<String, Integer>> result = new ArrayList<>();
		try {
			PreparedStatement prep = instance.conn
							.prepareStatement("SELECT * FROM leaderboard WHERE game" +
											" = ? ORDER BY time DESC;");
			prep.setString(1, Integer.toString(id));
			ResultSet rs = prep.executeQuery();
			int mapId = -1;
			int counter = 0;
			while (rs.next() && counter < 5) {
				mapId = Integer.parseInt(rs.getString(1));
				String username  = getProfile(Integer.parseInt(rs.getString(2))).getUsername();
				Integer time = Integer.parseInt(rs.getString(3));
				result.add(new Pair<>(username, time));
				counter++;
			}
			prep.close();
			rs.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
		return result;
	}

	public static Game getGameById(int id) {
		String map = "";
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT map FROM game WHERE id = ?;");
			prep.setString(1, Integer.toString(id));
	        ResultSet rs = prep.executeQuery();
	        int mapId = -1;
	        if (rs.next()) {
	        		mapId = Integer.parseInt(rs.getString(1));
	        }
	        prep.close();
	        rs.close();
	        
	        if	(id !=  -1) {
	        		map = getMapById(mapId);
	        		List<Tank> tanks = getTanksById(id);
	        		Tank user = parseTankList(tanks);
	        		Game theGame = new Game(convertFromDatabase(map), user, tanks);
	        		
	        		return theGame;
	        }
	        else {
	        		return null;
	        }
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	  
	  public static GameMap convertFromDatabase(String representations) {
		  
		  //assert representations.length() == 384;
		  List<List<Location>> locs = new ArrayList<List<Location>>();
		  for (int r = 0; r < 16; r++) {
		      List<Location> temp = new ArrayList<Location>();
		      for (int c = 0; c < 24; c++) {
		        temp.add(GameMap.representationToLocation(Character.toString(representations.charAt((24 * r) + c))));
		      }
		      locs.add(temp);
		    }
		  return new GameMap(locs);
	  }

	public static List<String> getUsernames() {
		List<String> usernames = new ArrayList<String>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT username FROM profiles;");
	        ResultSet rs = prep.executeQuery();
	        while (rs.next()) {
	          usernames.add(rs.getString(1));
	        }
	        prep.close();
	        rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return usernames;
	}
	
	public static void setSurvival(int id, int newSurvival) {
		Profile curr = getProfile(id);
		if (curr.getSurvival() < newSurvival) {
			try {
				PreparedStatement prep = instance.conn
								.prepareStatement("UPDATE profiles SET survival = ? WHERE id = ?;");

				prep.setString(2, Integer.toString(id));
				prep.setString(1, Integer.toString(newSurvival));
				prep.executeUpdate();
				prep.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setCampaign(int id, int newCampaign) {
		Profile curr = getProfile(id);
		if (curr.getCampaign() < newCampaign+1) {
			try {
				PreparedStatement prep = instance.conn
								.prepareStatement("UPDATE profiles SET campaign = ? WHERE id = ?;");

				prep.setString(2, Integer.toString(id));
				prep.setString(1, Integer.toString(newCampaign+1));

				prep.executeUpdate();
				prep.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void setTime(int id, int t) {
		Profile curr = getProfile(id);
		if (curr != null) {
      try {
        PreparedStatement prep = instance.conn
                .prepareStatement("UPDATE profiles SET time = ? WHERE id = ?;");

        prep.setString(2, Integer.toString(id));
        prep.setString(1, Integer.toString(curr.getTime() + t));

        prep.executeUpdate();
        prep.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
	}
	
	public static void setKills(int id, int kills) {
		Profile curr = getProfile(id);
		if (curr != null) {
      try {
        PreparedStatement prep = instance.conn
                .prepareStatement("UPDATE profiles SET kills = ? WHERE id = ?;");

        prep.setString(2, Integer.toString(id));
        prep.setString(1, Integer.toString(curr.getKills() + kills));

        prep.executeUpdate();
        prep.close();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
	}

	public static void createNewAccount(String username, String password) {
		int nextId = getNumProfiles();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(nextId));
				prep.setString(2, username);
				prep.setString(3, password);
				prep.setString(4, "5");
				prep.setString(5, "1");
				prep.setString(6, "0");
				prep.setString(7, "0");
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static void gameSent(int user1, int user2, int gameId, int playerOneTime, int playerOneKills) {
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO multiplayer VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(user1));
				prep.setString(2, Integer.toString(user2));
				prep.setString(3, Integer.toString(gameId));
				
				prep.setString(4, Integer.toString(playerOneTime));
				prep.setString(5, Integer.toString(playerOneKills));
				
				prep.setString(6, Integer.toString(-1));
				prep.setString(7, Integer.toString(-1));
				
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static Pair<Integer, Integer> getGameSent(int user1, int user2, int gameId) {
		
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT firstTime, firstKills FROM multiplayer WHERE game = ? AND playerOne = ? AND playerTwo = ?");
			
			prep.setString(1, Integer.toString(gameId));
			prep.setString(2, Integer.toString(user1));
			prep.setString(3, Integer.toString(user2));
				ResultSet rs = prep.executeQuery();
		        if (rs.next()) {
		        		 int time = Integer.parseInt(rs.getString(1));
		        		 int kills = Integer.parseInt(rs.getString(2));
		        		 
		        		 return new Pair<Integer, Integer>(kills, time);
		        		 
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				
			}
			return null;
	}
	
	public static List<MultiplayerGame> getInbox(int user){
		
		List<MultiplayerGame> currList = new ArrayList<MultiplayerGame>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM multiplayer WHERE playerOne = ?");
			
				prep.setString(1, Integer.toString(user));
			
				ResultSet rs = prep.executeQuery();
		        while (rs.next()) {
		        		 int playerTwo = Integer.parseInt(rs.getString(3));
		        		 String user2 = getProfile(playerTwo).getUsername();
		        		 int game = Integer.parseInt(rs.getString(3));
		        		 boolean winner = false;
		        		 int over = 0;
		        		 if (Integer.parseInt(rs.getString(7)) == -1) {
		        			 over = 1;
		        		 }
		        		 else {
		        			 int win = getWinner(user, playerTwo, game);
		        			 if (win == user) {
		        				 winner = true;
		        			 }
		        			 
		        		 }
		        		 
		        		 currList.add(new MultiplayerGame(user, playerTwo, user2, winner, game, over));
		        		 
		        }
		        
		        prep.close();
		        rs.close();
		        
		        PreparedStatement prep2 = instance.conn
				        .prepareStatement("SELECT * FROM multiplayer WHERE playerTwo = ?");
				
		        prep2.setString(1, Integer.toString(user));
			
				ResultSet rs2 = prep2.executeQuery();
		        while (rs2.next()) {
		        		 int playerTwo = Integer.parseInt(rs2.getString(1));
		        		 String user2 = getProfile(playerTwo).getUsername();
		        		 int game = Integer.parseInt(rs2.getString(3));
		        		 boolean winner = false;
		        		 int over = 0;
		        		 if (Integer.parseInt(rs2.getString(7)) == -1) {
		        			 over = -1;
		        		 }
		        		 else {
		        			 int win = getWinner(user, playerTwo, game);
		        			 if (win == user) {
		        				 winner = true;
		        			 }
		        			 
		        		 }
		        		 
		        		 currList.add(new MultiplayerGame(user, playerTwo, user2, winner, game, over));
		        		 
		        }
			        
			        prep2.close();
			        rs2.close();
			}
			catch(Exception e) {
				
			}
		return currList;
	}
	
	
	public static void updateGameFinal(int user1, int user2, int gameId, int playerTwoTime, int playerTwoKills) {
		
		try {
		PreparedStatement prep = instance.conn
		        .prepareStatement("UPDATE multiplayer set secondTime = ? AND secondKills = ? WHERE game = ? AND playerOne = ? AND playerTwo = ?");
		
			prep.setString(1, Integer.toString(playerTwoTime));
			prep.setString(2, Integer.toString(playerTwoKills));
			prep.setString(3, Integer.toString(gameId));
			prep.setString(4, Integer.toString(user1));
			prep.setString(5, Integer.toString(user2));

			prep.executeUpdate();
			prep.close();
		}
		catch(Exception e) {
			
		}
	}
	
	
	public static int getWinner(int user1, int user2, int gameId) {
		Pair<Integer, Integer> getPlayerOne = getGameSent(user1, user2, gameId);
		Pair<Integer, Integer> getPlayerTwo = null;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT secondTime, secondKills FROM multiplayer WHERE game = ? AND playerOne = ? AND playerTwo = ?");
			
			prep.setString(1, Integer.toString(gameId));
			prep.setString(2, Integer.toString(user1));
			prep.setString(3, Integer.toString(user2));
				ResultSet rs = prep.executeQuery();
		        if (rs.next()) {
		        		 int time = Integer.parseInt(rs.getString(1));
		        		 int kills = Integer.parseInt(rs.getString(2));
		        		 
		        		  getPlayerTwo = new Pair<Integer, Integer>(kills, time);
		        		 
		        }
		        prep.close();
		        rs.close();
		        
		        if(getPlayerOne.getFirst() < getPlayerTwo.getFirst()) {
		        		return user2;
		        }
		        else if(getPlayerOne.getFirst() > getPlayerTwo.getFirst()) {
	        			return user1;
		        }
		        else {
			        	if(getPlayerOne.getSecond() < getPlayerTwo.getSecond()) {
			        		return user1;
			        }
			        else  {
		        			return user2;
			        }
		        }
		        
		        
			}
			catch(Exception e) {
				
			}
		return user2;
		
	}
	
	public static void friendRequest(int id1, int id2, int status) {
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO friends VALUES (?, ?, ?);");
			
				prep.setString(1, Integer.toString(id1));
				prep.setString(2, Integer.toString(id2));
				prep.setString(3, Integer.toString(status));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static int getTime(int gameId, int userId) {
		int time = -1;
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT time FROM leaderboards WHERE game = ? AND user = ?");
			
				prep.setString(1, Integer.toString(gameId));
				prep.setString(2, Integer.toString(userId));
				ResultSet rs = prep.executeQuery();
		        if (rs.next()) {
		        		 time = Integer.parseInt(rs.getString(1));
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				
			}
			return time;
	}
	
	public static void updateLeaderboard(int gameId, int userId, int time) {
		int currentTime = getTime(gameId, userId);
		if (currentTime == -1) {
			try {
				PreparedStatement prep = instance.conn
								.prepareStatement("INSERT INTO leaderboard VALUES (?, ?, ?);");

				prep.setString(3, Integer.toString(time));
				prep.setString(2, Integer.toString(userId));
				prep.setString(1, Integer.toString(gameId));

				prep.addBatch();
				prep.executeBatch();
				prep.close();

			}
			catch (Exception e){

			}
		} else if (time < currentTime) {
			try {
				PreparedStatement prep = instance.conn
				        .prepareStatement("UPDATE leaderboard set time = ? where user = ? AND game = ?;");
				
					prep.setString(1, Integer.toString(time));
					prep.setString(2, Integer.toString(userId));
					prep.setString(3, Integer.toString(gameId));

					prep.executeUpdate();
					prep.close();
					
			}
			catch (Exception e){
				
			}
		}
	}
	
	public static List<Friend> getFriendIds(int id){
		List<Friend> friends = new ArrayList<Friend>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM friends WHERE first = ?");
			
				prep.setString(1, Integer.toString(id));
				ResultSet rs = prep.executeQuery();
		        while (rs.next()) {
		        		int second = Integer.parseInt(rs.getString(2));
		        		Profile prof = getProfile(second);
		        		int status = Integer.parseInt(rs.getString(3));
		          friends.add(new Friend(id, second, status, prof.getUsername()));
		        }
		        prep.close();
		        rs.close();
			
		        PreparedStatement prep2 = instance.conn
				        .prepareStatement("SELECT * FROM friends WHERE second = ?");
				
					prep2.setString(1, Integer.toString(id));
					ResultSet rs2 = prep.executeQuery();
					while (rs2.next()) {
		        		int first = Integer.parseInt(rs.getString(2));
								Profile prof = getProfile(first);
		        		int status = Integer.parseInt(rs.getString(3));
		        		friends.add(new Friend(id, first, status * -1, prof.getUsername()));
		        }
			        prep2.close();
			        rs2.close();			
		}
		catch (Exception e){
			
		}
		return friends;
	}
	
	public static List<Integer> getGamesByCreator(int userId){
		List<Integer> maps = getMapsByCreator(userId);
		return getGamesByMaps(maps);
	}
	
	public static List<Integer> getMapsByCreator(int userId){
		List<Integer> maps = new ArrayList<Integer>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT id FROM maps WHERE creator = ?");
			
				prep.setString(1, Integer.toString(userId));
				ResultSet rs = prep.executeQuery();
		        while (rs.next()) {
		        		maps.add(Integer.parseInt(rs.getString(1)));	
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return maps;
	}
	
	public static List<Integer> getGamesByMapId(int mapId){
		List<Integer> games = new ArrayList<Integer>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT id FROM game WHERE map = ?");
			
				prep.setString(1, Integer.toString(mapId));
				ResultSet rs = prep.executeQuery();
		        while (rs.next()) {
		        		games.add(Integer.parseInt(rs.getString(1)));	
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return games;
	}
	
	public static List<Integer> getGamesByMaps(List<Integer> mapIds){
		List<Integer> games = new ArrayList<Integer>();
		for (int i = 0; i < mapIds.size(); i++) {
			games.addAll(getGamesByMapId(mapIds.get(i)));
		}
		return games;
	}

	public static List<Profile> getAllProfiles(){
		List<Profile> profs = new ArrayList<Profile>();
		try {
			PreparedStatement prep = instance.conn
							.prepareStatement("SELECT * FROM profiles");

			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				String username = rs.getString(2);
				String password = rs.getString(3);
				int bestCampaign = Integer.parseInt(rs.getString(4));
				int bestSurvival = Integer.parseInt(rs.getString(5));
				int numKills = Integer.parseInt(rs.getString(6));
				int time = Integer.parseInt(rs.getString(7));


				profs.add(new Profile(id, username, password, bestSurvival, bestCampaign, time, numKills));

			}

			prep.close();
			rs.close();
		}
		catch(Exception e) {

		}
		return profs;
	}

	public static List<Profile> getLocalPlayers(int id){
		List<Profile> profs = new ArrayList<Profile>();
		profs.add(getProfile(id));
		List<Friend> friends = getFriendIds(id);
		for (int i = 0; i < friends.size(); i++){
			profs.add(getProfile(friends.get(i).getFriend()));
		}
		return profs;

	}
	
	public static Profile getProfile(int currId) {
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM profiles WHERE id = ?");
			
				prep.setString(1, Integer.toString(currId));
				ResultSet rs = prep.executeQuery();
		        if (rs.next()) {
		        		int id = Integer.parseInt(rs.getString(1));
		        		String username = rs.getString(2);
		        		String password = rs.getString(3);
		        		int bestCampaign = Integer.parseInt(rs.getString(4));
		        		int bestSurvival = Integer.parseInt(rs.getString(5));
		        		int numKills = Integer.parseInt(rs.getString(6));
		        		int time = Integer.parseInt(rs.getString(7));
		        		
		        		
		        		return new Profile(id, username, password, bestSurvival, bestCampaign, time, numKills);
		        		
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				
			}
			return null;
	}
	
	public static Profile signIn(String username, String password) {
		try {
		PreparedStatement prep = instance.conn
		        .prepareStatement("SELECT * FROM profiles WHERE username = ?");
		
			prep.setString(1, username);
			ResultSet rs = prep.executeQuery();
	        if (rs.next()) {
	        		int id = Integer.parseInt(rs.getString(1));
	        		String actualPassword = rs.getString(3);
	        		int bestCampaign = Integer.parseInt(rs.getString(4));
	        		int bestSurvival = Integer.parseInt(rs.getString(5));
	        		int numKills = Integer.parseInt(rs.getString(6));
	        		int time = Integer.parseInt(rs.getString(7));
	        		
	        		if (password.equals(actualPassword)) {
	        			return new Profile(id, username, password, bestSurvival, bestCampaign, time, numKills);
	        		}
	        }
	        
	        prep.close();
	        rs.close();
		}
		catch(Exception e) {
			
		}
		return null;
	}

}
