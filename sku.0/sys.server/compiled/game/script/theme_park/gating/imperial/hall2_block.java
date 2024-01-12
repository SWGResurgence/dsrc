package script.theme_park.gating.imperial;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.factions;
import script.library.groundquests;
import script.library.space_flags;
import script.library.space_quest;
import script.obj_id;
import script.string_id;

public class hall2_block extends script.base_script
{
    public hall2_block()
    {
    }

    public int OnAboutToReceiveItem(obj_id self, obj_id destinationCell, obj_id transferrer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        boolean hasSpaceAccess = space_quest.hasWonQuest(item, "destroy_surpriseattack", "naboo_station_emperors_access_quest_6");
        if (factions.isImperial(item) && space_flags.hasCompletedTierThree(item))
        {
            hasSpaceAccess = true;
        }
        if (groundquests.isQuestActiveOrComplete(item, "itp_kaja_01") || hasSpaceAccess || isGod(item))
        {
            return SCRIPT_CONTINUE;
        }
        else
        {
            string_id warning = new string_id("theme_park_imperial/warning", "kaja_orzee");
            sendSystemMessage(item, warning);
            return SCRIPT_OVERRIDE;
        }
    }
}
