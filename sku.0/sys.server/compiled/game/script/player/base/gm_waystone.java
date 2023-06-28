package script.player.base;/*
@Filename: script.player.base.gm_waystone
@Author: BubbaJoeX
@Purpose:
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;

public class gm_waystone extends script.base_script
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
        mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Return to the Galaxy"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            location homeLoc = getLocationObjVar(self, "jail.gm_wayback");
            warpPlayer(player, homeLoc.area, homeLoc.x, homeLoc.y, homeLoc.z, homeLoc.cell, homeLoc.x, homeLoc.y, homeLoc.z);
        }
        else
        {
            broadcast(player, "You are not authorized to use this item.");
        }
        return SCRIPT_CONTINUE;
    }
}
