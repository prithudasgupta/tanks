package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class HomeHandler implements TemplateViewRoute  {

  @Override
  public ModelAndView handle(Request arg0, Response arg1) throws Exception {
    // TODO Auto-generated method stub
    
      Map<String, Object> variables = ImmutableMap.of("title", "Tanks!");
      return new ModelAndView(variables, "home.ftl");
    
  }

}
