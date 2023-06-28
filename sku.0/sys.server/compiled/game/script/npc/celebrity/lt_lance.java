package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class lt_lance extends script.base_script
{
    public lt_lance()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Lt. Lance");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        return SCRIPT_CONTINUE;
    }
}
