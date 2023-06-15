package script.event.gjpud;/*
@Filename: script.event.gjpud.junk
@Author: BubbaJoeX
@Purpose: Junk object for Galactic Junk Pickup Day 2023
@ increases
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.static_item;
import script.library.trial;
import script.library.utils;

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

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Collect"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException, InvocationTargetException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            int currentSmashed = getIntObjVar(player, "gjpud.total");
            setObjVar(player, "gjpud.total", currentSmashed + 1);
            broadcast(player, "You have collected a piece of scrap.");
            destroyObject(self);
            static_item.createNewItemFunction(trial.GJPUD_ITEM, utils.getInventoryContainer(player));
        }
        return SCRIPT_CONTINUE;
    }
}
