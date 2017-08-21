
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        printAverageRatings("data/ratedmoviesfull.csv","data/ratings.csv", 20);
    }
    
    public void printAverageRatings(int minimalRaters) {
       printAverageRatings(new SecondRatings(), minimalRaters); 
    }
    
    public void printAverageRatings(String movieFilename, String raterFilename, int minimalRaters) {
        printAverageRatings(new SecondRatings(movieFilename, raterFilename), minimalRaters); 
    }
    
    public void printAverageRatings(SecondRatings sr, int minimalRaters) {
       System.out.println("Number of movies: " + sr.getMovieSize());
       System.out.println("Number of raters: " + sr.getRaterSize());
       ArrayList<Rating> ratings = sr.getAverageRatings(minimalRaters);
       Collections.sort(ratings);
       for(Rating rating: ratings) {
           System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {       
        getAverageRatingOneMovie("data/ratedmovies_short.csv","data/ratings_short.csv", "The Godfather");
    }
    
    public void getAverageRatingOneMovie(String title) {
        getAverageRatingOneMovie(new SecondRatings(), title);
    
    }
    
    public void getAverageRatingOneMovie(String movieFilename, String raterFilename, String title) {        
      getAverageRatingOneMovie(new SecondRatings(movieFilename, raterFilename), title);
    
    }
    
    public void getAverageRatingOneMovie(SecondRatings sr, String title) {       
        String movieID = sr.getID(title);
        double averageRating = sr.getAverageByID(movieID, 0);
        System.out.println("Average rating for "+ title+ ": "+" "+averageRating);
        
    }
}
