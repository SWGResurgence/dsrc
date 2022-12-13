package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.painter
@Author: BubbaJoeX
@Purpose: Gets coordinates from a specified object and plots them on an image on /home/swg/swg-main/exe/linux/
*/

import script.location;
import script.obj_id;

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
    public int paint(obj_id self, obj_id[] targets) throws IOException
    {
        for (int i = 0; i < targets.length; i++)
        {
            String information;
            location loc = getLocation(targets[i]);
            String x = Float.toString(loc.x);
            String z = Float.toString(loc.z);
            String y = Float.toString(loc.y);
            information = x + " " + y + " " + z + "\n";
            header += information;
        }
        saveTextOnClient(self, "SwgMapper_" + getCurrentSceneName() + ".txt", header);
        broadcast(self, "Painted " + targets.length + " objects. Run in SwgMapper to see the results.");
        return SCRIPT_CONTINUE;
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
