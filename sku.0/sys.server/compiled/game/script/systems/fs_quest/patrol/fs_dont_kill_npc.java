package script.systems.fs_quest.patrol;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.dictionary;
import script.obj_id;

public class fs_dont_kill_npc extends script.base_script
{
    public static final float DESTROY_TIME = 600.0f;

    public fs_dont_kill_npc()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
        if (hasObjVar(self, "quest.fs_patrol.spawned_for"))
        {
            obj_id player = getObjIdObjVar(self, "quest.fs_patrol.spawned_for");
            dictionary params = new dictionary();
            if (killer == player)
            {
                messageTo(player, "handleDontKillPatrolFSNpc", params, 0.0f, true);
            }
        }
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        dictionary params = new dictionary();
        messageTo(self, "autoCleanup", params, DESTROY_TIME, false);
        return SCRIPT_CONTINUE;
    }

    public int autoCleanup(obj_id self, dictionary params) throws InterruptedException
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
