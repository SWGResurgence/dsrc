package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class borvos_guard extends script.base_script
{
    public borvos_guard()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Borvo The Hutt's Guard");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setObjVar(self, "quest_table", "borvo_guard");
        return SCRIPT_CONTINUE;
    }
}
