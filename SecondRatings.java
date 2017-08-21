
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;

    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }

    public SecondRatings(String filename){
        this(filename, "ratings.csv");
    }

    public SecondRatings(String movieFilename, String raterFilename){
        fr = new FirstRatings (movieFilename, raterFilename);
        myMovies = fr.loadMovies(movieFilename);
        myRaters = fr.loadRaters(raterFilename);
    }

    public int getMovieSize() {
        if (myMovies != null) {
            return myMovies.size();
        }
        return -1;
    }

    public int getRaterSize() {
        if (myRaters != null) {
            return myRaters.size();
        }
        return -1;
    }

    public double getAverageByID(String id, int minimalRaters) {
        double numRaters = fr.numberOfRatersPerMovie(id, myRaters);
        //System.out.println("id= " + id);
        //System.out.println("numRaters = " + numRaters);
        if (numRaters >= minimalRaters) { 
            double ratingsTotal = 0;
            for (Rater rater : myRaters) {
                double ratingValue = rater.getRating(id);
                if (ratingValue > -1) {
                    ratingsTotal += rater.getRating(id);
                }
            }
            return ratingsTotal/numRaters;
        }        
        return 0.0D;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<>();
        for(Movie movie : myMovies) {
            double avgRating = getAverageByID(movie.getID(), minimalRaters);
            if (avgRating > 0.0) {
                Rating rating = new Rating (movie.getID(), avgRating);
                ratings.add(rating);
            }

        }
        return ratings;
    }

    public String getTitle (String movieID) {
        for(Movie movie : myMovies) {
            if(movie.getID().equals(movieID)) {
                return movie.getTitle();
            }
        }        
        return "no movie found";
    }
    
    public String getID (String title) {
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }        
        return "NO SUCH TITLE";
    }
}
