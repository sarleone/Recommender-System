
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Write a description of MovieRunnerAverageTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverageTest {
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
    public void testPrintAverageRatings()
    {
        MovieRunnerAverage mra = new MovieRunnerAverage ();
        mra.printAverageRatings("data/ratedmovies_short.csv","data/ratings_short.csv", 1);
    }
    
    @Test
    public void testGetAverageRatingOneMovie()
    {
        MovieRunnerAverage mra = new MovieRunnerAverage ();
        mra.getAverageRatingOneMovie();
    }
}

