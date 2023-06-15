package script.quest.task;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.quests;
import script.obj_id;

public class encounter extends script.base_script
{
    public encounter()
    {
    }

    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        LOG("newquests", "encounter - OnQuestActivated(+ " + questRow + ")");
        if (quests.isMyQuest(questRow, "quest.task.encounter"))
        {
            boolean success = quests.doEncounterSpawn(self, questRow);
            String questName = quests.getDataEntry(questRow, "NAME");
            quests.complete(questName, self, success);
        }
        return SCRIPT_CONTINUE;
    }
}
