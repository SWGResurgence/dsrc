package script.systems.crafting.repair;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class droid_repair extends script.base_script
{
    public droid_repair()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setObjVar(self, "crafting.type", GOT_creature_droid);
        return SCRIPT_CONTINUE;
    }
}
