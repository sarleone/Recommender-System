
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        printAverageRatings("ratedmovies_short.csv","ratings_short.csv", 1);
    }

    public void printAverageRatings(String movieFilename, String raterFilename, int minimalRaters) {        
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize(raterFilename);
        MovieDatabase.initialize(movieFilename);
        FourthRatings frr = new FourthRatings();
        System.out.println("Number of raters: " + RaterDatabase.size());
       
        System.out.println("Number of movies: " + MovieDatabase.size());

        ArrayList<Rating> ratings = frr.getAverageRatings(minimalRaters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            System.out.println("rating= " + rating);
            System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenere() {
        printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "964", 5, 20, new GenreFilter("Mystery"));
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        printAverageRatingsByYearAfterAndGenre("ratedmoviesfull.csv","ratings.csv", 1990, "Drama", 8);
    }
    
    public void printSimilarRatingsByDirector() {
        printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "120", 2, 10, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        AllFilters filter = new AllFilters();
        filter.addFilter (new GenreFilter("Drama"));
        filter.addFilter(new MinutesFilter(80, 160));
        printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "168", 3, 10, filter);
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        AllFilters filter = new AllFilters();
        filter.addFilter (new YearAfterFilter(1975));
        filter.addFilter(new MinutesFilter(70, 200));
        printSimilarRatings("ratedmoviesfull.csv","ratings.csv", "314", 5, 10, filter);
    }
    
     public void printAverageRatingsByYearAfterAndGenre (String movieFilename, String raterFilename, int year, String genre, int minimalRaters) {        
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize(raterFilename);
        System.out.println("Number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 
        FourthRatings frr = new FourthRatings();
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(year));
        filter.addFilter(new GenreFilter(genre));
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = frr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem())+ " "+ MovieDatabase.getGenres(rating.getItem()));
        }
    }
    //movie returned should be "The Fault in our Stars"
    public void printSimilarRatings() {
        printSimilarRatings("ratedmoviesfull.csv", "ratings.csv", "71", 5, 20, new TrueFilter());
    }
    
    public void printSimilarRatings(String movieFilename, String raterFilename, String raterId, int minRater, int numSimRaters, Filter filter) {
        RaterDatabase.nullify();
        MovieDatabase.nullify();
        RaterDatabase.initialize(raterFilename);
        System.out.println("Number of raters: " + RaterDatabase.size());
        MovieDatabase.initialize(movieFilename);
        System.out.println("Number of movies: "+ MovieDatabase.size()); 
        FourthRatings frr = new FourthRatings();
        System.out.println();
        //System.out.println(frr.getSimilarRatings(raterId, numSimRaters, minRater));
        ArrayList<Rating> ratings = frr.getSimilarRatings(raterId, numSimRaters, minRater, filter);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(MovieDatabase.getTitle(rating.getItem()) + ": " +rating.getValue());
        }
    }
}
