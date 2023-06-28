package script.quest.som;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.*;

public class lava_beetle_beads extends script.base_script
{
    public static final String STF = "som/som_quest";
    public static final string_id EXAMINE = new string_id(STF, "lava_beetle_beads_examine");
    public static final string_id ALREADY = new string_id(STF, "lava_beetle_beads_already");
    public lava_beetle_beads()
    {
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, EXAMINE);
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid == null)
        {
            return SCRIPT_CONTINUE;
        }
        mid.setServerNotify(true);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!groundquests.isQuestActive(player, "som_lava_beetle_hunt_15"))
            {
                groundquests.grantQuest(player, "som_lava_beetle_hunt_15");
                return SCRIPT_CONTINUE;
            }
            else
            {
                sendSystemMessage(player, ALREADY);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_CONTINUE;
    }
}
