package script.quest.task;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.quests;
import script.obj_id;

public class nothing extends script.base_script
{
    public nothing()
    {
    }

    public int OnQuestActivated(obj_id self, int questRow) throws InterruptedException
    {
        LOG("newquests", "nothing - OnQuestActivated(+ " + questRow + ")");
        if (quests.isMyQuest(questRow, "quest.task.nothing"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            LOG("newquests", "task nothing is completing now");
            quests.complete(questName, self, true);
        }
        return SCRIPT_CONTINUE;
    }
}
