
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;

    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile){
        fr = new FirstRatings ();
        myRaters = fr.loadRaters("data/"+ ratingsfile);
    }

    
    public int getRaterSize() {
        if (myRaters != null) {
            return myRaters.size();
        }
        return -1;
    }

    public double getAverageByID(String id, int minimalRaters) {
        double numRaters = fr.numberOfRatersPerMovie(id, myRaters);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movie : movies) {
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating > 0.0) {
                Rating rating = new Rating (movie, avgRating);
                ratings.add(rating);
            }

        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<>();
        for(String movie : movies) {
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating > 0.0) {
                Rating rating = new Rating (movie, avgRating);
                ratings.add(rating);
            }

        }
        return ratings;
    }
}
