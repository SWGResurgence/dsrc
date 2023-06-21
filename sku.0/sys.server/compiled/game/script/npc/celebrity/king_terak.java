package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.ai_lib;
import script.obj_id;

public class king_terak extends script.base_script
{
    public static final String CONVO = "celebrity/king_terak";

    public king_terak()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        setObjVar(self, "quest_table", "king_terak");
        attachScript(self, "npc.static_quest.quest_convo");
        return SCRIPT_CONTINUE;
    }
}
