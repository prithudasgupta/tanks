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
	
	public static Profile signIn(String username, String potentialPassword) {
		return null;
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
	
	public static void addGameToDatabase(int mapId, List<Tank> enemies) {
		
		int id = getNumGames();
		
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO game VALUES (?, ?, ?);");
			
			for(int i = 0; i < enemies.size(); i++) {
				String tankId = Integer.toString(getNumTanks());
				prep.setString(1, Integer.toString(id));
				prep.setString(2, Integer.toString(mapId));
				prep.setString(3, tankId);
				prep.addBatch();
				prep.executeBatch();
				addTankToDatabase(enemies.get(i), tankId);
			}
		}
		catch (Exception e){
			
		}
	}
	
	private static void addTankToDatabase(Tank tank, String id) {

		
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("INSERT INTO tanks VALUES (?, ?, ?, ?);");
			
				prep.setString(1, id);
				prep.setString(2, tank.getType());
				prep.setString(3, Integer.toString((int)tank.getCoord().getCoordinate(0)));
				prep.setString(4, Integer.toString((int)tank.getCoord().getCoordinate(1)));
				prep.addBatch();
				prep.executeBatch();
				
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
	
	private static Tank getTanksById(int id){
		try {
			PreparedStatement prep = instance.conn
			        .prepareStatement("SELECT * FROM tanks WHERE id = ?;");
			prep.setString(1, Integer.toString(id));
	        ResultSet rs = prep.executeQuery();
	        if (rs.next()) {
	          String type = rs.getString(2);
	          String startRow = rs.getString(3);
	          String startCol = rs.getString(4);
	          
	          if (type.equals("u")) {
	        	  	return new UserTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow)));
	          }
	          else if (type.equals("s")) {
	        	  return new StationaryEnemyTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow)));
	          }
	          else if (type.equals("d")) {
	        	  return new DrunkWalkTank(new Coordinate(Integer.parseInt(startCol), Integer.parseInt(startRow)));
	          }
	        }
	        prep.close();
	        rs.close();
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
			        .prepareStatement("SELECT * FROM game WHERE id = ?;");
			prep.setString(1, Integer.toString(id));
	        ResultSet rs = prep.executeQuery();
	        List<Tank> tanks = new ArrayList<Tank>();
	        if (!rs.next()) {
	        		return null;
	        }
	   
	        while (rs.next()) {
	          if (map.equals("")) {
	        	  	map = Querier.getMapById(Integer.parseInt(rs.getString(2)));
	          }
	          tanks.add(Querier.getTanksById(Integer.parseInt(rs.getString(3)))); 
	        }
	        prep.close();
	        rs.close();
	        
	        Tank user = parseTankList(tanks);
	        
	        return new Game(GameMap.representationToMap(convertFromDatabase(map)), user, tanks);
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		return null;
	}
	
	private static String convertToDatabase(List<List<String>> representations) {
		  StringBuilder sb = new StringBuilder();
		  for (int i = 0; i < representations.size(); i++) {
			  List<String> currentRow = representations.get(i);
			  for (int j = 0; j < currentRow.size(); j++) {
				  sb.append(currentRow.get(j));
			  }
		  }
		  return sb.toString();
	  }
	  
	  private static List<List<String>> convertFromDatabase(String representations) {
		  assert representations.length() == 384;
		  List<List<String>> locs = new ArrayList<List<String>>();
		  for (int r = 0; r < 16; r++) {
		      locs.add(new ArrayList<>());
		      for (int c = 0; c < 24; c++) {
		        locs.get(r).add(Character.toString(representations.charAt(16 * r + c)));
		      }
		    }
		  return locs;
	  }

}
