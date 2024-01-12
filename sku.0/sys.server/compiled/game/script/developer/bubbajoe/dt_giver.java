package script.developer.bubbajoe;/*
@Filename: script.developer.bubbajoe.
@Author: BubbaJoeX
@Purpose:
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.*;
import script.library.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class dt_giver extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        String desc = "Radial this gift and select 'Claim' to recieve your Remote Tactical Deployment Tool.";
        setDescriptionStringId(self, new string_id(desc));
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        String desc = "Radial this gift and select 'Claim' to recieve your Remote Tactical Deployment Tool.";
        setDescriptionStringId(self, new string_id(desc));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Claim"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int mi) throws InterruptedException
    {
        if (mi == menu_info_types.ITEM_USE)
        {
            if (hasObjVar(self, "dt_gift_" + player))
            {
                sendSystemMessage(player, "You have already claimed this gift.", null);
            }
            else
            {
                obj_id gift = createObject("object/tangible/loot/misc/picture_handheld_s02.iff", utils.getInventoryContainer(player), "");
                if (gift == null)
                {
                    sendSystemMessage(player, "Gift could not be created. Please contact a GM.", null);
                    return SCRIPT_CONTINUE;
                }
                attachScript(gift, "developer.bubbajoe.dt_gift");
                sendSystemMessage(player, "You have claimed this gift.", null);
                setObjVar(self, "dt_gift_" + player, 1);
            }
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
