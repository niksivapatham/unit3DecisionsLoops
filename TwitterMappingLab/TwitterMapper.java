import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import twitter4j.*;


/**
 * This class queries Twitter for tweets in a specific state that contain a specified keyword. It then associates a sentiment
 *      with that tweet. The sentiment for all tweets in a state is aggregated and averaged. The resulting average sentiments
 *      are then represented in a choropleth representing the sentiment of the tweets in each state.
 *      
 * The data for the geographic center and area of each state was obtained from Wikipedia.
 * 
 * The data is plotted using the DataMaps Javascript framework: <http://datamaps.github.io>. Specifically, the HTML file is
 *      based on the Choropleth with auto-calculated color example:
 *      <https://github.com/markmarkoh/datamaps/blob/master/README.md#choropleth-with-auto-calculated-color>.
 * 
 * @author gcschmit (based on Twitter_Driver.java by Rita Galanos)
 * @version 07jul2016
 */

public class TwitterMapper
{
    private Twitter twitter;
    private ArrayList<State> states;
    private String keyword;
    
    private static String HTML_TEMPLATE_FILENAME = "mapTemplate.html";
    private static String STATE_DATA_FILENAME = "states.csv";
    private static int MAX_TWEETS_PER_STATE = 100;

    
    /**
     * Standard main method for the program. Creates a TwitterMapper object for a given keyword, find tweets matching
     *      the keyword in each of the 50 US states, and then generates an HTML file visualizing the average sentiment
     *      of the tweets in each state.
     */
    public static void main ( String[] args ) throws TwitterException, IOException
    {
        TwitterMapper twitterMapper = new TwitterMapper( "coding" );
    }
    
    
    /**
     * Constructor for objects of class TwitterMapper.
     * 
     * @param keyword the word that will be used to search for tweets
     */
    public TwitterMapper(String keyword)
    {
        this.keyword = keyword;
        
        // make an instance of Twitter - this is re-useable and thread safe.
        twitter = new TwitterFactory().getInstance();
        
        // load US state information
        loadStateInformation( STATE_DATA_FILENAME );
    }

    /**
     * Publishes the specified tweet from the account associated with this project.
     * 
     * @param  message   the text of the tweet to publish
     */
    public void tweetOut( String message ) throws TwitterException, IOException
    {
        twitter.updateStatus( message );
    }

    /**
     * Searches Twitter for the specified number of tweets (MAX_TWEETS_PER_STATE) containing the keyword associated with this
     *      object in the specified state.
     *      The sentiment of each tweet will be calculated and the average sentiment of tweets associated with this state
     *          will be calculated and stored in the specified state object.
     *      Twitter allows queries to specify a geographic area in which to search for tweets. This area is specified as a
     *          geographic center and a radius. This doesn't exactly match to the boundary of a given state. Instead, the
     *          geographic center of the state is specified and the radius is specified to match a circle with the same
     *          area as the state.
     *      
     * @param state search for tweets associated with this state
     * 
     */
    public void findTweetsForState( State state) throws TwitterException
    {
        
    }
    
    /**
     * Searches Twitter for tweets containing the keyword associated with this object in each of the 50 US states.
     *      The sentiment of each tweet will be calculated and the average sentiment of tweets associated with each state
     *      will be calculated.
     * 
     */
    public void findTweetsForAllStates() throws TwitterException, IOException
    {
        for( State state : this.states )
        {
            findTweetsForState( state );
        }
    }

    /**
     * Generates an HTML file that visualizes the resulting average sentiments for each states as a choropleth
     *      representing the sentiment of the tweets in each state.
     * 
     * This method reads from a template HTML file and creates a new HTML file into which is inserted the sentiment values
     *      for each state. The name of the HTML file is the name of the keyword.html.
     *      
     */
    public void mapSentimentForAllStates()
    {
        try
        {
            File htmlTemplateFile = new File( HTML_TEMPLATE_FILENAME );
            Scanner in = new Scanner( htmlTemplateFile );

            PrintWriter out = new PrintWriter( this.keyword + ".html" );

            while( in.hasNextLine())
            {
                String line = in.nextLine();
                
                if( line.contains( "### insert state data here ###" ))
                {
                    // insert the sentiment values for each state according to the expected format
                    for( State state : this.states )
                    {
                        // for example: ["AL",0.75],
                        out.println( "[\"" + state.getAbbreviation() + "\"," + state.getSentiment() + "]," );
                    }
                }
                else if( line.contains( "### insert keyword here ###" ))
                {
                    // insert the keyword which will be reflected in the header of the web page
                    out.println( this.keyword );
                }
                else
                {
                    out.println( line );
                }
            }
            
            out.close();
            in.close();
        }
        catch( FileNotFoundException e )
        {
            System.out.println( "HTML Tempalte file: " + HTML_TEMPLATE_FILENAME + " not found." );
        }
    }

    /**
     * Creates a list of state objects based on data in the specified file.
     *      
     * @param fileName search for tweets associated with this state
     * 
     * @precondition the specified file must be a CSV file with four columns.
     *      The first contains the two-letter ANSI abbreviation for the state; the second, the longitude as a double;
     *              the third, the latitude as a double; the fourth, the area of the state in square miles.
     * 
     */
    private void loadStateInformation( String fileName )
    {
        this.states = new ArrayList<State>();
        
        try
        {
            File statesFile = new File( fileName );
            Scanner in = new Scanner( statesFile );
            in.useDelimiter( "[,\r\n]" );
            
            while( in.hasNext())
            {
                String abbreviation = in.next();
                Double longitude = in.nextDouble();
                Double latitude = in.nextDouble();
                Double area= in.nextDouble();
                in.nextLine();
                this.states.add( new State( abbreviation, new GeoLocation( longitude, latitude ), area ));
            }
            
            in.close();
        }
        catch( FileNotFoundException e )
        {
            System.out.println( "States file: " + fileName + " not found." );
        }
    }
}
