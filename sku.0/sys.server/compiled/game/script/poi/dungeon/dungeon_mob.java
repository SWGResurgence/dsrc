package script.poi.dungeon;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

import script.obj_id;

public class dungeon_mob extends script.base_script
{
    public dungeon_mob()
    {
    }

    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId) throws InterruptedException
    {
        obj_id objParent = getObjIdObjVar(self, "objParent");
        messageTo(objParent, "elementDestroyed", null, 0, true);
        return SCRIPT_CONTINUE;
    }
}
