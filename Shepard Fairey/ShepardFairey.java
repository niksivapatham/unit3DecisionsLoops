import java.awt.Color;

/**
 * Transforms a picture by applying a Shepard Fairey-inspired effect.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ShepardFairey
{
    private Picture picture;

    private static final Color OFF_WHITE = new Color( 248, 229, 175 );
    private static final Color LIGHT_BLUE = new Color( 121, 149, 159 );
    private static final Color RED = new Color( 198, 50, 45 );
    private static final Color DARK_BLUE = new Color( 16, 48, 77 );

    public ShepardFairey( Picture newPicture )
    {
        this.picture = newPicture;
    }

    /**
     * Transforms the picture by applying a Shepard Fairey-inspired effect.
     *
     */
    public void transform()
    {
        
    }

    public static void main(String args[])
    {
        // create a new picture object based on the original selfie
        Picture selfie = new Picture( "selfie.jpg" );

        // create a ShepardFairey object to transform the selfie picture
        ShepardFairey fairey = new ShepardFairey( selfie );

        // display the original selfie picture
        selfie.explore();

        // transform the selfie picture by applying a Shepard Fairey-inspired effect
        fairey.transform();

        // save the transformed selfie picture
        
        /* This code doesn't work for some students for unknown reasons.
         * You may need to specify an absolute path. For example:
         *  finalPic.write("C:\\Users\\gschmit\\Desktop\\GitHub\\unit6MediaComp\\images\\Schmit.jpg");
         */
        selfie.write( "/Users/geoff/newSelfie.jpg" );

        // display the transformed selfie picture
        selfie.explore();
    }
}