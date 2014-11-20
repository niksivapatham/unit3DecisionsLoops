import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
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
    private final int ROWS = 100;
    private final int COLS = 100;

    // constants for the location of the three cells initially alive
    private final int X1 = 32, Y1 = 49;
    private final int X2 = 32, Y2 = 50;
    private final int X3 = 30, Y3 = 50;
    private final int X4 = 32, Y4 = 53;
    private final int X5 = 32, Y5 = 54;
    private final int X6 = 32, Y6 = 55;
    private final int X7 = 31, Y7 = 52;

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
        createDudes(X1,Y1);
        createDudes(X2,Y2);
        createDudes(X3,Y3);
        createDudes(X4,Y4);
        createDudes(X5,Y5);
        createDudes(X6,Y6);
        createDudes(X7,Y7);       

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
        
        //Arraylist gets all of the live locations 
        ArrayList<Location> all_live_locations = new ArrayList<Location>(grid.getOccupiedLocations());
        
        //Arraylist initialized as empty, this will hold all the locations that should be alive
        //in the next generation
        ArrayList<Location> next_generation = new ArrayList<Location>();

        for (int row = 0; row<= ROWS; row++){
            for (int column = 0; column <=COLS; column++)
            {
                Location location = new Location(row,column);
                ArrayList<Location> adjacent_location = new ArrayList<Location>(grid.getOccupiedAdjacentLocations(location));

                if ((all_live_locations.contains(location) && adjacent_location.size()== 2) || 
                (all_live_locations.contains(location) && adjacent_location.size()== 3) ||
                (adjacent_location.size()== 3))
                {
                    next_generation.add(location);
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
        
        //Clears the arraylists for the next call
        all_live_locations.clear();
        next_generation.clear();

    }

    /**
     * This Method slightly speeds up the process of creating rocks
     * @param  locx   The x coordinate of the desired rock
     * @param  locy   The y coordinate of the desired rock
     * @pre    The grid has been created
     *     
     */
    private void createDudes(int locx,int locy)
    {
        Grid<Actor> grid = world.getGrid();
        Random generator = new Random();
        Color color = new Color(generator.nextInt(256),generator.nextInt(256),generator.nextInt(256));
        Rock rock = new Rock(color);
        Location location = new Location(locx,locy);
        grid.put(location,rock);
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
    public static void main(String[] args) throws InterruptedException
    {
        GameOfLife game = new GameOfLife(); 
        //INfinite loop that creates advances to the next generation ever 100 milliseconds
        while (true) {
            Thread.sleep(100);
            game.createNextGeneration();
        }        
    }
    
    
}
