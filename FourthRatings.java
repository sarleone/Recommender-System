import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Write a descripimport java.util.ArrayList;
import java.util.ArrayList;
tion of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {

    /**
     * Method getSimilarRatings
     * Like getAverageRatings, but as we loop through each rater and get its rating, average is 
     * multiplied by rating.getValue(), which is the weight for the given rater
     * 
     * For every rater, get their similarity rating to the given parameter rater id. Include only 
     * those raters with positive similarity ratings—those that are more similar to rater id. 
     * Which method could you call?
     * 
     * For each movie, calculate a weighted average movie rating based on: Use only the top (largest) 
     * numSimilarRaters raters. For each of these raters, multiply their similarity rating by the 
     * rating they gave that movie. This will emphasize those raters who are closer to the rater id, 
     * since they have greater weights. The weighted average movie rating for a particular movie is 
     * the sum of these weighted average ratings (for each rater multiply their similarity rating by 
     * their rating for the movie), divided by the total number of such ratings.
     * 
     * This method returns an ArrayList of Ratings for movies and their calculated weighted ratings, 
     * in sorted order.
     *
     * @param raterID A parameter
     * @param numSimilarRaters A parameter
     * @param minimalRaters A parameter
     * @return The return value
     * 
     * Assumes that movie database and rater database have been initialized
     */
      public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters)
    {   
        return getSimilarRatings(raterID, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters, Filter filter)
    {
        ArrayList<Rating> weightedRatings = new ArrayList<>();
        // For every rater, get their similarity rating to the given parameter rater id.
        // Use only the top numSimilarRaters raters.
        List<Rating> similarities = getSimilarities(raterID).subList(0, numSimilarRaters);
        // For each Rating object in similarities
        // Get the Rater object for the rating.getItem()
        // See if the Rater has the movie Spiderman or Fault in our Stars
        // If rater has that movie, print out rater, movie and similarity rating
        // Fault in our Stars - 2582846
        // Spiderman - 1872181
        
        // For each movie, calculate a weighted average movie rating
        for(String movie : MovieDatabase.filterBy(filter)) {
            double numRatings = 0.0D;
            double weightAverageSum = 0.0D;
            // For each rater, multiply their similarity rating by the rating they gave that movie. 
            for (Rating rating : similarities) {
                Rater rater = RaterDatabase.getRater(rating.getItem());
                if (rater.hasRating(movie)) {
                    weightAverageSum += rating.getValue() * rater.getRating(movie);
                    numRatings++;
                }
            }
            if (numRatings >= minimalRaters) {
                weightedRatings.add(new Rating(movie, weightAverageSum / numRatings));
            }
        }

        Collections.sort(weightedRatings, Collections.reverseOrder());

        return weightedRatings;
    }

    /**
     * Method getSimilarities
     *
     * @param raterID A parameter
     * @return a collection of ratings similar to raterID
     */
    public ArrayList<Rating>getSimilarities(String raterID)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(raterID);
        for (Rater r : RaterDatabase.getRaters()) {
            // add dotProduct(r, me) to list if r != me and dot product is > 0
            if (!r.equals(me)) {
                double dp = dotProduct(me, r);
                if (dp > 0) {
                    list.add(new Rating(r.getID(), dp));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    private HashMap<String, Double> getRaterMap(Rater r)
    {
        HashMap<String, Double> raterMap = new HashMap<>();
        ArrayList<String>itemsRated = r.getItemsRated();
        for (String movie : itemsRated) {
            raterMap.put(movie, Double.valueOf(r.getRating(movie)));
        }
        
        return raterMap;
    }
    
    private double dotProduct(Rater me, Rater r)
    {
        double sum = 0.0D;
        HashMap<String, Double> meMap = getRaterMap(me);
        ArrayList<String>rRatingsList = r.getItemsRated();
        
        for (String movie : rRatingsList)
        {
            Double meRating = meMap.get(movie); 
            if (meRating != null) {
                // get the adjusted ratings
                double myRating = meRating - 5;
                double rRating = r.getRating(movie) - 5;
                sum += myRating * rRating;
            }
        }
        // if the movie rated by r is in the hashmap for me, add in product for dot product
        return sum;
    }

    private double dotProductX(Rater me, Rater r)
    {
        double sum = 0.0D;
        ArrayList<String>myRatingsList = me.getItemsRated();
        ArrayList<String>rRatingsList = r.getItemsRated();
        Collections.sort(myRatingsList);
        Collections.sort(rRatingsList);
        Iterator<String>myRatings = myRatingsList.iterator();
        Iterator<String>rRatings = rRatingsList.iterator();
        String myMovie = "";
        String rMovie = "";
        while(myRatings.hasNext() || rRatings.hasNext()) {
            if (myRatings.hasNext()) {
                myMovie = myRatings.next();
                if (r.getID().equals("1040") && myMovie.endsWith("1300854")) {
                    System.out.println("############### 1040 rated " + myMovie + "#######################");
                }
            }
            if (rRatings.hasNext()) {
                rMovie = rRatings.next();
                if (r.getID().equals("1040") && rMovie.endsWith("1300854")) {
                    System.out.println("############### 1040 rated " + rMovie + "#######################");
                }
            }
            while (myMovie.compareToIgnoreCase(rMovie) > 0 && rRatings.hasNext()) {
                rMovie = rRatings.next();
                if (r.getID().equals("1040") && rMovie.endsWith("1300854")) {
                    System.out.println("############### 1040 rated " + rMovie + "#######################");
                }
            }
            while (myMovie.compareToIgnoreCase(rMovie) < 0 && myRatings.hasNext()) {
                myMovie = myRatings.next();
                if (r.getID().equals("1040") && myMovie.endsWith("1300854")) {
                    System.out.println("############### 1040 rated " + myMovie + "#######################");
                }
            }
            if (myMovie.equals(rMovie)) {
                // get the adjusted ratings
                double myRating = me.getRating(myMovie) - 5;
                double rRating = r.getRating(rMovie) - 5;
                // multiply the ratings and add the product to the sum
                if (r.getID().equals("1040")) {
                    System.out.println("||||||||||||| 1040 rated " + rMovie + "|||||||||||||");
                }
                sum += myRating * rRating;
            }
        }
        
        int intSum = (int)sum;
        double testSum = (double)intSum;
        if (testSum != sum) {
            System.out.println("**************************************************************");
            System.out.println("In dotSum, rater " + r.getID() + " has similarity " + sum);
            System.out.println("**************************************************************");
        }
        return sum;
    }

    public int numberOfRatingsForMovie(String movieID, List<Rater>raterList)
    {
        int totalRatings = 0;
        for(Rater rater : raterList){
            if (rater.hasRating(movieID)) {
                totalRatings++;
            }
        }
        return totalRatings;
    }

    /**
     * Method numberOfRatingsForMovie
     *
     * @param movieID A parameter
     * @param raterList A parameter
     * @return The return value
     * 
     * Assumes item in Rating is a rater
     */
    public int numberOfRatingsForMovieFromRatings(String movieID, List<Rating>raterList)
    {
        int totalRatings = 0;
        for(Rating rating : raterList){
            Rater rater = RaterDatabase.getRater(rating.getItem());
            if (rater.hasRating(movieID)) {
                totalRatings++;
            }
        }
        return totalRatings;
    }

    public double getAverageByID(String id, int minimalRaters)
    {
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        double numRaters = numberOfRatingsForMovie(id, raters);
        if(numRaters >= minimalRaters) {
            double ratingsTotal = 0;
            for (Rater rater : raters) {
                double ratingValue = rater.getRating(id);
                if (ratingValue > -1) {
                    ratingsTotal += rater.getRating(id);
                }
            }
            return ratingsTotal / numRaters;
        }

        return 0.0D;

    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters)
    {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> ratings = new ArrayList<>();
        for (String movie : movies) {
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating > 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
        }
        return ratings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(
    int minimalRaters, Filter filterCriteria)
    {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ratings = new ArrayList<>();
        for (String movie : movies) {
            double avgRating = getAverageByID(movie, minimalRaters);
            if (avgRating > 0.0) {
                Rating rating = new Rating(movie, avgRating);
                ratings.add(rating);
            }
        }
        return ratings;
    }
}
