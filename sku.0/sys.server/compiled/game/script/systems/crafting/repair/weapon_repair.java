package script.systems.crafting.repair;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class weapon_repair extends script.base_script
{
    public weapon_repair()
    {
    }

    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setObjVar(self, "crafting.type", GOT_weapon);
        return SCRIPT_CONTINUE;
    }
}
