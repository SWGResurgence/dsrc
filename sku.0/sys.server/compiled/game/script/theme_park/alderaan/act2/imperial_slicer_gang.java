package script.theme_park.alderaan.act2;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class imperial_slicer_gang extends script.base_script
{
    public imperial_slicer_gang()
    {
    }

    public int OnIncapacitated(obj_id self, obj_id attacker) throws InterruptedException
    {
        obj_id slicer = getObjIdObjVar(self, "coa2.imperial.slicer");
        if (isIdValid(slicer))
        {
            messageTo(slicer, "thugKilled", null, 0, false);
        }
        return SCRIPT_CONTINUE;
    }
}
