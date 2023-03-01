package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose: Set the hue of an object to a random value then loops through all the custom variables and sets them to a random value.
*/

import script.library.hue;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class rainbow_hue extends script.base_script
{
    public String PAL_MAIN = "/private/index_color_1";
    public String PAL_SECOND = "/private/index_color_2";
    public String PAL_TERTIARY = "/private/index_color_3";
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        int momma = mi.addRootMenu(menu_info_types.SERVER_MENU48, new string_id("Rainbowize"));
        mi.addSubMenu(momma, menu_info_types.SERVER_MENU50, new string_id("Stop"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.SERVER_MENU48)
        {
            startPrimusHueLoop(self);
            startSecondusHueLoop(self);
        }
        if (item == menu_info_types.SERVER_MENU49)
        {
            if (!hasObjVar(self, "loopLock"))
            {
                broadcast(player, "Stopping the rainbow.");
                setObjVar(self, "loopLock", 1);
                return SCRIPT_CONTINUE;
            }
            else
            {
                removeObjVar(self, "loopLock");
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void startPrimusHueLoop(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "loopLock"))
        {
            hueLoop(self);
        }
    }

    public void startSecondusHueLoop(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "loopLock"))
        {
            hueSecondLoop(self);
        }
    }

    public void hueLoop(obj_id self) throws InterruptedException
    {
        int pal_color = 0;
        for (int i = 0; i < 255; i++)
        {
            hue.setColor(self, PAL_MAIN, pal_color);
            pal_color++;
        }
        startPrimusHueLoop(self);
    }

    public void hueSecondLoop(obj_id self) throws InterruptedException
    {
        int pal_color = 0;
        for (int i = 0; i < 255; i++)
        {
            hue.setColor(self, PAL_SECOND, pal_color);
            pal_color++;
        }
        startSecondusHueLoop(self);
    }
}
