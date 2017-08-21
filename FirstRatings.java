import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    final private static int ID = 0;
    final private static int TITLE = 1;
    final private static int YEAR = 2;
    final private static int COUNTRY = 3;
    final private static int GENRE = 4;
    final private static int DIRECTOR = 5;
    final private static int MINUTES = 6;
    final private static int POSTER = 7;

    final private static int RATER_ID = 0;
    final private static int MOVIE_ID = 1;
    final private static int RATING = 2;
    final private static int TIME = 3;

    private ArrayList<Movie> movies;
    private ArrayList<Rater> raters;

    public FirstRatings(){
        movies = null;
        raters = null;
    }

    public FirstRatings(String filename){
        movies = loadMovies(filename);
        raters = null;
    }

    public FirstRatings(String movieFilename, String raterFilename){
        movies = loadMovies(movieFilename);
        raters = loadRaters(raterFilename);
    }
    
    public ArrayList<Movie> getMovies() {
        if(movies != null) {
            return movies;
        }
        movies = loadMovies();
        return movies;
    }
    
    public ArrayList<Rater> getRaters() {
        if(raters != null) {
            return raters;
        }
        raters = loadRaters();
        return raters;
    }
    
    public ArrayList<Movie> loadMovies() {
        return loadMovies(new FileResource());
    }
    
    public ArrayList<Movie> loadMovies(String filename) {
        return loadMovies(new FileResource(filename));
    }

    public ArrayList<Movie> loadMovies(FileResource fr) {        
        ArrayList<Movie> movies = new ArrayList<>();
        for(CSVRecord rec: fr.getCSVParser()) {
            Movie movie = new Movie(rec.get(ID), rec.get(TITLE), rec.get(YEAR),rec.get(GENRE), rec.get(DIRECTOR),
                    rec.get(COUNTRY), rec.get(POSTER),Integer.parseInt(rec.get(MINUTES)));
            movies.add(movie);             
        }
        this.movies=movies;
        return movies;
    }

    
    

    public int numberOfMoviesInGenre (String genre) {
        int count = 0;
        for(Movie movie : getMovies()){
            //System.out.println(movie);
            if(movie.getGenres().contains(genre)){
                count++;
            }
        }
        return count;
    }

    public int numberOfMoviesLongerThan (int minutes) {
        int count = 0;
        for (Movie movie : getMovies()) {
            if(movie.getMinutes() > minutes) {
                count++; 
            }
        }
                System.out.println("Number of Movies longer than n min: " + count);
        return count;

    }

    public void directorsWithMostMovies(){
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        int maxMovies = -1;
        for(Movie movie: getMovies()){
            String director = movie.getDirector();
            if (!map.containsKey(director)){
                map.put(director,1);
            }
            else {
                map.put(director,map.get(director)+1);
            }
            int movieCount = map.get(director);
            if(movieCount > maxMovies){
                maxMovies = movieCount;
            }
        }
        System.out.println("Max movies for a single director: "+ maxMovies);
        System.out.println("Directors who directed max number of movies: " );

        for(String director : map.keySet()){
            int value = map.get(director);
            if (value == maxMovies){
                System.out.println("\t"+director);
            }

        }

    }

    public ArrayList<Rater> loadRaters() {
        return loadRaters(new FileResource());
    }

    public ArrayList<Rater> loadRaters (String filename){
        System.out.println("Name of filename FR: "+ filename);
        return loadRaters(new FileResource(filename));
    }

    public Rater getRater(String raterID, ArrayList<Rater>raters){
        //if there is a rater in raters with id = raterID
        //return it
        //else return null

        for (Rater rater : raters   ){
            //System.out.println("raterID = "+ raterID + ", rater.getID() = " + rater.getID());
            if(raterID.equals(rater.getID())){
                //System.out.println("MATCH FOUND");
                return rater;
            }
        }
        return null;
    }

    public ArrayList<Rater> loadRaters (FileResource fr){
        ArrayList<Rater> raters = new ArrayList<>();
        for(CSVRecord rec: fr.getCSVParser()) {
            Rater rater = getRater(rec.get(RATER_ID), raters);
            if (rater == null) {
                rater = new EfficientRater(rec.get(RATER_ID));
                raters.add(rater);
            }
            rater.addRating(rec.get(MOVIE_ID), Integer.parseInt(rec.get(RATING)));

        }
        return raters;
    }

    public int numberOfRatings (String raterID) {
        Rater rater = getRater(raterID, raters);
        if(rater != null) {
            return rater.numRatings();
        }
        System.out.println("Number of Ratings for n userID: "+ rater.numRatings());
        return -1;
    }
    
    public void raterWithMostRatings(){

        int maxRatings = -1;
        for(Rater rater: getRaters()){
            if (rater.numRatings() > maxRatings){
               maxRatings = rater.numRatings();
            }
        }
        System.out.println("Max ratings for a single rater: "+ maxRatings);
        
        System.out.println("Raters that gave the most ratings: ");

        for(Rater rater: getRaters()){
            if (rater.numRatings() == maxRatings){
               System.out.println( "\t" + rater.getID());
            }
        }

    }
    
    public int numberOfRatersPerMovie (String movieID) {
        return numberOfRatersPerMovie(movieID, raters);
    }
    
    public int numberOfRatersPerMovie(String movieID, ArrayList<Rater> raterList) {
        int totalRatings = 0;
        for(Rater rater: raterList){
            if (rater.hasRating(movieID)){
               totalRatings++;
            }
        }
        return totalRatings;
    }
    
    public int numberOfMoviesRated() {
        HashSet<String> uniqueMovies = new HashSet<>();
        for(Rater rater: getRaters()){
            for (String movieID : rater.getItemsRated()){
               uniqueMovies.add(movieID);
            }
        }
        return uniqueMovies.size();
    }
}