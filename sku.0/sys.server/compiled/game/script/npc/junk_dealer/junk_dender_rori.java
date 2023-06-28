package script.npc.junk_dealer;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class junk_dender_rori extends script.base_script
{
    public junk_dender_rori()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_dender_rori");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_dender_rori");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }
}
