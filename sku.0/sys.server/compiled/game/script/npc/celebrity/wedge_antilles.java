package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class wedge_antilles extends script.base_script
{
    public wedge_antilles()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        debugSpeakMsg(self, "2 METERS?!?");
        attachScript(self, "conversation.rtp_wedge_main");
        return SCRIPT_CONTINUE;
    }
}
