import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.util.*;

/**
 * Game of Life starter code. Demonstrates how to create and populate the game using the GridWorld framework.
 * Also demonstrates how to provide accessor methods to make the class testable by unit tests.
 * 
 * @author @gcschmit
 * @version 18 July 2014
 */
public class GameOfLife
{
    // the world comprised of the grid that displays the graphics for the game
    private ActorWorld world;

    // the game board will have 5 rows and 5 columns
    private final int ROWS = 46;
    private final int COLS = 103;

    // constants for the location of the three cells initially alive
    private final int X1 = 4, Y1 = 2;
    private final int X2 = 4, Y2 = 3;
    private final int X3 = 4, Y3 = 4;
    private final int X4 = 4, Y4 = 5;
    private final int X5 = 4, Y5 = 6;

    /**
     * Default constructor for objects of class GameOfLife
     * 
     * @post    the game will be initialized and populated with the initial state of cells
     * 
     */
    public GameOfLife()
    {
        // create the grid, of the specified size, that contains Actors
        BoundedGrid<Actor> grid = new BoundedGrid<Actor>(ROWS, COLS);

        // create a world based on the grid
        world = new ActorWorld(grid);

        // populate the game
        populateGame();

        // display the newly constructed and populated world
        world.show();

    }

    /**
     * Creates the actors and inserts them into their initial starting positions in the grid
     *
     * @pre     the grid has been created
     * @post    all actors that comprise the initial state of the game have been added to the grid
     * 
     */
    private void populateGame()
    {
        //the grid of Actors that maintains the state of the game
        //(alive cells contains actors; dead cells do not)

        Grid<Actor> grid = world.getGrid();
        Random generator = new Random();

        for (int i=0;i<ROWS;i++){

            for (int j=0;j<COLS;j++) {
                if (i == 0 || i == ROWS) {
                    Rock rock = new Rock();
                    Location loc = new Location(i,j);
                    grid.put(loc,rock);
                }

                else if (j== 0 || j==COLS) {
                    Rock rock = new Rock();
                    Location loc = new Location(i,j);
                    grid.put(loc,rock);
                }

            }
        }

        // 
        // create and add rocks (a type of Actor) to the three intial locations
        //         Rock rock1 = new Rock();
        //         Location loc1 = new Location(X1, Y1);
        //         grid.put(loc1, rock1);
        // 
        //         Rock rock2 = new Rock();
        //         Location loc2 = new Location(X2, Y2);
        //         grid.put(loc2, rock2);
        // 
        //         Rock rock3 = new Rock();
        //         Location loc3 = new Location(X3, Y3);
        //         grid.put(loc3, rock3);
        // 
        //         Rock rock4 = new Rock();
        //         Location loc4 = new Location(X4, Y4);
        //         grid.put(loc4, rock4);
        // 
        //         Rock rock5 = new Rock();
        //         Location loc5 = new Location(X5, Y5);
        //         grid.put(loc5, rock5);

    }

    /**
     * Generates the next generation based on the rules of the Game of Life and updates the grid
     * associated with the world
     *
     * @pre     the game has been initialized
     * @post    the world has been populated with a new grid containing the next generation
     * 
     */
    public void createNextGeneration()
    {
        /** You will need to read the documentation for the World, Grid, and Location classes
         *      in order to implement the Game of Life algorithm and leverage the GridWorld framework.
         */

        // create the grid, of the specified size, that contains Actors
        Grid<Actor> grid = world.getGrid();

        ArrayList<Location> all_live_locations = new ArrayList<Location>(grid.getOccupiedLocations());
        ArrayList<Location> next_generation = new ArrayList<Location>();

        for (int row = 0; row<= 9; row++){
            for (int column = 0; column <=9; column++)
            {
                Location location = new Location(row,column);
                ArrayList<Location> adjacent_location = new ArrayList<Location>(grid.getOccupiedAdjacentLocations(location));

                //When the location is alive                
                if (all_live_locations.contains(location)){                    
                    if (adjacent_location.size()>3 || adjacent_location.size()<2)
                    {
                        String getRekt = "Get Shrekt son";                        
                    }
                    else {
                        next_generation.add(location);                          
                    }

                }
                //When the location is not alive
                else {
                    if (adjacent_location.size()==3)
                    {
                        next_generation.add(location);
                    }
                }
            }
        }

        //Resets the grid for the next generation
        for (Location remove : all_live_locations)
        {
            grid.remove(remove);
        }

        for (Location live : next_generation)
        {
            Rock rock = new Rock();
            grid.put(live, rock);
        }

        all_live_locations.clear();
        next_generation.clear();

    }

    /**
     * Returns the actor at the specified row and column. Intended to be used for unit testing.
     *
     * @param   row the row (zero-based index) of the actor to return
     * @param   col the column (zero-based index) of the actor to return
     * @pre     the grid has been created
     * @return  the actor at the specified row and column
     */
    public Actor getActor(int row, int col)
    {
        Location loc = new Location(row, col);
        Actor actor = world.getGrid().get(loc);
        return actor;
    }

    /**
     * Returns the number of rows in the game board
     *
     * @return    the number of rows in the game board
     */
    public int getNumRows()
    {
        return ROWS;
    }

    /**
     * Returns the number of columns in the game board
     *
     * @return    the number of columns in the game board
     */
    public int getNumCols()
    {
        return COLS;
    }

    /**
     * Creates an instance of this class. Provides convenient execution.
     *
     */
    public static void main(String[] args)
    {
        GameOfLife game = new GameOfLife();

        while (true) {
            try {                
                Thread.sleep(100);
                game.createNextGeneration();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
