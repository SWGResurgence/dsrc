package script.item;

import script.library.*;
import script.library.utils;
import script.library.xp;
import script.*;

public class mandalorian_strongbox extends script.base_script
{
    public mandalorian_strongbox()
    {
    }
    public boolean mandoEnemy_condition(obj_id player, obj_id npc) throws InterruptedException
    {
        float mandoFaction = factions.getFactionStanding(player, "death_watch");
        return mandoFaction <= 0;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        if (hasObjVar(self, "intUsed"))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.EXAMINE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (mandoEnemy_condition(player, self))
            {
                sendSystemMessage(player, new string_id("resurgence/quest", "this_is_the_way"));
                factions.addFactionStanding(player, factions.DEATH_WATCH, 1000.0f);
                return SCRIPT_CONTINUE;
            }
            else
            {
                sendSystemMessage(player, new string_id("resurgence/quest", "this_is_not_the_way"));
                factions.addFactionStanding(player, factions.DEATH_WATCH, -1.0f);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}