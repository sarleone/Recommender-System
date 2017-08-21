
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        printAverageRatings("ratedmoviesfull.csv","ratings.csv", 35);
    }

    public void printAverageRatings(String movieFilename, String raterFilename, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        System.out.println("Number of movies: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatings(minimalRaters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            System.out.println("rating= " + rating);
            System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByYear(){
        printAverageRatingsByYear("ratedmoviesfull.csv","ratings.csv", 2000, 20);
    }

    public void printAverageRatingsByYear (String movieFilename, String raterFilename, int year, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 

        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(year));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        printAverageRatingsByGenre("ratedmoviesfull.csv","ratings.csv", "Comedy", 20);
    }
    
    public void printAverageRatingsByGenre (String movieFilename, String raterFilename, String genre, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 

        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getGenres(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
     public void printAverageRatingsByMinutes(){
        printAverageRatingsByMinutes("ratedmoviesfull.csv","ratings.csv", 105, 135, 5);
    }

    public void printAverageRatingsByMinutes (String movieFilename, String raterFilename, int min, int max, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 

        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(110, 170));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getMinutes(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirector(){
        printAverageRatingsByGenre("ratedmoviesfull.csv","ratings.csv", "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack", 4);
    }
    
    public void printAverageRatingsByDirector (String movieFilename, String raterFilename, String directors, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 

        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter(directors));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getDirector(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        printAverageRatingsByYearAfterAndGenre("ratedmoviesfull.csv","ratings.csv", 1990, "Drama", 8);
    }
    
     public void printAverageRatingsByYearAfterAndGenre (String movieFilename, String raterFilename, int year, String genre, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 
        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(year));
        filter.addFilter(new GenreFilter(genre));
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())+ " "+ MovieDatabase.getTitle(rating.getItem())+ " "+ MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
       public void printAverageRatingsByDirectorsAndMinutes(){
        printAverageRatingsByDirectorsAndMinutes("ratedmoviesfull.csv","ratings.csv", "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack", 90, 180, 3);
    }
    
    public void printAverageRatingsByDirectorsAndMinutes (String movieFilename, String raterFilename, String directors, int min, int max, int minimalRaters) {
        ThirdRatings tr = new ThirdRatings(raterFilename);
        System.out.println("Number of raters: " + tr.getRaterSize());
        MovieDatabase.initialize(movieFilename);
        //System.out.println("MovieFilename is: "+ movieFilename); 
        AllFilters filter = new AllFilters();
        filter.addFilter(new DirectorsFilter(directors));
        filter.addFilter(new MinutesFilter(30, 170));
        System.out.println("Number of movies: " + MovieDatabase.size());
        ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(minimalRaters, filter);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating: ratings) {
            //System.out.println("rating= " + rating);
            //System.out.println("Looking for item " + "|" + rating.getItem() + "|");
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())+ " "+ MovieDatabase.getDirector(rating.getItem()) + " " + "Time: "+ MovieDatabase.getMinutes(rating.getItem()));
        }
    }
}