package script.event.wheres_watto;/*
@Filename: script.event.wheres_watto.controller
@Author: BubbaJoeX
@Purpose: Spawns a watto on attach randomly on the planet. If he gets talked to, he will be respawned via this script.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.create;
import script.*;

public class controller extends script.base_script
{
    public String[] TOYDARIANS = {
            "toydarian_m_greeter",
            "toydarian_m_greeter_1",
            "toydarian_m_greeter_2",
    };

    public int OnAttach(obj_id self)
    {
        setObjVar(self, "watto_controller_master", 1);
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (isGod(player))
        {
            if (!hasObjVar(self, "made_watto"))
            {
                mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Make a Watto"));
            }
            else
            {
                mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Find a Watto"));
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (isGod(player))
        {
            if (item == menu_info_types.ITEM_USE)
            {
                location watto_loc = new location(0, 0, 0, getCurrentSceneName(), null);
                watto_loc.x = watto_loc.x + (rand(-7250.0f, 7250.0f));
                watto_loc.z = watto_loc.z + (rand(-7250.0f, 7250.0f));
                watto_loc.y = getHeightAtLocation(watto_loc.x, watto_loc.z);
                obj_id watto = create.object(TOYDARIANS[rand(0, TOYDARIANS.length - 1)], watto_loc);
                attachScript(watto, "event.wheres_watto.wheres_watto");
                setName(watto, "Watto");
                setObjVar(watto, "watto_tag", 1);
                setObjVar(watto, "watto", watto);
                setObjVar(self, "made_watto", 1);
                return SCRIPT_CONTINUE;
            }
            if (item == menu_info_types.SERVER_MENU1)
            {
                obj_id watto = getObjIdObjVar(self, "watto");
                if (isIdValid(watto))
                {
                    location watto_loc = getLocation(watto);
                    warpPlayer(player, watto_loc.area, watto_loc.x, watto_loc.y, watto_loc.z, null, 0, 0, 0);
                }
                else
                {
                    sendSystemMessage(player, "Watto is not on this planet.", null);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
