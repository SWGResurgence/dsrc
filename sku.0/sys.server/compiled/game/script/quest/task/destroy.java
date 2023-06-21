package script.quest.task;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.quests;
import script.obj_id;

public class destroy extends script.base_script
{
    public destroy()
    {
    }

    public int OnIncapacitateTarget(obj_id self, obj_id target) throws InterruptedException
    {
        LOG("newquests", "destroy - OnIncapacitateTarget()");
        int questId = quests.getQuestIdForTarget(self, target);
        if (questId > -1)
        {
            LOG("newquests", "destroy - Destroyed target is for a player's current active task");
            String questName = quests.getDataEntry(questId, "NAME");
            quests.complete(questName, self, true);
        }
        return SCRIPT_CONTINUE;
    }
}
