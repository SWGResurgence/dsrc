package script.systems.crafting.repair;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class clothing_repair extends script.base_script
{
    public clothing_repair()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setObjVar(self, "crafting.type", GOT_clothing);
        return SCRIPT_CONTINUE;
    }
}
