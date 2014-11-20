import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import info.gridworld.actor.Actor;

/**
 * The test class GameOfLifeTest.
 *
 * @author  @gcschmit
 * @version 19 July 2014
 */
public class GameOfLifeTest
{
    /**
     * Default constructor for test class GameOfLifeTest
     */
    public GameOfLifeTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    /**
     * This test the initial seed pattern to make sure it was implemented properly
     */

    @Test
    public void testInitialState()
    {
        /* No expected Pattern because the grid is giant
         *        
         */

        GameOfLife game = new GameOfLife();
        final int ROWS = game.getNumRows();
        final int COLS = game.getNumCols();

        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                // in this example, an alive cell has a non-null actor and a dead cell has a null actor
                Actor cell = game.getActor(row, col);

                // if the cell at the current row and col should be alive, assert that the actor is not null
                if(     (row == 52 && col == 49) ||
                        (row == 52 && col == 50) ||
                        (row == 50 && col == 50) ||
                        (row == 52 && col == 53) ||
                        (row == 52 && col == 54) ||
                        (row == 52 && col == 55) ||
                        (row == 51 && col == 52)) 
                {
                    assertNotNull("expected alive cell at (" + row + ", " + col + ")", cell);
                }
                else // else, the cell should be dead; assert that the actor is null
                {
                    assertNull("expected dead cell at (" + row + ", " + col + ")", cell);
                }
            }
        }
    }
    /**
     * Though meant to test the final state, my seed infinitely expands, so it effectively
     * just tests the second phase, just to make sure the next Generation method is functioning
     * properly
     */

    @Test
    public void testFinalState()
    {
             
        
        GameOfLife game = new GameOfLife();
        final int ROWS = game.getNumRows();
        final int COLS = game.getNumCols();
        //Tests the second stage to make sure everything is going according to plan
        game.createNextGeneration();
        
        for(int row = 0; row < ROWS; row++)
        {
            for(int col = 0; col < COLS; col++)
            {
                // in this example, an alive cell has a non-null actor and a dead cell has a null actor
                Actor cell = game.getActor(row, col);

                // if the cell at the current row and col should be alive, assert that the actor is not null
                if(     (row == 51 && col == 49) ||
                        (row == 51 && col == 50) ||
                        (row == 51 && col == 51) ||
                        (row == 51 && col == 53) ||
                        (row == 51 && col == 54) ||
                        (row == 52 && col == 53) ||
                        (row == 52 && col == 54) ||   
                        (row == 53 && col == 54)
                
                )            
                
                {
                    assertNotNull("expected alive cell at (" + row + ", " + col + ")", cell);
                }
                else // else, the cell should be dead; assert that the actor is null
                {
                    assertNull("expected dead cell at (" + row + ", " + col + ")", cell);
                }
            }
        }
    }
}


