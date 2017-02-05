package Helpers;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Created by Dan Amador on 2/5/2017.
 */
public class ImageCharacteristics {
    public int width, height;
    public PixelReader pr;
    public PixelWriter pw;
    public WritableImage wImage;

    public ImageCharacteristics(Image image){
        pr = image.getPixelReader();
        width = (int) image.getWidth();
        height = (int) image.getWidth();
        wImage = new WritableImage(width, height);
        pw = wImage.getPixelWriter();
    }
}
