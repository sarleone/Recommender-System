
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
/**
 * Write a description of FourthRatingsTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatingsTest {
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown() {
    }

    @Test
    public void testDotProduct()
    {
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr= new FourthRatings();
        //assertEquals(17.0D, fr.dotProduct(RaterDatabase.getRater("2"), RaterDatabase.getRater("4")), 0.1D);

    }

    @Test
    public void testGetSimilarities()
    {
        //Need to track down which Database we are reading--Seems to be picking up the large database for TJ
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize("ratings_short copy.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr= new FourthRatings();
        ArrayList<Rating> ratings = fr.getSimilarities("2");
        assertEquals(1,ratings.size());
        for (Rating rating : ratings) {
            System.out.println("Rater: " + rating.getItem() + " Rating: " + rating.getValue()) ;
        }
        //assertEquals(17.0D, fr.dotProduct(RaterDatabase.getRater("2"), RaterDatabase.getRater("4")), 0.1D);

    }

    /*@Test
    public void testGetNumSimRatersPerMovie()
    {
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize("ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        FourthRatings fr= new FourthRatings();
        ArrayList<Rating> list = fr.getSimilarities("1");
        for (Rating rating : list) {
            System.out.println("Rater: " + rating.getItem() + " Rating: " + rating.getValue()) ;
        }
        //int numSimRaters = fr.getNumSimRatersPerMovie(list, 10, "0068646");
        System.out.println("numSimRaters: " + numSimRaters);
    }*/

}

