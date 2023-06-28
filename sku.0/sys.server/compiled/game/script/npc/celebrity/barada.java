package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class barada extends script.base_script
{
    public barada()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "conversation.barada");
        return SCRIPT_CONTINUE;
    }
}
