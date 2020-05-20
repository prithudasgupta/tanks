package edu.brown.cs.bdGaMbPp.Main;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.bdGaMbPp.Handlers.AcceptFriendRequestHandler;
import edu.brown.cs.bdGaMbPp.Handlers.AuthenticateHandler;
import edu.brown.cs.bdGaMbPp.Handlers.CreateProfileHandler;
import edu.brown.cs.bdGaMbPp.Handlers.DisplayProfileHandler;
import edu.brown.cs.bdGaMbPp.Handlers.FriendRequestHandler;
import edu.brown.cs.bdGaMbPp.Handlers.GameHandler;
import edu.brown.cs.bdGaMbPp.Handlers.GameLeaderboardHandler;
import edu.brown.cs.bdGaMbPp.Handlers.HomeHandler;
import edu.brown.cs.bdGaMbPp.Handlers.HomingTankHandler;
import edu.brown.cs.bdGaMbPp.Handlers.LeaderboardHandler;
import edu.brown.cs.bdGaMbPp.Handlers.LogoutHandler;
import edu.brown.cs.bdGaMbPp.Handlers.MapBuilderHandler;
import edu.brown.cs.bdGaMbPp.Handlers.MapHandler;
import edu.brown.cs.bdGaMbPp.Handlers.MatchmakerHandler;
import edu.brown.cs.bdGaMbPp.Handlers.NextRoundHandler;
import edu.brown.cs.bdGaMbPp.Handlers.SignInHandler;
import edu.brown.cs.bdGaMbPp.Handlers.GameDataHandler;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * The Main class of our project. This is where execution begins.
 *
 * @author jj
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args
   *          An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;
  private boolean gui = false;

  private Main(String[] args) {
    this.args = args;
  }
  private void run() {
	  MapBuilder builder = new MapBuilder();
	  builder.createMap(.2, .2);
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
    .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);
    // This is initialized before the GUI because the GUI will use it for its
    // information about the tree
    
    runSparkServer(getHerokuAssignedPort());
      

  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
}
  
  /**
   * Handle requests to the front page of Bacon website.
   */
  private static class TestHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title", "test");
      return new ModelAndView(variables, "test.ftl");
    }
  }
  
  private static class GamePageHandler implements TemplateViewRoute {
	    @Override
	    public ModelAndView handle(Request req, Response res) {
	      Map<String, Object> variables = ImmutableMap.of("title", "Tanks: Play a game!");
	      return new ModelAndView(variables, "game.ftl");
	    }
	  }
  
  private static class MapBuilderHomeHandler implements TemplateViewRoute {
	  @Override
	    public ModelAndView handle(Request req, Response res) {
	      Map<String, Object> variables = ImmutableMap.of("title", "Tanks: Create your own map");
	      return new ModelAndView(variables, "mapbuilder.ftl");
	    }
  }


  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }


  private void runSparkServer(int port) {
    Spark.port(port);
    Spark.externalStaticFileLocation("src/main/resources/static");
    // Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();

    Spark.get("/home", new HomeHandler(), freeMarker);
    Spark.post("/createAccount", new CreateProfileHandler());
    Spark.post("/signIn", new SignInHandler());
    Spark.post("/logout", new LogoutHandler());
    Spark.post("/authenticate", new AuthenticateHandler());
    Spark.post("/profileData", new DisplayProfileHandler());
    Spark.post("/friendRequest", new FriendRequestHandler());
    Spark.post("/friendAccept", new AcceptFriendRequestHandler());
    Spark.post("/leaderboard", new LeaderboardHandler());
    Spark.post("/matchmaker", new MatchmakerHandler());
    Spark.post("/gameLeaderboard", new GameLeaderboardHandler());

    Spark.get("/test", new TestHandler(), freeMarker);
    MapHandler mapHandler = new MapHandler();
    Spark.post("/map", mapHandler);
    Spark.get("/tank/game/:id", new GamePageHandler(), freeMarker);
    
    Spark.post("/nextRound", new NextRoundHandler());
    Spark.post("/endGame", new GameDataHandler());
  
    Spark.get("/gameHandler", new GameHandler(), freeMarker);
    Spark.post("/homing", new HomingTankHandler());
    Spark.post("/mapBuilderSubmit", new MapBuilderHandler());
    Spark.get("/mapbuilder", new MapBuilderHomeHandler(), freeMarker);

    // Spark.post("/user", new UserTankHandler());
  }


  /**
   * Display an error page when an exception occurs in the server.
   *
   * @author jj
   */
  // private static class ExceptionPrinter implements ExceptionHandler {
  //   @Override
  //   public void handle(Exception e, Request req, Response res) {
  //     res.status(500);
  //     StringWriter stacktrace = new StringWriter();
  //     try (PrintWriter pw = new PrintWriter(stacktrace)) {
  //       pw.println("<pre>");
  //       e.printStackTrace(pw);
  //       pw.println("</pre>");
  //     }
  //     res.body(stacktrace.toString());
  //   }
  //}





}
