package script.event.gjpud;/*
@Filename: script.event.gjpud.junk
@Author: BubbaJoeX
@Purpose: Junk object for Galactic Junk Pickup Day 2023
@ increases
*/

import script.*;

import java.lang.reflect.InvocationTargetException;

public class junk extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest( obj_id self, obj_id player, menu_info mi )
    {
        mi.addRootMenu( menu_info_types.ITEM_USE, new string_id( "Collect" ));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, InvocationTargetException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            broadcast(player, "An amazing loot message here with some cool effects and flavah text.");
        }
        return SCRIPT_CONTINUE;
    }
}
