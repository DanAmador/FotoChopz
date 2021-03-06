package Helpers;

import javafx.scene.paint.Color;

/**
 * Created by Dan Amador on 3/5/2017.
 */
public class AsciiPixelStructure {
    private String letter;
    private String hexColor;

    public AsciiPixelStructure(String letter, Color color){
        this.letter = letter;
        this.hexColor = toRGBCode(color);
    }


    public String getLetterWithColor(){
        return String.format("<span style=\"color:%s\">%s</span>", this.hexColor,this.letter);
    }

    public String getLetter(){
        return this.letter;
    }
    private String toRGBCode( Color color ) {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

}
