package script.npc.celebrity;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class reelo_baruk extends script.base_script
{
    public reelo_baruk()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        attachScript(self, "conversation.reelo_baruk");
        if (hasScript(self, "theme_park.tatooine.recruitment.hutt_recruit"))
        {
            detachScript(self, "theme_park.tatooine.recruitment.hutt_recruit");
        }
        return SCRIPT_CONTINUE;
    }
}
