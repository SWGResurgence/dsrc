package script.quest.force_sensitive;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.quests;
import script.library.utils;
import script.obj_id;
import script.string_id;

public class fs_crafting4_loot_kit extends script.base_script
{
    public static final string_id SID_LOOT_KIT_DESTROYED = new string_id("quest/force_sensitive/fs_crafting", "crafting4_kit_destroyed");
    public static final string_id SID_LOOT_KIT_DESTROYED_OVER = new string_id("quest/force_sensitive/fs_crafting", "crafting4_kit_destroyed_over");
    public fs_crafting4_loot_kit()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        if (!hasObjVar(self, "cleaningUp"))
        {
            obj_id owner = utils.getContainingPlayer(self);
            if (isIdValid(owner))
            {
                if (hasScript(owner, "quest.force_sensitive.fs_crafting4_player"))
                {
                    sendSystemMessage(owner, SID_LOOT_KIT_DESTROYED);
                }
                else
                {
                    sendSystemMessage(owner, SID_LOOT_KIT_DESTROYED_OVER);
                }
                quests.deactivate("fs_crafting4_quest_02", owner);
                int id = quests.getQuestId("fs_crafting4_quest_01");
                clearCompletedQuest(owner, id);
                quests.activate("fs_crafting4_quest_01", owner, null);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
