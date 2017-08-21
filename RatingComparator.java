
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of RatingComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RatingComparator {
    public int compare(Rating r1, Rating r2) {
        return Double.compare(r1.getValue(), r2.getValue());
    }

}
