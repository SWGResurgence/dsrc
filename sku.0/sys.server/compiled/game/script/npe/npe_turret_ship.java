package script.npe;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;

public class npe_turret_ship extends script.base_script
{
    public npe_turret_ship()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id parent = utils.getObjIdScriptVar(self, "objParent");
        messageTo(parent, "shipDied", null, 0, true);
        return SCRIPT_CONTINUE;
    }
}
