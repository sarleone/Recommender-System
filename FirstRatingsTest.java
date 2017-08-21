
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRatingsTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatingsTest {
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
    public void testLoadMovies()
    {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> movies = fr.loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of Movies: "+ movies.size());
        for (Movie movie : movies) {
            System.out.println(movie);
        }

    }

    @Test
    public void testNumberOfMoviesInGenre()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv");
        assertEquals(1, fr.numberOfMoviesInGenre("Comedy"));
    }

    @Test
    public void testNumberOfMoviesLongerThan()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv");
        fr.numberOfMoviesLongerThan(150);
        //assertEquals(2, fr.numberOfMoviesLongerThan(150));
    }

    @Test
    public void testLoadRaters()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv");
        ArrayList<Rater> raters = fr.loadRaters("data/ratings_short.csv");
        assertEquals(5, raters.size());
        System.out.println("Total number of raters: "+ raters.size());
        for (Rater rater : raters){
            System.out.println("Rater " + rater.getID() + " has " + rater.numRatings());
        }
        
        fr = new FirstRatings("data/ratedmoviesfull.csv");
        raters = fr.loadRaters("data/ratings.csv");
        assertEquals(1048, raters.size());
        System.out.println("Total number of raters: "+ raters.size());
    }
    
    @Test
    public void testNumberOfRatings()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        assertEquals(3, fr.numberOfRatings("2"));
        
    }
    
    @Test
    public void testRaterWithMostRatings()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        fr.raterWithMostRatings();
    }
    
    @Test
    public void testNumberOfRatersPerMovie()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        assertEquals(4,fr.numberOfRatersPerMovie("1798709"));
    }
    
     @Test
    public void testNumberOfMoviesRated()
    {
        FirstRatings fr = new FirstRatings("data/ratedmovies_short.csv","data/ratings_short.csv");
        assertEquals(4,fr.numberOfMoviesRated());
    }
   
}

