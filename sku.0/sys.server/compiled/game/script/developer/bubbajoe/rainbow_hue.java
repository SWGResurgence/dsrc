package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Set the hue of an object to a random value then loops through all the custom variables and sets them to a random value.
*/

import script.color;
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
    public void hueLoop(obj_id self)
    {
        color[] pal_primus = getPalcolorCustomVarColors(self, "/private/index_color_1");
        for (int i = 0; i < pal_primus.length; i++)
        {
            setPalcolorCustomVarClosestColor(self, "/private/index_color_1", pal_primus[i].getR(), pal_primus[i].getG(), pal_primus[i].getB(), pal_primus[i].getA());
        }
        hueLoop(self);
    }
    public void hueSecondLoop(obj_id self)
    {
        color[] pal_secondus = getPalcolorCustomVarColors(self, "/private/index_color_1");
        for (int i = 0; i < pal_secondus.length; i++)
        {
            setPalcolorCustomVarClosestColor(self, "/private/index_color_1", pal_secondus[i].getR(), pal_secondus[i].getG(), pal_secondus[i].getB(), pal_secondus[i].getA());
        }
        hueSecondLoop(self);
    }
}
