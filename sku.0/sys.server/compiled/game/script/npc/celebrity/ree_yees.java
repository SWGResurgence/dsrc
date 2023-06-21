package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class ree_yees extends script.base_script
{
    public ree_yees()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        attachScript(self, "conversation.ree_yees");
        return SCRIPT_CONTINUE;
    }
}
