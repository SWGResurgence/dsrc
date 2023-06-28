package script.theme_park.alderaan.act3;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class rebel_research_guard extends script.base_script
{
    public rebel_research_guard()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id attacker) throws InterruptedException
    {
        obj_id facility = getObjIdObjVar(self, "coa3.rebel.facility");
        if (isIdValid(facility))
        {
            messageTo(facility, "guardKilled", null, 0, false);
        }
        return SCRIPT_CONTINUE;
    }
}
