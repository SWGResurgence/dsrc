package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.painter
@Author: BubbaJoeX
@Purpose: Gets coordinates from a specified object and plots them on an image on /home/swg/swg-main/exe/linux/

@WIP: This script is a work in progress and is not yet functional.
*/

import script.location;
import script.obj_id;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class painter extends script.base_script
{
    public float PLANET_WIDE = 8192f;
    public location origin = new location(0, 0, 0, getCurrentSceneName(), null);
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public String header = "\n";
    public void paint(obj_id self, obj_id[] targets) throws IOException
    {
        final BufferedImage image = new BufferedImage(8000, 8000, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D graphics2D = image.createGraphics();
        for (obj_id target : targets)
        {
            location here = getLocation(target);
            graphics2D.setPaint(Color.BLACK);
            graphics2D.drawOval((int) here.x, (int) here.z, 2, 2);
        }
        graphics2D.dispose();
        ImageIO.write(image, "png", new File("/home/swg/swg-main/exe/linux/server/test.png"));
        broadcast(self, "Attempting to paint " + targets.length + " objects.");
    }

    public int OnSpeaking(obj_id self, String text) throws IOException
    {
        if (text.equals(toLower("paintPlayers")))
        {
            obj_id[] targets = getAllPlayers(origin, PLANET_WIDE);
            if (targets == null)
            {
                sendSystemMessageTestingOnly(self, "No targets selected.");
                return SCRIPT_CONTINUE;
            }
            try
            {
                paint(self, targets);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (text.equalsIgnoreCase("paintBySelection"))
        {
            StringTokenizer st = new StringTokenizer(text);
            String templateName = st.nextToken();
            obj_id[] targets = getAllObjectsWithTemplate(origin, PLANET_WIDE, templateName);
            paint(self, targets);
        }
        return SCRIPT_CONTINUE;
    }
}
