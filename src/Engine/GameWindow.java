package Engine;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GameWindow
{
    private Frame frame;
    private Panel canvas;

    private BufferedImage bufferedImage;

    private Color backgroundColor;

    private boolean shouldClose;



    public GameWindow(int width, int height, String title)
    {
        shouldClose = false;

        frame = new Frame(title, null);
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.addWindowListener(new java.awt.event.WindowAdapter()
        {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent)
            {
                shouldClose = true;
            }
        });

        canvas = new Panel();
        canvas.setPreferredSize(new Dimension(width, height));

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);

        bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

        backgroundColor = new Color(0, 0, 0);
    }



    public int getWidth()
    {
        return canvas.getWidth();
    }

    public int getHeight()
    {
        return canvas.getHeight();
    }

    public Graphics2D getGraphics()
    {
        Graphics2D g = (Graphics2D)bufferedImage.getGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
        g.setColor(Color.black);

        return g;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color c)
    {
        backgroundColor = c;
    }



    public void addKeyListener(KeyListener keyListener)
    {
        frame.addKeyListener(keyListener);
        canvas.addKeyListener(keyListener);
    }



    public boolean shouldClose()
    {
        return shouldClose;
    }

    public void renderCanvas()
    {
        ((Graphics2D)canvas.getGraphics()).drawImage(bufferedImage, null, 0, 0);
    }

    public void close()
    {
        frame.remove(canvas);
        canvas = null;
        frame.setVisible(false);
        frame.dispose();
    }
}
