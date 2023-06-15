package script.developer.soe.e3demo;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.factions;
import script.obj_id;

public class e3_deadguy extends script.base_script
{
    public e3_deadguy()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        kill(self);
        factions.setFaction(self, "Rebel");
        return SCRIPT_CONTINUE;
    }
}
