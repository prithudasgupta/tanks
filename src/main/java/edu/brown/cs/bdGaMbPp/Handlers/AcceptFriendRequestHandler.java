package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class AcceptFriendRequestHandler implements Route{
	
	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		
		  QueryParamsMap qm = request.queryMap();
		  
		  
		  int friendId = Integer.parseInt(qm.value("friendId"));
		  
		  int id = -1;
			
			Set<String> attributes = request.session().attributes();
		  if (attributes.contains("user")) {
				id = Integer.parseInt(request.session().attribute("user").toString());
			}
	      
	      Querier.acceptRequest(id, friendId);
	      Gson GSON = new Gson();
	      return new Gson();
	}

}
