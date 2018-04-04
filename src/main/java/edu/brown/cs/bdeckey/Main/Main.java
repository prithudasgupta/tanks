package edu.brown.cs.bdeckey.Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;


import edu.brown.cs.bdeckey.Autocorrect.CorrectionHandler;

import edu.brown.cs.bdeckey.Bacon.ActorHandler;
import edu.brown.cs.bdeckey.Bacon.ConnectionHandler;
import edu.brown.cs.bdeckey.Bacon.DropDownHandler;
import edu.brown.cs.bdeckey.Bacon.MovieHandler;
import edu.brown.cs.bdeckey.stars.StarsHandler;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import com.google.common.collect.ImmutableMap;

import freemarker.template.Configuration;

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
  private Repl repl;
  private boolean gui = false;

  private Main(String[] args) {
    this.args = args;
  }
  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
    .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);
    // This is initialized before the GUI because the GUI will use it for its
    // information about the tree.
    repl = new Repl();

    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
      gui = true;
    }


    // Process commands in a REPL
    repl.run(gui);
    // end GUI
    if (options.has("gui")) {
      Spark.stop();
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
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();

    // Setup Spark Routes
    Spark.get("/stars", new FrontHandler(), freeMarker);

    // Post results of search
    Spark.post("/results", new StarsHandler(repl), freeMarker);

    // Autocorrect Page
    Spark.get("/autocorrect", new AutoFrontHandler(), freeMarker);

    // Endpoint which manages the suggestions
    Spark.post("/suggestions", new CorrectionHandler(repl));

    // Home page
    Spark.get("/home", new HomeHandler(), freeMarker);

    // Bacon Page
    Spark.get("/connect", new BaconFrontHandlor(), freeMarker);

    // Endpoitn which manages the bacon path
    Spark.post("/connections", new ConnectionHandler(repl));

    // Actor Pages
    Spark.get("/actor/:actor", new ActorHandler(repl), freeMarker);

    // Movie Pages
    Spark.get("/movie/:movie", new MovieHandler(repl), freeMarker);

    // DropDown Suggestions
    Spark.post("/dropdown", new DropDownHandler(repl));
  }

  /**
   * Handle requests to the front page of Bacon website.
   */
  private static class BaconFrontHandlor implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
              "Bacon: Connect");
      return new ModelAndView(variables, "connect.ftl");
    }
  }

  /**
   * Handle requests to the front page of our Stars website.
   *
   * @author jj
   */
  private static class FrontHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
          "Stars: Query the database");
      return new ModelAndView(variables, "query.ftl");
    }
  }

  /**
   * Handle requests to the front page of the Autocorrect website.
   *
   */
  private static class AutoFrontHandler implements  TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
              "Corrector");
      return new ModelAndView(variables, "corrector.ftl");
    }
  }

  /**
   * Handle requests to the front page of the Autocorrect website.
   *
   */
  private static class HomeHandler implements  TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of("title",
              "Home");
      return new ModelAndView(variables, "home.ftl");
    }
  }

  /**
   * Display an error page when an exception occurs in the server.
   *
   * @author jj
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

}
