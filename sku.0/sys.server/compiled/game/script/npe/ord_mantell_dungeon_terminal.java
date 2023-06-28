package script.npe;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.library.npe;
import script.library.space_utils;
import script.*;

public class ord_mantell_dungeon_terminal extends script.base_script
{
    public static final string_id LAUNCH = new string_id("npe", "leave_dungeon");

    public ord_mantell_dungeon_terminal()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, LAUNCH);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!space_utils.hasShip(player))
            {
                string_id noCanFly = new string_id("npe", "gamma_travel_not_from_space");
                sendSystemMessage(player, noCanFly);
                return SCRIPT_CONTINUE;
            }
            if (checkGod(player))
            {
                return SCRIPT_CONTINUE;
            }
            location ordLoc = new location(475.0f, -680.0f, 1545.0f);
            npe.movePlayerFromOrdMantellDungeonToOrdMantellSpace(player, ordLoc);
            groundquests.sendSignal(player, "accessedSpace");
        }
        return SCRIPT_CONTINUE;
    }

    public boolean checkGod(obj_id self) throws InterruptedException
    {
        if (isGod(self))
        {
            broadcast(self, "Please turn off god mode when moving between npe locations. God mode and instances do not get along");
            return true;
        }
        return false;
    }
}
