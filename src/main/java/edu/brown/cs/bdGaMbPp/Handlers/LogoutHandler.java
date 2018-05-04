package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutHandler implements Route {

	@Override
	public Object handle(Request request, Response res) throws Exception {
		
		request.session().removeAttribute("user");
		return new Gson();
		
	}

}
