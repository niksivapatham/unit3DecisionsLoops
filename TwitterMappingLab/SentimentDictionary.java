import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * This class is a singleton that manages access to the sentiments dictionary.
 * 
 * The sentiments file is part of the Twitter Trends project by Aditi Muralidharan,
 * John DeNero, and Hamilton Nguyen and featured on Stanford’s Nifty Lab's site.
 * 
 * <http://nifty.stanford.edu/2013/denero-muralidharan-trends/>
 * 
 * @author gcschmit
 * @version 08jul2016
 */

public class SentimentDictionary
{
    private static SentimentDictionary singleton;
    
    private static String SENTIMENTS_FILENAME = "sentiments.csv";
        
    private Map<String, Double> dictionary;

    /**
     * Default Constructor for objects of class SentimentDictionary.
     * 
     * This is private to ensure that the getSingleton method is used by clients.
     */
    private SentimentDictionary()
    {
    }
    
    /**
     * Constructor for objects of class SentimentDictionary.
     * 
     * This is private to ensure that the getSingleton method is used by clients.
     * 
     * @param fileName name of the sentiments file from which to build this dictionary
     * 
     * @precondition the specified file must be a CSV file with two columns.
     *      The first contains the word; the second, a sentiment score.
     *      The sentiment will range from -1 to 1, where negative values correspond
     *      to negative sentiment; positive values, positive.
     */
    private SentimentDictionary( String fileName )
    {
        dictionary = new HashMap<String, Double>();
        
        try
        {
            File sentimentFile = new File( fileName );
            Scanner in = new Scanner( sentimentFile );
            in.useDelimiter( "[,\r\n]" );
            
            while( in.hasNext())
            {
                String word = in.next();
                Double sentiment = in.nextDouble();
                dictionary.put( word, sentiment );
            }
            
            in.close();
        }
        catch( FileNotFoundException e )
        {
            System.out.println( "Sentiment file: " + fileName + " not found." );
        }
        
    }
    
    /**
     * Returns the singleton instance of the SentimentDictionary.
     *      Clients should invoke this method to obtain a refernece to a SentimentDictionary object.
     * 
     * @return     the singleton instance of the SentimentDictionary
     * 
     * @precondition callers are responsible for invoking this method in a thread-safe manner
     */
    public static SentimentDictionary getSingleton()
    {
        // not thread safe
        if( singleton == null )
        {
            singleton = new SentimentDictionary( SENTIMENTS_FILENAME );
        }
        
        return singleton;
    }

    /**
     * Returns the sentiment associated with the specified word.
     *      The sentiment will range from -1 to 1, where negative values correspond
     *      to negative sentiment; positive values, positive. If there is no sentiment for
     *      the specified word, a null value will be returned.
     * 
     * @param  word   the word whose sentiment will be returned, the word must be all lowercase
     * @return     the sentiment associated with the specified word or null if none
     */
    public Double getSentiment( String word )
    {
        return dictionary.get( word );
    }
}
