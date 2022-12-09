package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.painter
@Author: BubbaJoeX
@Purpose: Gets coordinates from a specified object and plots them on an image on /home/swg/swg-main/exe/linux/
*/

import script.location;
import script.obj_id;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class painter extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int paint(obj_id self, obj_id[] targets) throws IOException
    {
        String path = "/home/swg/swg-main/exe/linux/hotmaps/";
        BufferedImage image = ImageIO.read(new File(path, getCurrentSceneName() + ".png"));
        if (image == null)
        {
            sendSystemMessageTestingOnly(self, "Image is null, making new image, try again.");
            BufferedImage newImage = new BufferedImage(8000, 8000, BufferedImage.TYPE_INT_RGB);
            ImageIO.write(newImage, "png", new File(path, getCurrentSceneName() + ".png"));
            return SCRIPT_CONTINUE;
        }
        for (obj_id target : targets)
        {
            location loc = getLocation(target);
            float x = loc.x;
            float y = loc.y;
            float z = loc.z;
            String xString = Float.toString(x);
            String yString = Float.toString(y);
            String zString = Float.toString(z);
            String coords = xString + " " + yString + " " + zString;
            sendSystemMessageTestingOnly(self, coords);
            Graphics g = image.getGraphics();
            g.drawOval((int) x, (int) y, 2, 2);
            ImageIO.write(image, "png", new File(path, getCurrentSceneName() + ".png"));
        }
        broadcast(self,"Painted " + targets.length + " objects.");
        return SCRIPT_CONTINUE;
    }
    public int OnSpeaking(obj_id self, String text)
    {
        if (text.equals(toLower("paint")))
        {
            obj_id[] targets = getAllPlayers(getLocation(self), 8000);
            if (targets == null)
            {
                sendSystemMessageTestingOnly(self, "No targets selected.");
                return SCRIPT_CONTINUE;
            }
            try
            {
                paint(self, targets);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return SCRIPT_CONTINUE;
    }
}
