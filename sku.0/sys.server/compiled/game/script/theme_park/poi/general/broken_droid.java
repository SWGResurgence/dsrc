package script.theme_park.poi.general;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class broken_droid extends script.theme_park.poi.base
{
    public broken_droid()
    {
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        String objective = poiGetObjective(self);
        if (objective.equals("droid"))
        {
            obj_id droid = poiCreateObject(self, "droid", "object/creature/npc/theme_park/r2d2.iff", 0, 1);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
