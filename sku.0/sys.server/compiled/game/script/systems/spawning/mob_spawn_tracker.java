package script.systems.spawning;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.library.utils;
import script.obj_id;

public class mob_spawn_tracker extends script.base_script
{
    public mob_spawn_tracker()
    {
    }

    public int OnDestroy(obj_id self) throws InterruptedException
    {
        obj_id objParent = utils.getObjIdScriptVar(self, "objParent");
        float fltRespawnTime = utils.getFloatScriptVar(self, "fltRespawnTime");
        messageTo(objParent, "respawnMob", null, fltRespawnTime, false);
        return SCRIPT_CONTINUE;
    }
}
