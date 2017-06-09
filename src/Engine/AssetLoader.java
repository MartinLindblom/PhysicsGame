package Engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class provides convenient means of loading assets.
 */
public class AssetLoader
{
    /**
     * Used to load an image from the "assets" folder.
     * @param path This is the path to the image as a string.
     * @return The image that was requested, or if image does not exist it returns a default image.
     */
    public static BufferedImage loadImage(String path)
    {
        BufferedImage bufferedImage;

        try
        {
            bufferedImage = ImageIO.read(new File("assets/" + path));
        }
        catch (IOException e)
        {
            System.err.println("Failed to load image: " + path);
            e.printStackTrace();

            try
            {
                bufferedImage = ImageIO.read(new File("assets/DefaultImage.png"));
            }
            catch (IOException e2)
            {
                bufferedImage = null;

                System.err.println("Failed to fall back on default image!");
                e2.printStackTrace();
            }
        }

        return bufferedImage;
    }
}
