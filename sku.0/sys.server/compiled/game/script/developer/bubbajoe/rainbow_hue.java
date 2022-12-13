package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Set the hue of an object to a random value then loops through all the custom variables and sets them to a random value.
*/

import script.color;
import script.library.hue;
import script.obj_id;

public class rainbow_hue extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        startPrimusHueLoop(self);
        startSecondusHueLoop(self);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public void startPrimusHueLoop(obj_id self)
    {
        messageTo(self, "hueLoop", null, 1, true);
    }

    public void startSecondusHueLoop(obj_id self)
    {
        messageTo(self, "hueSecondLoop", null, 1, true);
    }

    public void hueLoop(obj_id self) throws InterruptedException
    {
        color[] pal_primus = getPalcolorCustomVarColors(self, "/private/index_color_1");
        for (int i = 0; i < pal_primus.length; i++)
        {
            hue.setColor(self, "/private/index_color_1", pal_primus[i]);
        }
        hueLoop(self);
    }

    public void hueSecondLoop(obj_id self) throws InterruptedException
    {
        color[] pal_secondus = getPalcolorCustomVarColors(self, "/private/index_color_2");
        for (int i = 0; i < pal_secondus.length; i++)
        {
            hue.setColor(self, "/private/index_color_2", pal_secondus[i]);
        }
        hueSecondLoop(self);
    }
}
