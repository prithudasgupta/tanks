package edu.brown.cs.bdGaMbPp.Database;

import spark.Session;

public class PlayerSession {

  private Session session;
  private int id;
  private String username;
  
  public PlayerSession(int id, String username) {
    
    this.id = id;
    this.username = username;
    
  }
  
  public int getId() {
    return id;
  }
  
  public String getUsername() {
    return username;
  }
}

