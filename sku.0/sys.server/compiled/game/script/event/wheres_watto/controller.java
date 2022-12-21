package script.event.wheres_watto;/*
@Filename: script.event.wheres_watto.controller
@Author: BubbaJoeX
@Purpose: Spawns a watto on attach randomly on the planet. If he gets talked to, he will be respawned via this script.
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
                mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("Find Watto"));
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
                location here = getLocation(self);
                obj_id watto = create.object(TOYDARIANS[rand(0, TOYDARIANS.length - 1)], here);
                setObjVar(watto, "made_watto", 1);
                return SCRIPT_CONTINUE;
            }
            if (item == menu_info_types.SERVER_MENU1)
            {
                obj_id watto = getObjIdObjVar(self, "made_watto");
                if (isIdValid(watto))
                {
                    location here = getLocation(watto);
                    broadcast(player, "Watto is at " + here.x + ", " + here.y + ", " + here.z);
                    createWaypointInDatapad(player, watto);
                }
                else
                {
                    sendSystemMessage(player, "Watto is not valid.", null);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
