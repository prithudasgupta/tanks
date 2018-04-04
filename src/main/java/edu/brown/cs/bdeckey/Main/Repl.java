package edu.brown.cs.bdeckey.Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.brown.cs.bdeckey.Autocorrect.AutoCorrectCommands;
import edu.brown.cs.bdeckey.Autocorrect.Trie;
import edu.brown.cs.bdeckey.Bacon.Actor;
import edu.brown.cs.bdeckey.Bacon.BaconCommands;
import edu.brown.cs.bdeckey.Bacon.Hollywood;
import edu.brown.cs.bdeckey.Graph.GrowingGraph;
import edu.brown.cs.bdeckey.KDTree.KDTree;
import edu.brown.cs.bdeckey.stars.StarsCommands;


import java.io.IOException;


/**
 * A class that reads command line input and interprets the commands into
 * actions on the KDTree.
 */
public class Repl {
  private boolean running = true;
  private BufferedReader reader = new
          BufferedReader(new InputStreamReader(System.in));
  private KDTree tree;
  private Trie trie;
  private Hollywood actorSource;

  /**
   * Constructor that initializes an empty tree.
   */
  public Repl() {
    trie = new Trie();
    tree = new KDTree();
    actorSource = new Hollywood(new GrowingGraph<Actor>());
  }

  /**
   * A method that runs the while loop of the REPL. When the user inputs Ctrl-D
   * /EOF the method exits and returns.
   * @param gui A boolean determining whether the gui is on
   */
  public void run(boolean gui) {
    actorSource.setGui(gui);
    StarsCommands starsC = new StarsCommands(tree);
    AutoCorrectCommands autoC = new AutoCorrectCommands(trie);
    BaconCommands baconC = new BaconCommands(actorSource);

    while (running) {
      try {
        // read line of input and split into arguments
        String response = reader.readLine();
        // check for EOF
        if (response == null) {
          running = false;
          reader.close();
          continue;
        }
        if (response.startsWith(" ")) {
          System.out.println("ERROR: command cannot begin with a space.");
          continue;
        }

        // The following code runs the commands of stars and auto correct.
        if (!autoC.run(response)) {
          if (!starsC.run(response)) {
            if (!baconC.run(response)) {
              System.out.println("ERROR: Incorrect Command");
            }
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // close the reader and connection to the SQL db if it exists.
    actorSource.close();
    closeReader();
  }
  /**
   * This is a function to retrieve the KDTree that the REPL is using in order
   * for the GUI to utilize the contents and perform searches.
   * @return A KDTree
   */
  public KDTree getTree() {
    return tree;
  }

  /**
   * This is a function to retreive the Trie from the REPL, which is used by the
   * GUI to perform autocorrections.
   * @return The Trie, used by the repl containing the information in the
   * corpus.
   */
  public Trie getTrie() {
    return trie;
  }

  /**
   * This is a getter function to retrieve the Hollywood class object which
   * handles the actor graph functions.
   * @return A hollywood object.
   */
  public Hollywood getActorSource() {
    return actorSource;
  }
  /**
   * This wrapper method closes the reader after reading the input.
   */
  private void closeReader() {
    try {
      reader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
