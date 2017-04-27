package Engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AssetLoader
{
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
