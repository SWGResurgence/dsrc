package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class qualdo extends script.base_script
{
    public qualdo()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setName(self, "Qual'do Herm");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "conversation.rtp_qualdo_main");
        return SCRIPT_CONTINUE;
    }
}
