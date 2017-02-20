package Helpers;

/**
 * Created by Dan Amador on 2/19/2017.
 */
public class BasicImageHelper {
    public static double boundaryCheck(double value){
        if (value > 1) return 1;
        else if (value < 0) return 0;
        return value;
    }
}
