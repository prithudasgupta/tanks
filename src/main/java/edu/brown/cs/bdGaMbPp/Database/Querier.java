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
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.Location;
import edu.brown.cs.bdGaMbPp.Tank.DrunkWalkTank;
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

		
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO tanks VALUES (?, ?, ?, ?, ?);");
			
				prep.setString(1, id);
				prep.setString(2, tank.getType());
				prep.setString(3, Integer.toString((int)tank.getCoord().getCoordinate(0)));
				prep.setString(4, Integer.toString((int)tank.getCoord().getCoordinate(1)));
				prep.setString(5, Integer.toString(gameId));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
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
	          }
	        }
	        prep.close();
	        rs.close();
	        return tankList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
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
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(id));
				prep.setString(2, curr.getUsername());
				prep.setString(3, curr.getPassword());
				prep.setString(4, Integer.toString(curr.getCampaign()));
				prep.setString(5, Integer.toString(newSurvival));
				prep.setString(6, Integer.toString(curr.getKills()));
				prep.setString(7, Integer.toString(curr.getTime()));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static void setCampaign(int id, int newCampaign) {
		Profile curr = getProfile(id);
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(id));
				prep.setString(2, curr.getUsername());
				prep.setString(3, curr.getPassword());
				prep.setString(4, Integer.toString(newCampaign));
				prep.setString(5, Integer.toString(curr.getSurvival()));
				prep.setString(6, Integer.toString(curr.getKills()));
				prep.setString(7, Integer.toString(curr.getTime()));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static void setTime(int id, int time) {
		Profile curr = getProfile(id);
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(id));
				prep.setString(2, curr.getUsername());
				prep.setString(3, curr.getPassword());
				prep.setString(4, Integer.toString(curr.getCampaign()));
				prep.setString(5, Integer.toString(curr.getSurvival()));
				prep.setString(6, Integer.toString(curr.getKills()));
				prep.setString(7, Integer.toString(curr.getTime() + time));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
		}
	}
	
	public static void setKills(int id, int kills) {
		Profile curr = getProfile(id);
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO profiles VALUES (?, ?, ?, ?, ?, ?, ?);");
			
				prep.setString(1, Integer.toString(id));
				prep.setString(2, curr.getUsername());
				prep.setString(3, curr.getPassword());
				prep.setString(4, Integer.toString(curr.getCampaign()));
				prep.setString(5, Integer.toString(curr.getSurvival()));
				prep.setString(6, Integer.toString(curr.getKills() + kills));
				prep.setString(7, Integer.toString(curr.getTime()));
				
				prep.addBatch();
				prep.executeBatch();
				prep.close();
				
		}
		catch (Exception e){
			
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
		if (currentTime == -1 || time < currentTime) {
			try {
				PreparedStatement prep = instance.conn
				        .prepareStatement("INSERT INTO leaderboard VALUES (?, ?, ?);");
				
					prep.setString(1, Integer.toString(gameId));
					prep.setString(2, Integer.toString(userId));
					prep.setString(3, Integer.toString(time));
					
					prep.addBatch();
					prep.executeBatch();
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
		        		int status = Integer.parseInt(rs.getString(3));
		          friends.add(new Friend(id, second, status));
		        }
		        prep.close();
		        rs.close();
			
		        PreparedStatement prep2 = instance.conn
				        .prepareStatement("SELECT * FROM friends WHERE second = ?");
				
					prep2.setString(1, Integer.toString(id));
					ResultSet rs2 = prep.executeQuery();
					while (rs2.next()) {
		        		int first = Integer.parseInt(rs.getString(2));
		        		int status = Integer.parseInt(rs.getString(3));
		        		friends.add(new Friend(id, first, status * -1));
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
				
			}
			return maps;
	}
	
	public static List<Integer> getGamesByMapId(int mapId){
		List<Integer> games = new ArrayList<Integer>();
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT id FROM games WHERE map = ?");
			
				prep.setString(1, Integer.toString(mapId));
				ResultSet rs = prep.executeQuery();
		        while (rs.next()) {
		        		games.add(Integer.parseInt(rs.getString(1)));	
		        }
		        
		        prep.close();
		        rs.close();
			}
			catch(Exception e) {
				
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
		        		
		        		
		        		return new Profile(id, username, password, bestSurvival, bestCampaign, numKills, time);
		        		
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
	        			return new Profile(id, username, password, bestSurvival, bestCampaign, numKills, time);
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
