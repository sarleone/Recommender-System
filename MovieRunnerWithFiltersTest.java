
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Write a description of MovieRunnerWithFiltersTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFiltersTest {
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
    public void testPrintAverageRatings() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatings("ratedmoviesfull.csv","ratings.csv", 35);
    }
    
    @Test
    public void testPrintAverageRatingsByYear() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatingsByYear();
        //mrwf.printAverageRatingsByYear("ratedmovies_short.csv","ratings_short.csv", 2000, 1);
    }
    
    @Test
    public void testPrintAverageRatingsByGenre() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatingsByGenre();
        //mrwf.printAverageRatingsByYear("ratedmovies_short.csv","ratings_short.csv", 2000, 1);
    }
    
    @Test
    public void testPrintAverageRatingsByMinutes() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatingsByMinutes();
        //mrwf.printAverageRatingsByYear("ratedmovies_short.csv","ratings_short.csv", 2000, 1);
    }
    
    @Test
    public void testPrintAverageRatingsByDirector() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        //mrwf.printAverageRatingsByDirector();
        mrwf.printAverageRatingsByDirector("ratedmoviesfull.csv","ratings.csv", "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", 4);
    }
    
    @Test
    public void testPrintAverageRatingsByYearAfterAndGenre() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatingsByYearAfterAndGenre();
        //mrwf.printAverageRatingsByYear("ratedmovies_short.csv","ratings_short.csv", 2000, 1);
    }
    
    @Test
    public void testPrintAverageRatingsByDirectorsAndMinutes() {
        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        mrwf.printAverageRatingsByDirectorsAndMinutes();
        //mrwf.printAverageRatingsByYear("ratedmovies_short.csv","ratings_short.csv", 2000, 1);
    }
}
