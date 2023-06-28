package script.event.lifeday;/*
@Filename: script.event.lifeday.quest_grant_22
@Author: BubbaJoeX
@Purpose: Grants the new lifeday quest for 2022 on use.
*/

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.groundquests;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class quest_grant_22 extends script.base_script
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
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("Answer Call"));
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (groundquests.hasCompletedQuest(player, "lifeday_kkronch"))
            {
                sendSystemMessage(player, new string_id("You have already completed this quest."));
                return SCRIPT_CONTINUE;
            }
            groundquests.grantQuest(player, "lifeday_kkronch");
        }
        return SCRIPT_CONTINUE;
    }
}