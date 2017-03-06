package Helpers;

/**
 * Created by Dan Amador on 3/5/2017.
 */
public class AsciiPixelHelper {

    public static  String getCharFromGrayValue(double g){
        final String str;
        if (g >= 240.0) {
            str = "M";
        } else if (g >= 224.0) {
            str = "N";
        } else if (g >= 208.0) {
            str = "H";
        } else if (g >= 192.0) {
            str = "#";
        } else if (g >= 176.0) {
            str = "Q";
        } else if (g >= 160.0) {
            str = "U";
        } else if (g >= 144.0) {
            str = "A";
        } else if (g >= 128.0) {
            str = "D";
        } else if (g >= 112.0) {
            str = "O";
        } else if (g >= 96.0) {
            str = "Y";
        } else if (g >= 80.0) {
            str = "2";
        } else if (g >= 64.0) {
            str = "$";
        } else if (g >= 48.0) {
            str = "%";
        } else if (g >= 32.0) {
            str = "++";
        } else {
            str = "--";
        }
        return str;

    }
}
